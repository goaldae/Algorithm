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
	static int max = 0;
	static int[][] direct = {
			{1, 0, -1, 0},
			{0, 1, 0, -1}};

	static Queue<Pos> queue = new LinkedList<>();

	static boolean[][] checked;
	static PriorityQueue<Pos> priQueue = new PriorityQueue<>();

	static void melt(){
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				if(arr[i][j] == '.'){
					queue.offer(new Pos(i, j, 0));
					water[i][j] = 0;
				}
			}
		}

		Pos tempPos;
		while(!queue.isEmpty()){
			tempPos=queue.poll();
			for(int i = 0; i<4; i++){
				int tempM = tempPos.x+direct[0][i];
				int tempN = tempPos.y+direct[1][i];

				if(tempM<0||tempM>=m||tempN<0||tempN>=n) continue;
				if(arr[tempM][tempN]=='X'){
					queue.offer(new Pos(tempM, tempN, tempPos.day+1));
					arr[tempM][tempN] = '.';
					water[tempM][tempN] = tempPos.day+1;
				}
			}
		}
	}

	static void touchSwan(){
		Pos tempPos;

		while(!priQueue.isEmpty()){
			tempPos = priQueue.poll();
			if(max<tempPos.day) max=tempPos.day;
			if(arr[tempPos.x][tempPos.y] == 'L') {
				System.out.println(max);
				return;
			}
			for(int i = 0; i<4; i++){
				int newM = tempPos.x + direct[0][i];
				int newN = tempPos.y + direct[1][i];
				if(newM>=m||newN>=n||newM<0||newN<0||checked[newM][newN]) continue;

				priQueue.add(new Pos(newM, newN, water[newM][newN]));

				checked[newM][newN] = true;
				System.out.println(tempPos.day);



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
		checked = new boolean[m][n];
		water = new int[m][n];

		for(int i = 0; i < m; i++){
			input = br.readLine();
			arr[i] = input.toCharArray();
		}
		judge();/*
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}*/
		//System.out.print(judge());


	}
}