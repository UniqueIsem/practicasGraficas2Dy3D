package practicasSegundoParcial;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Figuras_12 extends JFrame {

    private BufferedImage buffer;
    private Graphics2D g2d;
    private int x0, y0, x1, y1;
    private Color c = Color.RED;
    private int WIDTH = 600, HEIGHT = 600, RADIO = 100;
    private String figureType = "dot";

    public Figuras_12() {
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
        System.out.println("TYPE 1 TO 5 FOR EACH FIGURE (+ OR - KEY TO CIRCLE SIZE)");
    }

    public void drawFigure() {
        if (figureType.equals("dot")) {
            putPixel(x0, y0, c);
        } else if (figureType.equals("line")) {
            drawContLine(x0, y0, x1, y1, c);
        } else if (figureType.equals("circle")) {
            drawCircle(x0, y0, x1, y1, c);
        } else if (figureType.equals("rectangle")) {
            drawRectangle(x0, y0, x1, y1, c);
        } else if (figureType.equals("oval")) {
            drawOval(x0, y0, x1, y1, c);
        }
    }

    public void putPixel(int x, int y, Color c) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, c.getRGB());
        }
        repaint();
    }

    public void drawContLine(int x0, int y0, int x1, int y1, Color color) {
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

    public void drawCircle(int x0, int y0, int x1, int y1, Color color) {
        for (double t = 0; t <= 2 * Math.PI; t += 0.01) {
            int x = (int) (x0 + RADIO * Math.sin(t));
            int y = (int) (y0 + RADIO * Math.cos(t));
            putPixel(x, y, c); // Dibujar un punto en la posición (x, y)
        }
        repaint();
    }

    public void drawRectangle(int x0, int y0, int x1, int y1, Color color) {
        drawContLine(x0, y0, x1, y0, color); //Linea inferior
        drawContLine(x0, y0, x0, y1, color); //Linea izquierda
        drawContLine(x0, y1, x1, y1, color); //Linea superior
        drawContLine(x1, y0, x1, y1, color); //Linea derecha
    }

    public void drawOval(int x0, int y0, int x1, int y1, Color color) {
        int a = Math.abs(x1 - x0) / 2;
        int b = Math.abs(y1 - y0) / 2;
        int xCenter = (x0 + x1) / 2;
        int yCenter = (y0 + y1) / 2;

        int x = 0;
        int y = b;
        int aSquared = a * a;
        int bSquared = b * b;
        int twoASquared = 2 * aSquared;
        int twoBSquared = 2 * bSquared;
        int xChange = bSquared * (1 - 2 * a) + 2 * aSquared * y;
        int yChange = aSquared * (1 + 2 * b) - 2 * bSquared * x;
        int ellipseError = (int) (bSquared - aSquared * b + 0.25 * aSquared);

        while (x * bSquared <= y * aSquared) {
            putPixel(xCenter + x, yCenter + y, color);
            putPixel(xCenter - x, yCenter + y, color);
            putPixel(xCenter + x, yCenter - y, color);
            putPixel(xCenter - x, yCenter - y, color);

            x++;
            if (ellipseError < 0) {
                ellipseError += twoBSquared * x + bSquared;
            } else {
                y--;
                ellipseError += twoBSquared * x - twoASquared * y + bSquared;
            }
        }

        x = a;
        y = 0;
        xChange = aSquared * (1 - 2 * b) + 2 * bSquared * x;
        yChange = bSquared * (1 + 2 * a) - 2 * aSquared * y;
        ellipseError = (int) (aSquared - bSquared * a + 0.25 * bSquared);

        while (x * bSquared > y * aSquared) {
            putPixel(xCenter + x, yCenter + y, color);
            putPixel(xCenter - x, yCenter + y, color);
            putPixel(xCenter + x, yCenter - y, color);
            putPixel(xCenter - x, yCenter - y, color);

            y++;
            if (ellipseError < 0) {
                ellipseError += twoASquared * y + aSquared;
            } else {
                x--;
                ellipseError += twoASquared * y - twoBSquared * x + aSquared;
            }
        }

        repaint();
    }

    public static void main(String[] args) {
        new Figuras_12();
    }

    private void initComponents() {
        setTitle("Linea Recta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) { //Select line type with 1-6 keys
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_1:
                        figureType = "dot";
                        System.out.println(figureType);
                        break;
                    case KeyEvent.VK_2:
                        figureType = "line";
                        System.out.println(figureType);
                        break;
                    case KeyEvent.VK_3:
                        figureType = "circle";
                        System.out.println(figureType);
                        break;
                    case KeyEvent.VK_4:
                        figureType = "rectangle";
                        System.out.println(figureType);
                        break;
                    case KeyEvent.VK_5:
                        figureType = "oval";
                        System.out.println(figureType);
                        break;
                    case KeyEvent.VK_MINUS:
                        RADIO -= 10;
                        System.out.println("Radio: " + RADIO);
                        break;
                    case KeyEvent.VK_PLUS:
                        RADIO += 10;
                        System.out.println("Radio: " + RADIO);
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
                x1 = e.getX();
                y1 = e.getY();
                drawFigure();
            }
        });
        setVisible(true);
    }
}
