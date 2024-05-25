package practicasTercerParcial;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Rotacion_5 {

    private final int width = 800;
    private final int height = 600;
    private final int size = 100;
    private final int[][] vertices;
    private final int[][] aristas;
    private final Graficos graficos;
    private final int offsetX, offsetY;
    private final int posArista = 45;
    private int traslacionX = 0;
    private int traslacionY = 0;
    private int traslacionZ = 0;
    private double factorEscala = 1.0;
    private double anguloX = 0;
    private double anguloY = 0;
    private double anguloZ = 0;

    public Rotacion_5() {
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
    }

    public static void main(String[] args) {
        new Rotacion_5();
    }

    public void dibujarCubo(Color color, double factorEscala) {
        int xc = 0;
        int yc = 0;
        int zc = -300;

        for (int[] arista : aristas) {
            int[] p1 = proyectar(rotar(scale(translate(vertices[arista[0]], traslacionX, traslacionY, traslacionZ), factorEscala), anguloX, anguloY, anguloZ), xc, yc, zc);
            int[] p2 = proyectar(rotar(scale(translate(vertices[arista[1]], traslacionX, traslacionY, traslacionZ), factorEscala), anguloX, anguloY, anguloZ), xc, yc, zc);
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

    private int[] translate(int[] punto3D, int tx, int ty, int tz) {
        return new int[]{punto3D[0] + tx, punto3D[1] + ty, punto3D[2] + tz};
    }

    private int[] scale(int[] punto3D, double factor) {
        return new int[]{
            (int) (punto3D[0] * factor),
            (int) (punto3D[1] * factor),
            (int) (punto3D[2] * factor)
        };
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
        
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                drawBackground();
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_A -> {
                        traslacionX -= 10;
                        System.out.println("traslacionX " + traslacionX);
                    }
                    case KeyEvent.VK_D -> {
                        traslacionX += 10;
                        System.out.println("traslacionX " + traslacionX);
                    }
                    case KeyEvent.VK_W -> {
                        traslacionY -= 10;
                        System.out.println("traslacionY " + traslacionY);
                    }
                    case KeyEvent.VK_S -> {
                        traslacionY += 10;
                        System.out.println("traslacionY " + traslacionY);
                    }
                    case KeyEvent.VK_Q -> {
                        traslacionZ -= 10;
                        System.out.println("traslacionZ " + traslacionZ);
                    }
                    case KeyEvent.VK_E -> {
                        traslacionZ += 10;
                        System.out.println("traslacionZ " + traslacionZ);
                    }
                    case KeyEvent.VK_Z -> {
                        factorEscala *= 0.9;
                        System.out.println("factorEscala " + factorEscala);
                    }
                    case KeyEvent.VK_X -> {
                        factorEscala *= 1.1;
                        System.out.println("factorEscala " + factorEscala);
                    }
                    case KeyEvent.VK_I -> {
                        anguloX += 10;
                        System.out.println("anguloX " + anguloX);
                    }
                    case KeyEvent.VK_K -> {
                        anguloX -= 10;
                        System.out.println("anguloX " + anguloX);
                    }
                    case KeyEvent.VK_J -> {
                        anguloY -= 10;
                        System.out.println("anguloY " + anguloY);
                    }
                    case KeyEvent.VK_L -> {
                        anguloY += 10;
                        System.out.println("anguloY " + anguloY);
                    }
                    case KeyEvent.VK_U -> {
                        anguloZ -= 10;
                        System.out.println("anguloZ " + anguloZ);
                    }
                    case KeyEvent.VK_O -> {
                        anguloZ += 10;
                        System.out.println("anguloZ " + anguloZ);
                    }
                }
                graficos.clear();
                dibujarCubo(Color.BLACK, factorEscala);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        dibujarCubo(Color.BLACK, factorEscala);
    }
}