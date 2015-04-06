//coderadi
//Minimum spanning tree using greedy approach of prims algorithm
//http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
import java.io.*;
import java.util.*;

public class PrimsMST {
	
	public static void primsMST(ArrayList<ArrayList<Node>> adj, boolean[] visited, int[] parent, int[] val){
		PriorityQueue<Node> pq = new PriorityQueue<Node>(adj.size(), new Comparator<Node>(){
			public int compare(Node one, Node two){
				return Integer.compare(one.getCost(), two.getCost());
			}
		});
		val[0] = 0;
		pq.add(new Node(0, val[0]));
		while(pq.size() > 0){
			Node temp = pq.remove();
			int n = temp.getVal();
			visited[n] = true;

			Iterator<Node> it = adj.get(n).iterator();
			while(it.hasNext()){
				Node temp2 = it.next();
				int n2 = temp2.getVal();
				int c = temp2.getCost();
				if(!visited[n2] && c < val[n2]){
					parent[n2] = n;
					val[n2] = c;
					pq.add(new Node(n2, val[n2]));
				}
			}
		}
	}

	public static void main(String[] args)throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Number of node, number of edges, edges with cost");
		int n = sc.nextInt();
		int e = sc.nextInt();

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
		int[] parent = new int[n];		//will store the actual tree
		int[] val = new int[n];
		Arrays.fill(val, Integer.MAX_VALUE);

		primsMST(adj, visited, parent, val);

		for(int x: parent)
			System.out.print(x + " ");
		System.out.println();

		for(int x: val)
			System.out.print(x + " ");
		System.out.println();
	}
}

class Node{
	private int val, cost;

	Node(int val, int cost){
		this.val = val;
		this.cost = cost;
	}

	public int getVal(){
		return val;
	}
	public int getCost(){
		return cost;
	}
}
