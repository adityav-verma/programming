//coderadi
//floyd warshal all pair shortest path for small graphs using adjancy matrix represntation
import java.io.*;
import java.util.*;

public class FloydWarshal {

	public static void	floydWarshal(int[][] adj, int n){	
		for (int k=0; k<n; k++) {
			for (int i=0; i<n; i++) {
				for (int j = 0; j< n; j++) {
					if(adj[i][k] + adj[k][j] < adj[i][j])	//if dist via intermediate is less then update
						adj[i][j] = adj[i][k] + adj[k][j];
				}
			}
		}
	}
	public static void main(String[] args)throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of nodes followed by number of edges");
		int n = sc.nextInt();
		int e = sc.nextInt();

		int[][] adj = new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j = 0; j<n; j++) {
				adj[i][j] = (Integer.MAX_VALUE)/2;
			}
		}
		while(e-- > 0){
			int a = sc.nextInt();
			int b = sc.nextInt();
			int cost = sc.nextInt();
			adj[a][b] = cost;
			adj[b][a] = cost;
		}
		System.out.println();
		floydWarshal(adj, n);
		for (int i=0; i<n; i++) {
			for (int j = 0; j<n; j++) {
				System.out.print(adj[i][j] + " ");
			}
			System.out.println();
		}
	}
}