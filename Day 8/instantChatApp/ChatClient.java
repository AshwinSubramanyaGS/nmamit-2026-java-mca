import java.io.*;
import java.net.*;

public class ChatClient {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java ChatClient <server-ip> <port>");
            return;
        }

        String serverAddress = args[0];
        int port = Integer.parseInt(args[1]);

        try (
            Socket socket = new Socket(serverAddress, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to chat server at " + serverAddress + ":" + port);

            // Thread to read messages from the server and print them
            Thread readerThread = new Thread(() -> {
                try {
                    String serverLine;
                    while ((serverLine = in.readLine()) != null) {
                        // Synchronize on System.out to avoid garbled output
                        synchronized (System.out) {
                            System.out.println(serverLine);
                        }
                    }
                } catch (IOException e) {
                    // Connection likely closed
                    System.out.println("Disconnected from server.");
                }
            });
            readerThread.setDaemon(true);
            readerThread.start();

            // Main thread reads user input from keyboard and sends to server
            String userInput;
            System.out.println("Type messages and press Enter. /quit to exit, /users to list users.");
            while ((userInput = console.readLine()) != null) {
                userInput = userInput.trim();
                if (userInput.equalsIgnoreCase("/quit")) {
                    out.println("/quit"); // Tell the server we are leaving
                    break;
                } else {
                    out.println(userInput);
                }
            }
        } // socket is closed here automatically
        System.out.println("Client terminated.");
    }
}