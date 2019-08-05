package com.alex_java.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketWriter extends Thread {
    private Socket socket;
    public ClientSocketWriter(Socket socket) {
        this.socket = socket;
    }
    // 客户端写进程
    @Override
    public void run() {
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 字节流->字符流
        PrintWriter writer = new PrintWriter(os);
        Scanner scanner = new Scanner(System.in);
        String messageOut = "";
        do {
            messageOut = scanner.nextLine();
            writer.println(messageOut);
            writer.flush();
        } while (messageOut.equals("bye"));
        scanner.close();
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
