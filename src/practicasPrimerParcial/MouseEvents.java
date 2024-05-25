package practicasPrimerParcial;

import javax.swing.*;
import java.awt.event.*;

public class MouseEvents extends JFrame implements MouseListener, MouseMotionListener{
    
    public MouseEvents() {
        setTitle("Mouse Listener");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 400);
        setLocationRelativeTo(null);
        // Agregar los listeners de mouse
        addMouseListener(this);
        addMouseMotionListener(this);
        show();
    }
    
    // Implementaciones de los métodos de los listeners de mouse
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
    }
    
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed at (" + e.getX() + ", " + e.getY() + ")");
    }
    
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released at (" + e.getX() + ", " + e.getY() + ")");
    }
    
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered at (" + e.getX() + ", " + e.getY() + ")");
    }
    
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited at (" + e.getX() + ", " + e.getY() + ")");
    }
    
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged at (" + e.getX() + ", " + e.getY() + ")");
    }
    
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse moved at (" + e.getX() + ", " + e.getY() + ")");
    }
    
    public static void main (String[] args) {
        new MouseEvents();
    }
}
