import javax.swing.*;

class NameInput extends JFrame {

    NameInput() {

        String x = JOptionPane.showInputDialog(null, "Enter name for player X: ");
        String o = JOptionPane.showInputDialog(null, "Enter name for player O: ");
        new TicTac(x, o);
    }
}


