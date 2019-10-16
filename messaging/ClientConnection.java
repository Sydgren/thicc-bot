package messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientConnection implements Runnable {

    private Socket socket;

    private DataInputStream inputStream;

    private DataOutputStream outputStream;

    private boolean isLoggedIn = false;

    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new DataInputStream(this.socket.getInputStream());
        this.outputStream = new DataOutputStream(this.socket.getOutputStream());
    }

    public void run() {
        while (! this.isLoggedIn()) {
            String input = this.inputStream.readUTF();
            boolean success = Messenger.login(input);

            this.sendLoginResponse(success, input);

            if (success) {
                break;
            }
        }

        while(this.isClientAlive()) {
            String input = this.inputStream.readUTF();
            String[] command = input.split(" ", 2);

            this.handleCommand(command[0], command[1]);
        }
    }

    private void handleCommand(String type, String params) {
        switch (type) {
            case "JOIN":

                break;
        }
    }

    private boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    private boolean isClientAlive() {
        return this.hasReceivedKeepAlive()
            && this.socket.isConnected();
    }

    private void disconnect() throws IOException {
        this.socket.close();
    }

    private boolean hasReceivedKeepAlive() {
        return true;
    }

    private void sendLoginResponse(boolean success, String username) {
        String command = success ? "J_OK" : "J_ER";

        this.outputStream.writeUTF(command + " " + username);
    }

    public void sendMessageToClient(String sender, String message) {
        
    }
}