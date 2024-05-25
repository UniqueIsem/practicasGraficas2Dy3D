package practicasSegundoParcial;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class LineaRecta_01 extends JFrame implements MouseListener {

    private BufferedImage buffer;
    private Graphics px;
    private int x0, y0, x1, y1;
    private Color c = Color.RED;

    public LineaRecta_01() {
        initComponents();
        
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        px = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
    
    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        // Algorithm for (m)
        double m = (double)(y1 - y0) / (double)(x1 - x0);
        // Algorithm for (b)
        double b = y0 - m * x0;
        // Drawing the line px by px
        for (int x = x0; x <= x1; x++) {
            int y = (int)(m * x + b);
            putPixel(x, y, color);
        }
    }

    public static void main(String[] args) {
        new LineaRecta_01();
    }

    private void initComponents() {
        setTitle("Linea Recta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        addMouseListener(this);
        show();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

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

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
