package com.alex_java.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
    public static void main(String[] args) {
        int port = 9999;

        try {
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();
            System.out.println("我是服务器，我会一直等你直到天荒地老！");

            // 服务器接收消息
            InputStream is = socket.getInputStream();
            // 字节流->字符流
            InputStreamReader  isr = new InputStreamReader(is);
            // 利用BufferedReader包装实现readLine()读取一行
            BufferedReader reader = new BufferedReader(isr);
            String messageIn = reader.readLine();
            System.out.println(messageIn);

            // 服务器发送消息
            OutputStream os = socket.getOutputStream();
            // 字节流->字符流
            PrintWriter writer = new PrintWriter(os);
            Scanner input = new Scanner(System.in);
            String messageOut = input.nextLine();
            writer.println(messageOut);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
