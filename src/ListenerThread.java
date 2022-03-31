import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * This is a class
 * Created 2021-03-16
 *
 * @author Magnus Silverdal
 */
public class ListenerThread implements Runnable{
    private BufferedReader in;
    String msg;

    public ListenerThread(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        msg = null;
        boolean running = true;
        while (running) {
            try {
                msg = in.readLine();
            } catch (IOException e) {
                running = false;
                //e.printStackTrace();
            }
            System.out.println(msg);

        }
    }

    public void stop()  {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
