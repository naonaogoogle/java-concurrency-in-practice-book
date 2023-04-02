package com.naonao.concurrency.chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Six_1_SingleThreadWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) {

    }
}
