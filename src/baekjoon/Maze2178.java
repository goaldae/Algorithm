package baekjoon;

import java.io.*;
import java.util.*;

class Dot{
	int x;
	int y;
	int total;
	boolean c;
	
	Dot(int x, int y){
		this.x = x;
		this.y = y;
		total = 1;
		this.c = false;
	}
}

public class Maze2178 {

	static void dfs(int m, int n, int u, int v, int[][] matrix){
		Queue<Dot> queue = new LinkedList<>();
		
		Dot[][] d = new Dot[m][n]; //matrix배열이 1이면 좌표를 가지는 dot 생성하는 dot배열 d
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				d[i][j] = new Dot(i, j);
			}
		}
		
		d[u][v].c = true;
		queue.add(d[u][v]);
			
		while(!queue.isEmpty()){			
			Dot td = queue.poll();
			matrix[td.x][td.y] = 2;
			
			if((td.x)-1>=0 && (td.x)-1<m && td.y>=0 && td.y<n ){
				if(d[(td.x)-1][td.y].c == false&&matrix[(td.x)-1][td.y]==1){
					d[(td.x)-1][td.y].c=true;
					queue.add(d[(td.x)-1][td.y]);
					
					d[(td.x)-1][td.y].total += d[td.x][td.y].total;
				}
			}if(td.x >= 0 && td.x < m && (td.y)+1>=0 && (td.y)+1<n ){
				if(d[td.x][(td.y)+1].c == false&&matrix[td.x][(td.y)+1]==1){
					d[td.x][(td.y)+1].c=true;
					queue.add(d[td.x][(td.y)+1]);
					d[td.x][(td.y)+1].total += d[td.x][td.y].total;
				}
			}
			if((td.x)+1>=0 && (td.x)+1<m && td.y>=0 && td.y<n ){
				if(d[(td.x)+1][td.y].c == false&&matrix[(td.x)+1][td.y]==1){
					d[(td.x)+1][td.y].c=true;
					queue.add(d[(td.x)+1][td.y]);
					
					d[(td.x)+1][td.y].total += d[td.x][td.y].total;
				}
			}
			if(td.x>=0 && td.x<m && (td.y)-1>=0 && (td.y)-1<n ){
				if(d[td.x][(td.y)-1].c == false&&matrix[td.x][(td.y)-1]==1){
					d[td.x][(td.y)-1].c = true;
					queue.add(d[td.x][(td.y)-1]);
					d[td.x][(td.y)-1].total += d[td.x][td.y].total;
				}
			}
		}
		
		System.out.println(d[m-1][n-1].total);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				System.out.print(d[i][j].total+"|");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		
		StringTokenizer st = new StringTokenizer(input, " ");
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[][] matrix = new int[m][n];
		
		String[] line = new String[n];
		for(int i = 0; i < m; i++){
			line[i] = br.readLine();
			String[] t = line[i].split(""); 
			for(int j = 0; j < n; j++){
				matrix[i][j] = Integer.parseInt(t[j]);
			}
		}	
		
		dfs(m, n, 0, 0, matrix);	
	}
}
