package practicasPrimerParcial;

import java.util.Scanner;

public class Hex2IP {

    public void ip2Hex(String IP) {
        boolean isIP = IP.matches("^([0-9]{1,3}\\.){3}[0-9]{1,3}$");
        if (!isIP) {
            System.out.println("ERROR: " + IP + "is not IP\n");
        } else {
            String[] octetos = IP.split("\\.");
            StringBuilder hexBuilder = new StringBuilder();
            for (String octeto : octetos) {
                int decimal = Integer.parseInt(octeto);
                String hexadecimal = Integer.toHexString(decimal);
                hexBuilder.append(hexadecimal.length() == 1 ? "0" + hexadecimal : hexadecimal);
            }
            String hex = hexBuilder.toString().toUpperCase();
            System.out.println("Hexadecimal from " + IP + ": " + hex + "\n\n");
        }
    }

    public void hex2IP(String hex) {
        boolean isHexadecimal = hex.matches("[0-9A-Fa-f]+");
        if (!isHexadecimal) {
            System.out.println("ERROR: " + hex + " is not hex\n");
        } else {
            StringBuilder ipBuilder = new StringBuilder();
            for (int i = 0; i < hex.length(); i += 2) {
                String octetoHex = hex.substring(i, i + 2);
                int decimal = Integer.parseInt(octetoHex, 16);
                ipBuilder.append(decimal);
                if (i < hex.length() - 2) {
                    ipBuilder.append(".");
                }
            }
            String ip = ipBuilder.toString();
            System.out.println("IP from " + hex + ": " + ip + "\n\n");
        }
    }

    public static void main(String[] args) {
        Hex2IP obj = new Hex2IP();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose what you want to convert:");
            System.out.println("1: -hex");
            System.out.println("2: -ip");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    String text = "-hex";
                    System.out.println("Input the " + text + " please: ");
                    String hex = scanner.nextLine();
                    obj.hex2IP(hex);
                    break;
                case 2:
                    text = "-ip";
                    System.out.println("Input the " + text + " please: ");
                    String ip = scanner.nextLine();
                    obj.ip2Hex(ip);
                    break;
                default:
                    System.out.println("Type a correct choice...\n");
            }
        }
    }
}
