/*
20
7
23
19
10
15
25
8
13
14280KB
*/
package baekjoon;

import java.util.*;
import java.io.*;

public class Dwarf2309 {
	static int N = 9;
	static int[] arr = new int[N];
	static boolean[] check = new boolean[N];
	static int[] ans = new int[7];
	
	static void powerSet(int k){
		int count = 0;
		int res = 0;
		int j = 0;
		
		if(k==N){
			for(int i = 0; i < N; i++){
				if(check[i]){
					count++;
				}
			}
			if(count==7){
				
				for(int i = 0; i < N; i++){
					if(check[i]){
						
						res = res + arr[i];	
					}
				}
				if(res == 100){
					for(int i = 0; i < N; i++){
						if(check[i]){
							ans[j++] = arr[i];
						}
					}
				}
			}
		} else{
			check[k] = true;
			powerSet(k+1);
			check[k] = false;
			powerSet(k+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < N; i++){
			arr[i] = sc.nextInt();
		}
		
		powerSet(0);
		Arrays.sort(ans);
		
		for(int i = 0; i < 7; i++){
			System.out.println(ans[i]);
		}
	}
}
