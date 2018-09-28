import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class PaperXO {

    private char[][] box;
    private JPanel glasspane;
    private int XO = 1;
    private int length;
    private int height;
    private int dist;
    private int circle;
    private Point[] boxes;

    PaperXO(int l, int h) {

        this.length = l * 100;
        this.height = h * 100;
        dist = Math.min(length, height) / 12;
        circle = Math.min(length, height) / 6;
        boxes = new Point[9];
        box = new char[3][3];
        JFrame window = new JFrame();
        window.setSize(length, height);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setStroke(new BasicStroke(3));
                System.out.println(length + " " + height);
                g2d.drawLine(length / 3, 0, length / 3, height);
                g2d.drawLine(length / 3 * 2, 0, length / 3 * 2, height);
                g2d.drawLine(0, height / 3, length, height / 3);
                g2d.drawLine(0, height / 3 * 2, length, height / 3 * 2);
            }
        };
        window.add(background);
        glasspane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setStroke(new BasicStroke(5));
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++)
                        if ((int) box[i][j] != 0 && box[i][j] == 'x')
                            drawX(g2d, j * (length / 3) + length / 6, i * height / 3 + height / 6);
                        else if ((int) box[i][j] != 0 && box[i][j] == 'o')
                            drawO(g2d, j * length / 3 + length / 6, i * height / 3 + height / 6);
                }
            }
        };
        window.setGlassPane(glasspane);
        glasspane.setVisible(true);
        glasspane.setOpaque(false);
        for (int i = 0; i < 9; i++) {
            boxes[i] = new Point(i % 3 * length / 3 + length / 3, i / 3 * height / 3 + height / 3);
        }

        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = e.getY() / (height / 3);
                int j = e.getX() / (length / 3);
                System.out.println(i + " " + j);
                if ((int) box[i][j] == 0) {
                    if (XO % 2 == 1)
                        box[i][j] = 'x';
                    else box[i][j] = 'o';
                    glasspane.repaint();
                    XO++;
                    checkwin();
                    if (XO == 10)
                        JOptionPane.showMessageDialog(null, "DRAW");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        window.addMouseListener(ml);
    }

    private void checkwin() {
        for (int i = 0; i < 3; i++) {
            if (box[i][0] == box[i][1] && box[i][1] == box[i][2] && box[i][0] !=0)
                JOptionPane.showMessageDialog(null, "Someone won!");
        }
        for (int j = 0; j < 3; j++)
            if (box[0][j] == box[1][j] && box[1][j] == box[2][j] && box[0][j] !=0)
                JOptionPane.showMessageDialog(null, "Someone won!");
        if (box[0][0] == box[1][1] && box[1][1] == box[2][2] && box[0][0] !=0)
            JOptionPane.showMessageDialog(null, "Someone won!");
        if (box[0][2] == box[1][2] && box[1][1] == box[2][1] && box[0][2] !=0)
            JOptionPane.showMessageDialog(null, "Someone won!");
    }

    private void drawX(Graphics2D g2d, int x, int y) {
        g2d.drawLine(x - dist, y - dist, x + dist, y + dist);
        g2d.drawLine(x + dist, y - dist, x - dist, y + dist);
    }

    private void drawO(Graphics2D g2d, int x, int y) {
        g2d.draw(new Ellipse2D.Double(x - dist, y - dist, circle, circle));
    }
}