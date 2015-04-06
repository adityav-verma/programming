//coderadi
//dynamic problem approach for longest common subsequence, source is geeksforgeeks
import java.io.*;
import java.util.*;
import java.math.*;

public class LCS{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


	
	
	public static void main(String[] args)throws IOException {			//main method
		
		System.out.println("Enter two strings for LCS");
		String s = br.readLine();
		String p = br.readLine();
		int sl = s.length();
		int pl = p.length();
		int[][] dp = new int[sl+1][pl+1];

		for(int i=0; i<sl+1; i++)
			for(int j = 1; j<pl+1; j++){

				if(i == 0 || j == 0)
					dp[i][j] = 0;

				else if(s.charAt(i-1) == p.charAt(j-1))
					dp[i][j] = dp[i-1][j-1] + 1;
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}

			System.out.println(dp[sl][pl]);

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