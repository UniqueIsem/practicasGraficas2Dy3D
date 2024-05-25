package practicasPrimerParcial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortNumberList {
    
    public static void sort(List<Integer> numList) {
        Collections.sort(numList);
    }
    
    public static void main (String[] args) {
        List<Integer> numList = new ArrayList<>();
        Random rand = new Random();
        
        for (int i = 0; i < 10; i++) {
            int randomNumber = rand.nextInt(100);
            numList.add(randomNumber);
        }
        System.out.println("List before sort: " + numList);
        sort(numList);
        System.out.println("List after sort: " + numList);
        numList.clear();
    }
}
