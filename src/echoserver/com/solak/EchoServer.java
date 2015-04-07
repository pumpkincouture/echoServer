package echoserver.com.solak;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true) {


                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                FileInputStream file = new FileInputStream("./public/testfile.txt");
                BufferedReader textReader = new BufferedReader(new InputStreamReader(file));

                OutputStream outStream = clientSocket.getOutputStream();

                List<Hashtable<String, String>> storedData = new ArrayList<Hashtable<String, String>>();

                String line = null;

                while ((line = textReader.readLine()) != null) {
                    System.out.println(line);
                }

                textReader.close();


                String inputLine = in.readLine();


                if (inputLine.contains("GET /")) {
                    out.flush();
                    out.write("HTTP/1.1 200 OK");
                    out.write("Content-Type: text/plain");
                    out.write("Date: " + new Date());
                    out.write("\r\n");
                    out.flush();

                } else if (inputLine.contains("GET /form")) {
                    out.flush();
                    out.write("HTTP/1.1 200 OK");
                    out.write("Content-Type: text/plain");
                    out.write("Date: " + new Date());
                    out.write("\r\n");
                    out.write(formResponse());
                    out.flush();
                }

                else if (inputLine.contains("POST /form")) {
                    Hashtable<String, String> data = new Hashtable<>();

                    save(storedData, data);
                    out.flush();
                    out.write("HTTP/1.1 200 OK");
                    out.write("");
                    out.flush();
                    System.out.println(data);
                }
                else if (inputLine.contains("PUT /form")) {
                    out.flush();
                    out.write("HTTP/1.1 200 OK");
                    out.flush();
                }
                else if (inputLine.contains("GET /flower.png")) {
                    byte[] bytes = convertToByteArray(".public/flower.png");

                    out.flush();
                    outStream.write(bytes);
                    out.flush();
                }

                else {
                    out.flush();
                    System.out.println("In the else");
                    out.write("HTTP/1.1 404 Not Found");
                    out.flush();
                }

                System.out.println("before the close statement");
//                System.out.println(formResponse());
                in.close();

            }

        } catch (Exception err) {
            System.out.println(err);
            err.printStackTrace();
        }
    }

    public static byte[] convertToByteArray(String file) throws IOException {
        Path path = Paths.get(file);
        byte[] data = Files.readAllBytes(path);

        return data;
    }

    private static void save(List<Hashtable<String, String>> savedData, Hashtable data) {
        savedData.add(data);
    }

    private static String formResponse() {
        String responseString =
                "method=\"post\">" +
                "Enter the name of the File <input name=\"file\" type=\"file\"><br>" +
                "<input value=\"Upload\" type=\"submit\"></form>" +
                "Upload only text files.";

        return responseString;
    }
}
