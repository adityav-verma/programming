//coderadi
//basic segment tree implementation, Range Minimum Query
import java.io.*;
import java.util.*;
import java.math.*;

class Node{					
	private int x;

	Node(int x){
		this.x = x;
	}
	public void setX(int x){
		this.x = x;
	}

	public int getX(){
		return x;
	}
}

class SegmentTree{
	private Node tree[];
	private int ROOT, STARTINDEX, ENDINDEX;

	/********************* Create Segment Tree *******************/
	SegmentTree(int ar[]){							//constructer for segment tree
		int len = ar.length;
		int height = (int) Math.ceil(Math.log(len)/Math.log(2));			//calculating size of tree, can be calculated using pen n paper 
		int maxSize = 2 * (int) Math.pow(2, height) - 1;
		tree = new Node[maxSize];
		ROOT = 0;
		STARTINDEX = 0;
		ENDINDEX = len - 1;

		createST(ar, STARTINDEX, ENDINDEX, ROOT);
	}

	private Node makeNode(Node left, Node right){				//creates the parent node of the two children
		int val = Math.min(left.getX(), right.getX());
		Node temp = new Node(val);
		return temp;
	}

	private Node createST(int[] ar, int si, int ei, int c){			//c stands for current position
		if(si == ei){
			tree[c] = new Node(ar[si]);
			return tree[c];
		}

		int mid = getMid(si, ei);
		tree[c] = makeNode(createST(ar, si, mid, leftChild(c)), createST(ar, mid + 1, ei, rightChild(c)));
		return tree[c];
	}
	/************ Create Segment tree ends here *************/

	/*** getMin value  ****/
	public int getVal(int queryStart, int queryEnd){
		if(queryStart < 0 || queryEnd > tree.length)
			return -1;
		else
			return getValUtil(STARTINDEX, ENDINDEX, queryStart, queryEnd, ROOT);
	}

	private int getValUtil(int si, int ei, int qs, int qe, int c){
		if(si>=qs && ei<=qe)		//if range is completely within the query range
			return tree[c].getX();

		if(ei < qs || si > qe)				//if range is outside the query range
			return Integer.MAX_VALUE;

		int mid = getMid(si, ei);				//else divide the range in half and procede recursively
		return Math.min(getValUtil(si, mid, qs, qe, leftChild(c)), getValUtil(mid + 1, ei, qs, qe, rightChild(c)));
	}
	/*** getMin value ends here ****/

	/***** Update Value ****/
	public void updateValue(int[] ar, int val, int pos){

	}
	/** Update value ends here ******/


	/*************** Helper methods ******************/
	private int getMid(int a, int b){
		if(b < a){
			int temp = a;
			a = b;
			b = temp;
		}
		return a + (b - a)/2;
	}

	private int leftChild(int pos){
		return 2*pos + 1;
	}
	private int rightChild(int pos){
		return 2*pos + 2;
	}

	private Node minNode(Node left, Node right){
		if(left.getX() < right.getX())
			return left;
		else
			return right;
	}
	/********* Helper methods ends here *************/
}

public class SegmentTreeTest{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


	public static void main(String[] args)throws IOException {			//main method
		
		System.out.println("Enter number of elements followed by the elements");
		int n = readInt();
		int[] ar = new int[n];
		for (int i=0; i<n; i++) {
			ar[i] = readInt();
		}
		SegmentTree st = new SegmentTree(ar);

		System.out.println("Enter number of queries followed by the queries");
		int q = readInt();
		while(q-- > 0){
			int x = readInt();
			int y = readInt();
			System.out.println(st.getVal(x, y));
		}

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