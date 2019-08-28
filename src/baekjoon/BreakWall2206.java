package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos7{
    int x, y, p;
    int wall;

    Pos7(int x, int y, int wall, int p){
        this.x = x;
        this.y = y;
        this.wall = wall;
        this.p = p;
    }
}

public class BreakWall2206 {
    static Queue<Pos7> queue = new LinkedList<>();
    static int[][] direction = {{-1, 0, 1, 0},{0, 1, 0, -1}};
    static int N, M;
    static int[][] wall = new int[1001][1001];
    static boolean[][][] visited = new boolean[1001][1001][2];
    static int bfs(){
        Pos7 p;
        int x, y;
        queue.add(new Pos7(0, 0, 0, 1));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            p = queue.poll();
            if(p.x == N-1 && p.y == M-1) return p.p;
            for(int i = 0; i<4; i++){
                x = p.x + direction[0][i];
                y = p.y + direction[1][i];
                if(x>=N || y>=M || x<0 || y<0) continue; // 바운더리 체크

                if(wall[y][x] == 1){ // 벽일 때
                    if(p.wall == 1 || visited[y][x][1]) continue;
                    else{
                        queue.add(new Pos7(x, y, 1, p.p+1));
                        visited[y][x][1] = true;
                    }
                }else if(wall[y][x] == 0){ // 길일 때
                    if(visited[y][x][p.wall]) continue;
                    else {
                        queue.add(new Pos7(x, y, p.wall, p.p+1));
                        visited[y][x][p.wall] = true;
                    }

                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        input = br.readLine();

        StringTokenizer st = new StringTokenizer(input, " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i<M; i++){
            input = br.readLine();
            for(int j = 0; j<N; j++){
                wall[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.print(bfs());
    }
}
