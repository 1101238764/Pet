package com.nala.helper.netty.local.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * todo 描述当前类的功能
 *
 * @author 彭术成
 * @since 2021/12/31
 */
@Component
public class Client {

    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) {
                        channel.pipeline()
                                .addLast(new StringEncoder())
                                .addLast(new ByteArrayEncoder())
                                .addLast(new ClientHandler());
                    }
                });
        // 连接服务
        ChannelFuture cf = bootstrap.connect("127.0.0.1", 888);

        // 消息发送
        try {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String msg = scanner.nextLine();
                if (StringUtils.isNotBlank(msg)) {
                    cf.channel().writeAndFlush(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
