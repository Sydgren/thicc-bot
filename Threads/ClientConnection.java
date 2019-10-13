import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientConnection extends Thread {

    private Socket socket;

    private DataInputStream inputStream;

    private DataOutputStream outputStream;

    private List<String> stopCommands = new ArrayList<String>(
        Arrays.asList(
            "quit", 
            "exit", 
            "disconnect"));

    public ClientConnection(Socket socket) {
        this.socket = socket;
        this.inputStream = new DataInputStream(this.socket.getInputStream());
        this.outputStream = new DataOutputStream(this.socket.getOutputStream());

    }

    public void run() {
        while(this.isNotLoggedIn()) {
            String input = this.inputStream.readUTF();
            boolean success = LoginHandler.attempt(input);

            this.sendLoginResponse(input);

            if (success) {
                break;
            }
        }

        while(this.isClientAlive()) {
            String input = this.inputStream.readUTF();
            String[] command = message.split(" ", 2);

            this.handleCommand(command[0], command[1]);
        }
    }

    private void handleCommand(String type, String params) {
        //
    }

    private boolean isClientAlive() {
        return this.hasReceivedKeepAlive()
            && this.socket.isConnected();
    }

    private void disconnect() {
        this.socket.close();
    }

    public void sendMessageToClient(String sender, String message) {
        this.outputStream.
    }
}