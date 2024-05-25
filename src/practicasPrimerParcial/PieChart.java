package practicasPrimerParcial;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PieChart extends JPanel {

    private Map<String, Double> data;

    public PieChart(Map<String, Double> data) {
        this.data = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int minSize = Math.min(width, height);

        int cx = width / 2;
        int cy = height / 2;
        int radius = minSize / 2 - 20;

        // Calculate total value
        double total = 0.0;
        for (double value : data.values()) {
            total += value;
        }

        // Draw pie slices
        double startAngle = 0.0;
        int colorIndex = 0;
        int topMarginCircles = 10;
        int topMarginLabels = 20;
        
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            String label = entry.toString();
            double value = entry.getValue();
            double arcAngle = value / total * 360;
            //Pie Chart
            g2d.setColor(getColor(colorIndex++));
            g2d.fillArc(cx - radius, cy - radius, 2 * radius, 2 * radius, (int) startAngle, (int) arcAngle);
            //Labels
            g2d.fillOval(10, topMarginCircles, 10, 10);
            g2d.drawString(label, 20, topMarginLabels);
            //Increments margin top for each value
            topMarginCircles += 15; 
            topMarginLabels += 15;

            startAngle += arcAngle;
        }
    }

    private Color getColor(int index) {
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.MAGENTA, Color.CYAN};
        return colors[index % colors.length];
    }

    public static void main(String[] args) {
        // Parsing input arguments
        if (args.length % 2 != 0 || args.length == 0) {
            System.out.println("You have to give the values too");
            System.out.println("Example: java PieChart.java Java 50  python 50 ...");
            return;
        }

        Map<String, Double> data = new java.util.HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            String label = args[i];
            double value = Double.parseDouble(args[i + 1]);
            data.put(label, value);
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pie Chart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.getContentPane().add(new PieChart(data));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}