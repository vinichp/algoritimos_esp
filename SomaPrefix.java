import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

 class Querie{
     int a,b,k;
 }
 
 class Result2 {

    /*
     * Algoritimo de soma prefixo
     * 
     * e capaz de computar milhares de adds e removes em um array com perfomance
     * 
     *  Exemplo
     *  
     *  Array original
     *  
     *  1,2,3,4,5,6
     *  
     *  Operacoes a serem executadas (ignore o indice 0)
     *  add 10 entre os nros 2 e 4 (inclusive)
     *  add 1 entre os nros 2 e 6 (inclusive)
     *  diminui 2 entre os nros 1 e 2 (inclusive)
     *  
     *  o resultado final deve ser
     *  -1, 11, 14, 15, 6, 7
     *  
     *  POREM, é possivel fazer este calculo com mto mais perfomance c
     *  com o algoritimo de soma prefixo
     *  
     *  assim
     *  1 - criar um array artifical (ignore a pos 0)
     *  
     *  POS = 1 2 3 4 5 6 7 
     *  VAL = 0 0 0 0 0 0 0
     *  
     *  2 - Acresentar ou diminuir nos valores
     *
     *  POS = 1   2 3 4   5 6 7 8
     *  VAL = 0 +10 0 0 -10 0 0 0
     *
     *  POS = 1   2  3  4   5 6 7 8
     *  VAL = 0 +11 +1 -1 -10 0 0 0
     *  
     *  POS =  1   2  3  4   5 6 7 8
     *  VAL = -2 +11 -1 -1 -10 0 0 0
     *  
     *  Soma final
     *  -1, 12, 14, 16, 11, 17, 25
     */
	 
	 public static long arrayManipulation(int n, List<List<Integer>> queries) {
		 long result = Long.MIN_VALUE;
		 
		 int a[] = new int[n+2];
		   for(List<Integer> op : queries){
			   a[op.get(0)] =+op.get(2);
			   a[op.get(1)+1] =-op.get(2);
		   }
		   long sum = 0;
		  
		 for(int i: a) {
			sum =+ i;
			if(sum > result) result = sum;
		 }
		 
		 return result;
		 
	 }




}

public class SomaPrefix {
    public static void main(String[] args) throws IOException {
    	
    	  final Path path = Paths.get("src/main/resources", "input13.txt");
          Reader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
          BufferedReader bufferedReader = new BufferedReader(reader);

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        System.out.println("vai carregar do arquivo");
        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        System.out.println("carregou do arquivo");
        long result = Result2.arrayManipulation(n, queries);


        
        System.out.println(result);

        bufferedReader.close();
    }
}
