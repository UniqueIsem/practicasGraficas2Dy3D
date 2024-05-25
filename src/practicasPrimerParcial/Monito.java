package practicasPrimerParcial;

import java.awt.Graphics;
import javax.swing.JFrame;

public class Monito extends JFrame {

    public Monito() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Monito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(200, 300);
        setLocationRelativeTo(null);
        show();
    }
    
    public void paint (Graphics g) {
        g.drawString("Demo de graficos", 10, 50);
        //Cara
        g.drawArc(50, 60, 50, 50 , 0, 360);
        g.drawArc(60, 70, 30, 30, 180, 180);
        g.fillOval(65, 75, 5, 5);
        g.fillOval(80, 75, 5, 5);
        //Cuerpo
        g.drawLine(75, 110, 75, 200);
        //Brazos
        g.drawLine(75, 120, 45, 160);
        g.drawLine(75, 120, 105, 160);
        //Piernas
        g.drawLine(75, 200, 45, 240);
        g.drawLine(75, 200, 105, 240);
    }

    public static void main(String[] args) {
        new Monito();
    }
}
