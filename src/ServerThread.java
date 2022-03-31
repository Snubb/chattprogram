import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class ServerThread implements Runnable{
    Model model;
    int port;

    public ServerThread(Model model, int port) {
        this.model = model;
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            SocketThread socketThread = new SocketThread(serverSocket, model);
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
}
