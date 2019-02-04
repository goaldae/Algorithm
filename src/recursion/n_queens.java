package recursion;

public class n_queens {
	public static int N = 20;
	public static int[] chess = new int[N];
	
	public static void main(String[] args) {
		queens(0);
	}
	
	public static boolean promising(int level){ 
		for(int i = 0; i<level; i++){ //그 전에 모든 말들에 대해
			if(chess[i]==chess[level]){ //같은 열에 있을 떄
				return false;
			}else if(level-i==Math.abs(chess[i]-chess[level])){ //대각선 상에 있을 떄
				return false;
			}
		}
		return true;
	}
	
	public static boolean queens(int level){
		if(!promising(level)){ //규칙을 위반했을 때
			return false;
		}else if(N-1 == level){ //정답 노드에 최종 도착했을 때
			for(int i = 0; i<N; i++){
				System.out.println(i+","+chess[i]);
			}
			return true;
		}else{ //규칙은 만족하지만 끝까지 도달한 것은 아닐 때: 다시 한번 child 노드로 진입
			for(int i = 0; i < N; i++){
				chess[level+1] = i;
				if(queens(level+1)){
					return true;
				}
			}
			return false;
		}
	}
}
