package dag;

import java.util.*;
import java.io.*;

public class DAG {
	static int E;
	static int N;
	static LinkedList<Integer>[] a;
	static boolean[] visited;
	static Queue<Integer> queue;
	
	static int[] nodeCount; //�ڽſ��� ���� ȭ��ǥ ���� ����
	static int[] result; //�������ĵ� �迭
	
	public static void addEdge(int n1, int n2){
		a[n1].add(n2);
		nodeCount[n2]++;
	}
	
	public static void topologicalSort(int s){
		visited[s] = true;
		queue.add(s);
		
		nodeCount[s]--;
		
		while(!queue.isEmpty()){
			int temp = queue.poll();
			System.out.println(temp+" ");
			
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		
		StringTokenizer st = new StringTokenizer(input, " ");
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++){
			a[i] = new LinkedList<>();
		}
		
		for(int i = 0; i < E; i++){
			String[] tempArr = br.readLine().split(" ");
			addEdge(Integer.parseInt(tempArr[0]), Integer.parseInt(tempArr[0]));
		}
				
	}

}
