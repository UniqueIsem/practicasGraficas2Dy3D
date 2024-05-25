package practicasSegundoParcial;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class LineaRectaBresenham_04 extends JFrame {

    private BufferedImage buffer;
    private Graphics2D px;
    private int x0, y0, x1, y1;
    private Color c = Color.RED;

    public LineaRectaBresenham_04() {
        initComponents();
        
        buffer = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        px = buffer.createGraphics();
        px.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        px.setColor(Color.WHITE);
        px.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, null);
    }
    
    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        int incyi, incxi, incyr, incxr, aux, avr, av, avi;
        int dx = x1 - x0;
        int dy = y1 - y0;

        if (dy >= 0) {
            incyi = 1;
        } else {
            dy = -dy;
            incyi = -1;
        }
        
        if(dx >= 0){
            incxi = 1;
        } else {
            dx = -dx;
            incxi = -1;
        }
        
        if(dx >= dy){
            incyr = 0;
            incxr = incxi;
        } else {
            incxr = 0;
            incyr = incyi;
            aux = dx;
            dx = dy;
            dy = aux;
        }
        
        int x = x0;
        int y = y0;
        avr = 2 * dy;
        av = avr - dx;
        avi = av - dx;
        do {
            putPixel(x, y, color);
            if(av >= 0){
                x = x + incxi;
                y = y + incyi;
                av = av + avi;
            } else {
                x = x + incxr;
                y = y + incyr;
                av = av + avr;
            }
        }while(x != x1);

        repaint();
    }

    public void putPixel(int x, int y, Color c) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, c.getRGB());
        }
    }

    public static void main(String[] args) {
        new LineaRectaBresenham_04();
    }

    private void initComponents() {
        setTitle("Linea Recta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
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
                drawLine(x0, y0, x1, y1, c);
            }
        });
        setVisible(true);
    }
}
