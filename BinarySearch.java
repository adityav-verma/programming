//coderadi
//program to implement binary search and lower and upper bound functions for binary search
import java.io.*;
import java.util.*;
import java.math.*;

public class BinarySearch{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	//returns the index if key is found, else returns -1
	public static int binarySearch(int[] ar, int key, int low, int high){
		if(low == high){
			if(ar[low] == key)
				return low;
			else
				return -1;
		}

		while(high - low > 1){
			int mid = low + (high - low)/2;
			if(ar[mid] == key)
				return mid;
			else if(ar[mid] > key){
				high = mid - 1;
			}
			else if(ar[mid] < key){
				low = mid + 1;
			}
		}

		if(ar[low] == key)
			return low;
		else if(ar[high] == key)
			return high;
		else
			return -1;
	}

	public static int lowerBound(int[] ar, int key, int low, int high){
		return -1;
	}

	public static int upperBound(int[] ar, int key, int low, int high){
		return -1;
	}

	public static void main(String[] args)throws IOException {			//main method
		
		int n = readInt();
		int[] ar = new int[n];
		for (int i=0; i<n; i++) {
			ar[i] = readInt();
		}
		
		Arrays.sort(ar, 0, n);
		int key = readInt();
		
		bw.append(binarySearch(ar, key, 0, ar.length - 1) + "\n");		

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