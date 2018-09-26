import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTac extends JFrame implements ActionListener {

    private int clicks = 1;
    private JButton box1;
    private JButton box2;
    private JButton box3;
    private JButton box4;
    private JButton box5;
    private JButton box6;
    private JButton box7;
    private JButton box8;
    private JButton box9;

    TicTac() {
        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new GridLayout(3, 3, 0, 0));

        initBoxes();
        setVisible(true);
        pack();

    }

    private void initBoxes() {
        box1 = new JButton();
        box2 = new JButton();
        box3 = new JButton();
        box4 = new JButton();
        box5 = new JButton();
        box6 = new JButton();
        box7 = new JButton();
        box8 = new JButton();
        box9 = new JButton();
        box1.addActionListener(this);
        box2.addActionListener(this);
        box3.addActionListener(this);
        box4.addActionListener(this);
        box5.addActionListener(this);
        box6.addActionListener(this);
        box7.addActionListener(this);
        box8.addActionListener(this);
        box9.addActionListener(this);
        add(box1);
        add(box2);
        add(box3);
        add(box4);
        add(box5);
        add(box6);
        add(box7);
        add(box8);
        add(box9);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println(e.getSource() + "x");
        clickBox((JButton)e.getSource());
        if (clicks > 5)
            checkWinner();

    }

    public void clickBox(JButton btn) {
        {
            if (clicks % 2 == 1)
                btn.setText("x");
            else
                btn.setText("0");
            btn.setEnabled(false);
            clicks++;
            System.out.println(btn.getText());
        }
    }

    public void checkWinner() {

        if (box1.getText().equals(box2.getText()) && box1.getText().equals(box3.getText()))
        JOptionPane.showMessageDialog(null, "X won the game");
    }
}