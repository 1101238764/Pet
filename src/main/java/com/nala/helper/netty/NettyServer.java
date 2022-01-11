package com.nala.helper.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * websocket 服务配置类
 *
 * @author 彭术成
 * @since 2021/12/31
 */
@Component
@Slf4j
public class NettyServer {

    public void run(int port, ChannelInitializer<SocketChannel> channelHandler) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            System.out.println("netty 服务开启,端口:" + port);
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(channelHandler);
            // 绑定端口,同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            //等待服务监听端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("端口绑定失败", e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
