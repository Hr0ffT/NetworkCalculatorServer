//package server;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.*;
//
//public class Server {
//
//    public static void main(String[] args) {
//        int port = 6666;
//
//        while (true) {
//
//            try {
//
//                final ServerSocket serverSocket = new ServerSocket(port);
//
//                System.out.println("Waiting for a client...");
//
//                Socket clientSocket = serverSocket.accept();
//
//
//                System.out.println("Got a client! :)");
//                System.out.println();
//
//                InputStream sin = clientSocket.getInputStream();
//                OutputStream sout = clientSocket.getOutputStream();
//
//                DataInputStream in = new DataInputStream(sin);
//                DataOutputStream out = new DataOutputStream(sout);
//
//                String line = null;
//                String line2 = null;
//
//
//                while(true) {
//
//                    line = in.readUTF();
//                    System.out.println("The client just sent me the line: " + line);
//                    System.out.println("Sending it back...");
//                    line2 = line.toUpperCase();
//                    line = new StringBuilder(line).reverse().toString();
//                    out.writeUTF(line);
//                    out.flush();
//                    out.writeUTF(line2);
//                    out.flush();
//                    System.out.println("Waiting for the next line...");
//                    System.out.println();
//
//
//                }
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//
//        }
//
//
//
//
//    }
//}
