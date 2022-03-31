import java.io.PrintWriter;
import java.net.http.WebSocket;

public class Client {
    ServerListenThread in;
    PrintWriter out;

    public Client(ServerListenThread in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }
}
