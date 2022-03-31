import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.Cleaner;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread implements Runnable {
    ServerSocket serverSocket;
    ServerThread serverThread;

    public SocketThread(ServerSocket serverSocket, ServerThread serverThread) {
        this.serverSocket = serverSocket;
        this.serverThread = serverThread;
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                ServerListenThread in =
                        new ServerListenThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), serverThread);
                Thread listener = new Thread(in);
                listener.start();
                System.out.println("New connection found");
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Client client = new Client(in, out);
                serverThread.addConnection(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void stop() {

    }
}

