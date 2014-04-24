import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import graph.*;
/**
 * java Path FROM TO
 * FROM och TO �r tv� heltal som anger en startnod och en slutnod i en graf. 
 * Programmet ska skriva ut noderna p� n�gon v�g fr�n FROM till TO om det finns en s�dan v�g i grafen, 
 * annars skriver programmet ut en tom rad. 
 * V�gen ska inneh�lla minimalt antal noder. 
 * Anv�nd breddenf�rsts�kning. 
 * @author Daniel
 * @version 2014-04-24
 *
 */
public class Path {
    private Graph hgraph;
	private String FROM;
	private String TO;
	private Scanner sc;
	
	private Path(String FROM, String TO) throws FileNotFoundException {
		sc = new Scanner(new File("distances.txt"));
		this.FROM = FROM;
		this.TO = TO;
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
        final String FROM = args[0];
        final String TO = args[1];

        Path path = new Path(FROM, TO);
        path.creatGraph();
    }
    
    /**
     * Creates an HashGraph based on the supplied text document  when you create a Path object.
     */
    private void creatGraph() {
    	while(!sc.hasNextInt()){
    		sc.nextLine();
    	}
    	int size = sc.nextInt();
    	hgraph = new HashGraph(size);
    	System.out.println(hgraph.numVertices());
    	while(sc.hasNextLine()) {
    		if(!sc.hasNextInt()){
    			if(sc.hasNextLine())
    				sc.nextLine();
    			else
    				return;
    			}
    		else {
    			int a = sc.nextInt();
    			int b = sc.nextInt();
    			int c = sc.nextInt();
    			hgraph.addBi(a, b, c);
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
    public static void bfs(Graph graph, int vertex) {
    	/*    
    	Q = new empty queue
    	Mark v as visited.
    	Q.enqueue(v) // Add v to end of queue
    	while Q is not empty
    		a = Q.dequeue() // Remove a from top of queue.
    	    // Here you may perform some operation on a.
    	    for all unvisited neighbors x of a
    	    	Mark x as visited.
    	         Q.enqueue(x)
    	  */
    }
}
