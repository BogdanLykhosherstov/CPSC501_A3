import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receiver {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {


        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("Server opened at: " + inetAddress.getHostAddress());

        System.out.print("Enter Port: ");
        int portNumber;
        portNumber = scanner.nextInt();


        ServerSocket serverSocket = new ServerSocket(portNumber, 0, inetAddress);
        System.out.println("Socket created and listening on port: "+portNumber);

        while (true) {

            Socket socket = serverSocket.accept();


            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(socket.getInputStream());
            System.out.println("Received file");

            Visualizer visualizer = new Visualizer();
            Deserializer deserializer = new Deserializer();
            //deserialize and output object to the screen
            Object object = deserializer.deserialize(document);
            System.out.println("Inspecting file");
            visualizer.inspect(object, true);

        }
    }
}