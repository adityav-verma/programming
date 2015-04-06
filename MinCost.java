//coderadi
//calculate the minimum cost from top left corner to bottom right corner of the matrix
//source is geeksforgeeks
import java.io.*;
import java.util.*;
import java.math.*;

public class MinCost{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


	public static void main(String[] args)throws IOException {			//main method
		
		System.out.println("Enter size of the cost matrix?, followed by the numbers");
		int m = readInt();
		int n = readInt();
		int[][] cost = new int[m][n];
		for (int i=0; i<m; i++) {
			for (int j = 0; j<n; j++) {
				cost[i][j] = readInt();
			}
		}

		int[][] minCost = new int[m][n];
		minCost[0][0] = cost[0][0];
		for (int i=1; i<m; i++) {
			minCost[i][0] = minCost[i-1][0] + cost[i][0]; 
		}
		for (int j = 1; j<n; j++) {
			minCost[0][j] = minCost[0][j-1] + cost[0][j];
		}
		for (int i=1; i<m; i++) {
			for (int j=1; j<n; j++) {
				minCost[i][j] = cost[i][j] + Math.min(minCost[i-1][j-1], Math.min(minCost[i-1][j], minCost[i][j-1]));		//dp relation
			}
		}
		System.out.println(minCost[m-1][n-1]);
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