import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) {

        try {
            Socket s = new Socket("127.0.0.1", 3000);
            System.out.println("--Connected--");
            DataInputStream input = new DataInputStream(s.getInputStream());
            String message = input.readUTF();
            System.out.println(message);
        }
        catch(Exception ex) {

        }

    }


}