package practicasPrimerParcial;

import javax.swing.*;
import java.awt.*;

public class EspiralArquimedes extends JFrame {

    public EspiralArquimedes() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Espiral de Arquímedes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width, screenSize.height);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int loops = 10; 
        double a = 5; 

        try{
            for (double theta = 0; theta < loops * 3 * Math.PI; theta += 0.01) {
                double r = a * theta;
                int x = centerX + (int) (r * Math.cos(theta));
                int y = (centerY) + (int) (r * Math.sin(theta));
                int x2 = (centerX) - (int) (r * Math.cos(theta));
                int y2 = (centerY) - (int) (r * Math.sin(theta));
                g.fillOval(x, y, 5, 5); 
                g.fillOval(x2, y2, 5, 5); 

                Thread.sleep(5); //this set the speed of the spiral
            }
        }catch (InterruptedException e) {e.printStackTrace();}
    }

    public static void main(String[] args) {
        new EspiralArquimedes();
    }
}
