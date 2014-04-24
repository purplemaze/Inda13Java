import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
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
	private int FROM;
	private int TO;
	private Scanner sc;
	LinkedList<Integer> pathList;
	
	private Path(int FROM, int TO) throws FileNotFoundException {
		sc = new Scanner(new File("distances.txt"));
		this.FROM = FROM;
		this.TO = TO;
		pathList = new LinkedList<Integer>();
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
    			if(sc.hasNextLine())
    				sc.nextLine();
    			else
    				return;
    			}
    		else {
    			int i = sc.nextInt();
    			int j = sc.nextInt();
    			int d = sc.nextInt();
    			hgraph.addBi(i, j, d);
    		}          
    	}
    	sc.close();
    	System.out.println(hgraph);
    }
     
    /**
     * 
     * @param graph
     * @param vertex
     */
    public void bfs(Graph graph, boolean[] visited, int FROM, int TO) {
    	if(FROM == TO) {
    		System.out.println("You are allready at that node");
    	}
    	
    	pathList.add(FROM);
    	visited[FROM] = true;
    	
    	if(visited[FROM] == true) {
    		return;
    	}

    	for (VertexIterator it = graph.neighbors(FROM); it.hasNext();) {
    		int node = it.next();
    		bfs(graph, visited, node, TO);	
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

        Path path = new Path(FROM, TO);
        path.creatGraph();
        boolean[] visited = new boolean[path.hgraph.numVertices()];
        path.bfs(path.hgraph, visited, path.FROM, path.TO);
    }
    
}
