package practicasPrimerParcial;

import java.util.Scanner;

public class SubstringGenerator {

    public static void printSubstring(String palabra) {
        int inicio = -1;
        int fin = palabra.length();
        
        for (int i = palabra.length(); i > 0; i--) {
            System.out.print(palabra.substring(0, i));
            if (i > 1) {
                System.out.println("");
            }
        }
        
        for (int j = fin; j > inicio; j--) {
            System.out.println(palabra.substring(j));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una palabra: ");
        String palabra = scanner.nextLine();

        printSubstring(palabra);
    }

}
