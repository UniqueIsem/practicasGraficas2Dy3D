package practicasSegundoParcial;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class TiposLinea_13 extends JFrame {

    private BufferedImage buffer;
    private Graphics2D g2d;
    private int x0, y0, x1, y1;
    private Color c = Color.RED;
    private int WIDTH = 600, HEIGHT = 600;
    private int grosor = 2;
    private String lineType = "continua";

    public TiposLinea_13() {
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
        System.out.println("TYPE 1 TO 6 FOR EACH LINE (+ or - key for thicker line)");
    }

    public void drawLine() {
        if (lineType.equals("continua")) {
            drawContLine(x0, y0, x1, y1, c);
        } else if (lineType.equals("discontinua")) {
            drawDiscontLine(x0, y0, x1, y1, c);
        } else if (lineType.equals("punteada")) {
            drawDottedLine(x0, y0, x1, y1, c);
        } else if (lineType.equals("rayada")) {
            drawDashedLine(x0, y0, x1, y1, c);
        } else if (lineType.equals("doble")) {
            drawDoubleLine(x0, y0, x1, y1, c);
        } else if (lineType.equals("gruesa")) {
            drawThickLine(x0, y0, x1, y1, grosor, c);
        }
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

    public void drawDiscontLine(int x0, int y0, int x1, int y1, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        boolean draw = true;

        while (true) {
            if (draw) {
                putPixel(x0, y0, color);
            }
            draw = !draw;

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

    public void drawDottedLine(int x0, int y0, int x1, int y1, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        boolean draw = true;

        int counter = 0;
        while (true) {
            if (counter % 10 < 5) { // Controla el patrón de puntos
                putPixel(x0, y0, color);
            }
            counter++;
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

    public void drawDashedLine(int x0, int y0, int x1, int y1, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        boolean draw = true;

        int counter = 0;
        while (true) {
            if (counter % 10 < 5) { // Controla el patrón de líneas
                putPixel(x0, y0, color);
            }
            counter++;
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

    public void drawDoubleLine(int x0, int y0, int x1, int y1, Color color) {
        int offset = 10; // Distancia entre las dos líneas
        drawContLine(x0, y0 - offset, x1, y1 - offset, color);
        drawContLine(x0, y0 + offset, x1, y1 + offset, color);
    }

    public void drawThickLine(int x0, int y0, int x1, int y1, int thickness, Color color) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double unitDx = dx / distance;
        double unitDy = dy / distance;
        int halfThickness = thickness / 2;

        for (int i = -halfThickness; i <= halfThickness; i++) {
            int xOffset = (int) (i * unitDy);
            int yOffset = (int) (i * unitDx);
            drawContLine(x0 + xOffset, y0 - yOffset, x1 + xOffset, y1 - yOffset, color);
        }
    }

    public void putPixel(int x, int y, Color c) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, c.getRGB());
        }
    }

    public static void main(String[] args) {
        new TiposLinea_13();
    }

    private void initComponents() {
        setTitle("Linea Recta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) { //Select line type with 1-6 keys
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_1:
                        lineType = "continua";
                        System.out.println(lineType);
                        break;
                    case KeyEvent.VK_2:
                        lineType = "discontinua";
                        System.out.println(lineType);
                        break;
                    case KeyEvent.VK_3:
                        lineType = "punteada";
                        System.out.println(lineType);
                        break;
                    case KeyEvent.VK_4:
                        lineType = "rayada";
                        System.out.println(lineType);
                        break;
                    case KeyEvent.VK_5:
                        lineType = "doble";
                        System.out.println(lineType);
                        break;
                    case KeyEvent.VK_6:
                        lineType = "gruesa";
                        System.out.println(lineType);
                        break;
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
                drawLine();
            }
        });
        setVisible(true);
    }
}
