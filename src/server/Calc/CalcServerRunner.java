package server.Calc;

import java.io.*;

public class CalcServerRunner {

    static boolean online = true;
    static final int DEFAULT_PORT = 6666;

    public static void main(String[] args) {

        while (online) {

            try {
                Connection connection = new Connection(DEFAULT_PORT);
                Sender sender = new Sender(connection);
                final CalculatorEngine calculatorEngine = new CalculatorEngine(sender);

                while(connection.isConnected()) {
                   calculatorEngine.receiveButtonText(connection.getInputStream().readUTF());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
