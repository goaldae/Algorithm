package baekjoon;

import java.util.*;

public class BabyShark16236 {
	static int N;
	static int[][] map;	
	static boolean[][] visited;
	static int[][] direction = {{0, -1, 0, 1}, {-1, 0, 1, 0}};
	static Queue<FishPos> q;
	static int si, sj, size = 2, tempSize = 0 ,time = 0;  
	
	
	static void bfs(){
		FishPos curr = null;
		int newI, newJ;
		FishPos minPos = new FishPos(si, sj, 0);
		do{
			q = new LinkedList<>();
			visited = new boolean[N][N];
			q.add(new FishPos(minPos.i, minPos.j, 0));
			visited[minPos.i][minPos.j] = true;
			map[minPos.i][minPos.j] = 0;
			minPos.time = Integer.MAX_VALUE; 
			
			while(!q.isEmpty()){
				curr = q.poll();
				
				if(minPos.time<curr.time) break; //갱신된 시간보다 더 큰녀석이 나왔을 때 스킵
				
				if(map[curr.i][curr.j]<size && map[curr.i][curr.j]!=0){ //자기보다 작은 물고기를 만났을 때
					if(curr.time < minPos.time){ //이미 같은 거리에서 갱신된 적 있을때
						minPos = curr;
					}else if(curr.time == minPos.time){
						if(minPos.i>curr.i){
							minPos = curr;
						}else if(minPos.i==curr.i && minPos.j>curr.j){
							minPos = curr;
						}
					}
				}else{ 
					for(int i = 0; i<4; i++){
						newJ = curr.j + direction[0][i];
						newI = curr.i + direction[1][i];
						
						if(newJ>N-1||newI>N-1||newJ<0||newI<0||visited[newI][newJ]||map[newI][newJ]>size)continue;
						q.add(new FishPos(newI, newJ, curr.time+1));
						visited[newI][newJ]= true;;
					}
				}
			}
			
			if(minPos.time != Integer.MAX_VALUE){ //갱신되었다는 뜻
				time += minPos.time;
				tempSize++;
				if(tempSize==size){
					size++;
					tempSize=0;
				}				
			}
		}while(minPos.time!=Integer.MAX_VALUE);		
		
		System.out.println(time);
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new boolean[N][N];
		map = new int[N][N];
		
		for(int i= 0; i<N; i++){
			for(int j = 0; j<N; j++){
				map[i][j] = sc.nextInt();
				if(map[i][j] == 9){
					si = i;
					sj = j;					
				}
			}
		}		
		bfs();		
	}
}

class FishPos{
	int i, j, time;
	
	FishPos(int i, int j, int time){
		this.i = i;
		this.j = j;
		this.time = time;
	}	
}





