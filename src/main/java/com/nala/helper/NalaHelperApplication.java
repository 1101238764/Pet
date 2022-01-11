package com.nala.helper;

import com.nala.helper.netty.NettyServer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 彭术成
 * @since 2021/12/22
 */
@SpringBootApplication
@EnableSwagger2
public class NalaHelperApplication implements CommandLineRunner {

    @Value("${netty.port}")
    private Integer nettyPort;

    @Resource
    private NettyServer server;

    /**
     * <p> 1、 websocket 处理器 -> websocketChannelHandler</p>
     * <p> 2、默认处理器 java socket服务 -> localChannelHandler</p>
     */
    private final String name = "websocketChannelHandler";

    @Resource(name = name)
    ChannelInitializer<SocketChannel> channelHandler;

    public static void main(String[] args) {
        SpringApplication.run(NalaHelperApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //netty服务启动
        server.run(nettyPort, channelHandler);
    }
}
