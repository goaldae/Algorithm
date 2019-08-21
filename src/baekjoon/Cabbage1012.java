package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

class Pos3{
    int x;
    int y;

    Pos3(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Cabbage1012 {

    static int N, M;
    static int[][] cabbage = new int[51][51];
    static LinkedList<Pos3> queue = new LinkedList<>();
    static boolean[][] visited = new boolean[51][51];
    static int[][] direction = {
            {-1, 0, 1, 0}, //x
            {0, 1, 0, -1}};//y
    static int res = 0;

    static void bfs(int x, int y){
        visited[y][x] = true;
        Pos3 p = new Pos3(x, y);
        queue.add(p);

        while(!queue.isEmpty()){
            p = queue.poll();
            for(int j = 0; j<4; j++){
                int tempX = p.x+direction[0][j];
                int tempY = p.y+direction[1][j];
                if(tempX<N&& tempY<M && tempY>-1&&tempX>-1&& visited[tempY][tempX]==false && cabbage[tempY][tempX]==1){
                    queue.add(new Pos3(tempX, tempY));
                    visited[tempY][tempX]= true;
                    cabbage[tempY][tempX] = 9;
                }
            }
        }

        res++;
    }
    public static void main(String[] args){
        int T, P, x, y;
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for(int i = 0; i<T; i++){
            N = sc.nextInt();
            M = sc.nextInt();
            P = sc.nextInt();
            for(int j = 0; j<P; j++){
                x = sc.nextInt();
                y = sc.nextInt();
                cabbage[y][x] = 1;
            }


            for(int k = 0; k<M; k++){
                for(int l = 0; l<N; l++){
                    if(cabbage[k][l]==1 && visited[k][l] == false){
                        bfs(l, k);
                    }
                }

            }
            System.out.println(res);

            cabbage = new int[51][51]; //변수 초기화
            queue = new LinkedList<>();
            visited = new boolean[51][51];
            res = 0;
        }
    }
}
