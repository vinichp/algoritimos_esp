import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result33 {

	/*
	 * Complete the 'bfs' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY.
	 * The function accepts following parameters:
	 *  1. INTEGER n
	 *  2. INTEGER m
	 *  3. 2D_INTEGER_ARRAY edges
	 *  4. INTEGER s
	 */

	static HashMap<Integer, List<Integer>> map = new HashMap<>();
//	static ArrayDeque<Integer> visited = new ArrayDeque<Integer>();
	static LinkedList<Integer> toVisit = new LinkedList<Integer>();


	public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
		map.clear();
		//visited.clear();
		toVisit.clear();
		loadGraph(edges);

		int cost = 6;
		int distance[] = new int[n+1];

		Arrays.fill(distance, n);
		distance[s] =0;
		toVisit.add(s);
		while(!toVisit.isEmpty()) {    		
			Integer cur = toVisit.remove();
			List<Integer> neighbors = map.get(cur);
			if(neighbors!=null) {
				for(int nei : neighbors) {
					if(distance[nei]>distance[cur]+1) {
						distance[nei] = distance[cur]+1;
						toVisit.add(nei);
					}
				}	
			}
		}

		List<Integer> resp = new ArrayList<Integer>();
		for(int i=1; i<distance.length; i++) {
			if(distance[i]!=0) {
				if(distance[i]==n) {
					resp.add(-1);	
				}else {
					resp.add(distance[i]*6);
				}
			}
		}
		return resp;

	}


	private static void loadGraph(List<List<Integer>> edges) {
		for(List<Integer> l : edges) {
			//adicionando no Hash os caminhos entre os nos (ida e volta)
			if(map.containsKey(l.get(0))) {
				map.get(l.get(0)).add(l.get(1));
			}else {
				ArrayList<Integer> arr = new ArrayList<>();
				arr.add(l.get(1));
				map.put(l.get(0), arr);
			}

			//volta
			if(map.containsKey(l.get(1))) {
				map.get(l.get(1)).add(l.get(0));
			}else {
				ArrayList<Integer> arr = new ArrayList<>();
				arr.add(l.get(0));
				map.put(l.get(1), arr);
			}



		}
	}

}

public class BFSBetter {
	public static void main(String[] args) throws IOException {
		
		long time = System.currentTimeMillis();
		final Path path = Paths.get("src/main/resources", "inputBFS.txt");
		Reader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
		BufferedReader bufferedReader = new BufferedReader(reader);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				int n = Integer.parseInt(firstMultipleInput[0]);

				int m = Integer.parseInt(firstMultipleInput[1]);

				List<List<Integer>> edges = new ArrayList<>();

				IntStream.range(0, m).forEach(i -> {
					try {
						edges.add(
								Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
								.map(Integer::parseInt)
								.collect(toList())
								);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				});

				int s = Integer.parseInt(bufferedReader.readLine().trim());

				List<Integer> result = Result33.bfs(n, m, edges, s);

				bufferedWriter.write(
						result.stream()
						.map(Object::toString)
						.collect(joining(" "))
						+ "\n"
						);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		 time = System.currentTimeMillis()-time;
		// time = time/1000;
		 System.out.println(time);
		bufferedReader.close();
		bufferedWriter.close();
		

	}
}
