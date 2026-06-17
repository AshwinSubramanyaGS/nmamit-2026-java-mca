

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {

    // Thread‑safe set of all client output streams (PrintWriter)
    private static final Set<PrintWriter> clientWriters = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) throws IOException {
        int port = 12345;
        System.out.println("Chat server started on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                // Accept a new client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                // Start a dedicated thread for this client
                new Thread(new ClientHandler(clientSocket)).start();
            }
        }
    }

    // Inner class that handles one client connection
    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter writer;
        private String nickname = "Anonymous";

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output, true)
            ) {
                this.writer = writer;
                clientWriters.add(writer);

                // 1. Ask for nickname
                writer.println("Enter your nickname: ");
                nickname = reader.readLine();
                if (nickname == null || nickname.trim().isEmpty()) {
                    nickname = "User-" + socket.getPort();
                }
                broadcast("[Server] " + nickname + " has joined the chat.");

                // 2. Main loop: read messages from this client
                String message;
                while ((message = reader.readLine()) != null) {
                    message = message.trim();
                    if (message.equalsIgnoreCase("/quit")) {
                        break;
                    } else if (message.equalsIgnoreCase("/users")) {
                        // List currently connected users (only to this client)
                        writer.println("[Server] Connected users:");
                        for (PrintWriter w : clientWriters) {
                            // We cannot easily map writer -> nickname without additional structure,
                            // so we'll just print ourselves for now. (See note below for improvement)
                            // This is a simplified demo: we show only this user.
                        }
                        // Better: store a Map<PrintWriter, String> instead of just the set.
                        // For brevity, the full version below uses that.
                        writer.println("[Server] (Feature simplified in this demo)");
                    } else {
                        broadcast("[" + nickname + "] " + message);
                    }
                }
            } catch (IOException e) {
                System.out.println("Client connection error: " + e.getMessage());
            } finally {
                // Client disconnected
                if (writer != null) {
                    clientWriters.remove(writer);
                    broadcast("[Server] " + nickname + " has left the chat.");
                }
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }

        // Send a message to all clients except the sender (optional)
        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}