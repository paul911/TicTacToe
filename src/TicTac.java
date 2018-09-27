import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTac extends JFrame implements ActionListener {

    private int clicks = 1;
    private String winner = "";
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean won = false;
    private JButton[] box = new JButton[10];
    private int scorex = 0;
    private int scoreo = 0;
    private JLabel scoreX;
    private JLabel scoreO;
    private String nameX;
    private String nameO;
    private JPanel glassLayer;
    private JLabel turn;

    TicTac(String x, String o) {

        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (x.isEmpty())
            nameX = "Player X";
        else
            nameX = x;
        if (o.isEmpty())
            nameO = "Player O";
        else
            nameO = o;
        initialize();
        setVisible(true);
        pack();
    }

    private void initialize() {

        JPanel leaderboard = new JPanel();
        leaderboard.setLayout(new GridLayout(1, 4, 0, 0));
        scoreX = new JLabel(String.valueOf(scorex));
        scoreO = new JLabel(String.valueOf(scoreo));
        leaderboard.add(new JLabel(nameX + ": "));
        leaderboard.add(scoreX);
        leaderboard.add(new JLabel(nameO + ": "));
        leaderboard.add(scoreO);
        turn = new JLabel("It's player " + nameX + "'s turn to place X");
        glassLayer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setStroke(new BasicStroke(20));
                if (won) {
                    g2d.drawLine(x1, y1 + 50, x2, y2 + 50);
                }
            }
        };
        glassLayer.setOpaque(false);
        setGlassPane(glassLayer);
        glassLayer.setVisible(true);
        JPanel boxes = new JPanel();
        boxes.setLayout(new GridLayout(3, 3));
        boxes.setPreferredSize(new Dimension(600, 600));
        add(boxes, BorderLayout.CENTER);
        add(leaderboard, BorderLayout.SOUTH);
        turn.setPreferredSize(new Dimension(600, 50));
        add(turn, BorderLayout.NORTH);
        turn.setFont(new Font("Arial", Font.ITALIC, 20));
        for (Component item : leaderboard.getComponents()) {
            item.setFont(new Font("Arial", Font.BOLD, 25));
        }
        for (int i = 1; i <= 9; i++) {
            box[i] = new JButton();
            boxes.add(box[i]);
            box[i].addActionListener(this);
            box[i].setFont(new Font("Arial", Font.PLAIN, 150));
            box[i].setOpaque(true);
            box[i].setBackground(Color.gray);
            box[i].setForeground(Color.BLACK);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clickBox((JButton) e.getSource());
        if (clicks > 5)
            checkWinner();
        glassLayer.repaint();
    }

    private void clickBox(JButton btn) {
        {
            if (clicks % 2 == 1) {
                btn.setText("x");
                turn.setText("It's player " + nameO + "'s turn to place O");
            } else {
                btn.setText("0");
                turn.setText("It's player " + nameX + "'s turn to place X");
            }
            btn.setEnabled(false);
            clicks++;
            if (clicks == 10) {
                int reply = JOptionPane.showConfirmDialog(null,
                        "It's a DRAW! Do you wish to play again?", "Game Over!", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                    restart();
                else {
                    JOptionPane.showMessageDialog(null, "GOODBYE");
                    System.exit(0);
                }
            }
        }
    }

    private void checkWinner() {

        if (compare(box[1], box[2], box[3], 1) || compare(box[4], box[5], box[6], 1) || compare(box[7], box[8], box[9], 1)
                || compare(box[1], box[4], box[7], 2) || compare(box[2], box[5], box[8], 2) || compare(box[3], box[6], box[9], 2)
                || compare(box[1], box[5], box[9], 3) || compare(box[3], box[5], box[7], 4)) {
            int reply = JOptionPane.showConfirmDialog(null, winner, "Game Over!", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                restart();
            } else {
                JOptionPane.showMessageDialog(null, "GOODBYE");
                System.exit(0);
            }
        }
    }

    private void win(String whoWon) {
        if (whoWon.equals("O")) {
            winner = nameO + " has won the game. Do you wish to play again?";
            scoreo++;
        } else if (whoWon.equals("X")) {
            winner = nameX + " has won the game. Do you wish to play again?";
            scorex++;
        }
        won = true;
    }

    private boolean compare(JButton btn1, JButton btn2, JButton btn3, int dr) {
        if (btn1.getText().equals(btn2.getText()) && btn1.getText().equals(btn3.getText()) && !btn1.getText().equals("")) {
            if (btn1.getText().equals("x"))
                win("X");
            else
                win("O");
            btn1.setBackground(Color.GREEN);
            btn2.setBackground(Color.GREEN);
            btn3.setBackground(Color.GREEN);
            switch (dr) {
                case 1:
                    x1 = btn1.getX();
                    y1 = btn1.getY() + btn1.getHeight() / 2;
                    x2 = btn3.getX() + btn3.getWidth();
                    y2 = btn3.getY() + btn3.getHeight() / 2;
                    return true;
                case 2:
                    x1 = btn1.getX() + btn1.getWidth() / 2;
                    y1 = btn1.getY();
                    x2 = btn3.getX() + btn3.getWidth() / 2;
                    y2 = btn3.getY() + btn3.getHeight();
                    return true;
                case 3:
                    x1 = btn1.getX();
                    y1 = btn1.getY();
                    x2 = btn3.getX() + btn3.getWidth();
                    y2 = btn3.getY() + btn3.getHeight();
                    return true;
                case 4:
                    x1 = btn1.getX() + btn1.getWidth();
                    y1 = btn1.getY();
                    x2 = btn3.getX();
                    y2 = btn3.getY() + btn3.getHeight();
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    private void restart() {
        won = false;
        clicks = 1;
        turn.setText("It's player " + nameX + "'s turn to place X");
        scoreX.setText(String.valueOf(scorex));
        scoreO.setText(String.valueOf(scoreo));
        for (int i = 1; i <= 9; i++) {
            box[i].setText("");
            box[i].setEnabled(true);
            box[i].setBackground(Color.gray);
        }
    }
}