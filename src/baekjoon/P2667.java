/*
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
 */

package baekjoon;

import java.util.*;
import java.io.*;

class Dot1{
	int x;
	int y;
	
	Dot1(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class P2667 {
	static int[][] matrix;
	static int n;
	static boolean[][] visited;
	static Queue<Dot1> que = new LinkedList<>();
	
	static ArrayList<Integer> houseNum = new ArrayList<>(); //������ ����Ʈ ���� �������� ������ �迭
	
	static int[][] direct = {
			{-1, 0, 1, 0}, // ��
			{0, 1, 0, -1}, // ��
		};
	static int res = 0;
	
	public static void bfs(int x, int y){
		int count = 0;
		//��Ḧ ����
		que.add(new Dot1(x, y)); //�̶� ���� �̸��� �������� �ʾƵ� ��
		//��Ḧ �������� ���� ���� �ʿ���
		visited[x][y]=true;
		 
		Dot1 d;//������
		
		while(!que.isEmpty()){
			d = que.poll(); //��Ḧ ������ ����
			count++;
			for(int i = 0; i<4; i++){
				int posX = (d.x) + direct[0][i];
				int posY = (d.y) + direct[1][i];
				
				if(posX>=0 && posX<n && posY>=0 && posY<n 
						&& matrix[posX][posY]==1&&!visited[posX][posY]){
					que.add(new Dot1(posX, posY));
					visited[posX][posY] = true;
				}
			}
		}
		houseNum.add(count);
		res++;		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		matrix = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++){
			String tempInput = br.readLine();
			for(int j = 0; j < n; j++){
				matrix[i][j] = Integer.parseInt(tempInput.substring(j, j+1));
			}
		}
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(matrix[i][j]==1 && !visited[i][j]){
					bfs(i, j);
				}
			}
		}		
		Collections.sort(houseNum);//�������� ����
		System.out.println(res);
		
		for(Integer i : houseNum){
			System.out.println(i);
		}	
	}
}
