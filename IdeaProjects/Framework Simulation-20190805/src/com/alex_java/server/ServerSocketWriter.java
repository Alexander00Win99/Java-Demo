package com.alex_java.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketWriter extends Thread {
    private Socket socket;
    public ServerSocketWriter(Socket socket) {
        this.socket = socket;
    }
    // 服务器写进程
    @Override
    public void run() {
        OutputStream os = null;
        try {
            os = this.socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 字节流->字符流
        PrintWriter writer = new PrintWriter(os);
        Scanner input = new Scanner(System.in);
        String messageOut = input.nextLine();
        writer.println(messageOut);
        writer.flush();
    }
}
