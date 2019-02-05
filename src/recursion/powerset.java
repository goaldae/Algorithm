package recursion;

public class powerset {
	static char[] set = {'a', 'b', 'c', 'd', 'e'};
	static int n = set.length;
	static boolean[] include = new boolean[n];
	
	public static void main(String[] args) {
		powerSet(0);
	}
	
	public static void powerSet(int k){
		if(k == n){
			for(int i = 0; i<n; i++){
				if(include[i]){
					System.out.print(set[i]+" ");
				}				
			}
			System.out.println();
		}else{
			include[k]=true;
			powerSet(k+1);
			include[k]=false;
			powerSet(k+1);
		}
	}
}
   