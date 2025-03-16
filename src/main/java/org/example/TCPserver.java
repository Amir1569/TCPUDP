package org.example;
import java.io.*;
import java.net.*;

public class TCPserver {
    public static void main(String[] args) throws IOException {
        int port = 12345; // Standaard poort
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server draait op poort " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> handleClient(clientSocket)).start();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String fileName = in.readLine();
            File file = new File(fileName);

            if (!file.exists()) {
                out.println("Bestand niet gevonden.");
                return;
            }

            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
