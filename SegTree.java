//coderadi
import java.io.*;
import java.util.*;
import java.math.*;

public class SegTree{

	//static int n;
	static int[] tree;
	static int[] ar;
	static int[] lazy;

	//build tree
	static void createTree(int node, int si, int ei){
		if(si > ei)		//out of range
			return;

		if(si == ei){
			tree[node] = ar[si];
			return;
		}
		int mid = si + (ei - si)/2;
		createTree(node*2 + 1, si, mid);
		createTree(node*2 + 2, mid+1, ei);

		tree[node] = Math.max(tree[node*2 + 1], tree[node*2 + 2]);

	}

	//increment the range with the val
	static void updateRange(int node, int si, int ei, int qs, int qe, int val){
		if(si > ei || si > qe || ei < qs)	//out of range 
			return;

		if(si == ei){						//leaf node
			tree[node] += val;
			return;
		}
										//break in two
		int mid = si + (ei - si)/2;
		updateRange(node*2 +1, si, mid, qs, qe, val);
		updateRange(node*2 +2, mid+1, ei, qs, qe, val);
		tree[node] = Math.max(tree[node *2 +1], tree[node * 2 + 2]);
		return;
 	}

 	//get the max element between qs and qe
 	static int queryTree(int node, int si, int ei, int qs, int qe){
 		if(si > ei || si > qe || ei < qs)		// out of range
 			return Integer.MIN_VALUE;

 		if(si >= qs && ei <= qe)		//curent segment totally within range
 			return tree[node];

 		int mid = si + (ei - si)/2;			//divide in half and query them
 		int q1 = queryTree(node * 2 + 1, si, mid, qs, qe);
 		int q2 = queryTree(node * 2 + 2, mid+1, ei, qs, qe);

 		int res = Math.max(q1, q2);
 		return res;
 	}

 	///////////Lazy Progpogation update and query
 	static void updateRangeLazy(int node, int si, int ei, int qs, int qe, int val){
 		if(lazy[node] != 0){			//the node needs to be updated	
 			tree[node] += lazy[node];		//update

 			if(si != ei){		//mark children as lazy
 				lazy[2*node +1] += lazy[node];
 				lazy[2*node +2] += lazy[node];
 			}

 			lazy[node] = 0;			//reset the lazy node for parent
 		}
		
		if(si > ei || si > qe || ei < qs)	//out of range 
			return;

 		if(si >= qs && ei <= qe){	//if completely within range
 			tree[node] += val;		//udpate

 			if(si != ei){			//mark children as lazy
 				lazy[2*node +1] += val;
 				lazy[2*node +2] += val;
 			}
 			return;
 		}

 		int mid = si + (ei - si)/2;		//else divide int two, update
 		updateRangeLazy(node*2 +1, si, mid, qs, qe, val);
		updateRangeLazy(node*2 +2, mid+1, ei, qs, qe, val);
		tree[node] = Math.max(tree[node *2 +1], tree[node * 2 + 2]);
		return;
 	}

 	static int queryTreeLazy(int node, int si, int ei, int qs, int qe){
 		if(si > ei || si > qe || ei < qs)	//out of range 
			return Integer.MIN_VALUE;

		if(lazy[node] != 0){			//the node needs to be updated	
 			tree[node] += lazy[node];		//update it

 			if(si != ei){		//mark children as lazy
 				lazy[2*node +1] += lazy[node];
 				lazy[2*node +2] += lazy[node];
 			}

 			lazy[node] = 0;			//reset the lazy node for parent
 		}

		if(si >= qs && ei <= qe)	//if completely within range
 			return tree[node];		//return it


 		int mid = si + (ei - si)/2;			//divide in half and query them
 		int q1 = queryTreeLazy(node * 2 + 1, si, mid, qs, qe);
 		int q2 = queryTreeLazy(node * 2 + 2, mid+1, ei, qs, qe);

 		int res = Math.max(q1, q2);
 		return res;
 	}

	public static void main(String[] args)throws IOException {			//main method
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		ar = new int[n];
		for(int i=0; i<n; i++)
			ar[i] = s.nextInt();

		//initialize the tree height of tree is log2(#leafNodes) + 1
		int height = (int)(Math.ceil(Math.log(n)/Math.log(2))) + 1;
		int maxSize = (int)(Math.pow(2, height) + 1);
		tree = new int[maxSize];
		lazy = new int[maxSize];
		createTree(0, 0, n-1);


		//queries
		int q = s.nextInt();
		while(q-- > 0){
			int a = s.nextInt();
			if(a == 0){
				int qs = s.nextInt();
				int qe = s.nextInt();
				System.out.println(queryTreeLazy(0, 0, n-1, qs, qe));
			}
			else{
				int l = s.nextInt();
				int r = s.nextInt();
				int val = s.nextInt();
				updateRangeLazy(0, 0, n-1, l, r, val);
			}

		}
		
	}
}