package practicasSegundoParcial;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class CircunferenciaPuntoMedio_10 extends JFrame {
    private final BufferedImage buffer;
    private final Graphics2D px;
    private int xc, yc, radio = 100;
    private Color c = Color.RED;

    public CircunferenciaPuntoMedio_10() {
        initComponents();
        
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        px = buffer.createGraphics();
        px.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        px.setColor(Color.WHITE);
        px.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, null);
    }

    public void drawCircle() {
        int x = 0;
        int y = radio;
        int p = 1 - radio;

        drawDots(xc, yc, x, y);
        while (x < y) {
            x++;
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
            drawDots(xc, yc, x, y);
        }
        repaint();
    }

    public void drawDots(int xc, int yc, int x, int y) {
        putPixel(xc + x, yc + y, c);
        putPixel(xc - x, yc + y, c);
        putPixel(xc + x, yc - y, c);
        putPixel(xc - x, yc - y, c);
        putPixel(xc + y, yc + x, c);
        putPixel(xc - y, yc + x, c);
        putPixel(xc + y, yc - x, c);
        putPixel(xc - y, yc - x, c);
    }
    
    public void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }
    
    private void initComponents() {
        setTitle("Circunferencia Punto Medio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xc = e.getX();
                yc = e.getY();
                drawCircle();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new CircunferenciaPuntoMedio_10();
    }
}