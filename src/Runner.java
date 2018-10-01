import javax.swing.*;

public class Runner {

    public static void main(String[] args) {

        int choice = JOptionPane.showOptionDialog(null, "Choose game", "TicTacToes",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, new Object[]{"Buttons", "Plain"}, null);
        if (choice == JOptionPane.YES_OPTION)
            new NameInput();
        else {
            int size = 0;
            while (size < 1 || size > 9)
                size = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter window size" +
                        " in pixels (a value between 1 and 9)"));
            new PaperXO(size);
        }
    }
}

