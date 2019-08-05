package com.alex_java.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9999;

        try {
            Socket socket = new Socket(host, port);
            System.out.println("我是客户端！");

            // 客户端发送消息
            OutputStream os = socket.getOutputStream();
            // 字节流->字符流
            PrintWriter writer = new PrintWriter(os);
            Scanner input = new Scanner(System.in);
            String messageOut = input.nextLine();
            writer.println(messageOut);
            writer.flush();

            // 客户端接收消息
            InputStream is = socket.getInputStream();
            // 字节流->字符流
            InputStreamReader isr = new InputStreamReader(is);
            // 利用BufferedReader包装实现readLine()读取一行
            BufferedReader reader = new BufferedReader(isr);
            String messageIn = reader.readLine();
            System.out.println(messageIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
