/*
6 4
0 -1 0 0 0 0
-1 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1
301492KB
*/

package baekjoon;

import java.util.*;
import java.io.*;

class Dot3{
	int x;
	int y;
	
	Dot3(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Tomato7576 {
	static int[][] matrix;
	static int m, n;
	static int res;
	static int[][] time;
	
	static Queue<Dot3> queue;
	static int[][] direct = {
			{-1, 0 , 1, 0},
			{0, 1, 0, -1}
		};
	static boolean judge;
	
	static void bfs(){		
		Dot3 tempDot;
		while(!queue.isEmpty()){
			tempDot = queue.poll();
			
			for(int i=0; i<4; i++){
				int nx = tempDot.x + direct[0][i];
				int ny = tempDot.y + direct[1][i];
				
				if(nx<0||ny<0||nx>=m||ny>=n){
					continue;
				}
				if(time[nx][ny]==0&&matrix[nx][ny]==0){
					queue.offer(new Dot3(nx, ny));
					time[nx][ny] = time[tempDot.x][tempDot.y] + 1; //�Ǽ����� ����
					res = time[nx][ny]-1; //������ 1�� �����߱� ������
				}
			}
		}
		
	}
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);	
	queue = new LinkedList<>();
	
	n = sc.nextInt();
	m = sc.nextInt();
	matrix = new int[m][n];
	time = new int[m][n];
	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			matrix[i][j] = sc.nextInt();
		}
	}
	
	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			if(matrix[i][j] == 1){
				queue.offer(new Dot3(i, j)); //�켱 �� ó���� ����ִ� �丶����� ť�� �ִ´�.
				time[i][j] = 1;  
			}
			
		}
	}
	
	bfs();
	
	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			if(time[i][j]==0&&matrix[i][j]!=-1){ //�������� �����鼭 ������ ������� ���� ��
				judge = true;
			}
		}
	}
	
	if(judge){System.out.print(-1); //�Ѱ��� �丶��� ���� �ʾҴٸ� -1
	}else{System.out.print(res);} //��� �� �;��ٸ� �ɸ� �ð� ���
	
	}
}
