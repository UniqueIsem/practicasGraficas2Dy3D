package practicasTercerParcial;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Superficie_8 extends JFrame {
    private final int width = 800;
    private final int height = 600;
    private final Graficos graficos;
    private final int offsetX, offsetY;
    private double anguloX = 0;
    private double anguloY = 0;
    private double anguloZ = 0;
    private Timer timer;

    public Superficie_8() {
        offsetX = width / 2;
        offsetY = height / 2;
        graficos = new Graficos(width, height);

        initComponents();
        startAnimation();
    }

    public static void main(String[] args) {
        new Superficie_8();
    }

    private void dibujarSuperficie(Color color) {
        int xc = 0;
        int yc = 0;
        int zc = -300;
        int gridSize = 200;
        int gridStep = 20;

        for (int x = -gridSize; x <= gridSize; x += gridStep) {
            for (int y = -gridSize; y <= gridSize; y += gridStep) {
                int[] p1 = proyectar(rotar(new int[]{x, y, 0}, anguloX, anguloY, anguloZ), xc, yc, zc);
                int[] p2 = proyectar(rotar(new int[]{x + gridStep, y, 0}, anguloX, anguloY, anguloZ), xc, yc, zc);
                int[] p3 = proyectar(rotar(new int[]{x, y + gridStep, 0}, anguloX, anguloY, anguloZ), xc, yc, zc);
                int[] p4 = proyectar(rotar(new int[]{x + gridStep, y + gridStep, 0}, anguloX, anguloY, anguloZ), xc, yc, zc);
                
                graficos.drawLine(p1[0], p1[1], p2[0], p2[1], color);
                graficos.drawLine(p1[0], p1[1], p3[0], p3[1], color);
                graficos.drawLine(p2[0], p2[1], p4[0], p4[1], color);
                graficos.drawLine(p3[0], p3[1], p4[0], p4[1], color);
            }
        }
    }

    private int[] proyectar(int[] punto3D, int xc, int yc, int zc) {
        int x1 = punto3D[0];
        int y1 = punto3D[1];
        int z1 = punto3D[2];

        double u = (double) zc / (zc - z1);

        int x = (int) (xc + (x1 - xc) * u);
        int y = (int) (yc + (y1 - yc) * u);

        return new int[]{x + offsetX, y + offsetY};
    }

    private int[] rotar(int[] punto3D, double anguloX, double anguloY, double anguloZ) {
        double radX = Math.toRadians(anguloX);
        double radY = Math.toRadians(anguloY);
        double radZ = Math.toRadians(anguloZ);

        double cosX = Math.cos(radX);
        double sinX = Math.sin(radX);
        double cosY = Math.cos(radY);
        double sinY = Math.sin(radY);
        double cosZ = Math.cos(radZ);
        double sinZ = Math.sin(radZ);

        int x = punto3D[0];
        int y = punto3D[1];
        int z = punto3D[2];

        // Rotación en el eje X
        int newX = x;
        int newY = (int) (y * cosX - z * sinX);
        int newZ = (int) (y * sinX + z * cosX);

        // Rotación en el eje Y
        int tempX = (int) (newX * cosY + newZ * sinY);
        newZ = (int) (-newX * sinY + newZ * cosY);
        newX = tempX;

        // Rotación en el eje Z
        tempX = (int) (newX * cosZ - newY * sinZ);
        int tempY = (int) (newX * sinZ + newY * cosZ);
        newX = tempX;
        newY = tempY;

        return new int[]{newX, newY, newZ};
    }

    private void drawBackground() {
        graficos.fillRect(0, 0, width, height, Color.WHITE);
    }

    private void initComponents() {
        setTitle("Superficie 3D con Rotación");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        add(graficos);
    }

    private void startAnimation() {
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anguloX += 1;
                anguloY += 1;
                anguloZ += 1;
                graficos.clear();
                drawBackground();
                dibujarSuperficie(Color.BLACK);
            }
        });
        timer.start();
    }
}