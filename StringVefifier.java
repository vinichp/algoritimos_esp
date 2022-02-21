import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringVefifier {

	public static long sumUsingRecursion(int n) {
		if (n <= 1) {
			return n;
		}
		return n + sumUsingRecursion(n - 1);
	}

	private static long verifyStringContinuos(int index, char c, char[]arr){
		if(arr[index]==c){
			if(index+1<arr.length){
				return 1+verifyStringContinuos(++index, c, arr);
			}else{
				return 1;
			}
		}else{
			return 0;
		}  
	}
	private static long verifyStringSplitted(char c, int start, int end, char[] arr){                   
		if(arr[start]== arr[end] && arr[start]== c){
			if((start-1>=0 && end+1<arr.length)){
				return 1+verifyStringSplitted(c, --start, ++end,arr);
			}else return 1;
		}else return 0;                
	}

	// Complete the substrCount function below.
	static long substrCount(int n, String s) {
		long count=0;
		count += s.length();

		char[] arr= s.toCharArray();
		for(int i=1; i<s.length(); i++){
			if(arr[i-1]==arr[i]){                
				Long res =verifyStringContinuos(i,arr[i-1],arr);
				count += sumUsingRecursion(res.intValue());
				i+=res;
			}
		}
		for(int i=1; i<s.length()-1; i++){
			if(arr[i-1]!=arr[i]){  
				count+=verifyStringSplitted(arr[i-1], i-1,i+1,arr);
			}
		}
		return count;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {


		final Path path = Paths.get("src/main/resources", "StringVerifier.txt");
		
		Reader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
		BufferedReader bufferedReader = new BufferedReader(reader);

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		String s1 = bufferedReader.readLine().toString();

		List<List<Integer>> queries = new ArrayList<>();

		System.out.println("vai carregar do arquivo");


		System.out.println("carregou do arquivo");


		String s= "aaaa";//10 OK
		//String s= "abcbaba"; //10
		//String s = "asasd"; //7
		long result = substrCount(0, s1);

		System.out.println(String.valueOf(result));

	}
}
