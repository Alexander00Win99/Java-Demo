package com.alex_java.client;

import java.io.IOException;
import java.net.Socket;

public class ClientSocketDemo {
    public static void main(String[] args) {
        try {
            String host = "127.0.0.1";
//            String host = "localhost";
            int port = 9999;
            System.out.println("我是客户端！");
            Socket socket = new Socket(host, port);

            ClientSocketReader reader = new ClientSocketReader(socket);
            ClientSocketWriter writer = new ClientSocketWriter(socket);
            reader.start();
            writer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
