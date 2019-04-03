package baekjoon;

import java.io.*;
import java.util.*;

class Pos implements Comparable<Pos>{
	int x, y, day;
	
	Pos(int x, int y, int day){
		this.x = x;
		this.y = y;
		this.day = day;
	}
	
	@Override
    public int compareTo(Pos tempP) {
        return this.day - tempP.day;
    }
}

public class Swan3197 {
	static int m, n;
	static char[][] arr;
	static int[][] water;
	
	static int[][] direct = {
			{1, 0, -1, 0},
			{0, 1, 0, -1}};
	
	static boolean res = false;
	static Queue<Pos> queue = new LinkedList<>();
	static boolean[][] mchecked;
	static boolean[][] checked;
	static PriorityQueue<Pos> priQueue = new PriorityQueue<>();
	static Pos tempPos;
	static int count = 1;
	
	static void melt(){
		Queue<Pos> que = new LinkedList<>();
		while(true){
			for(int i = 0; i < m; i++){
				for(int j = 0; j < n; j++){
					if(arr[i][j]=='L' || mchecked[i][j]) continue; //백조면 생략
					
					if(arr[i][j] == '.') mchecked[i][j] = true;
					
					for(int k = 0; k < 4; k++){
						if(i+direct[0][k]>=m||j+direct[1][k]>=n||
								i+direct[0][k]<0||j+direct[1][k]<0) continue; //범위를 넘어서면 생략
						
						if(arr[i+direct[0][k]][j+direct[1][k]]=='.' && arr[i][j] == 'X'){ //인접한 곳이 물이면
							mchecked[i][j] = true;
							que.add(new Pos(i, j, water[i][j])); //큐에 넣는다
							break; //하나라도 물이면 큐에 넣고 반복문 빠져나오기
						}
					}
				}
			}
			if(que.isEmpty()){
				break;
			}
			
			Pos tempP;
			while(!que.isEmpty()){
				tempP = que.poll();
				arr[tempP.x][tempP.y] = '.';
				water[tempP.x][tempP.y] = count;
			}
			count++;
		}
				
	}
	
	static void touchSwan(){
		
		while(!priQueue.isEmpty()){
			tempPos = priQueue.poll();
			System.out.println(tempPos.day);
			for(int i = 0; i<4; i++){
				int newM = tempPos.x + direct[0][i];
				int newN = tempPos.y + direct[1][i];
				if(newM>=m||newN>=n||newM<0||newN<0) continue;
				if(!checked[newM][newN]){
					priQueue.add(new Pos(newM, newN, water[newM][newN]));
					//arr[newM][newN] = 'o';
					checked[newM][newN] = true;
					
					if(arr[newM][newN] == 'L') {
						System.out.println(tempPos.day);
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
					priQueue.add(new Pos(i, j, water[i][j]));
					arr[i][j] = 'o';
					checked[i][j] = true;
					touchSwan();
					return;
				}
			}	
		}
	}
	
	static void judge(){
		melt();
		
		initTouchSwan();
		
	}
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, " ");
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arr = new char[m][n];
		mchecked = new boolean[m][n];
		checked = new boolean[m][n];
		water = new int[m][n];
		
		for(int i = 0; i < m; i++){
			input = br.readLine();
			arr[i] = input.toCharArray();
		}
		judge();
		//System.out.print(judge());
		
		
	}
}