package practice_java;
import java.util.Scanner;
//최대공약수 구하기 함수
public class p0130 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		
		int res = gcd(x, y);
		System.out.println(res);
	}
	
	public static int gcd(int p, int q){
		if(q==0){
			return p;
		}else{
			return gcd(q, p%q);
		}
	}

}
