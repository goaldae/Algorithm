package baekjoon.uyoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot_tomato{
    public int y, x, day;

    public Dot_tomato(int y, int x, int day) {
        this.y = y;
        this.x = x;
        this.day = day;
    }
}

public class Tomato7576 {
    static int n, m;
    static int[][] tomatoes;
    static boolean[][] chek;
    static Queue<Dot_tomato> queue;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int day = 0;

    //토마토 중에 익지 않은 토마토, 즉 마킹이 안된 토마토가 존재하면 -> -1
    private static void deterMinimumDay() {
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(tomatoes[i][j] == 0 && !chek[i][j]){
                    day = -1;
                    break;
                }
            }
        }
    }

    private static void doBfs() {
        day = 0;
        Dot_tomato q;
        while (!queue.isEmpty()){
            q = queue.poll();
            day = Math.max(day, q.day);

            for(int i=0; i<4; i++){
                int adj_Y = q.y + dy[i];
                int adj_X = q.x + dx[i];

                if(isRanged(adj_Y, adj_X) && !chek[adj_Y][adj_X]){

                    //인접한 토마토를 익히면서(check = true) 날짜를 하루씩 증가
                    if(tomatoes[adj_Y][adj_X] == 0){
                        chek[adj_Y][adj_X] = true;
                        queue.offer(new Dot_tomato(adj_Y, adj_X, q.day + 1));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String config = br.readLine();
        st = new StringTokenizer(config, " ");
        m = Integer.parseInt(st.nextToken());   //열
        n = Integer.parseInt(st.nextToken());   //행

        tomatoes = new int[n][m];
        chek = new boolean[n][m];

        queue = new LinkedList<>();
        for(int i=0; i<n; i++){
            String input = br.readLine();
            st = new StringTokenizer(input, " ");
            for(int j=0; j<m; j++){
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if(tomatoes[i][j] == 1){
                    chek[i][j] = true;
                    queue.offer(new Dot_tomato(i, j, 0));
                }
            }
        }
        doBfs();
        deterMinimumDay();

        System.out.println(day);
    }

    private static boolean isRanged(int y, int x) {
        if(y >= 0 && y < n && x >= 0 && x < m) return true;
        return false;
    }
}
