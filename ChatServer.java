import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatServer {

    public static void main(String[] args) {
        Messenger.startAcceptingConnections();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while (ChatServer.isAlive()) {
            String command = input.readLine();

            ChatServer.handleCommand(command);
        }

        ChatServer.cleanUp();
    }

    public static function handleCommand(String command) {
        
    }
}