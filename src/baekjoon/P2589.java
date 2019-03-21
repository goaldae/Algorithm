package baekjoon;

import java.util.*;
import java.io.*;

class Dot2{
	int x;
	int y;
	
	Dot2(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class P2589 {
	static int m, n;
	static String[][] matrix;
	static boolean[][] visited;
	static Queue<Dot2> queue = new LinkedList<>();
	static int count;
	
	static int[][] direct = {
			{-1, 0, 1, 0},
			{0, 1, 0, -1},
		};
	
	public void bfs(int x, int y){
		count = 0;
		queue.add(new Dot2(x, y));
		count++;
		visited[x][y] = true;
		
		Dot2 d;
		while(!queue.isEmpty()){
			d = queue.poll();
		
			for(int i = 0; i < 4; i++){
				int posX = d.x + direct[0][i];
				int posY = d.y + direct[i][0];
				if(posX < 0 || posY < 0 || posX >= m || posY >= n){
					continue;
				}
				if(!visited[posX][posY] && matrix[posX][posY] == "L" ){
					queue.add(new Dot2(posX, posY));
					count++;
					visited[posX][posY] = true;
				}
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		StringTokenizer st = new StringTokenizer(input, " ");
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		matrix = new String[m][n];
		visited = new boolean[m][n];
		
		for(int i = 0; i < m; i++){
			String tempInput = br.readLine();
			for(int j = 0; j<n; j++){
				matrix[i][j] = tempInput.substring(j, j+1);
			}
		}
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j<n; j++){
				if(matrix[i][j]== "L" && !visited[i][j]){
					bfs(i, j);
				}
			}
		}
				
	}

}
