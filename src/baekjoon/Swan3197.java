package baekjoon;

import java.io.*;
import java.util.*;

public class Swan3197 {
	static int m, n;
	static char[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, " ");
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arr = new char[m][n];
		
		for(int i = 0; i < m; i++){
			input = br.readLine();
			arr[i] = input.toCharArray();
		}
			
	}

}
