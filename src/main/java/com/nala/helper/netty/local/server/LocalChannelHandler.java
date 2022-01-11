package com.nala.helper.netty.local.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 服务端初始化管道
 *
 * @author 彭术成
 * @since 2022/1/5
 */
@Component
public class LocalChannelHandler extends ChannelInitializer<SocketChannel> {

    @Resource
    private LocalServerHandler serverHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline()
                .addLast(new StringEncoder())
                .addLast(new ByteArrayEncoder())
                .addLast(serverHandler);
    }
}
