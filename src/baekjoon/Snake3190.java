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
    boolean end; //���� ���Ῡ��
    Info2(int n){
        this.n = n;
        map = new int[102][102];
        dirInfo = new char[10002];
        for(int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                map[i][j] = 0; //��ĭ
            }
        }
        hpos = new int[2];
        hpos[0] = 0; //�Ӹ���ġ ��
        hpos[1] = 0; //�Ӹ���ġ ��
        map[0][0] = 2; //ó�� ������ġ

        q = new LinkedList<>();
        q.add(new snakePos(0,0)); //������ġ ť����

        isApple = false;
        sec = 0;
        direction = 2;
        end = false;
    }
    void markApple(int i, int j){
        map[i][j] = 1; //���
    }
    void changeDir(int s, char d){
        dirInfo[s] = d;
    }
    void head(int ch){
        if(ch == 2){ // ����
            hpos[1]++;
            if(hpos[1]>= n || map[hpos[0]][hpos[1]]==2){ //���̳� ���� �ε�����
                end = true;
                return;
            }
            if(map[hpos[0]][hpos[1]] == 1) isApple = true; //�̵��ߴµ� �����
            q.add(new snakePos(hpos[1], hpos[0]));
            map[hpos[0]][hpos[1]] = 2;
        }else if(ch == 0){ //��
            hpos[1]--;
            if(hpos[1]< 0|| map[hpos[0]][hpos[1]]==2){ //���̳� ���� �ε�����
                end = true;
                return;
            }
            if(map[hpos[0]][hpos[1]] == 1) isApple = true; //�̵��ߴµ� �����
            q.add(new snakePos(hpos[1], hpos[0]));
            map[hpos[0]][hpos[1]] = 2;
        }else if(ch == 1){ //��
            hpos[0]--;
            if(hpos[0]< 0 || map[hpos[0]][hpos[1]]==2){ //���̳� ���� �ε�����
                end = true;
                return;
            }
            if(map[hpos[0]][hpos[1]] == 1) isApple = true; //�̵��ߴµ� �����
            q.add(new snakePos(hpos[1], hpos[0]));
            map[hpos[0]][hpos[1]] = 2;
        }else if(ch == 3){ //��
            hpos[0]++;
            if(hpos[0] >= n|| map[hpos[0]][hpos[1]]==2){ //���̳� ���� �ε�����
                end = true;
                return;
            }
            if(map[hpos[0]][hpos[1]] == 1) isApple = true; //�̵��ߴµ� �����
            q.add(new snakePos(hpos[1], hpos[0]));
            map[hpos[0]][hpos[1]] = 2;
        }

    }
    void tail(){
        snakePos tPos;
        if(isApple == true) { //����� �����ٸ�
            isApple = false; //�ʱ�ȭ�ϰ�
            sec++;
            return; //����
        }
        tPos =  q.poll();
        map[tPos.y][tPos.x] = 0; //ť���� �ϳ� ������ ��ĭ�����

        sec++;
    }
    int start(){
        while(!end){
            //System.out.println(sec);
            if(direction == 0){ //����������� ���� ��ġ ���ϱ�
                if(dirInfo[sec] == 'D') direction = 1;
                else if(dirInfo[sec] == 'L') direction = 3;
            }else if(direction == 1){ //��
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
