import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
    String ip = "localhost";
    int port = 8000;
    ServerSocket serverSocket;
    Socket socket;
    ArrayList<Socket> socketArrayList = new ArrayList<>();
    ArrayList<PrintWriter> printWriterArrayList = new ArrayList<>();

    public Model() {

    }

    public void startClient() {
        String ip = "localhost";
        int port = 8000;
        Socket socket = null;
        String name = JOptionPane.showInputDialog("What is your name?");

        try {
            socket = new Socket(ip,port);
        } catch (IOException e) {
            System.out.println("Client failed to connect");
            System.exit(0);
        }

        // GO
        try {
            Scanner tgb = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            Thread listener = new Thread(in);
            listener.start();
            boolean run = true;
            while (run) {
                String msg = tgb.nextLine();
                if (msg.equals("quit")) {
                    run = false;
                    out.println(msg);
                } else {
                    out.println(name +  ": " + msg);
                }
            }

            out.close();
            socket.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Client failed to communicate");
        }
    }

    public void addConnection(Socket newSocket, PrintWriter out) {
        socketArrayList.add(newSocket);
        printWriterArrayList.add(out);
    }

    public void startServer(Model model) {
        ServerThread serverThread = new ServerThread(model, port);
        Thread server = new Thread(serverThread);
        server.start();
    }
}
