package org.example;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kies protocol: TCP(1) of UDP(2): ");
        int protocol = scanner.nextInt();
        scanner.nextLine(); // Leeg buffer

        System.out.print("Kies modus: Server(1) of Client(2): ");
        int mode = scanner.nextInt();
        scanner.nextLine();

        if (protocol == 1) {
            if (mode == 1) {
                TCPserver.main(new String[]{});
            } else {
                TCPclient.main(new String[]{});
            }
        } else if (protocol == 2) {
            if (mode == 1) {
                UDPserver.main(new String[]{});
            } else {
                UDPclient.main(new String[]{});
            }
        } else {
            System.out.println("Ongeldige keuze.");
        }

        scanner.close();
    }
}