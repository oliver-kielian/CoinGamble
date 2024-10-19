import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    ArrayList<Thread> threads = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true) {
                System.out.println("server> waiting for client to connect...");
                Socket client = serverSocket.accept();
                System.out.println("server> connect to client Socket[addr=" + client.getInetAddress() + ",port=" + client.getPort() + ",localport=5000");
                handleClient(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void handleClient(Socket client)
    {
        
    }
}
