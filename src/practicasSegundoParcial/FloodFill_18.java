package practicasSegundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class FloodFill_18 extends JFrame {

    private BufferedImage buffer;
    private Graphics2D g2d;
    private int WIDTH = 600, HEIGHT = 600;
    private int x0, y0, x1, y1;
    private boolean fill = false;
    private Color c = Color.BLACK;
    private Color fillColor = Color.blue;

    public FloodFill_18() {
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
        g.drawImage(buffer, 0, 0, this);
    }
    
    public void showInstructions() {
        System.out.println("DRAW A RECTANGLE AND PRESS 'F' TO FILL");
    }

    public void floodFill(int x, int y, int targetColor, Color replacementColor) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) {
        return;
    }

    Set<Integer> visited = new HashSet<>();
    visited.add(x + y * WIDTH);

    while (!visited.isEmpty()) {
        Integer pixel = visited.iterator().next();
        visited.remove(pixel);

        x = pixel % WIDTH;
        y = pixel / WIDTH;

        if (buffer.getRGB(x, y) == targetColor) {
            buffer.setRGB(x, y, replacementColor.getRGB());

            if (x + 1 < WIDTH && !visited.contains((x + 1) + y * WIDTH)) {
                visited.add((x + 1) + y * WIDTH);
            }
            if (x - 1 >= 0 && !visited.contains((x - 1) + y * WIDTH)) {
                visited.add((x - 1) + y * WIDTH);
            }
            if (y + 1 < HEIGHT && !visited.contains(x + (y + 1) * WIDTH)) {
                visited.add(x + (y + 1) * WIDTH);
            }
            if (y - 1 >= 0 && !visited.contains(x + (y - 1) * WIDTH)) {
                visited.add(x + (y - 1) * WIDTH);
            }
        }
    }

    repaint();
    }

    public void drawRectangle(int x0, int y0, int x1, int y1, Color color) {
        drawContLine(x0, y0, x1, y0, color); //Linea inferior
        drawContLine(x0, y0, x0, y1, color); //Linea izquierda
        drawContLine(x0, y1, x1, y1, color); //Linea superior
        drawContLine(x1, y0, x1, y1, color); //Linea derecha
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

    public void putPixel(int x, int y, Color c) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, c.getRGB());
        }
        repaint();
    }

    private void initComponents() {
        setTitle("Flood Fill");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!fill) {
                    x0 = e.getX();
                    y0 = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!fill) {
                    x1 = e.getX();
                    y1 = e.getY();
                    drawRectangle(x0, y0, x1, y1, c);
                } else {
                    floodFill(e.getX(), e.getY(), buffer.getRGB(e.getX(), e.getY()), fillColor);
                    repaint();
                }
            }

        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    if (!fill) {
                        fill = true;
                        System.out.println("Ready to fill");
                    } else {
                        fill = false;
                        System.out.println("Fill disabled");
                    }
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new FloodFill_18();
    }
}