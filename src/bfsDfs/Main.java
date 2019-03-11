package bfsDfs;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		StringTokenizer st = new StringTokenizer(input, " ");
		
		//정점의 개수 n, 간선의 개수 m, 시작 노드 번호 v 
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken())-1;
		
		Graph g = new Graph(N);

		for(int i = 0; i < M; i++){
			String einput = br.readLine();
			StringTokenizer st2 = new StringTokenizer(einput, " ");
			
			int v1 = Integer.parseInt(st2.nextToken())-1;
			int v2 = Integer.parseInt(st2.nextToken())-1;
			
			g.addEdge(v1, v2);
		}
		
		g.dfs(V);
		System.out.println();
		g.bfs(V);
		
		/*
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 7);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 6);
		g.addEdge(6, 7);
		g.addEdge(6, 8);
		g.addEdge(7, 8);
		*/	
	}
}
