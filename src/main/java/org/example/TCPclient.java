package org.example;
import java.io.*;
import java.net.*;

public class TCPclient {
    public static void main(String[] args) throws IOException {
        String serverIp = "127.0.0.1";
        int port = 12345;
        Socket socket = new Socket(serverIp, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String fileName = "example.txt";
        out.println(fileName);

        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        socket.close();
    }
}