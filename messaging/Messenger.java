package messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Messenger {

    private static boolean running = false;

    private static ThreadPoolExecutor executor;

    private static List<String> usernames;

    public static void startAcceptingConnections(int poolSize) throws IOException {
        Messenger.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);

        Messenger.running = true;

        ServerSocket serverSocket = new ServerSocket(9000);

        while (Messenger.stillRunning()) {
            if (executor.getPoolSize() >= poolSize) {
                continue;
            }

            Socket socket = serverSocket.accept();
            Messenger.executor.submit(new ClientConnection(socket));
        }

        serverSocket.close();
    }

    public static void stop() {
        Messenger.running = false;
    }

    public static boolean stillRunning() {
        return Messenger.running;
    }

    public static void broadcastMessage(String message) {

    }

    public static boolean login(String username) {
        if (Messenger.usernameisTaken(username)) {
            return false;
        }

        Messenger.usernames.add(username);

        return true;
    }

    public static boolean usernameisTaken(String username) {
        return Messenger.usernames.contains(username);
    }
}