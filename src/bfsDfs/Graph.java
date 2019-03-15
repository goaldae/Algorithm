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
		if(!a[v].contains(u)){//������̱� ������  �ݴ���⵵ �߰����ش�
			//���� ��� (3,1)�̸� (1,3)�� �߰��ϴ� ������ �Ѵ�
			a[v].add(u);
		}
	}
	
	void bfs(int s){
		boolean[] visited = new boolean[v]; //�湮���� Ȯ���ϴ� �迭
		Queue<Integer> queue = new LinkedList<>();
		
		visited[s] = true;
		queue.add(s);
			
		while(!queue.isEmpty()){
			s = queue.poll();
			System.out.print(s+1 + " ");
			
			//Iterator<Integer> iter = a[s].listIterator();
			/*while(iter.hasNext()){
			int n = iter.next();
			if(!visited[n]){
				visited[n] = true;
				queue.add(n);
			}
		}*/
			for(int i = 0; i < a[s].size(); i++){
				if(!visited[a[s].get(i)]){
					visited[a[s].get(i)] = true;
					queue.add(a[s].get(i));
				}
			}
			
		}
		
	}
	
	void dfs(int s){
		boolean[] visited = new boolean[v];
		dfsUtil(s, visited);
	}
	
	void dfsUtil(int s, boolean[] visited){
		visited[s] = true;
		System.out.print(s+1 + " ");
		
		Iterator<Integer> iter = a[s].listIterator();
		
		while(iter.hasNext()){
			int n = iter.next();
			
			if(!visited[n]){
				dfsUtil(n, visited);
			}
		}
	}
}
