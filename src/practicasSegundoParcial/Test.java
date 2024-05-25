package practicasSegundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Test extends JFrame {

    private BufferedImage buffer;
    private Graphics2D g2d;
    private int WIDTH = 600, HEIGHT = 600;
    private int x0, y0, x1, y1;
    private boolean isAreaSpecified = false;
    private Color c = Color.BLACK;
    private Color lineColor = Color.blue;
    private int clipX0, clipY0, clipX1, clipY1;

    public Test() {
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

    public void drawRectangle(int x0, int y0, int x1, int y1, Color color) {
        clipX0 = x0;
        clipY0 = y0;
        clipX1 = x1;
        clipY1 = y1;
        drawLine(x0, y0, x1, y0, color); //Linea inferior
        drawLine(x0, y0, x0, y1, color); //Linea izquierda
        drawLine(x0, y1, x1, y1, color); //Linea superior
        drawLine(x1, y0, x1, y1, color); //Linea derecha
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        // Algoritmo de recorte de línea Cohen-Sutherland
        int code0 = computeOutCode(x0, y0);
        int code1 = computeOutCode(x1, y1);

        // Verificar si los puntos finales de la línea están dentro del área de recorte
        if ((code0 | code1) == 0) {
            // Ambos puntos están dentro del área de recorte
            int dx = Math.abs(x1 - x0);
            int dy = Math.abs(y1 - y0);
            int sx = x0 < x1 ? 1 : -1;
            int sy = y0 < y1 ? 1 : -1;
            int err = dx - dy;

            while (true) {
                if (isInsideClip(x0, y0)) {
                    putPixel(x0, y0, color);
                }

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
    }

    public void putPixel(int x, int y, Color c) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, c.getRGB());
        }
    }

    private int computeOutCode(int x, int y) {
        int code = 0;

        if (x < clipX0) {
            code |= 1;
        } else if (x > clipX1) {
            code |= 2;
        }

        if (y < clipY0) {
            code |= 4;
        } else if (y > clipY1) {
            code |= 8;
        }

        return code;
    }

    private boolean isInsideClip(int x, int y) {
        return x >= clipX0 && x <= clipX1 && y >= clipY0 && y <= clipY1;
    }

    private void initComponents() {
        setTitle("Clipping");
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
                if (!isAreaSpecified) {
                    x1 = e.getX();
                    y1 = e.getY();
                    drawRectangle(x0, y0, x1, y1, c);
                    isAreaSpecified = true;
                    System.out.println("ÁREA DE RECORTE ESPECIFICADA.");
                } else {
                    x1 = e.getX();
                    y1 = e.getY();
                    drawLine(x0, y0, x1, y1, lineColor);
                    x0 = x1;
                    y0 = y1; // Establecer el nuevo punto inicial para permitir el dibujo continuo de líneas
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new RecorteLineas_19();
    }
}
