package baekjoon;

import java.util.*;
import java.io.*;

class Pos2 {
    int m, n, waterNum;

    Pos2(int m, int n, int waterNum){
        this.m = m;
        this.n = n;
        this.waterNum = waterNum;
    }

    Pos2(int m, int n){
        this.m = m;
        this.n = n;
    }
}

public class Iceburg {
    static  int m, n;
    static int[][] iceburg;
    static Queue<Pos2> queue = new LinkedList<>();
    static int[][] direct = {
            {-1, 0, 1, 0},
            {0, 1, 0, -1}};
    static boolean[][] checked;

    static void findStart(){
        for(int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                if(iceburg[i][j]==0) continue;
                queue.add(new Pos2(i, j));
                checked[i][j] = true;
                bfs();
                return;
            }
        }
    }
    static void bfs(){
        Pos2 tempPos;
        while(!queue.isEmpty()){
            tempPos = queue.poll();

            for(int i = 0; i<4; i++){
                int newM = tempPos.m + direct[0][i];
                int newN = tempPos.n + direct[1][i];
                if(newM>=m || newN>=n || newM<0 || newN <0 || iceburg[newM][newN] == 0) continue;

                if(!checked[newM][newN]){
                    queue.add(new Pos2(newM, newN));
                    checked[newM][newN] = true;
                }
            }
        }
    }

    static boolean isDivided(){
        boolean res;
        for(int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                if(iceburg[i][j]==0) continue;
                if(!checked[i][j]){
                    return true;
                }
            }
        }
        checked = new boolean[m][n];
        return false;
    }

    static void melt(){
        for(int i = 0; i <m; i++){
            for(int j = 0; j < n; j++){
                if(iceburg[i][j] == 0) continue;
                int waterCnt = 0;
                for(int k = 0; k<4; k++){
                    int newM = i+direct[0][k];
                    int newN = j+direct[1][k];
                    if(newM>=m || newN>=n || newM<0 || newN <0) continue;
                    if(iceburg[newM][newN]==0) waterCnt++;
                }
                if(waterCnt>0) {
                    queue.add(new Pos2(i, j, waterCnt));
                    //System.out.println(waterCnt);
                }
            }
        }
        Pos2 tempPos;
        while (!queue.isEmpty()){
            tempPos = queue.poll();
            iceburg[tempPos.m][tempPos.n] -= tempPos.waterNum;
            if(iceburg[tempPos.m][tempPos.n] < 0) iceburg[tempPos.m][tempPos.n] = 0;
        }
    }

    static boolean isAllMelt(){
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(iceburg[i][j] != 0) return false;
            }
        }
        return true;
    }

    static  void judge(){
        int meltCount = 0;

        while(true){
            findStart();
            if(isDivided()){
                System.out.print(meltCount);
                break;
            }
            if(isAllMelt()){
                System.out.println("0");
                break;
            }
            melt();
            meltCount++;
        }
    }

    static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input, " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        iceburg = new int[m][n];
        checked = new boolean[m][n];

        for(int i = 0; i < m; i++){
            input = br.readLine();
            st = new StringTokenizer(input, " ");
            for (int j = 0; j < n; j++){
                iceburg[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        judge();
    }
}