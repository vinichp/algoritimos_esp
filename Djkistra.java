import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ResultDijkistra {

	/*
	 * Complete the 'shortestReach' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY.
	 * The function accepts following parameters:
	 *  1. INTEGER n
	 *  2. 2D_INTEGER_ARRAY edges
	 *  3. INTEGER s
	 */

	public static List<Integer> shortestReach(int n, List<List<Integer>> edges, int s) {
		List<Integer> result = new ArrayList<>();
		long start = System.currentTimeMillis();
		//Map with all edges mapped
		Map<Integer, Map<Integer, Integer>> edgesMap = new HashMap<>();
		for(List<Integer> edge : edges){        

			if(edgesMap.containsKey(edge.get(0))){            
				if(edgesMap.get(edge.get(0)).containsKey(edge.get(1)) && edgesMap.get(edge.get(0)).get(edge.get(1))<edge.get(2)) {
					continue;					
				}else {
					edgesMap.get(edge.get(0)).put(edge.get(1), edge.get(2));
				}
			}else{
				HashMap<Integer, Integer> e = new HashMap<>();
				e.put(edge.get(1), edge.get(2));    
				edgesMap.put(edge.get(0), e);

			}

			//Aresta invertida
			if(edgesMap.containsKey(edge.get(1))){
				if(edgesMap.get(edge.get(1)).containsKey(edge.get(0)) && edgesMap.get(edge.get(1)).get(edge.get(0))<edge.get(2)) {
					continue;					
				}else {
					edgesMap.get(edge.get(1)).put(edge.get(0), edge.get(2));
				}
			}else{
				HashMap<Integer, Integer> e = new HashMap<>();
				e.put(edge.get(0), edge.get(2));    
				edgesMap.put(edge.get(1), e);
			}
		}
		//Carregando todos os nos
		long node_time = System.currentTimeMillis()-start;		
		long proc_time = System.currentTimeMillis();
		//for(int t = 1; t<=n; t++){
		int[] dist = new int[n+1];
		
		boolean[] closed = new boolean[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		Arrays.fill(closed, false);
		dist[s] = 0;
		int loops = 1;
		while(true) {

			int small = Integer.MAX_VALUE;
			int small_index = 0;
			for(int i =1; i<=n; i++) {
				if(small> dist[i] && !closed[i]) {
					small = dist[i];
					small_index = i;
				}
			}
			if(edgesMap.get(small_index)!=null) {
				for(Map.Entry<Integer, Integer> e : edgesMap.get(small_index).entrySet()) {
					if(!closed[e.getKey()]) {
						if(e.getValue()+dist[small_index]< dist[e.getKey()]) {
							dist[e.getKey()] = e.getValue()+dist[small_index];
							
						}
					}									
				}
			}

			closed[small_index] = true;
			loops++;
			if(loops>n) {
				break;
			}
		}

		//Carregando todos os nos
		proc_time = System.currentTimeMillis()-proc_time;
		long write_time =  System.currentTimeMillis();
		for(int i =1; i<dist.length; i++) {
			if(dist[i]!=0) {
				if(dist[i]==Integer.MAX_VALUE) result.add(-1);					
				else result.add(dist[i]);
			}
		}
		
		write_time = System.currentTimeMillis()-write_time;
		System.out.println("node time=" +node_time);
		System.out.println("proc time=" +proc_time);
		System.out.println("write time=" +write_time);
		
		return result;
	}	
}

public class Djkistra {
	public static void main(String[] args) throws IOException {
		final long start_time =  System.currentTimeMillis();
		long total_time =  System.currentTimeMillis();
		BigDecimal read_time =  BigDecimal.valueOf(0);
//		final Path path = Paths.get("src/main/resources", "input_dijsktra_full.txt");
		final Path path = Paths.get("resources", "input_dijsktra_full.txt");
		Reader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
		BufferedReader bufferedReader = new BufferedReader(reader);
		//BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
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
				read_time.add(BigDecimal.valueOf(System.currentTimeMillis()-start_time));
				List<Integer> result = ResultDijkistra.shortestReach(n, edges, s);

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

		total_time = System.currentTimeMillis()-total_time;
		System.out.println("read time=" +read_time);
		System.out.println("total time=" +total_time);
		bufferedReader.close();
		bufferedWriter.close();
	}



}

