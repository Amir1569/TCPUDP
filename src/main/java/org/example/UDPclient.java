package org.example;

import java.io.*;
import java.net.*;

public class UDPclient {
    public static void main(String[] args) throws IOException {
        String serverIp = "127.0.0.1";
        int port = 12345;
        DatagramSocket clientSocket = new DatagramSocket();

        String fileName = "example.txt";
        byte[] sendData = fileName.getBytes();
        InetAddress serverAddress = InetAddress.getByName(serverIp);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
        clientSocket.send(sendPacket);
        System.out.println("Bestandsaanvraag verzonden.");

        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        clientSocket.receive(receivePacket);
        System.out.println("Bestand ontvangen: " + new String(receivePacket.getData(), 0, receivePacket.getLength()));

        clientSocket.close();
    }
}
