package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pos5{
    int x, y, p;

    Pos5(int x, int y, int p){
        this.p = p;
        this.x = x;
        this.y = y;
    }
}

public class Knight7562 {
    static int T;
    static int L;

    static int desX, desY;
    static Queue<Pos5> queue = new LinkedList<>();
    static boolean[][] visited = new boolean[301][301];
    static int[][] direction = {
            {-2, -1, 1, 2, 2, 1, -1, -2},
            {1, 2, 2, 1, -1, -2, -2, -1}};

    static int bfs(int x, int y){
        Pos5 p;
        int tempX, tempY;
        if(x==desX && y==desY) return  0;
        queue.add(new Pos5(x, y, 0));
        visited[y][x] = true;

        while (!queue.isEmpty()){
            p = queue.poll();
            if(p.x == desX && p.y == desY) return p.p;
            for(int i = 0; i<8; i++){
                tempX = p.x + direction[0][i];
                tempY = p.y + direction[1][i];
                if(tempX>=L||tempY>=L||tempX<0||tempY<0||visited[tempY][tempX]) continue;
                visited[tempY][tempX] = true;
                queue.add(new Pos5(tempX, tempY, p.p+1));
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        int x,y;
        for(int i = 0; i<T; i++){
            L = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            desX = sc.nextInt();
            desY = sc.nextInt();
            System.out.println(bfs(x, y));
            queue = new LinkedList<>();
            visited = new boolean[301][301];
        }
    }
}
