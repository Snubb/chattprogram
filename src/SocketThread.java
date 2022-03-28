import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread implements Runnable {
    ServerSocket serverSocket;
    Model model;

    public SocketThread(ServerSocket serverSocket, Model model) {
        this.serverSocket = serverSocket;
        this.model = model;
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                ListenerThread in =
                        new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream())));
                Thread listener = new Thread(in);
                listener.start();
                System.out.println("New connection found");
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                model.addConnection(socket, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void stop() {

    }
}

