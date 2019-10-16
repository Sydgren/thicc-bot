import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import messaging.Messenger;

public class ChatServer {

    public static void main(String[] args) throws IOException {
        Messenger.startAcceptingConnections(5);

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while (Messenger.stillRunning()) {
            String command = input.readLine();

            ChatServer.handleCommand(command);
        }
    }

    public static void handleCommand(String command) {
        
    }
}