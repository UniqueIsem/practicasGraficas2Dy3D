package practicasPrimerParcial;

public class TwoRandomNumbers {
static double num1, num2;
    public static void main(String[] args) {
        num1 = Math.random();
        num2 = Math.random();
        
        if (num1 > num2) {
            System.out.println("num1: " + num1 + "is bigger than num2: " + num2);
        } else {
            System.out.println("num2: " + num2 + "is bigger than num1: " + num1);
        }
    }    
}