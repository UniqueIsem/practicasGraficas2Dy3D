package practicasPrimerParcial;

import javax.swing.JFrame;

public class JFrameExample extends JFrame{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Practica 07 Ventana 'JFrame'");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); //we can use this extending JFrame to set location at the center
        frame.setVisible(true);
    }
}
