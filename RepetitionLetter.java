import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class Result9 {

	/*
	 * Verifica se todos as letras da String aparecem na mesma qtde
	 * Pode haver uma exceção com uma unidade a mais
	 * 
	 * 
	 */

	public static String isValid(String s) {       
		HashMap<Character, Integer> map = new HashMap<>();
		BiFunction<Character, Integer, Integer> remappingFunction =(key,value) -> value==null? 1: (value+1);
		for(Character c : s.toCharArray()){
			map.compute(c, remappingFunction);
		}

		Map<Integer, List<Integer>> res = map.values().stream().collect(Collectors.groupingBy(i->i));
		System.out.println(res);
		//Todas a letras tem a mesma quantidade, retornar "YES"
		if(res.size()==1) {
			return "YES";
		}
		//Temos 3 ou mais quantidade de letras diferentes, nao da pra arrumar
		//retornar "NO"
		if(res.size()>2) {
			return "NO";
		}
		//Temos 2 quantidades de letras
		//Verificar se somente uma letra possui quantidade = 1;
		if(res.size()==2) {
			Iterator<Entry<Integer, List<Integer>>> it = res.entrySet().iterator();
			Entry<Integer, List<Integer>> a =   it.next();
			Entry<Integer, List<Integer>> b =   it.next();
			if((a.getKey()==1 && a.getValue().size()==1) || (b.getKey()==1 && b.getValue().size()==1)) {
				//regra que pode remover 1 aplicada
				return "YES"; 
			}else {    	
				if(a.getValue().size()==1 && (a.getKey()-b.getKey()==1)) {
					//regra que pode remover 1 aplicada
					return "YES"; 
				}else {
					if(b.getValue().size()==1 && (b.getKey()-a.getKey()==1)) {
						//regra que pode remover 1 aplicada
						return "YES"; 
					}else {
						return "NO";
					}  			
				}
			}
		}
		return "YES";
	}
}

public class RepetitionLetter {
	public static void main(String[] args) throws IOException {

		String s ="ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd";
		String result = Result9.isValid(s);
		System.out.println(result); //YES


		s = "abcdefghhgfedecba"; //YES
		result = Result9.isValid(s);
		System.out.println(result);

		s = "aabbcd"; //NO
		result = Result9.isValid(s);
		System.out.println(result);

		s = "aabbccddeefghi"; //NO
		result = Result9.isValid(s);
		System.out.println(result);

		s = "abbac"; //YES
		result = Result9.isValid(s);
		System.out.println(result);


		s = "abbccc"; //NO
		result = Result9.isValid(s);
		System.out.println(result);




	}
}
