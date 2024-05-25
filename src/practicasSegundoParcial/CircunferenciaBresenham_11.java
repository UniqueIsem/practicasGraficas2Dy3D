package practicasSegundoParcial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CircunferenciaBresenham_11 extends JFrame {
    private BufferedImage buffer;
    private Color c = Color.green;
    private int xc, yc, radio = 100;

    public CircunferenciaBresenham_11() {
        initComponents();
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    private void drawCircle() {
        int x = 0;
        int y = radio;
        int d = 3 - 2 * radio;

        while (x <= y) {
            drawDots(xc, yc, x, y);
            x++;
            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                y--;
                d = d + 4 * (x - y) + 10;
            }
            drawDots(xc, yc, x, y);
        }
        repaint();
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
    
    private void putPixel(int x, int y, Color color) {
        buffer.setRGB(x, y, color.getRGB());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }
    
    public void initComponents() {
        setTitle("Circunferencia Bresenham");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addMouseListener(new MouseAdapter(){
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
        new CircunferenciaBresenham_11();
    }
}


