package practicasSegundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ScanLine_17 extends JFrame {

    private BufferedImage buffer;
    private Graphics2D g2d;
    private int x0, y0, x1, y1;
    private int WIDTH = 600, HEIGHT = 600;

    public ScanLine_17() {
        initComponents();
        
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g2d = buffer.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public void fillRectangle(int x0, int y0, int x1, int y1, Color color) {
        int nx0 = Math.min(x0, x1);
        int ny0 = Math.min(y0, y1);
        int nx1 = Math.max(x0, x1);
        int ny1 = Math.max(y0, y1);

        for (int y = ny0; y <= ny1; y++) {
            int xInicio = -1;
            for (int x = nx0; x <= nx1; x++) {
                if (xInicio == -1) {
                    xInicio = x;
                }
                if (xInicio != -1) {
                    putPixel(xInicio, y, color);
                    xInicio = -1;
                }
            }
        }
        repaint();
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    
    
    private void initComponents() {
        setTitle("Scan Line");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
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
                fillRectangle(x0, y0, x1, y1, Color.blue);
            }
        });
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new ScanLine_17();
    }
}
