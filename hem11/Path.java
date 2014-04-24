import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import graph.*;
/**
 * java Path FROM TO
 * FROM och TO är två heltal som anger en startnod och en slutnod i en graf. 
 * Programmet ska skriva ut noderna på någon väg från FROM till TO om det finns en sådan väg i grafen, 
 * annars skriver programmet ut en tom rad. 
 * Vägen ska innehålla minimalt antal noder. 
 * Använd breddenförstsökning. 
 * @author Daniel
 * @version 2014-04-24
 *
 */
public class Path {
    private Graph hgraph;
	private Scanner sc;
	LinkedList<Integer> path;
	
	private Path(int FROM, int TO) throws FileNotFoundException {
		sc = new Scanner(new File("distances.txt"));
		path = new LinkedList<Integer>();
		creatGraph();
        boolean[] visited = new boolean[hgraph.numEdges()];
    	bfs(hgraph, visited, FROM, TO);
	}
	
    /**
     * Creates an HashGraph based on the supplied text document  when you create a Path object.
     */
    private void creatGraph() {
    	//scans for the size
    	while(!sc.hasNextInt()){
    		sc.nextLine();
    	}
    	int size = sc.nextInt();
    	hgraph = new HashGraph(size);
    	
    	//Scans for integers i, j, and d separated by spaces.
    	while(sc.hasNextLine()) {
    		if(!sc.hasNextInt()){
    			if(sc.hasNextLine()) sc.nextLine();
    		}else {
    			int i = sc.nextInt();
    			int j = sc.nextInt();
    			int d = sc.nextInt();
    			hgraph.addBi(i, j, d);
    		}          
    	}
    	sc.close();
    }

    
    /**
     * Breddenförstsökning är en algoritm som också besöker alla hörn i grafen g som ligger i samma komponent som hörnet v. 
     * Hörnen besöks i avståndsordning: först besöks hörnet v, sedan alla grannar till v, sedan deras grannar, etc. 
     * @param graph
     * @param visited
     * @param FROM
     * @param TO
     */
    private void bfs(Graph graph, boolean[] visited, int FROM, int TO) {
    	
    	Queue<Integer> q = new LinkedList<Integer>();
    	LinkedList<Integer> path = new LinkedList<Integer>();
    	int a = FROM;
    	q.add(a);
    	path.add(a);
    	visited[a] = true;
    	while(!q.isEmpty() && a != TO ){
    		a = q.remove();
    		VertexIterator it = graph.neighbors(a);
    		while(it.hasNext()) {
    			int b = it.next();
    			if(visited[b] != true) { 
    				visited[b] = true;
    				q.add(b);
    			}
    		}
    	}
    	//String
    	for(int n = 0; n < visited.length; n++) {
    		System.out.println(visited[n]);
    	}
    	
    }
    
    
    /**
     * Searches the input file (args[1]) for lines containing the
     * given pattern (args[0]) and prints these lines.
     * Leaves program with System.exit(n), where n is 0 if successful.
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.err.printf("Usage: java %s FROM TO%n", Path.class.getSimpleName());
            System.exit(1); // Unix error handling
        }
        Integer FROM = Integer.parseInt(args[0]);
        Integer TO = Integer.parseInt(args[1]);

        @SuppressWarnings("unused")
		Path path = new Path(FROM, TO);
    }
    
}
