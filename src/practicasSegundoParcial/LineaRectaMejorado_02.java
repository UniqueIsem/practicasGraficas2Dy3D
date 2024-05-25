package practicasSegundoParcial;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class LineaRectaMejorado_02 extends JFrame {

    private BufferedImage buffer;
    private Graphics2D px; // We use G2D for an accelerate render
    private int x0, y0, x1, y1;
    private Color c = Color.RED;

    public LineaRectaMejorado_02() {
        initComponents();

        buffer = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        px = buffer.createGraphics();
        // Accelerate render with setRenderingHint()
        px.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Cleaning Buffered image before drawing a new line
        px.fillRect(0, 0, getWidth(), getHeight());
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        double m = (double)(y1 - y0) / (double)(x1 - x0);
        // Algorithm for (b)
        double b = y0 - m * x0;
        // Drawing the line px by px
        for (int x = x0; x <= x1; x++) {
            int y = (int)(m * x + b);
            putPixel(x, y, color);
        }
        repaint();
    }
    
    // Drawing directly on BufferedImage
    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    public static void main(String[] args) {
        new LineaRectaMejorado_02();
    }

    private void initComponents() {
        setTitle("Linea Recta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        // Mejoramos la implementacion de Mouse Listeners
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
