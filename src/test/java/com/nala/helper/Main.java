package com.nala.helper;

import com.alibaba.fastjson.JSON;
import com.nala.helper.enums.MsgType;
import com.nala.helper.pojo.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * todo 描述当前类的功能
 *
 * @author 彭术成
 * @since 2021/12/23
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 888);
        if (socket.isConnected()) {
            // 发送数据
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            Message online = Message.builder()
                    .content("1")
                    .from("psc")
                    .to(null)
                    .type(MsgType.MSG.getKey()).build();
            writer.write(JSON.toJSONString(online));
            writer.flush();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                Message msg = Message.builder()
                        .content(scanner.nextLine())
                        .from("psc")
                        .to("cmm")
                        .type(MsgType.MSG.getKey()).build();
                writer.write(JSON.toJSONString(msg));
                writer.flush();
                if (!receiveMsg(socket)) {
                    break;
                }
            }
            scanner.close();
            writer.close();
        }
    }

    public static boolean receiveMsg(Socket socket) throws Exception {
        Callable<Boolean> callable = () -> {
            // 接收数据
            try (InputStream in = socket.getInputStream()) {
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                if (socket.isInputShutdown()) {
                    socket.close();
                    reader.reset();
                    reader.close();
                    return false;
                }
                char[] chars = new char[1024];
                int len;
                while ((len = reader.read(chars)) != -1) {
                    sb.append(new String(chars, 0, len));
                }
                System.out.println(sb);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        };
        FutureTask<Boolean> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        return futureTask.get();
    }


}
