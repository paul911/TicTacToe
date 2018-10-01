import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class PaperXO {

    protected static char[][] box;
    private PaintPane glasspane;
    private int XO = 1;
    private int size;
    private int dist;
    private int circle;
    private Point[] boxes;

    PaperXO(int px) {

        this.size = px * 100;

        boxes = new Point[9];
        box = new char[3][3];
        JFrame window = new JFrame();
        window.setSize(size, size);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(size / 3, 0, size / 3, size);
                g2d.drawLine(size / 3 * 2, 0, size / 3 * 2, size);
                g2d.drawLine(0, size / 3, size, size / 3);
                g2d.drawLine(0, size / 3 * 2, size, size / 3 * 2);
            }
        };
        window.add(background);
        glasspane = new PaintPane(size);
        window.setGlassPane(glasspane);
        glasspane.setVisible(true);
        glasspane.setOpaque(false);
        for (int i = 0; i < 9; i++) {
            boxes[i] = new Point(i % 3 * size / 3 + size / 3, i / 3 * size / 3 + size / 3);
        }

        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = e.getY() / (size / 3);
                int j = e.getX() / (size / 3);
                if ((int) box[i][j] == 0) {
                    if (XO % 2 == 1)
                        box[i][j] = 'x';
                    else box[i][j] = 'o';
                    glasspane.repaint();
                    XO++;
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



}