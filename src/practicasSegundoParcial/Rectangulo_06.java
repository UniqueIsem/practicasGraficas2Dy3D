package practicasSegundoParcial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rectangulo_06 extends JPanel {

    private int x0, y0, x1, y1;
    private Color color = Color.RED;

    public Rectangulo_06() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 400));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x0 = e.getX();
                y0 = e.getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        drawRectangle(x0, y0, x1, y1, g2d, color);
    }

    public void drawRectangle(int x0, int y0, int x1, int y1, Graphics2D g2d, Color color) {
        drawLineBresenham(x0, y0, x1, y0, g2d, color); //Linea inferior
        drawLineBresenham(x0, y0, x0, y1, g2d, color); //Linea izquierda
        drawLineBresenham(x0, y1, x1, y1, g2d, color); //Linea superior
        drawLineBresenham(x1, y0, x1, y1, g2d, color); //Linea derecha
    }

    public void drawLineBresenham(int x0, int y0, int x1, int y1, Graphics2D g2d, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            g2d.setColor(color);
            g2d.fillRect(x0, y0, 1, 1);

            if (x0 == x1 && y0 == y1) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rectángulo con Bresenham");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Rectangulo_06());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}