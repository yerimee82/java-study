package echo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static final int PORT = 6000;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // 1. Server Socket 생성
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
            log("starts...[port:"+PORT+"]");

            while(true) {
                Socket socket = serverSocket.accept();  // blocking
                new EchoRequestHandler(socket).start();
            }
        } catch (IOException e) {
            log("error: " + e);
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void log(String message) {
        System.out.println("[EchoServer] " + message);
    }
}
