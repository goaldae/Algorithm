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
            {-1, 0, -1, 0},
            {0, 1, 0, -1}};
    static int res = 0;

    static void bfs(int x, int y){
        visited[x][y] = true;
        Pos3 p = new Pos3(x, y);
        queue.add(p);

        while(!queue.isEmpty()){
            p = queue.poll();
            for(int i = 0; i<2; i++){
                for(int j = 0; j<4; j++){
                    int tempM = p.x+direction[i][j];
                    int tempN = p.y+direction[i][j];
                    if(tempN>-1&&tempM>-1&&visited[tempM][tempN]==false &&cabbage[tempM][tempN]==1){
                        queue.add(new Pos3(tempM, tempN));
                        visited[tempM][tempN]= true;
                    }
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
            M = sc.nextInt();
            N = sc.nextInt();
            P = sc.nextInt();
            for(int j = 0; j<P; j++){
                x = sc.nextInt();
                y = sc.nextInt();
                cabbage[y][x] = 1;
            }
            for(int k = 0; k<N; k++){
                for(int l = 0; l<M; l++){
                    if(visited[k][l] == false&&cabbage[k][l]==1){
                        bfs(k, l);
                    }
                }

            }
        System.out.println(res);
        }
    }
}
