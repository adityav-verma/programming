//coderadi
//breadth first search in graphs
import java.io.*;
import java.util.*;
import java.math.*;

public class BreadthFirstSearch{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	//performs bfs using adjajancy matrix representation for small graphs
	public static void bfsMatrix(int[][] adj, int start, boolean[] visited)throws IOException{
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.addLast(start);
		visited[start] = true;
		while(Q.size() > 0){
			int node = Q.removeFirst();
			bw.append(node + " ");
			for (int i=0; i<visited.length; i++) {
				if(adj[node][i] == 1 && visited[i] == false){
					Q.addLast(i);
					visited[i] = true;
				}
			}
		}
	}

	//performs bfs using adjancy list representation for large graphs
	public static void bfsList(ArrayList<ArrayList<Integer>> adj, int start, boolean[] visited)throws IOException{
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.addLast(start);
		visited[start] = true;
		while(Q.size() > 0){
			int node = Q.removeFirst();
			bw.append(node + " ");
			ArrayList<Integer> con = adj.get(node);
			Iterator<Integer> it = con.iterator();
			while(it.hasNext()){
				int temp = it.next();
				if(visited[temp] == false){
					Q.addLast(temp);
					visited[temp] = true;
				}
			}
		}
	}

	public static void main(String[] args)throws IOException {			//main method
		
		System.out.println("Enter number of nodes, edges and edges");
		int n = readInt();
		int e = readInt();
		/* code for adjancy matrix
		int[][] adj = new int[n][n];
		while(e-- > 0){
			int a = readInt();
			int b = readInt();
			adj[a][b] = 1;
		}
		boolean[] visited = new boolean[n];
		bfsMatrix(adj, 0, visited);
		*/

		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n);
		for (int i=0; i<n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		while(e-- > 0){
			int a = readInt();
			int b = readInt();
			adj.get(a).add(b);
		}
		boolean[] visited = new boolean[n];
		bfsList(adj, 0, visited);
		bw.flush();
		bw.close();
	}

 //////////////////////////////////////////////////////////////////////////////////////////////////////   
    private static int readInt()throws IOException{		//method for fast input in java
        int n=0;
        char ch = (char)br.read();
        int sign = 1;
	    while(ch < '0' || ch > '9'){		
		    if(ch == '-')
			    sign = -1;
		    ch = (char)br.read();
	    }
	    while(ch >= '0' && ch <= '9'){	
		    n = (n<<3) + (n<<1) + (int)ch - '0';
		    ch = (char)br.read();
	    }
	    n = n*sign;
	    return n;
    }

    private static long readLong()throws IOException{		//method for fast input in java
        long n=0;
        char ch = (char)br.read();
        int sign = 1;
	    while(ch < '0' || ch > '9'){		
		    if(ch == '-')
			    sign = -1;
		    ch = (char)br.read();
	    }
	    while(ch >= '0' && ch <= '9'){	
		    n = (n<<3) + (n<<1) + (int)ch - '0';
		    ch = (char)br.read();
	    }
	    n = n*sign;
	    return n;
    }
}