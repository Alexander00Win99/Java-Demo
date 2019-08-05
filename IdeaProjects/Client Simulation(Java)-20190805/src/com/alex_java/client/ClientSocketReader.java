package com.alex_java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocketReader extends Thread {
    private Socket socket;
    public ClientSocketReader(Socket socket) {
        this.socket = socket;
    }
    // 客户端读进程
    @Override
    public void run() {
        InputStream is = null;
        try {
            is = socket.getInputStream();
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
