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

class Result51 {

    /*
     * Complete the 'kruskals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
    	//TreeMap<Integer, List<int[]>> map = new TreeMap<>();
    	//Set<int[]> set = new HashSet<>();
    	//Set<Integer> set = new HashSet<>();
    	
    	HashMap<Integer, List<int[]>> map = new HashMap<>();
    	
    	Iterator<Integer> itFrom = gFrom.iterator();
    	Iterator<Integer> itTo = gTo.iterator();
    	Iterator<Integer> itWeight = gWeight.iterator();
    	
    	while(itFrom.hasNext()) {
    		int[] i = new int[2];
    		i[0] = itTo.next();
    		i[1] = itWeight.next();
    		int key = itFrom.next();
    		
    		if(map.containsKey(key)) {
    			map.get(key).add(i);
    		}else {
    			ArrayList<int[]> arr = new ArrayList<int[]>();
    			arr.add(i);
    			map.put(key, arr);
    		}
    		
    		
    		
    		int[] j = new int[2];
    		j[0] = key;
    		j[1] = i[1];
    		if(map.containsKey(i[0])) {    			
    			map.get(i[0]).add(j);
    		}else {
    			ArrayList<int[]> arr = new ArrayList<int[]>();
    			arr.add(j);
    			map.put(i[0], arr);
    		}
    	
    	}
    	int res =0;
    	boolean[] visited = new boolean[gNodes+1];
    	Arrays.fill(visited, false);
    	Integer i=1;
    	int r = shortPath(map, i, visited);
    		
    	
    	
    	
    	
    	return r; 
    		
    	
    }

	private static int shortPath(HashMap<Integer, List<int[]>> map, Integer i, boolean[] visited) {
		for(int[] adj : map.get(i)) {
			if(!visited[adj[0]]) {
				visited[adj[0]]=true;
				return adj[1] + shortPath(map, adj[0], visited);
			}
		}
		return 0;
	}

}

public class DFSKruskalRecursive {
    public static void main(String[] args) throws IOException {
       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });*/

       /* int gNodes =5;
		List<Integer> gFrom= Arrays.asList(1,1,1,1,2,3,4);
		List<Integer> gTo= Arrays.asList(2,3,4,5,3,4,5);
		List<Integer> gWeight= Arrays.asList(20,50,70,90,30,40,60);//150 ok*/
		
		int gNodes =4;
		List<Integer> gFrom= Arrays.asList(1,3,4,1,3);
		List<Integer> gTo= Arrays.asList(2,2,3,4,1);
		List<Integer> gWeight= Arrays.asList(1,150,99,100,200);//150 ok
		int res = Result51.kruskals(gNodes, gFrom, gTo, gWeight);

		System.out.println(res);
        // Write your code here.

      //  bufferedReader.close();
      //  bufferedWriter.close();
    }
}
