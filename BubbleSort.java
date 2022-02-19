import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'countSwaps' function below.
     *
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static void countSwaps(List<Integer> a) {
    int[] arr = a.stream().mapToInt(b->b).toArray();
    int limite = arr.length-1;
    int swap_count = 0;
    for(;;){
    boolean change = false;
    for(int i=0; i<limite; i++){
        
        if(arr[i]>arr[i+1]){
            int temp = arr[i];
            
            arr[i] = arr[i+1];
            arr[i+1] = temp;
            
            swap_count++;
            change = true;
        }
    }
    limite--;
    if(!change) break;
    }
    
    
    System.out.println("Array is sorted in "+swap_count+" swaps.");
    System.out.println("First Element: "+arr[0]);
    System.out.println("Last Element: "+arr[arr.length-1]);
     }

}

public class BubbleSort {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.countSwaps(a);

        bufferedReader.close();
    }
}
