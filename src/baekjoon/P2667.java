package baekjoon;

import java.util.*;
import java.io.*;

public class P2667 {
	static int[][] matrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		
		matrix = new int[n][n];
		
		for(int i = 0; i < n; i++){
			String tempInput = br.readLine();
			for(int j = 0; j < n; j++){
				matrix[i][j] = Integer.parseInt(tempInput.substring(j));
			}
		}
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
}
