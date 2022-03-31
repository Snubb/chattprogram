import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerThread implements Runnable{
    Model model;
    int port;
    ArrayList<Client> clientArrayList = new ArrayList<>();

    public ServerThread(Model model, int port) {
        this.model = model;
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            SocketThread socketThread = new SocketThread(serverSocket, this);
            Thread server = new Thread(socketThread);
            server.start();
            boolean run = true;
            Scanner tgb = new Scanner(System.in);
            System.out.println("Server started");
            while (run) {
                String msg = tgb.nextLine();
                if (msg.endsWith("quit")) {
                    run = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop() {

    }

    public void addConnection(Client cLient) {
        clientArrayList.add(cLient);
    }

    public void sendMessageToAll(String msg) {
        for (int i = 0; i < clientArrayList.size(); i++) {
            clientArrayList.get(i).out.println(msg);
        }
    }
}
