import javax.swing.*;

public class Runner {

    public static void main(String[] args) {

        int choice = JOptionPane.showOptionDialog(null, "Choose game", "TicTacToes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, new Object[]{"Buttons", "Plain"}, null);
        if (choice == JOptionPane.YES_OPTION)
            new NameInput();
        else {
            int length = 0;
            while (length < 1 || length > 9)
                length = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter window width in pixels (a value between 1 and 9)"));
            int height = 0;
            while (height < 1 || height > 9)
                height = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter window height in pixels (a value between 1 and 9)"));
            new PaperXO(length, height);
        }
    }
}

