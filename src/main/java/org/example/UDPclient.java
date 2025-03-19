package org.example;

import java.io.*;
import java.net.*;

public class UDPclient {
    public static void main(String[] args) throws IOException {
        String serverIp = "127.0.0.1";
        int port = 12345;
        DatagramSocket clientSocket = new DatagramSocket();

        String fileName = "test.txt";
        byte[] sendData = fileName.getBytes();
        InetAddress serverAddress = InetAddress.getByName(serverIp);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
        clientSocket.send(sendPacket);              // Send filename request
        System.out.println("File request sent.");

        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        clientSocket.receive(receivePacket);        // Receive file content
        System.out.println("Files received: " + new String(receivePacket.getData(), 0, receivePacket.getLength()));

        clientSocket.close();
    }
}
