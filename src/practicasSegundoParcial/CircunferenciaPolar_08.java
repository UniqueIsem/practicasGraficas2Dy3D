package practicasSegundoParcial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CircunferenciaPolar_08 extends JFrame {
    private static final int RADIO = 100;
    private int xc ;
    private int yc ;
    BufferedImage buffer;
    Graphics2D g2d;
    Color c = Color.RED;

    public CircunferenciaPolar_08() {
        initComponents();
        
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        g2d = buffer.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }
    
    public void drawCircle() {
        for (double t = 0; t <= 2 * Math.PI; t += 0.01) {
            int x = (int) (xc + RADIO * Math.sin(t));
            int y = (int) (yc + RADIO * Math.cos(t));
            putPixel(x, y, c); // Dibujar un punto en la posición (x, y)
        }
        repaint();
    }
    
    public void putPixel(int x, int y, Color c) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, c.getRGB());
        }
    }

    private void initComponents() {
        setTitle("Circunferencia con Coordenadas Polares");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        new CircunferenciaPolar_08();
    }

}