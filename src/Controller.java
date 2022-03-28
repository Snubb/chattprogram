import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private Model model;
    private Vieww view;
    public Controller() {
        model = new Model();
        view = new Vieww();

        JFrame frame = new JFrame("notepadView");
        frame.setContentPane(view.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        view.getServerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.startServer(model);
            }
        });

        view.getConnectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.startClient();
            }
        });
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
