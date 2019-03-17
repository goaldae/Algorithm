		/* 입력 예시
8 9
0 1
0 2
0 3
3 4
4 5
2 5
2 6
1 6
6 7
		*/
package dag;

import java.util.*;
import java.io.*;

public class DAG {
	static int E;
	static int N;
	static LinkedList<Integer>[] a;
	
	static Queue<Integer> queue = new LinkedList<>();
	
	static int[] nodeCount; //자신에게 들어온 화살표 세는 변수
	static ArrayList<Integer> result = new ArrayList<>(); //위상정렬된 배열
	
	public static void addEdge(int n1, int n2){
		a[n1].add(n2);
		nodeCount[n2] += 1;
	}
	
	public static void topologicalSort(){
		for(int i = 0; i < N; i++){
			if(nodeCount[i]==0) queue.add(i);
		}
		
		while(!queue.isEmpty()){
			int temp = queue.poll();
			result.add(temp);
			
			for(int i =0; i < a[temp].size(); i++){
				nodeCount[a[temp].get(i)]--;
				if(nodeCount[a[temp].get(i)]==0){
					queue.add(a[temp].get(i));
				}
			}			
		}	
		
		for(int i = 0; i < result.size(); i++){
			System.out.print(result.get(i)+" ");
		}
	}
	
	public static void main(String[] args) throws Exception {
		//입력및 선언부분//
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		
		StringTokenizer st = new StringTokenizer(input, " ");
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		a = new LinkedList[N];
		result = new ArrayList<>();
		nodeCount = new int[N];
		
		for(int i = 0; i < N; i++){
			a[i] = new LinkedList<>();
		}
		
		for(int i = 0; i < E; i++){
			String[] tempArr = br.readLine().split(" ");
			addEdge(Integer.parseInt(tempArr[0]), Integer.parseInt(tempArr[1]));
		}
		/////////////////

		topologicalSort();
				
	}

}
