import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PaintPane extends JPanel {

    private int size;
    private int dist;
    private int circle;
    Graphics2D g2d;

    PaintPane(int boardSize) {
        size = boardSize;
        dist = Math.min(size, size) / 12;
        circle = Math.min(size, size) / 6;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(5));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if ((int) PaperXO.box[i][j] != 0 && PaperXO.box[i][j] == 'x') {
                    int a = j * (size / 3) + size / 6;
                    int b = i * size / 3 + size / 6;
                    g2d.drawLine(a - dist, b - dist, a + dist, b + dist);
                    g2d.drawLine(a + dist, b - dist, a - dist, b + dist);
                } else if ((int) PaperXO.box[i][j] != 0 && PaperXO.box[i][j] == 'o')
                    g2d.draw(new Ellipse2D.Double((j * size / 3 + size / 6) - dist,
                            (i * size / 3 + size / 6) - dist, circle, circle));
        }
        checkWin();
    }

    private void checkWin() {
        for (int i = 0; i < 3; i++) {
            if (PaperXO.box[i][0] ==PaperXO.box[i][1] &&PaperXO.box[i][1] ==PaperXO.box[i][2] &&PaperXO.box[i][0] !=0)
                JOptionPane.showMessageDialog(null, "Someone won!");
        }
        for (int j = 0; j < 3; j++)
            if (PaperXO.box[0][j] ==PaperXO.box[1][j] &&PaperXO.box[1][j] ==PaperXO.box[2][j] &&PaperXO.box[0][j] !=0)
                JOptionPane.showMessageDialog(null, "Someone won!");
        if (PaperXO.box[0][0] ==PaperXO.box[1][1] &&PaperXO.box[1][1] ==PaperXO.box[2][2] &&PaperXO.box[0][0] !=0)
            JOptionPane.showMessageDialog(null, "Someone won!");
        if (PaperXO.box[0][2] ==PaperXO.box[1][2] &&PaperXO.box[1][1] ==PaperXO.box[2][1] &&PaperXO.box[0][2] !=0)
            JOptionPane.showMessageDialog(null, "Someone won!");
    }
}


