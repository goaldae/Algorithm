package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos6{
    int x, y, p;

    Pos6(int x, int y, int p){
        this.x = x;
        this.y = y;
        this.p = p;
    }
}

public class Fire5427 {
    static int W,H;
    static char[][] fire = new char[1001][1001];
    static boolean[][] fvisited = new boolean[1001][1001];
    static boolean[][] svisited = new boolean[1001][1001];
    static Queue<Pos6> squeue = new LinkedList<>();
    static Queue<Pos6> fqueue = new LinkedList<>();
    static int[][] direction = {
            {-1, 0, 1, 0},{0, 1, 0, -1}};

    static int bfs(){
        Pos6 sp;
        Pos6 fp;
        int tempX, tempY;
        int step;
        while(!squeue.isEmpty()){
            step = squeue.peek().p;

            while(!fqueue.isEmpty()&&step>=fqueue.peek().p){
                fp = fqueue.poll();
                for(int i= 0; i<4; i++){
                    tempX = fp.x + direction[0][i];
                    tempY = fp.y + direction[1][i];
                    if(tempX<0||tempY<0||tempX>=W||tempY>=H||fvisited[tempY][tempX]||fire[tempY][tempX]!='.') continue;
                    fqueue.add(new Pos6(tempX, tempY, fp.p+1));
                    fvisited[tempY][tempX] = true;
                }
            }
            while(!squeue.isEmpty()&&step>=squeue.peek().p){
                sp = squeue.poll();
                if(sp.x == W-1 || sp.x == 0||sp.y == H-1||sp.y == 0) return sp.p;
                for(int i= 0; i<4; i++){
                    tempX = sp.x + direction[0][i];
                    tempY = sp.y + direction[1][i];
                    if(tempX<0||tempY<0||tempX>=W||tempY>=H||fvisited[tempY][tempX]||svisited[tempY][tempX]||fire[tempY][tempX]!='.') continue;
                    squeue.add(new Pos6(tempX, tempY, sp.p+1));
                    svisited[tempY][tempX] = true;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        int t;
        int res;
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i = 0; i<t; i++){
            input = br.readLine();
            st = new StringTokenizer(input, " ");
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            for(int j = 0; j<H; j++) {
                input = br.readLine();
                for (int k = 0; k < W; k++) {
                    fire[j][k] = input.charAt(k);
                }
            }
            for(int j = 0; j<H; j++) {
                for (int k = 0; k < W; k++) {
                    if(fire[j][k] == '*'){
                        fqueue.add(new Pos6(k, j, 1));
                        fvisited[j][k] = true;
                    }else if(fire[j][k] == '@'){
                        squeue.add(new Pos6(k, j, 1));
                        svisited[j][k] = true;
                    }
                }
            }
            res = bfs();
            if(res==-1){
                System.out.println("IMPOSSIBLE");
            }else System.out.println(res);

            fire = new char[1001][1001];
            fvisited = new boolean[1001][1001];
            svisited = new boolean[1001][1001];
            squeue = new LinkedList<>();
            fqueue = new LinkedList<>();
        }
    }
}
