import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;


/**
 * ATENCAO!!
 * Temos dois metodos nesta classe abaixo
 * substrings possiveis 
 * Ex AAB
 * A,A,B (3)
 * AA, AB (2)
 * AAB (1) 
 * total = 6 substrings possiveis
 * 
 * findPasswordStrength (conta os caracteres DIFERENTES encontrados nas substrings possiveis
 * ex AAB = 2
 * 
 * findPasswordStrengthUnique (conta todos os caracteres que são UNICOS nas substrings possiveis
 * 
 * Ex AAB =1
 *
 */
class ResultCountingUniqueOrDiffChars {

	   public static long findPasswordStrength(String password) {
		   long res = 0;
		   //Stream.of(password.toCharArray()).forEach(System::println);
		   //password.chars().forEach(a-> {char b = (char) a; System.out.println(b);});
		   //System.out.println(password.chars().count()==password.chars().distinct().count());
		   
		   if(password.chars().count()==password.chars().distinct().count()) {
			   int b =password.length();
			   for(int a = 1; a<=password.length(); a++ ) {
				   res +=a*b;
				   b--;
			   }
			   System.out.print("sem repetir ");
			   return res;
		   }
		   
		   
		   
		   HashMap<Character, int[]> map = new HashMap<>();
		   int index = 0;
		   for(Character c : password.toCharArray()) {
			   if(map.containsKey(c)) {
				   
				   map.get(c)[1]= map.get(c)[1] + map.get(c)[0];
				   map.get(c)[0]=index+1;
				   
			   }else {
				   int[] data = new int[2];
				   data[0] = index+1;
				   data[1] = 0;
				   map.put(c, data);
			   }
			   for(int[] v : map.values()) {
				   res += v[0];
			   }
			   index++;
		   }
		   
		   return res;
		   
	   }
	   
	   public static long findPasswordStrengthUnique(String password) {
		   long res = 0;		 
		   
		   if(password.chars().count()==password.chars().distinct().count()) {
			   int b =password.length();
			   for(int a = 1; a<=password.length(); a++ ) {
				   res +=a*b;
				   b--;
			   }
			   //System.out.print("sem repetir ");
			   return res;
		   }
		   
		   
		   
		   HashMap<Character, int[]> map = new HashMap<>();
		   int index = 0;
		   for(Character c : password.toCharArray()) {
			   if(map.containsKey(c)) {
				   
				   map.get(c)[1]= map.get(c)[0];
				   map.get(c)[0]=index+1;
				   
			   }else {
				   int[] data = new int[2];
				   data[0] = index+1;
				   data[1] = 0;
				   map.put(c, data);
			   }
			   for(int[] v : map.values()) {
				   res += v[0];
				   res -= v[1];
			   }
			   index++;
		   }
		   
		   return res;
		   
	   }





}

public class CountUniqueAndDiffChars {
    public static void main(String[] args) throws IOException {
       
    
    	//Contando as letras Diferentes em cada substring
    	//Exemplo
    	//Input = "ABA"
    	//A,B,A = 3
    	//AB, BA = 4
    	//ABA = 2 
    	//OUTPUT = 9
        long result = ResultCountingUniqueOrDiffChars.findPasswordStrength("ABA"); //9
        System.out.println(result);
        assertEquals(result, 9);
        
    	//Contando as letras UNICAS em cada substring
    	//Exemplo
    	//Input = "ABA"
    	//A,B,A = 3
    	//AB, BA = 4
    	//ABA = 1 
    	//OUTPUT = 8
        result = ResultCountingUniqueOrDiffChars.findPasswordStrengthUnique("ABA"); //8 
        System.out.println(result);
        assertEquals(result, 8);
        
    	//Contando as letras Diferentes em cada substring
    	//Exemplo
    	//Input = "ABAA"
    	//A,B,A,A= 4
    	//AB, BA, AA =5
    	//ABA, BAA =4
    	//ABAA = 2 
    	//OUTPUT = 15
        result = ResultCountingUniqueOrDiffChars.findPasswordStrength("ABAA"); //15
        System.out.println(result);
        assertEquals(result, 15);
        
    	//Contando as letras UNICA em cada substring
    	//Exemplo
    	//Input = "ABAA"
    	//A,B,A,A = 4
    	//AB, BA, AA= 4
    	//ABA, BAA = 2
        //ABAA = 1 
    	//OUTPUT = 11
        result = ResultCountingUniqueOrDiffChars.findPasswordStrengthUnique("ABAA"); //11 
        System.out.println(result);
        assertEquals(result, 11);
        
        
    	result = ResultCountingUniqueOrDiffChars.findPasswordStrengthUnique("LEETCODE"); //92
        System.out.println(result);
        assertEquals(result, 92);
       
      
    }
}

