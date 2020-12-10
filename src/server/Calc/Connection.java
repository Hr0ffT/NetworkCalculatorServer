package server.Calc;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {

    private boolean connected;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    DataInputStream inputStream;
    DataOutputStream outputStream;

    public Connection(int defaultPort) {

        try {
            this.serverSocket = new ServerSocket(defaultPort);
            CalcServerRunner.online = true;
            System.out.print("Waiting for a client...");

            this.clientSocket = serverSocket.accept();
            connected = true;
            System.out.println("Got a connection...");

            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            this.inputStream = new DataInputStream(inputStream);
            this.outputStream = new DataOutputStream(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isConnected() {
        return connected;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }
}
