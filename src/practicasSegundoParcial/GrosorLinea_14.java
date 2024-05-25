package practicasSegundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class GrosorLinea_14 extends JFrame {

    private int WIDTH = 600, HEIGHT = 600;
    private int x0, y0, x1, y1;
    private int grosor = 2;
    private Color c = Color.RED;
    private BufferedImage buffer;
    private Graphics2D g2d;

    public GrosorLinea_14() {
        initComponents();
        showInstructions();
        
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g2d = buffer.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, null);
    }
    
    public void showInstructions() {
        System.out.println("(+ or - key for thicker line)");
    }

    public void putPixel(int x, int y, Color c) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, c.getRGB());
        }
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color, int thickness) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double unitDx = dx / distance;
        double unitDy = dy / distance;
        int halfThickness = thickness / 2;

        for (int i = -halfThickness; i <= halfThickness; i++) {
            int xOffset = (int) (i * unitDy);
            int yOffset = (int) (i * unitDx);
            drawBresenhamLine(x0 + xOffset, y0 - yOffset, x1 + xOffset, y1 - yOffset, color);
        }
    }

    private void drawBresenhamLine(int x0, int y0, int x1, int y1, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x0, y0, color);

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
        repaint();
    }

    private void initComponents() {
        setTitle("Linea Recta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
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
                drawLine(x0, y0, x1, y1, c, grosor);
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) { //Select line type with 1-6 keys
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_MINUS:
                        if (grosor > 2) {
                            grosor -= 2;
                        }
                        System.out.println("grosor: " + grosor);
                        break;
                    case KeyEvent.VK_PLUS:
                        grosor += 2;
                        System.out.println("grosor: " + grosor);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new GrosorLinea_14();
    }

}
