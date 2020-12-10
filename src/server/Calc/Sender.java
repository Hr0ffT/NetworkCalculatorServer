package server.Calc;

import java.io.IOException;

public class Sender {

    Connection connection;

    public Sender(Connection connection) {
        this.connection = connection;
    }

    public void send(String displayText) {
        try {
            connection.getOutputStream().writeUTF(displayText);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
