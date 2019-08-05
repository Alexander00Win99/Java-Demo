package com.alex_java.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {
    public static void main(String[] args) {
        try {
            int port = 9999;
            System.out.println("我是服务器，我会一直等你直到天荒地老！");
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();
            System.out.println(socket.getInetAddress().getHostAddress() + "已经上钩！");

            ServerSocketReader reader = new ServerSocketReader(socket);
            ServerSocketWriter writer = new ServerSocketWriter(socket);
            reader.run();
            writer.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
