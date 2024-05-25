package practicasSegundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Circunferencia_07 extends JFrame {
    private BufferedImage buffer;
    private Graphics2D g2d;
    private Color c = Color.red;
    private int xc, yc, radio = 75;

    public Circunferencia_07() {
        initComponents();
        
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        g2d = buffer.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        //dibujarCircunferencia();
    }

    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(x, y, color.getRGB());
        repaint();
    }

    private void drawCircle() {
        int x = 0;
        int y = radio;
        int p = (5 - radio * 4) / 4;

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
    }

    private void drawDots(int xc, int yc, int x, int y) {
        putPixel(xc + x, yc + y, c);
        putPixel(xc - x, yc + y, c);
        putPixel(xc + x, yc - y, c);
        putPixel(xc - x, yc - y, c);
        putPixel(xc + y, yc + x, c);
        putPixel(xc - y, yc + x, c);
        putPixel(xc + y, yc - x, c);
        putPixel(xc - y, yc - x, c);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }
    
    public void initComponents() {
        setTitle("Modelo matematico Circunferencia");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.white);
        setLocationRelativeTo(null);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xc = e.getX();
                yc = e.getY();
                drawCircle();
            }
        });
    }

    public static void main(String[] args) {
        new Circunferencia_07();
    }
}
