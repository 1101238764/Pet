package com.nala.helper.netty.local.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 服务端自定义消息处理器
 * <p>{@link Sharable}  @Sharable注解不可缺,否则不能进行多个客服端业务处理</p>
 *
 * @author 彭术成
 * @since 2022/1/5
 */
@Component
@Sharable
@Slf4j
public class LocalServerHandler extends ChannelHandlerAdapter {

    /**
     * 账号->channel 的映射集合
     */
    private static final ConcurrentHashMap<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();

    /**
     * channel->账号 的映射集合
     */
    private static final ConcurrentHashMap<Channel, String> USER_MAP = new ConcurrentHashMap<>();

    /**
     * 当前服务启动后，注册的通道集合
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 服务端消息读取
     *
     * @param ctx 通道上下文
     * @param msg 消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof ByteBuf) {
            System.out.println(((ByteBuf) msg).toString(StandardCharsets.UTF_8));
            ctx.writeAndFlush("你好啊！");
            // 接收到指定信息后进行业务处理
            // ......
        }
    }

    /**
     * 通道消息读取完毕
     *
     * @param ctx 通道上下文
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("ok");
    }

    /**
     * 通道激活通知
     *
     * @param ctx 通道上下文
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().id() + "已建立链接");
        // 响应客户端欢迎消息
        String welcome = "\n+++++++++++++++++++++++++++++++++++++++++\n+ Welcome To Login Netty Socket Service + \n+_______________________________________+";
        ctx.writeAndFlush(welcome);
    }

    /**
     * 通道关闭通知
     *
     * @param ctx 通道上下文
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        // 从当前注册的通道集合中移除
        channels.remove(ctx.channel());

        // 当前通道关闭
        ctx.close();
    }

    /**
     * 异常处理
     *
     * @param ctx   通道上下文
     * @param cause 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 从当前注册的通道集合中移除
        channels.remove(ctx.channel());

        // 关闭链接
        ctx.disconnect();

        // 关闭上下文
        ctx.close();

        // 异常处理
        log.error("【异常信息】 " + cause);
    }
}
