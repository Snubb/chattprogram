import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vieww {
    private JButton setUpServerButton;
    private JButton connectButton;
    private JPanel panel;

    public Vieww() {

    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getServerButton() {
        return setUpServerButton;
    }

    public JButton getConnectButton() {
        return connectButton;
    }
}
