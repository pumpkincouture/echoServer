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

            FileInputStream file = new FileInputStream("/Users/test/code/testfile.txt");
            BufferedReader textReader = new BufferedReader(new InputStreamReader(file));

            String line = null;

            while ((line = textReader.readLine()) != null) {
                System.out.println(line);
            }

            textReader.close();

            File textFile = new File("/Users/test/code/testfile.txt");
            System.out.println(textFile.getAbsolutePath());
            System.out.println(textFile.length());


        while (true) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("INFO: " + inputLine);
                out.println(inputLine);

            }
        }
    }
}