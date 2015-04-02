package echoserver.com.solak;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {

            ServerSocket serverSocket = new ServerSocket(2345);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            FileInputStream file = new FileInputStream("./public/testfile.txt");
            BufferedReader textReader = new BufferedReader(new InputStreamReader(file));

            String line = null;

            while ((line = textReader.readLine()) != null) {
                System.out.println(line);
            }

            textReader.close();

        while (true) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("INFO: " + inputLine);
                out.println(inputLine);

            }
        }
    }
}