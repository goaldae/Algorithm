package baekjoon;

import java.util.*;

public class Population26234 {
	static int N;
	static int L;
	static int R;
	static int[][] arr;
	static int[][] direction = {{-1,0,1,0},{0,1,0,-1}};
	static boolean check = true;
	
	static void bfs(){
		Queue<Pos16234> q;
		Queue<Pos16234> mustChange;
		boolean[][] visited = new boolean[N][N];
		Pos16234 temp;
		int newI, newJ;
		int sum;
		int count;
		int tempRes;
		check = false;
		for(int i=0; i<N;i++){
			for(int j=0; j<N; j++){
				if(visited[i][j]) continue; //이미 방문이 끝난거면 넘어가기
				q =  new LinkedList<>();
				mustChange =  new LinkedList<>();
				
				q.add(new Pos16234(i, j));
				mustChange.add(new Pos16234(i, j));
				sum = arr[i][j];
				visited[i][j] = true;
				count = 1;
				
				while(!q.isEmpty()){
					temp = q.poll();
					
					for(int k = 0; k<4; k++){
						newI = temp.i+direction[1][k];
						newJ = temp.j+direction[0][k];
						if(newI<0||newJ<0||newI>N-1||newJ>N-1||visited[newI][newJ]) continue;
						if(Math.abs(arr[temp.i][temp.j]-arr[newI][newJ])>=L&&Math.abs(arr[temp.i][temp.j]-arr[newI][newJ])<=R){
							q.add(new Pos16234(newI, newJ));
							mustChange.add(new Pos16234(newI, newJ));
							visited[newI][newJ] = true;
							sum+=arr[newI][newJ];
							count++;
						}
					}
				}
				if(count == 1) {visited[i][j] = false; continue;}
				
				tempRes = sum/count;
				check = true;
				while(!mustChange.isEmpty()){
					temp = mustChange.poll();
					arr[temp.i][temp.j] = tempRes;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		arr = new int[N][N];
		int ans = -1;
		
		for(int i=0; i<N;i++){
			for(int j=0; j<N; j++){
				arr[i][j] = sc.nextInt();
			}
		}
		
		while(check){
			bfs();
			ans++;
		}
		System.out.println(ans);
		
		

	}

}

class Pos16234{
	int i, j;
	
	Pos16234(int i, int j){
		this.i = i;
		this.j = j;
	}
}