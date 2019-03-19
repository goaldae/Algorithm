package baekjoon;

import java.util.*;
import java.io.*;

class Dot{
	int x;
	int y;
	
	Dot(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class P2667 {
	static int[][] matrix;
	static boolean[][] visited;
	static Queue<Dot> queue = new LinkedList<>();
	static int[][] direct = {
			{-1, 0, 1, 0}, // За
			{0, 1, 0, -1}, // ї­
	};
	
	public static void bfs(int x, int y){
		Dot d = new Dot(x, y);
		queue.add(d);
		visited[x][y]=true;
		
		Dot tempDot = d;
		
		while(!queue.isEmpty()){
			for(int i = 0; i<4; i++){
				int posX =  
				if(matrix)
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		matrix = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++){
			String tempInput = br.readLine();
			for(int j = 0; j < n; j++){
				matrix[i][j] = Integer.parseInt(tempInput.substring(j, j+1));
			}
		}
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
}
