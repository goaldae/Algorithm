package baekjoon;

import java.util.*;

public class FineDust17144 {
	static int M;
	static int N;
	static int T;
	static int[][] arr;
	static int[][] direction = {{-1,0,1,0},{0,1,0,-1}}; 
	static Queue<Dust> q;
	
	static void spread(){
		Dust temp;
		int newI, newJ;
		while(!q.isEmpty()){
			temp = q.poll();
			for(int k = 0; k<4; k++){
				newI = temp.i+direction[1][k];
				newJ = temp.j+direction[0][k];
				if(newI>M-1||newJ>N-1||newI<0||newJ<0||arr[newI][newJ]==-1) continue;
				arr[newI][newJ] += temp.mount;
			}			
		}
	}
	
	static void move(int ch, int i){
		if(ch == 0){
			for(int k = i-2; k>=0; k--){
				arr[k+1][0] = arr[k][0];
			}
			for(int k = 1; k<=N-1; k++){
				arr[0][k-1] = arr[0][k];
			}
			for(int k = 1; k<=i; k++){
				arr[k-1][N-1] = arr[k][N-1];
			}
			for(int k = N-2; k>0; k--){
				arr[i][k+1] = arr[i][k];
			}
			arr[i][1] = 0;
		}else if(ch == 1){
			for(int k = i+2; k<=M-1; k++){
				arr[k-1][0] = arr[k][0];
			}
			for(int k = 1; k<=N-1; k++){
				arr[M-1][k-1] = arr[M-1][k];
			}
			for(int k = M-2; k>=i; k--){
				arr[k+1][N-1] = arr[k][N-1];
			}
			for(int k = N-2; k>0; k--){
				arr[i][k+1] = arr[i][k];
			}
			arr[i][1] = 0;
		
		}
	}
	
	static void clean(){
		int count = 1;
		for(int i=0; i<M; i++){
			if(arr[i][0]==-1&&count==1) {
				move(0,i);
				count++;
			}else if(arr[i][0]==-1&&count==2){
				move(1,i);
			}
			
		}
	}
	static void aroundCheck(){
		q = new LinkedList<>();
		int newI, newJ;
		int count;
		for(int i = 0; i<M; i++){
			for(int j = 0; j<N; j++){
				if(arr[i][j]==0 && arr[i][j]==-1) continue;
				count = 0;
				for(int k = 0; k<4; k++){
					newI = i+direction[1][k];
					newJ = j+direction[0][k];
					if(newI>M-1||newJ>N-1||newI<0||newJ<0||arr[newI][newJ]==-1) continue;
					count++;
				}
				q.add(new Dust(i, j, arr[i][j]/5));
				arr[i][j] -= (arr[i][j]/5)*count;
			}
		}
	}
	static int sum(){
		int sum = 0;
		for(int i = 0; i<M; i++){
			for(int j = 0; j<N; j++){
				if(arr[i][j]==-1)continue;
				sum += arr[i][j];
			}
		}
		return sum;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		T = sc.nextInt();
		arr = new int[M][N];
		
		for(int i = 0; i<M; i++){
			for(int j = 0; j<N; j++){
				arr[i][j] = sc.nextInt();
			}
		}
		
		for(int i= 0; i<T; i++){
			aroundCheck();
			spread();
			clean();
		}
		
		System.out.println(sum());		
	}
}
		
class Dust{
	int i,j;
	int mount;
	
	Dust(int i, int j, int mount){
		this.i = i;
		this.j = j;
		this.mount = mount;
	}
}

