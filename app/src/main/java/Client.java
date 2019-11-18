import java.net.*;
import java.io.*;

public class Client {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public Client(String address, int port) {

        //establish connection
        try {
            socket = new Socket(address, port);
            System.out.println("connected");

            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        String line = "";

        while(!line.equals("earthquake")) {
            try {
                line = input.readLine();
                output.writeUTF(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // close connection
        try {
            input.close();
            output.close();
            socket.close();
        } catch(IOException e) {
            System.out.println(e);
        }


    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }
}
