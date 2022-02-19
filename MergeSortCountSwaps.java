import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class Result {

	/*
	 *Utilizaremos o merge sort para ordernar uma array
	 *contando a qtde de swaps q foi necessario
	 */

	public static long mergeSort(int[] arr, int n) {
		int swaps=0;
		if(n<2) {
			return swaps;
		}

		int mid = n/2;
		int[] leftSide = new int[mid];
		int[] rightSide = new int[n -mid];

		for (int i = 0; i<mid; i++) {
			leftSide[i] = arr[i];
		}
		for(int i= mid; i<arr.length; i++) {
			rightSide[i-mid] = arr[i];
		}

		swaps += mergeSort(leftSide, mid);
		swaps += mergeSort(rightSide, n-mid);

		swaps += mesclar(arr, leftSide, rightSide, mid, n-mid);
		return swaps;

	}

	private static long mesclar(int[] arr, int[] leftSide, int[] rightSide, int leftMaxIdx, int rightMaxIdx) {
		long swaps = 0;
		int i=0,j=0,k=0;

		while(i<leftMaxIdx && j<rightMaxIdx) {
			if(leftSide[i]<=rightSide[j]) {
				arr[k] = leftSide[i];
				k++;
				i++;
			}else {
				arr[k] = rightSide[j];
				k++;
				j++;
				swaps++;
			}
		}
		//Se um dos dois arrays (left ou right)
		//se esgotarem, entao o que restou no array que sobrou 
		//deve ser copiado diretamente para o array de resposta
		while(i<leftMaxIdx) {
			arr[k] = leftSide[i];
			k++;
			i++;

		}
		while(j<rightMaxIdx) {
			arr[k] = rightSide[j];
			k++;
			j++;		
		}

		return swaps;


	}

	public static long countInversionsold(List<Integer> arr) {
		long result = 0;
		long[] sort_arr = arr.stream().mapToLong(i->i).toArray();
		for(int i=0; i< sort_arr.length; i++){
			for(int j = i+1; j<sort_arr.length; j++){
				if(sort_arr[i]>sort_arr[j]){
					long temp = sort_arr[i];
					sort_arr[i] = sort_arr[j];
					sort_arr[j] =  temp;
					result++;
					//break;
				}
			}
		}
		return result;
	}

}

public class MergeSortCountSwaps {
	public static void main(String[] args) throws IOException {

		//int[] arr = {1,1,1,2,2};
		List<Integer> arr = Arrays.asList(2,1,3,1,2);
		long result = Result.mergeSort(arr.stream().mapToInt(i->i).toArray(), arr.size());
		System.out.println(result);


	}
}
