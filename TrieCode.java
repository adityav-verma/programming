//coderadi
//implementing the trie ds for efficient searching of patters(P) in a given text(T)

import java.io.*;
import java.util.*;

public class TrieCode {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("#######Enter the number of values in text#######");
		int n = Integer.parseInt(br.readLine());
		String[] text = new String[n];
		for(int i=0; i<n; i++){
			text[i] = br.readLine().toLowerCase();
		}
		Trie t = new Trie(text);	//initailize and make trie
		System.out.println("#######Enter number of searches#######");
		int m = Integer.parseInt(br.readLine());
		while(m-- > 0){
			int ans = t.search(br.readLine());
			if(ans > 0)
				System.out.println("YES" + ans);
			else
				System.out.println("NO" + ans);
		}
	}
}

class Trie{
	private TrieNode root;		//pointer to the root
	private int count;			//count

	Trie(String[] text){						//constructor for trie
		root = new TrieNode();
		count = 0;
		for(int i=0; i<text.length; i++)
			insert(text[i]);
	}

	public void insert(String s){		//inserts the String in the trie
		count++;						//count the number of strings in trie
		TrieNode curr = root;
		int len = s.length();
		for(int i=0; i<len; i++){
			if(curr.getChild(s.charAt(i) - 'a') == null){		//if curr does not have the letter, then make a new node
				curr.setChild(s.charAt(i) - 'a');
			}
			curr = curr.getChild(s.charAt(i) - 'a');
		}
		curr.setValue(count);			//mark the node as leaf that contains the nth String of Text
	}

	public int search(String p){		//searches for the pattern and nonZero if String is present else 0
		int len = p.length();
		TrieNode curr = root;
		for (int i=0; i<len; i++) {
			if(curr.getChild(p.charAt(i) - 'a') == null)
				return 0;
			curr = curr.getChild(p.charAt(i) - 'a');
		}
		if(curr.getValue() == 0)	//the pattern is a substring for a particular text entry
			return 0;
		else
			return curr.getValue();	//the pattern is a complete text entry
	}
}


//TrieNode data structure to hold the basic node of trie
class TrieNode{
	private int value;
	private TrieNode[] child;
	private final int SIZE = 26;

	TrieNode(){
		value = 0;			//contains a non zero value for leafNodes
		child = new TrieNode[SIZE];	//childs all pointing to null
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	public void setChild(int c){
		child[c] = new TrieNode();
	}

	public TrieNode getChild(int c){
		return child[c];
	}
}