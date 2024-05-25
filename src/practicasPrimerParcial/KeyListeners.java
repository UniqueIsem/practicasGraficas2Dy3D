package practicasPrimerParcial;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class KeyListeners extends JFrame implements KeyListener{
    
    public KeyListeners() {
        setTitle("Key Listener");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 400);
        setLocationRelativeTo(null);
        addKeyListener(this);
        show();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key typed: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key released: " + KeyEvent.getKeyText(e.getKeyCode()));
    }
    
    public static void main(String[] args){
        new KeyListeners();
    }
}
