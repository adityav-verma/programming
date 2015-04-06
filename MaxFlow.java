//coderadi
//finding maximum flow in a graph
//source is topcoder tutorial

import java.io.*;
import java.util.*;

public class MaxFlow {

	//returns the path capacity if it finds an augmenting path else -1
	public static int findPath(int[][] capacity, int source, int sink){
		int n = capacity[0].length;			//intializing
		int[] from = new int[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(from, -1);
		int where = -1;
		ArrayDeque<Integer> q = new ArrayDeque<Integer>(n);

		q.addLast(source);				//bfs to find the path
		visited[source] = true;
		while(q.size() > 0){
			where = q.removeFirst();
			for (int next = 0; next < n; next++) {
				if(capacity[where][next] > 0 && !visited[next]){
					visited[next] = true;
					q.addLast(next);
					from[next] = where;
					if(next == sink)
						break;
				}
			}
		}

		//compute path capacity
		where = sink; int pathCapacity = Integer.MAX_VALUE;
		while(from[where] != -1){
			int prev = from[where];
			pathCapacity = Math.min(pathCapacity, capacity[prev][where]);
			where = prev;
		}
		//upadate residual network, if no path found then it will not enter the loop
		where = sink;
		while(from[where] != -1){
			int prev = from[where];
			capacity[prev][where] -= pathCapacity;
			capacity[where][prev] += pathCapacity;
			where = prev;
		}

		//if no path found capacity is infinite
		if(pathCapacity == Integer.MAX_VALUE)
			return -1;
		else
			return pathCapacity;	
	}

	public static int maxFlow(int[][] adj, int source, int sink){	//adds path capacity of all augmenting path to acheive maxFlow
		int maxF = 0;
		while(true){
			int pathCapacity = findPath(adj, source, sink);
			if(pathCapacity == -1)			//no path found
				return maxF;
			else
				maxF = maxF + pathCapacity;		//else add path capacity to maxFlow
		}
	}

	public static void main(String[] args)throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter nodes, edges and then edges with capacity");
		int n = sc.nextInt();
		int e = sc.nextInt();
		int[][] adj = new int[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				adj[i][j] = -1;
		while (e-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int cost = sc.nextInt();
			adj[a][b] = cost;
		}
		System.out.println("enter source and sink vertex");
		int source = sc.nextInt();
		int sink = sc.nextInt();
		int ans = maxFlow(adj, source, sink);
		System.out.println(ans);
	}
}