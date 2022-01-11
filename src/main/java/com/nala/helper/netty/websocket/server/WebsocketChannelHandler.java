package com.nala.helper.netty.websocket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * channel初始化
 *
 * @author 彭术成
 * @since 2021/12/31
 */
@Component
public class WebsocketChannelHandler extends ChannelInitializer<SocketChannel> {

    @Resource
    private WebsocketServerHandler handler;

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline()
                .addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(65535))
                .addLast(new HttpClientCodec())
                .addLast(new ChunkedWriteHandler())
                .addLast(handler);
    }
}
