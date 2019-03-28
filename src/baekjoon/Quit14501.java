package baekjoon;

import java.util.*;
import java.io.*;

public class Quit14501 {
	static int D;
	static int[][] table;
	static boolean[] check;
	static int ans = 0;
	
	static void maxPay(int k){
		if(k>D-1){ //해당 인덱스가 마지막 날을 초과하거나 걸리는 날이 마지막 날을 초과할 때 
			int max = 0;
			for(int i = 0; i<D; i++){
				if(check[i]){
					max += table[i][1];
				}
			}
			if(ans<max) ans=max;
			
		}else{
			if(k + table[k][0]-1>D-1){
				check[k] = false;
				maxPay(k + 1);
			}else{
				check[k] = true;
				maxPay(k + table[k][0]);
				check[k] = false;
				maxPay(k + 1);
			}
			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		D = sc.nextInt();
		
		table = new int[D][2];
		check = new boolean[D];
		
		for(int i = 0; i<D; i++){
			for(int j = 0; j<2; j++){
				table[i][j] = sc.nextInt();
			}
		}
		
		maxPay(0);
		System.out.println(ans);
	}
}
