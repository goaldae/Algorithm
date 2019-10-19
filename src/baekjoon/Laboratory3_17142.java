package baekjoon;

import java.util.*;

public class Laboratory3_17142 {
	static int[][] map;
	static int N;
	static int M;
	static int virusNum=0;
	static int removeVirusNum;
	static int minTime = Integer.MAX_VALUE;;
	static int[][] direction = {{-1,0,1,0},{0,1,0,-1}};
	static VirusPos[] vp;
	
	static int[][] copyArray(){
		int[][] newArr = new int[N][N];
		
		for(int i = 0; i<N; i++){
			for(int j = 0; j<N; j++){
				newArr[i][j] = map[i][j];
			}
		}	
		return newArr;
	}
	
	static void bfs(){
		Queue<VirusPos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int[][] arr = copyArray();
		int newI, newJ;
		VirusPos temp;
		int distance = 0;

		for(int i = 0; i<virusNum; i++){
			if(vp[i].check){
				q.add(new VirusPos(vp[i].i, vp[i].j, 0));
				visited[vp[i].i][vp[i].j] = true;
			}
		}
		
		while(!q.isEmpty()){
			temp = q.poll();
			
			if(arr[temp.i][temp.j] != 2) {
				distance = Math.max(distance ,temp.time);
			}
			
			for(int i=0; i<4; i++){ 
				newI = temp.i + direction[1][i];
				newJ = temp.j + direction[0][i];				
				
				if(newI>N-1||newJ>N-1||newI<0||newJ<0||visited[newI][newJ]||arr[newI][newJ]== 1) continue;
				
				q.add(new VirusPos(newI, newJ, temp.time+1));
				visited[newI][newJ] = true; //바이러스가 막 활성화 된곳이면 
			}			
		}
		
		for(int i = 0; i<N; i++){
			for(int j = 0; j<N; j++){
				if(arr[i][j]==0 && visited[i][j]==false) return;			
			}
		}		
		if(minTime>distance) minTime = distance;
	}
	
	static void bruteForce(int count){
		if(count == virusNum) {
			int tempCount = 0;
			for(int i = 0; i<virusNum;i++){
				if(vp[i].check)tempCount++;
			}
			if(tempCount==M){
				bfs();
			}
			return;		
		}
		vp[count].check = true;
		bruteForce(count + 1);
		vp[count].check = false;
		bruteForce(count + 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input;
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		
		vp = new VirusPos[10];
		
		for(int i = 0; i<N; i++){
			for(int j = 0; j<N; j++){
				map[i][j] = sc.nextInt();	
				if(map[i][j] == 2){
					vp[virusNum++] = new VirusPos(i, j, 0);
				}
			}
		}		
		bruteForce(0);
		System.out.println(minTime==Integer.MAX_VALUE? -1:minTime);
	}
}

class VirusPos{
	int i, j;
	int time;
	boolean check = false;
	
	VirusPos(int i, int j, int time){
		this.i = i;
		this.j = j;
		this.time = time;
	}
}

