package com.nala.helper.netty.websocket.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.nala.helper.enums.MsgType;
import com.nala.helper.netty.websocket.serivce.NoteNotifyService;
import com.nala.helper.pojo.Message;
import com.nala.helper.vo.NoteVO;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * websocket业务处理类
 *
 * @author 彭术成
 * @since 2021/12/31
 */
@Component
@Sharable
public class WebsocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final ConcurrentHashMap<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, String> USER_MAP = new ConcurrentHashMap<>();

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static final String NOW = SDF.format(new Date());

    @Resource
    private NoteNotifyService notifyService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FullHttpRequest) {
            // 以http请求形式接入，但是走的是websocket
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            // 处理websocket客户端的消息
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object buf) {
        // 暂时不实现
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println(USER_MAP.get(ctx.channel().id().asLongText()) + "------已下线");
        channels.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 握手处理
     *
     * @param ctx     channel上下文
     * @param request http请求
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        String connection = "Connection";
        String upgrade = "Upgrade";
        String wsProtocol = "websocket";

        // 如果是websocket请求就握手升级
        if (request != null && upgrade.contentEquals(request.headers().get(connection))
                && wsProtocol.contentEquals(request.headers().get(upgrade))) {

            Channel channel = ctx.channel();
            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                    "ws:/" + channel, null, false);
            WebSocketServerHandshaker handShaker = wsFactory.newHandshaker(request);
            if (handShaker == null) {
                // 版本不支持,握手失败
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channel);
            } else {
                // 握手成功
                handShaker.handshake(channel, request);
                Message msg = Message.builder()
                        .type(MsgType.LOGIN.getKey())
                        .time(NOW)
                        .to(null)
                        .from("sys")
                        .content("恭喜连接成功!").build();
                channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msg)));
            }
        }
    }

    /**
     * 处理websocket消息
     *
     * @param ctx   channel上下文
     * @param frame websocket消息对象
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // web端消息
        String receiveMsg = frame.content().retain().toString(StandardCharsets.UTF_8);

        // 当前通道
        Channel channel = ctx.channel();

        // 消息转发
        try {
            JSONObject message = JSON.parseObject(receiveMsg);

            // 打印消息
            System.out.println(message);

            String type = message.getString("type");
            // 登录信息
            if (MsgType.LOGIN.getKey().equals(type)) {
                String phone = message.getString("phone");
                // 存入缓存
                CHANNEL_MAP.put(phone, channel);
                USER_MAP.put(ctx.channel().id().asLongText(), phone);

                // 检测是否需要提醒备忘录
                List<NoteVO> notifyList = notifyService.notify(phone);
                if (CollectionUtils.isNotEmpty(notifyList)) {
                    StringBuilder content = new StringBuilder();
                    Iterator<NoteVO> iterator = notifyList.iterator();
                    while (iterator.hasNext()) {
                        NoteVO vo = iterator.next();
                        content.append(vo.getContent());
                        if (iterator.hasNext()) {
                            content.append("\n");
                        }
                    }
                    Message msg = Message.builder()
                            .type(MsgType.NOTE.getKey())
                            .to(null)
                            .time(NOW)
                            .from("sys")
                            .content(content.toString()).build();
                    ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msg)));
                }
            }
            // 聊天消息
            if (MsgType.MSG.getKey().equals(type)) {
                String to = message.getString("to");
                String content = message.getString("content");
                String from = USER_MAP.get(channel.id().asLongText());
                Channel toChannel = CHANNEL_MAP.get(to);
                if (toChannel == null) {
                    toChannel = channel;
                    from = "sys";
                    content = "对方不在线";
                }
                Message msg = Message.builder()
                        .type(MsgType.SYS.getKey())
                        .to(null)
                        .time(NOW)
                        .from(from)
                        .content(content).build();
                toChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msg)));
            }
        } catch (JSONException e) {
            ctx.close();
            channels.remove(channel);
        }
    }
}
