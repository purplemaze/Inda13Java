package kth.csc.inda;

/**
 * Prova din nya grafklass genom att skriva ett program som bygger en graf med n noder och n slumpm�ssigt utplacerade kanter. 
 * Programmet ska med hj�lp av djupetf�rsts�kning ber�kna antalet komponenter i grafen och storleken p� den st�rsta komponentet.
 * L�mna in koden f�r detta program. (Du beh�ver naturligtvis inte l�mna in de klasser och gr�nssnitt som redan finns f�rdiga.)
 * 
 * Prova hur snabbt ditt program blir f�r olika v�rden p� n, n <= 5000, n�r man anv�nder n�rhetsmatris respektive n�rhetslistor.
 * 
 * Ange antalet komponenter och den st�rsta komponentents storlek n�r n = 1000.
 * MatrixGraph took: 4 ms calculated: Number of components: 470 , Size of biggest component: 26
 * HashGraph took: 4 ms calculated: Number of components: 940 , Size of biggest component: 26
 * I det h�r fallet gick det lika snabbt(om man r�knar i ms) och den st�rsta komponenten blev samma(i det h�r fallet)
 * 
 * 
 * Vilken datastruktur �r b�st i det h�r fallet? Varf�r? F�rklara genom att ber�kna tidskomplexiteten f�r DFS med n�rhetsmatris samt f�r DFS med n�rhetslistor.
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