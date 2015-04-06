//coderadi
//code for longest increasing subsequence, dynamic programming approach and source is geeksforgeeks
import java.io.*;
import java.util.*;
import java.math.*;

public class LIS{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public static void main(String[] args)throws IOException {			//main method
		
		System.out.println("Enter number of elements, followed by the elements");
		int n = readInt();
		int[] arr = new int[n];
		for (int i=0; i<n; i++) {
			arr[i] = readInt();
		}
		int[] dp = new int[n];
		Arrays.fill(dp, 1);			//initialise all indices with default values

		for(int i=1; i<n; i++)		//use the basic approach of LIS 
			for(int j = 0; j<i; j++)
				if(arr[j] < arr[i] && dp[i] < dp[j] + 1)
					dp[i] = dp[j] + 1;

		int max = Integer.MIN_VALUE;
		for(int x:dp)
			if(max < x)max = x;
		System.out.println(max);

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