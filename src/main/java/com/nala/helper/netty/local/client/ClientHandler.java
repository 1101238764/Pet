package com.nala.helper.netty.local.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端自定义消息处理器
 *
 * @author 彭术成
 * @since 2021/12/31
 */
@Slf4j
public class ClientHandler extends ChannelHandlerAdapter {

    /**
     * 消息读取
     *
     * @param ctx 通道上下文
     * @param msg 消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString(StandardCharsets.UTF_8));
        // 接收到指定信息后进行业务处理
        // ......
    }

    /**
     * 异常处理
     *
     * @param ctx   通道上下文
     * @param cause 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 出现异常断开当前通道
        ctx.disconnect();
        // 关闭当前通道
        ctx.close();
        // 记录当前异常内容
        log.error("【异常信息】 ", cause);
    }
}
