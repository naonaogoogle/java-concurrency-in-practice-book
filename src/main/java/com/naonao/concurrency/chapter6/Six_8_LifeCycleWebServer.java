package com.naonao.concurrency.chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class Six_8_LifeCycleWebServer {

        private final ExecutorService exec = Executors.newFixedThreadPool(100);

        public void start() throws IOException {
            ServerSocket socket = new ServerSocket(80);
            while (!exec.isShutdown()) {
                try {
                    final Socket connection = socket.accept();
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            handleRequest(connection);
                        }
                    });
                } catch (RejectedExecutionException e) {
                    if (!exec.isShutdown()) {
                        System.out.println("task submission rejected");
                    }
                }
            }
        }

        public void stop() {
            exec.shutdown();
        }

        private void handleRequest(Socket connection) {
            Request req = readRequest(connection);
            if (isShutdownRequest(req)) {
                stop();
            } else {
                dispatchRequest(req);
            }
        }

        private void dispatchRequest(Request req) {
        }

        private boolean isShutdownRequest(Request req) {
            return false;
        }

        private Request readRequest(Socket connection) {
            return null;
        }

        interface Request {
        }
}
