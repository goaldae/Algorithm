package baekjoon;

import java.util.*;

class Pos14502{
	int i;
	int j;
	Pos14502(int i, int j){
		this.i = i;
		this.j = j;
	}
}
public class Laboratory14502 {
	static int N;
	static int M;
	
	static int[][] direction = {
			{-1, 0, 1, 0}, {0, 1, 0, -1}
	};
	static int max =  0;
	
	static void bfs(int[][] arr){
		Queue<Pos14502> q = new LinkedList<>();
		int[][] tarr = arrayCopy(arr);
		boolean[][] visited = new boolean[10][10];
		Pos14502 temp;
		int count = 0;
		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++){
				if(tarr[i][j]==2) {
					q.add(new Pos14502(i, j));
					visited[i][j] = true;
				}
			}
		}
		
		int newI;
		int newJ;
		while(!q.isEmpty()){
			temp = q.poll();
			for(int i =0; i<4; i++){
				newI = temp.i + direction[1][i];
				newJ = temp.j + direction[0][i];
				
				if(newI>M-1||newJ>N-1||newI<0||newJ<0||visited[newI][newJ] == true||tarr[newI][newJ]!=0)continue;
				q.add(new Pos14502(newI, newJ));
				tarr[newI][newJ] = 2;
				visited[newI][newJ] = true;				
			}
		}
		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++){
				if(tarr[i][j] == 0) count++;
			}
		}
		if(max<count) {
			max=count;
		}
	}
	
	static int[][] arrayCopy(int[][] arr){
		int[][] newArr = new int[M][N]; 
		for(int i = 0; i<M; i++){
			for(int j = 0; j<N; j++){
				newArr[i][j] = arr[i][j];
			}
			
		}
		return newArr;
	}
	
	static void bruteForce(int[][] arr, int count, int i, int j){
		int[][] tarr1 = arrayCopy(arr); //벽을 만들 배열
		int[][] tarr2 = arrayCopy(arr); //아무것도 안할 배열
		if(count == 3) {
			bfs(arr);
		}else{
			if(j>N-1){
				j = 0;
				i++;
				if(i>M-1) return;
			}
			if(arr[i][j] != 0){
				bruteForce(tarr2, count, i, j+1);
			}else{				
				tarr1[i][j] = 1;
				bruteForce(tarr1, count+1, i, j+1);
				bruteForce(tarr2, count, i, j+1);
			}
		}
		
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[10][10];
		M = sc.nextInt();
		N = sc.nextInt();
		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++){
				arr[i][j] = sc.nextInt();
			}
		}
		
		bruteForce(arr, 0, 0, 0);
		System.out.println(max);
	}

}
