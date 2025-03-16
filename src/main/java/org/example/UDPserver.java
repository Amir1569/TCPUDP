package org.example;

import java.io.*;
import java.net.*;

public class UDPserver {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        DatagramSocket serverSocket = new DatagramSocket(port);
        System.out.println("UDP Server draait op poort " + port);

        byte[] buffer = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivePacket);
            String fileName = new String(receivePacket.getData(), 0, receivePacket.getLength());

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            sendFileContent(fileName, clientAddress, clientPort, serverSocket);
        }
    }

    private static void sendFileContent(String fileName, InetAddress clientAddress, int clientPort, DatagramSocket socket) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Bestand niet gevonden: " + fileName);
            return;
        }

        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = fileReader.readLine()) != null) {
            byte[] sendData = line.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        }
        fileReader.close();
        System.out.println("Bestand verzonden naar client.");
    }
}