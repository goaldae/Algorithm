package baekjoon;

import java.io.*;
import java.util.*;

class Pos4{
	int x, y, p;
	Pos4(int x, int y, int p){
		this.x = x;
		this.y = y;
		this.p = p;
	}
}
public class Maze2178 {
	static int n, m;
	static int[][] maze = new int[101][101];
	static Queue<Pos4> queue = new LinkedList<>();
	static boolean[][] visited = new boolean[101][101];
	static int[][] direction = {
			{-1, 0, 1, 0},{0, 1, 0, -1}};

	static int bfs(int x, int y){
		Pos4 p;
		queue.add(new Pos4(x, y, 1));
		visited[y][x] = true;
		int tempX, tempY;
		while(!queue.isEmpty()){
			p = queue.poll();
			if(p.x == n-1&&p.y==m-1) return p.p;
			for(int i = 0; i<4; i++){
				tempX = p.x+direction[0][i];
				tempY = p.y+direction[1][i];
				if(tempX<0 || tempY<0 || tempX>=n || tempY>=m || visited[tempY][tempX]==true ||maze[tempY][tempX] == 0) continue;
				visited[tempY][tempX] = true;
				queue.add(new Pos4(tempX, tempY, p.p+1));
			}
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		StringTokenizer st = new StringTokenizer(input, " ");

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		for(int i = 0; i<m; i++){
			input = br.readLine();
			for(int j = 0; j<n; j++){
				maze[i][j] = Character.digit(input.charAt(j), 10);
			}
		}
		System.out.println(bfs(0,0));
	}
}
