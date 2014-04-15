package kth.csc.inda;

/**
 * Prova din nya grafklass genom att skriva ett program som bygger en graf med n noder och n slumpmässigt utplacerade kanter. 
 * Programmet ska med hjälp av djupetförstsökning beräkna antalet komponenter i grafen och storleken på den största komponentet.
 * Lämna in koden för detta program. (Du behöver naturligtvis inte lämna in de klasser och gränssnitt som redan finns färdiga.)
 * 
 * Prova hur snabbt ditt program blir för olika värden på n, n <= 5000, när man använder närhetsmatris respektive närhetslistor.
 * 
 * Ange antalet komponenter och den största komponentents storlek när n = 1000.
 * MatrixGraph took: 4 ms calculated: Number of components: 470 , Size of biggest component: 26
 * HashGraph took: 4 ms calculated: Number of components: 940 , Size of biggest component: 26
 * I det här fallet gick det lika snabbt(om man räknar i ms) och den största komponenten blev samma(i det här fallet)
 * 
 * 
 * Vilken datastruktur är bäst i det här fallet? Varför? Förklara genom att beräkna tidskomplexiteten för DFS med närhetsmatris samt för DFS med närhetslistor.
 * 
 * @author Stefan Nilsson //Implementation of dfs
 * @author Daniel Cserhalmi
 * @version 2014-04-15
 */

import java.util.Random;

public class RandomGraph {

	int verticies;
	Graph graph;
	Random rand;
	private static int BiggestComponent;
	private static int numberOfComponents;
	
	
	public RandomGraph(int n, Graph graph) {
		rand = new Random();
		verticies = n;
		this.graph = graph;
		
		makeRandomEdges();
		printComponents(graph);

	}
	private void makeRandomEdges(){
		for(int i = 1; i<verticies; i++){
			int a = rand.nextInt(verticies);
			int b = rand.nextInt(verticies);
			graph.add(a, b);
		}
	}
	
	/**
	* Prints the components of g to stdout. Each component is written on a
	* separate line.
	*/
	private static void printComponents(Graph g) {
		int n = g.numVertices();
		boolean[] visited = new boolean[n];
		for (int v = 0; v < n; v++) {
			if (!visited[v]) {
				dfs(g, v, visited, 0);
				numberOfComponents++;
			}
		}
	}
	
	/**
	 * Traverses the nodes of g that have not yet been visited. The nodes are
	 * visited in depth-first order starting at v. The act() method in the
	 * VertexAction object is called once for each node.
	 * 
	 * @param g
	 *            an undirected graph
	 * @param v
	 *            start vertex
	 * @param visited
	 *            visited[i] is true if node i has been visited
	 */
	private static void dfs(Graph g, int v, boolean[] visited, int size) {
		int Component = size;
		if (visited[v])
		return;	
		visited[v] = true;
		Component++;
		
		if(Component > BiggestComponent) BiggestComponent = Component;	
		for (VertexIterator it = g.neighbors(v); it.hasNext();) {
			dfs(g, it.next(), visited, Component);	
		}
	
	}
	
	
	public static void main(String[] arg) {
		
		HashGraph hgraph = new HashGraph(1000);
		MatrixGraph mGraph = new MatrixGraph(1000);
		
		long startTime = System.currentTimeMillis();
		@SuppressWarnings("unused")
		RandomGraph rGraph = new RandomGraph(1000, mGraph);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("MatrixGraph took: " + estimatedTime + " ms : " + "Number of components: " + RandomGraph.numberOfComponents + " \nSize of biggest component: " + RandomGraph.BiggestComponent);
		
		startTime = System.currentTimeMillis();
		@SuppressWarnings("unused")
		RandomGraph a = new RandomGraph(1000, hgraph);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("HashGraph took: " + estimatedTime + " ms : " + "Number of components: " + RandomGraph.numberOfComponents + " \nSize of biggest component: " + RandomGraph.BiggestComponent);
	
	}
}