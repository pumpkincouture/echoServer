package echoserver.com.solak;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

            int portNumber = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("INFO:" + inputLine);
                out.println(inputLine);

            }
        }
    }
}