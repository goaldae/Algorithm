/*
5 7
WLLWWWL
LLLWLLL
LWLWLWW
LWLWLLL
WLLWLWW

166984KB
 */
//누적합(최소 거리) 구할 때 숫자 배열 사용하기
//String => char배열 : toCharArray
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
	static char[][] matrix;
	static int res = 0;
	
	static Queue<Dot2> queue = new LinkedList<>();
		
	static int[][] direct = {
			{-1, 0, 1, 0},
			{0, 1, 0, -1},
		};
	
	static void bfs(int x, int y){
		boolean[][] visited = new boolean[m][n];
		
		int[][] distance = new int[m][n];
		
		queue.add(new Dot2(x, y));
		visited[x][y] = true;
		distance[x][y] = 0;
		
		Dot2 d;
		while(!queue.isEmpty()){
			d = queue.poll();
			
			for(int i = 0; i < 4; i++){
				int posX = d.x + direct[0][i];
				int posY = d.y + direct[1][i];
				if(posX < 0 || posY < 0 || posX >= m || posY >= n){
					continue;
				}
				if(!visited[posX][posY] && matrix[posX][posY] == 'L' ){
					queue.add(new Dot2(posX, posY));

					visited[posX][posY] = true;
					distance[posX][posY] = distance[d.x][d.y] + 1;

					if(distance[posX][posY]>res){
						res = distance[posX][posY];
					}
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
		
		matrix = new char[m][n];
		
		for(int i = 0; i < m; i++){
			String tempInput = br.readLine();
			matrix[i] = tempInput.toCharArray();
		}
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j<n; j++){
				if(matrix[i][j]== 'L'){
					bfs(i, j);
				}
			}
		}
			
		System.out.println(res);
	}

}
