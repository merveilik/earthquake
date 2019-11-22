import java.net.*;
import java.io.*;

public class Server {

    public static void main(String args[]) {
        try {
            ServerSocket server = new ServerSocket(3000);
            Socket s = server.accept();
            //System.out.println("Connected");

            DataOutputStream output = new DataOutputStream(s.getOutputStream());
            output.writeUTF("An earthquake occured!");


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    }






