package practicasTercerParcial;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class RotacionAutomatica_6 {

    private final int width = 800;
    private final int height = 600;
    private final int size = 100;
    private final int[][] vertices;
    private final int[][] aristas;
    private final Graficos graficos;
    private final int offsetX, offsetY;
    private final int posArista = 45;
    private double anguloX = 0;
    private double anguloY = 0;
    private double anguloZ = 0;
    private Timer timer;

    public RotacionAutomatica_6() {
        vertices = new int[][]{
            {-size, -size, -size},
            {size, -size, -size},
            {size, size, -size},
            {-size, size, -size},
            {-size, -size, size},
            {size, -size, size},
            {size, size, size},
            {-size, size, size}
        };

        aristas = new int[][]{
            {0, 1}, {1, 2}, {2, 3}, {3, 0},
            {4, 5}, {5, 6}, {6, 7}, {7, 4},
            {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };

        offsetX = width / 2;
        offsetY = height / 2;
        graficos = new Graficos(width, height);

        initComponents();
        startAnimation();
    }

    public static void main(String[] args) {
        new RotacionAutomatica_6();
    }

    public void dibujarCubo(Color color) {
        int xc = 0;
        int yc = 0;
        int zc = -300;

        for (int[] arista : aristas) {
            int[] p1 = proyectar(rotar(vertices[arista[0]], anguloX, anguloY, anguloZ), xc, yc, zc);
            int[] p2 = proyectar(rotar(vertices[arista[1]], anguloX, anguloY, anguloZ), xc, yc, zc);
            graficos.drawLine(p1[0], p1[1], p2[0], p2[1], color);
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
        JFrame frame = new JFrame();
        frame.setTitle("Rotacion");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(graficos);
        frame.setVisible(true);

        drawBackground();
        dibujarCubo(Color.BLACK);
    }

    private void startAnimation() {
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anguloX += 2;
                anguloY += 2;
                anguloZ += 2;
                graficos.clear();
                drawBackground();
                dibujarCubo(Color.BLACK);
            }
        });
        timer.start();
    }
}