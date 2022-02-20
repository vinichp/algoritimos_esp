import java.io.IOException;
import java.util.HashMap;

class Result8 {

   /*
    * Conta quantas letras "perdidas" temos entre duas strings para que possaom formar um anagrama
    * 
    * Ex
    * 
    * abcd
    * abeef
    * 
    * para tornar um anagrama temos que retirar 5 letras
    * cd da primeira
    * e eef da segunda
    * 
    * usei um HashMap que a posicao(key) eh cada letra do alfabeto
    * e o valor é incrementado a cada repeticao e dps ele eh decrementado a cada match na segunda string
    * 
    *  o que sobrar neste hash (valores negativos ou positivos, devem ser modulados (multiplicando negativso por -1)
    *  e somados para dizer qtas letras devem ser removidas de ambos (inclusive as repetidas que tbm n deram match)
    *
    * 
    */

 public static int makeAnagram(String a, String b) {
          int result =0;
            HashMap<String, Long> map = new HashMap<String, Long>();
    
    for(char temp : a.toCharArray()){   
            String tempS = String.valueOf(temp);
            map.compute(tempS, (key,value)->{if(value==null) return 1l; else return value +1;});
        }
          
        for(char temp : b.toCharArray()){
            String tempS = String.valueOf(temp);
            map.compute(tempS, (key,value)->{if(value==null) return -1l; else return value -1;});
        }
        
      
        Long res = map.values().stream().mapToLong(i->{if(i<0) { return (i*-1);} else {return i;}}).sum();
     
            return res.intValue();

    }

}



public class Anagramas {
    public static void main(String[] args) throws IOException {

 //     String a = "abcdd";
//		String b= "abeeew";

//      String a = "abcd";
//		String b= "abe";
		String a= "fcrxzwscanmligyxyvym";
		String b= "jxwtrhvujlmrpdoqbisbwhmgpmeoke";
		int res = Result8.makeAnagram(a, b);
		System.out.println(res);
       
    }
}

