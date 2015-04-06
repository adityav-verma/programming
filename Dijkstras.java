//coderadi
import java.io.*;
import java.util.*;

public class Dijkstras {

	//dist array will contain the min distance from source to all vertex
	public static void dijkstras(ArrayList<ArrayList<Node>> adj, int source, int[] dist, boolean[] visited){
		PriorityQueue<Node> pq = new PriorityQueue<Node>(adj.size(), new Comparator<Node>(){
			public int compare(Node one, Node two){
				return Integer.compare(one.getDist(), two.getDist());
			}
		});
		//intializing default distance form source to all vertex as infinite
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;

		pq.add(new Node(source, dist[source]));

		while(pq.size() > 0){
			Node temp = pq.remove();
			int n = temp.getVal();

			if(!visited[n]){		//if not visited normalize distance of all nodes neighbour to it
				Iterator<Node> it = adj.get(n).iterator();
				visited[n] = true;
				while(it.hasNext()){
					Node temp2 = it.next();
					int n2 = temp2.getVal();
					int n2Dist = temp2.getDist();

					if((dist[n] + n2Dist) < dist[n2]){ 	//if distance via this node is less than original
						dist[n2] = dist[n] + n2Dist;
						pq.add(new Node(n2, dist[n2]));
					}
				}
			}
		}
	}

	public static void main(String[] args)throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter number of nodes, edges and edges");
		int n = sc.nextInt();
		int e = sc.nextInt();
		/* code for adjancy matrix
		int[][] adj = new int[n][n];
		while(e-- > 0){
			int a = readInt();
			int b = readInt();
			adj[a][b] = 1;
		}
		boolean[] visited = new boolean[n];
		dfsMatrix(adj, 0, visited);
		*/

		ArrayList<ArrayList<Node>> adj = new ArrayList<ArrayList<Node>>(n);
		for (int i=0; i<n; i++) {
			adj.add(new ArrayList<Node>());
		}
		while(e-- > 0){
			int a = sc.nextInt();
			int b = sc.nextInt();
			int cost = sc.nextInt();
			adj.get(a).add(new Node(b, cost));
			adj.get(b).add(new Node(a, cost));
		}
		boolean[] visited = new boolean[n];
		int[] dist = new int[n];
		dijkstras(adj, 0, dist, visited);

		for(int x:dist)
			System.out.print(x + " ");
		System.out.println();
	}
}


class Node {
	private int val;
	private int dist;

	Node(int v, int d){
		val = v;
		dist = d;
	}

	public int getDist(){
		return dist;
	}
	public int getVal(){
		return val;
	}
}
