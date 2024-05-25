package practicasSegundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class RecorteCircunferencias_20 extends JFrame {

    private BufferedImage buffer;
    private Graphics2D g2d;
    private int WIDTH = 600, HEIGHT = 600;
    private int x0, y0, x1, y1;
    private boolean isAreaSpecified = false;
    private Color c = Color.BLACK;
    private Color lineColor = Color.BLUE;
    private int clipX0, clipY0, clipX1, clipY1;

    public RecorteCircunferencias_20() {
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
        g2d.setColor(color);
        g2d.fillRect(x0, y0, x1 - x0, y1 - y0);
        repaint();
    }

    public void drawEllipse(int x, int y, int radiusX, int radiusY, Color color) {
        if (x - radiusX > clipX1 || x + radiusX < clipX0 || y - radiusY > clipY1 || y + radiusY < clipY0) {
            // Si la elipse está completamente fuera del área de recorte, no se dibuja
            return;
        }

        // Ajustar los radios de la elipse si están parcialmente fuera del área de recorte
        int adjustedRadiusX = radiusX;
        int adjustedRadiusY = radiusY;
        if (x - radiusX < clipX0) {
            adjustedRadiusX -= clipX0 - (x - radiusX);
        }
        if (x + radiusX > clipX1) {
            adjustedRadiusX -= (x + radiusX) - clipX1;
        }
        if (y - radiusY < clipY0) {
            adjustedRadiusY -= clipY0 - (y - radiusY);
        }
        if (y + radiusY > clipY1) {
            adjustedRadiusY -= (y + radiusY) - clipY1;
        }

        // Dibujar la elipse con los radios ajustados
        g2d.setColor(color);
        g2d.fillOval(x - adjustedRadiusX, y - adjustedRadiusY, adjustedRadiusX * 2, adjustedRadiusY * 2);
        repaint();
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
                    int radiusX = Math.abs(x1 - x0) / 2;
                    int radiusY = Math.abs(y1 - y0) / 2;
                    int centerX = Math.min(x0, x1) + radiusX;
                    int centerY = Math.min(y0, y1) + radiusY;
                    drawEllipse(centerX, centerY, radiusX, radiusY, lineColor);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new RecorteCircunferencias_20();
    }
}