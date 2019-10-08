package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class snakePos{
    int x;
    int y;
    snakePos(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Info2{
    Queue<snakePos> q;
    int[][] map;
    char[] dirInfo;
    boolean isApple;
    int sec;
    int[] hpos;
    int direction;
    int n;
    boolean end; //게임 종료여부
    Info2(int n){
        this.n = n;
        map = new int[102][102];
        dirInfo = new char[10002];
        for(int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                map[i][j] = 0; //빈칸
            }
        }
        hpos = new int[2];
        hpos[0] = 0; //머리위치 행
        hpos[1] = 0; //머리위치 열
        map[0][0] = 2; //처음 시작위치

        q = new LinkedList<>();
        q.add(new snakePos(0,0)); //꼬리위치 큐삽입

        isApple = false;
        sec = 0;
        direction = 2;
        end = false;
    }
    void markApple(int i, int j){
        map[i][j] = 1; //사과
    }
    void changeDir(int s, char d){
        dirInfo[s] = d;
    }
    void head(int ch){
        if(ch == 2){ // 동쪽
            hpos[1]++;
            if(hpos[1]>= n || map[hpos[0]][hpos[1]]==2){ //벽이나 몸에 부딪히면
                end = true;
                return;
            }
            if(map[hpos[0]][hpos[1]] == 1) isApple = true; //이동했는데 사과면
            q.add(new snakePos(hpos[1], hpos[0]));
            map[hpos[0]][hpos[1]] = 2;
        }else if(ch == 0){ //서
            hpos[1]--;
            if(hpos[1]< 0|| map[hpos[0]][hpos[1]]==2){ //벽이나 몸에 부딪히면
                end = true;
                return;
            }
            if(map[hpos[0]][hpos[1]] == 1) isApple = true; //이동했는데 사과면
            q.add(new snakePos(hpos[1], hpos[0]));
            map[hpos[0]][hpos[1]] = 2;
        }else if(ch == 1){ //북
            hpos[0]--;
            if(hpos[0]< 0 || map[hpos[0]][hpos[1]]==2){ //벽이나 몸에 부딪히면
                end = true;
                return;
            }
            if(map[hpos[0]][hpos[1]] == 1) isApple = true; //이동했는데 사과면
            q.add(new snakePos(hpos[1], hpos[0]));
            map[hpos[0]][hpos[1]] = 2;
        }else if(ch == 3){ //남
            hpos[0]++;
            if(hpos[0] >= n|| map[hpos[0]][hpos[1]]==2){ //벽이나 몸에 부딪히면
                end = true;
                return;
            }
            if(map[hpos[0]][hpos[1]] == 1) isApple = true; //이동했는데 사과면
            q.add(new snakePos(hpos[1], hpos[0]));
            map[hpos[0]][hpos[1]] = 2;
        }

    }
    void tail(){
        snakePos tPos;
        if(isApple == true) { //사과를 만났다면
            isApple = false; //초기화하고
            sec++;
            return; //리턴
        }
        tPos =  q.poll();
        map[tPos.y][tPos.x] = 0; //큐에서 하나 꺼내서 빈칸만들기

        sec++;
    }
    int start(){
        while(!end){
            //System.out.println(sec);
            if(direction == 0){ //어느방향으로 갈지 위치 정하기
                if(dirInfo[sec] == 'D') direction = 1;
                else if(dirInfo[sec] == 'L') direction = 3;
            }else if(direction == 1){ //북
                if(dirInfo[sec] == 'D') direction = 2;
                else if(dirInfo[sec] == 'L') direction = 0;
            }else if(direction == 2){
                if(dirInfo[sec] == 'D') direction = 3;
                else if(dirInfo[sec] == 'L') direction = 1;
            }else if(direction == 3){
                if(dirInfo[sec] == 'D') direction = 0;
                else if(dirInfo[sec] == 'L') direction = 2;
            }
            if(direction == 0){
                head(0);
                tail();
            }else if(direction == 1){
                head(1);
                tail();
            }else if(direction == 2){
                head(2);
                tail();
            }else if(direction == 3){
                head(3);
                tail();
            }
        }
        return sec;
    }
}

public class Snake3190 {
    public static void main(String[] args){
        int n = 0;
        int a = 0;
        int input1, input2;
        char cinput;

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = sc.nextInt();
        Info2 info = new Info2(n);
        for(int i = 0; i<a; i++){
            input1 = sc.nextInt();
            input2 = sc.nextInt();
            info.markApple(input1-1, input2-1);
        }
        n = sc.nextInt();
        for(int i = 0; i<n; i++){
            input1=sc.nextInt();
            cinput = sc.next().charAt(0);
            info.changeDir(input1, cinput);
        }
        System.out.println(info.start());
    }
}
