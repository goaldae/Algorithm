package baekjoon;

import java.util.Scanner;

public class Tetromino14500 {
    static int N;
    static int M;
    static int[][] map;
    static int[][] direction = {{0,0,-1,1},{1,-1,0,0}};
    static boolean[][] visited;
    static int max = 0;

    static void dfs(int i, int j, int count, int sum){
        if (count == 4) { //꺼낸 블록이 이미 네번째일때
            if(max < sum) max = sum;
            return;
        }
        int newX, newY;

        for(int k = 0; k<4; k++){
            newX = j + direction[0][k];
            newY = i + direction[1][k];
            if(newX>N-1||newX<0||newY>M-1||newY<0 || visited[newY][newX]==true) continue;
            visited[newY][newX] = true;
            dfs(newY, newX, count+1, sum+map[newY][newX]);
            visited[newY][newX] = false;
        }
    }
    static void exception(int i, int j){
        int newX;
        int newY;

        int tempSum;
        for(int k = 0 ; k<4; k++){
            tempSum = map[i][j];
            for(int l = k ; l<k+3; l++){
                newX = j+direction[0][l%4];
                newY = i+direction[1][l%4];
                if(newX>N-1||newX<0||newY>M-1||newY<0) break;
                tempSum += map[newY][newX];
            }
            if(tempSum>max) max = tempSum;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        map = new int[M][N];
        visited = new boolean[M][N];
        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                map[i][j] = sc.nextInt();
            }
        }
        for(int i = 0; i<M; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
                exception(i, j);
            }
        }
        System.out.println(max);
    }
}