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

public class GrosorLineaCricunferencia_16 extends JFrame {

    private BufferedImage buffer;
    private Graphics2D g2d;
    private Color c = Color.RED;
    private String circleType = "continuo";
    private int WIDTH = 600, HEIGHT = 600;
    private int x0, y0, radius = 80;
    private int thickness = 2;

    public GrosorLineaCricunferencia_16() {
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
        g.drawImage(buffer, 0, 0, null);
    }
    
    public void drawCircle(int x0, int y0, int radio, Color c) {
        int x = 0;
        int y = radio;
        int d = 3 - 2 * radio;

        while (x <= y) {
            drawDots(x0, y0, x, y);
            x++;
            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                y--;
                d = d + 4 * (x - y) + 10;
            }
            drawDots(x0, y0, x, y);
        }
        repaint();
    }
    
     public void drawThickerCircle(int x0, int y0, int radio, int thickness, Color color) {
        for (int i = -thickness / 2; i <= thickness / 2; i++) {
            drawCircle(x0, y0, radio + i, color);
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
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }
    
    public void initComponents() {
        setTitle("Tipos de lineas en circunferencia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) { //Select circle type with 1-2 keys
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_1:
                        circleType = "continuo";
                        System.out.println(circleType);
                        break;
                    case KeyEvent.VK_2:
                        circleType = "discontinuo";
                        System.out.println(circleType);
                        break;
                    case KeyEvent.VK_3:
                        circleType = "punteada";
                        System.out.println(circleType);
                        break;
                    case KeyEvent.VK_4:
                        circleType = "rayada";
                        System.out.println(circleType);
                        break;
                    case KeyEvent.VK_5:
                        circleType = "doble";
                        System.out.println(circleType);
                        break;
                    case KeyEvent.VK_6:
                        circleType = "gruesa";
                        System.out.println(circleType);
                        break;
                    case KeyEvent.VK_MINUS:
                        if (thickness > 2) {
                            thickness -= 2;
                        }
                        System.out.println("grosor: " + thickness);
                        break;
                    case KeyEvent.VK_PLUS:
                        thickness += 2;
                        System.out.println("grosor: " + thickness);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x0 = e.getX();
                y0 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drawThickerCircle(x0, y0, radius, thickness, c);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new GrosorLineaCricunferencia_16();
    }
}
