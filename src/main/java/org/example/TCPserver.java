package org.example;
import java.io.*;
import java.net.*;

public class TCPserver {
    public static void main(String[] args) throws IOException {
        int port = 12345; // Default port
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server runs on port:  " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();            // Accept client connection
            new Thread(() -> handleClient(clientSocket)).start();   // Handle in a new thread
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String fileName = in.readLine();    // Read requested file name
            File file = new File("files/" + fileName);

            if (!file.exists()) {
                out.println("File not found.");
                return;
            }

            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    out.println(line);          // send file content line by line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
