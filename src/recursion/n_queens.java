package recursion;

public class n_queens {
	public static int N = 20;
	public static int[] chess = new int[N];
	
	public static void main(String[] args) {
		queens(0);
	}
	
	public static boolean promising(int level){ 
		for(int i = 0; i<level; i++){ //�� ���� ��� ���鿡 ����
			if(chess[i]==chess[level]){ //���� ���� ���� ��
				return false;
			}else if(level-i==Math.abs(chess[i]-chess[level])){ //�밢�� �� ���� ��
				return false;
			}
		}
		return true;
	}
	
	public static boolean queens(int level){
		if(!promising(level)){ //��Ģ�� �������� ��
			return false;
		}else if(N-1 == level){ //���� ��忡 ���� �������� ��
			for(int i = 0; i<N; i++){
				System.out.println(i+","+chess[i]);
			}
			return true;
		}else{ //��Ģ�� ���������� ������ ������ ���� �ƴ� ��: �ٽ� �ѹ� child ���� ����
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
