package practicasSegundoParcial;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class LineaRectaDDA_03 extends JFrame {

    private BufferedImage buffer;
    private Graphics2D px;
    private int x0, y0, x1, y1;
    private Color c = Color.RED;

    public LineaRectaDDA_03() {
        initComponents();
        
        buffer = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        px = buffer.createGraphics();
        px.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        px.setColor(Color.WHITE);
        px.fillRect(0, 0, getWidth(), getHeight());
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        px.setColor(color);
        int dx = x1 - x0;
        int dy = y1 - y0;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        double xIncrement = (double) dx / steps;
        double yIncrement = (double) dy / steps;
        double x = x0;
        double y = y0;
        for (int i = 0; i <= steps; i++) {
            putPixel((int) Math.round(x), (int) Math.round(y), color);
            x += xIncrement;
            y += yIncrement;
        }
        repaint();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    public static void main(String[] args) {
        new LineaRectaDDA_03();
    }

    private void initComponents() {
        setTitle("Linea Recta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
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
                drawLine(x0, y0, x1, y1, c);
            }
        });
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, null);
    }
}