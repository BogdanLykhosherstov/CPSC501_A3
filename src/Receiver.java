import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receiver {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {


        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("Server opened at: " + inetAddress.getHostAddress());

        System.out.print("Please enter desired port number: ");
        int portNumber;
        portNumber = scanner.nextInt();

        ServerSocket serverSocket = new ServerSocket(portNumber, 0, inetAddress);

        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println("Received file");

            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(socket.getInputStream());


            try {
                new XMLOutputter(Format.getPrettyFormat()).output(document, new FileWriter("test_received.xml"));
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
}