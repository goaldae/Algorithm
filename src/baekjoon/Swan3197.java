package baekjoon;

import java.io.*;
import java.util.*;

class Pos{
	int x, y;
	
	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Swan3197 {
	static int m, n;
	static char[][] arr;
	static int[][] direct = {
			{1, 0, -1, 0},
			{0, 1, 0, -1}};
	
	static boolean res = false;
	
	static void melt(){
		Queue<Pos> queue = new LinkedList<>();
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(arr[i][j]=='L')continue; //백조면 생략
				
				for(int k = 0; k < 4; k++){
					if(i+direct[0][k]>=m||j+direct[1][k]>=n||
							i+direct[0][k]<0||j+direct[1][k]<0) continue; //범위를 넘어서면 생략
					
					if(arr[i+direct[0][k]][j+direct[1][k]]=='.'){ //인접한 곳이 물이면
						queue.add(new Pos(i, j)); //큐에 넣는다
						break; //하나라도 물이면 큐에 넣고 반복문 빠져나오기
					}
				}
			}
		}
		
		Pos tempPos;
		while(!queue.isEmpty()){
			tempPos = queue.poll();
			arr[tempPos.x][tempPos.y] = '.';
		}
	}
	
	static void touchSwan(int x, int y){
		Queue<Pos> queue = new LinkedList<>();
		boolean[][] checked = new boolean[m][n];
		
		queue.add(new Pos(x, y));
		checked[x][y] = true;
		
		Pos tempPos;
		while(!queue.isEmpty()){
			tempPos = queue.poll();
			
			for(int i = 0; i<4; i++){
				int newM = tempPos.x + direct[0][i];
				int newN = tempPos.y + direct[1][i];
				if(newM>=m||newN>=n||newM<0||newN<0) continue;
				if(arr[newM][newN] != 'X'&& !checked[newM][newN]){
					queue.add(new Pos(newM, newN));
					checked[newM][newN] = true;

					if(arr[newM][newN] == 'L') {
						res = true; //만날 수 있다
						arr[newM][newN] = 'P';
						
						return;
					}
				}
				
			}
		}
	}
	
	static void initTouchSwan(){		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(arr[i][j]=='L'){
					touchSwan(i,j);
					return;
				}
			}	
		}
	}
	
	static int judge(){
		int meltCount = 0;
		initTouchSwan();
		
		while(!res){
			melt();
			meltCount++;
			initTouchSwan();
		}
		return meltCount;
	}
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, " ");
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arr = new char[m][n];
		
		for(int i = 0; i < m; i++){
			input = br.readLine();
			arr[i] = input.toCharArray();
		}
	
		System.out.print(judge());	
	}

}
