package bfsDfs;

import java.util.*;
import java.io.*;

public class Graph {
	int v;
	LinkedList<Integer>[] a;
	
	Graph(int v){
		this.v = v;
		a = new LinkedList[v];
		for(int i = 0; i<v; i++){
			a[i] = new LinkedList<>();
		}
	}
	
	void addEdge(int u, int v){
		a[u].add(v);
		if(!a[v].contains(u)){//양방향이기 때문에  반대방향도 추가해준다
			//예를 들어 (3,1)이면 (1,3)도 추가하는 역할을 한다
			a[v].add(u);
		}
	}
	
	void bfs(int s){
		boolean[] visited = new boolean[v]; //방문한지 확인하는 배열
		Queue<Integer> queue = new LinkedList<>();
		
		visited[s] = true;
		queue.add(s);
			
		while(!queue.isEmpty()){
			s = queue.poll();
			System.out.print(s+1 + " ");
			
			Iterator<Integer> iter = a[s].listIterator();
			while(iter.hasNext()){
				int n = iter.next();
				if(!visited[n]){
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		
	}
}
