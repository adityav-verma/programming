//coderadi
//classic 0-1 knapsack problem, either nth item is included or not included method is used

import java.io.*;
import java.util.*;

public class KnapSack {			//returns the maximum value that can be attained within W weight
	public static int knapsack(int W, int[] val, int[] weight){
		int[][] knap = new int[val.length + 1][W + 1];
		knap[0][0] = 0;
		for (int i=0; i<val.length+1; i++) {
			for (int j = 0; j<W+1; j++) {
				if(i == 0 || j == 0)
					knap[i][j] = 0;

				else if(weight[i-1] <= j){
					knap[i][j] = Math.max(knap[i-1][j], knap[i-1][j-weight[i-1]] + val[i-1]);
				}
				else if(weight[i-1] > j)	//we cannot include that item so
					knap[i][j] = knap[i-1][j];
			}
		}
		return knap[val.length][W];
	}

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of elements, vals, weights, Max Weight");
		int n = sc.nextInt();
		int[] val = new int[n];
		int[] weight = new int[n];
		int W = -1;

		for (int i=0; i<n; i++) {
			val[i] = sc.nextInt();
		}
		for (int i=0; i<n; i++) {
			weight[i] = sc.nextInt();
		}
		W = sc.nextInt();
		System.out.println(knapsack(W, val, weight));
	}
}