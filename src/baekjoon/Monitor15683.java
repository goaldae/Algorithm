package baekjoon;

import java.util.*;


class Cctv{
	int i, j;
	int ch; //cctv 종류
	int dir; //바라보는 방향
	
	void changeDir(){
		dir++;
		if(dir==4) dir=0;
	}
	
	Cctv(int i, int j, int ch, int dir){
		this.i = i;
		this.j = j;
		this.ch = ch;
		this.dir = dir;
	}
}



public class Monitor15683 {
	static int N;
	static int M;
	static int[][] arr;
	static int cctvNum = 0; //cctv개수
	static int min = 10000;
	static int[][] copyArray(int[][] arr){
		int[][] newArr = new int[M][N];
		for(int i = 0; i<M; i++){
			for(int j = 0; j<N; j++){
				newArr[i][j] = arr[i][j];
			}
		}
		return newArr;
	}
	
	static void move(int ch, int[][] arr, Cctv cctv){
		int i;
		if(ch == 0){ //북
			i = cctv.i;
			while(true){
				i--;
				if(i < 0 || arr[i][cctv.j] == 6) break;
				arr[i][cctv.j] = 7;
			}
		}if(ch == 1){ //동
			i = cctv.j;
			while(true){
				i++;
				if(i > N-1 || arr[cctv.i][i] == 6) break;
				arr[cctv.i][i] = 7;
			}
		}if(ch == 2){ //남
			i = cctv.i;
			while(true){
				i++;
				if(i > M-1 || arr[i][cctv.j] == 6) break;
				arr[i][cctv.j] = 7;
			}
		}if(ch == 3){ //서
			i = cctv.j;
			while(true){
				i--;
				if(i < 0 || arr[cctv.i][i] == 6) break;
				arr[cctv.i][i] = 7;
			}
		}
	}
	
	static void watch(Cctv cctv, int[][] arr){
		if(cctv.ch == 1){
			if(cctv.dir == 0){
				move(0, arr, cctv);
			}if(cctv.dir == 1){
				move(1, arr, cctv);
			}if(cctv.dir == 2){
				move(2, arr, cctv);
			}if(cctv.dir == 3){
				move(3, arr, cctv);
			}
		}if(cctv.ch == 2){
			if(cctv.dir == 0 || cctv.dir == 2){
				move(0, arr, cctv);
				move(2, arr, cctv);
			}if(cctv.dir == 1 || cctv.dir == 3){
				move(1, arr, cctv);
				move(3, arr, cctv);				
			}
		}if(cctv.ch == 3){
			if(cctv.dir == 0){
				move(0, arr, cctv);
				move(1, arr, cctv);
			}if(cctv.dir == 1){
				move(1, arr, cctv);
				move(2, arr, cctv);
			}if(cctv.dir == 2){
				move(2, arr, cctv);
				move(3, arr, cctv);
			}if(cctv.dir == 3){
				move(3, arr, cctv);
				move(0, arr, cctv);
			}
			
		}if(cctv.ch == 4){
			if(cctv.dir == 0){
				move(0, arr, cctv);
				move(3, arr, cctv);
				move(1, arr, cctv);
			}if(cctv.dir == 1){
				move(0, arr, cctv);
				move(1, arr, cctv);
				move(2, arr, cctv);
			}if(cctv.dir == 2){
				move(1, arr, cctv);
				move(3, arr, cctv);
				move(2, arr, cctv);
			}if(cctv.dir == 3){
				move(0, arr, cctv);
				move(3, arr, cctv);
				move(2, arr, cctv);
			}
			
		}if(cctv.ch == 5){
			move(0, arr, cctv);
			move(3, arr, cctv);
			move(1, arr, cctv);
			move(2, arr, cctv);
		}
	}
	
	static void getResult(ArrayList<Cctv> cctv){
		int count = 0;
		int[][] newArr = copyArray(arr); //실험을 할 배열 만들기
		for(int i = 0; i<cctvNum; i++){
			watch(cctv.get(i), newArr);
		}
		for(int i = 0; i<M; i++){
			for(int j = 0; j<N; j++){
				if(newArr[i][j] == 0){
					count++;
				}
			}
		}
		if(count<min) min = count; 
	}
	
	static void bruteForce(ArrayList<Cctv> cctv, int count){
		if(count==cctvNum+1) return;
		if(count==cctvNum){
			getResult(cctv);			
			return;
		}
		for(int i = 0; i<4; i++){
			bruteForce(cctv, count+1);
			cctv.get(count).changeDir();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		arr = new int[M][N];
		
		ArrayList<Cctv> cctv = new ArrayList<>();
		
		for(int i = 0; i<M; i++){
			for(int j = 0; j<N; j++){
				arr[i][j] = sc.nextInt();
				if(arr[i][j]!=0 &&arr[i][j]!=6){ //길이거나 벽이 아닐때 : cctv일때
					cctv.add(new Cctv(i,j,arr[i][j], 0));
					cctvNum++;
				}
			}
		}		
		bruteForce(cctv, 0);
		System.out.println(min);
	}

}
