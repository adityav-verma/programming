//coderadi
//Topological sorting...
//make edges from the given words... first non matching character from first word to second word is a directed edge
// eg Aditya, Adixfd... then edge from t->x
//in given word order.. {adi, aditya} is right.. but {aditya, adi} is worng.. not lexographic
public class TopologicalSort {
	static boolean[][] edge = new boolean[26][26];

	//returns true if edge is formed or ordering is right or else false
	public static boolean makeEdge(String s1, String s2){	
		int l1 = s1.length(); int l2 = s2.length(); int min = Math.min(l1, l2);
		boolean flag = false; 		//there is no edge
		for(int i=0; i<min; i++){
			if(s1.charAt(i) != s2.charAt(i)){
				edge[s1.charAt(i)-'a'][s2.charAt(i)-'a'] = true;
				flag = true;
				break;
			}
		}
		if(flag == true)
			return true;
		else{
			if(l1 == min)
				return true;
			else
				return false;
		}
	}

	public static void topoSort(){

		ArrayDeque<Integer> st = new ArrayDeque<Integer>();		//will hold the topolical ordering

		boolean[] visited = new boolean[26];
		boolean[] temp = new boolean[26];

		for(int i=0; i<26; i++){
			if(!visited[i]){						//if not visited call the dfs
				if(dfs(i, visited, temp, st) == false){		// Graph is not DAG, if dfs returned false
					System.out.println("Impossible");
					return;
				}
			}
		}
		while(st.size() > 0){		//print the ordring
			System.out.print((char)(st.removeFirst() + 'a'));
		}
		System.out.println();
	}

	public static boolean dfs(int s, boolean[] visited, boolean[] temp, ArrayDeque<Integer> st){
		if(temp[s]){		//if s is temporary visited then stop, not a DAG
			return false;
		}
		if(!visited[s]){
			temp[s] = true;
			for(int i=0; i<26; i++){
				if(edge[s][i]){
					if(!dfs(i, visited, temp, st))
						return false;
				}
			}
			visited[s] = true;
			temp[s] = false;
			st.addFirst(s);
			return true;
		}
		return true;
	}


	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] ar = new String[n];		//input word dictionary

		for(int i=0; i<n; i++)
			ar[i] = br.readLine();

		boolean flag = true;		//ordering is possible
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				boolean temp = makeEdge(ar[i], ar[j]);		//will return true if edge is made.. or ordering of dictionary is right
				if(temp == false){			//{aditya, adi} this case
					flag = false;
					break;
				}
			}
		}
		if(!flag){
			System.out.println("Impossible");	//{aditya, adi} this case 
			return;
		}

		topoSort();
	}
}
