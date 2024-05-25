package practicasTercerParcial;

import java.awt.Color;
import javax.swing.JFrame;

public class ProyeccionPerspectiva_2 {

    private final int width = 800;
    private final int height = 600;
    private final int size = 100;
    private int[][] vertices;
    private int[][] aristas;
    private Graficos graficos;
    private int offsetX, offsetY;
    private int posArista = 45;

    public ProyeccionPerspectiva_2() {
        vertices = new int[][] {
            {-size, -size, -size},
            {size, -size, -size},
            {size, size, -size},
            {-size, size, -size},
            {-size, -size, size},
            {size, -size, size},
            {size, size, size},
            {-size, size, size}
        };

        aristas = new int[][] {
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
        new ProyeccionPerspectiva_2();
    }

    public void dibujar(Color color) {
        // Parámetros de la cámara (posición del observador)
        int xc = 0;
        int yc = 0;
        int zc = -300; // Ajusta la posición en z para cambiar la perspectiva

        for (int[] arista : aristas) {
            int[] p1 = proyectar(rotar(vertices[arista[0]], posArista, posArista, posArista), xc, yc, zc);
            int[] p2 = proyectar(rotar(vertices[arista[1]], posArista, posArista, posArista), xc, yc, zc);
            graficos.drawLine(p1[0], p1[1], p2[0], p2[1], color);
        }
    }

    private int[] proyectar(int[] punto3D, int xc, int yc, int zc) {
        int x1 = punto3D[0];
        int y1 = punto3D[1];
        int z1 = punto3D[2];

        // Parámetro de proyección (distancia desde el observador)
        double u = (double) zc / (zc - z1);

        // Aplicar la proyección de perspectiva
        int x = (int) (xc + (x1 - xc) * u);
        int y = (int) (yc + (y1 - yc) * u);

        // Devolver el punto proyectado
        return new int[] {x + offsetX, y + offsetY};
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
        
        return new int[] {newX, newY, newZ};
    }

    private void initComponents() {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(graficos);
        frame.setVisible(true);

        dibujar(Color.BLACK);
    }
}
