import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Sender {


    public static void main(String[] args) throws IllegalAccessException, IOException {

        Scanner input = new Scanner(System.in);
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("Server opened at: "+inetAddress.getHostAddress());

        System.out.println("Enter Host:");
        String host = input.nextLine();

        System.out.println("Enter Port:");
        String port = input.nextLine();



        while(true){

            Socket socket = new Socket(host, Integer.parseInt(port));
            DataOutputStream d_output_stream = new DataOutputStream(socket.getOutputStream());
            ObjectCreator c = new ObjectCreator();
            Object obj =  c.createObject();

            if(obj == null){
                break;
            }
            Serializer s = new Serializer();
            Document document = s.serialize(obj);

            System.out.println("Sending document to Receiver");
            ByteArrayOutputStream b_output_stream = new ByteArrayOutputStream();
            new XMLOutputter().output(document, b_output_stream);
            d_output_stream.write(b_output_stream.toByteArray());
            d_output_stream.close();
            socket.close();
        }


    }
}
