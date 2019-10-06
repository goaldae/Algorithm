package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Map{
    int[][] map = new int[21][21];
    int cnt;
    int n;

    Map(int[][] map, int cnt, int n){
        this.n = n;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++) {
                this.map[i][j] = map[i][j];
            }
        }
        this.cnt = cnt;
    }
}

public class Game2048_12100 {
    static int n;


    static int[][] move(int i, int[][] map2) {
        int[][] map = new int[21][21];
        for(int k = 0; k<n; k++){
            for(int j = 0; j<n; j++) {
                map[k][j] = map2[k][j];
            }
        }

        boolean[][] visited = new boolean[21][21];
        int temp;
        int tempPos;
        if (i == 0) { //��
            //System.out.println("1");
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(map[j][k] == 0) continue;
                    temp = map[j][k];
                    map[j][k] = 0;
                    tempPos = k;
                    while (true) {
                        tempPos--;
                        if (tempPos < 0) {
                            tempPos++;
                            map[j][tempPos] = temp;
                            break;
                        } //���� ��Ż
                        else if (map[j][tempPos] != 0) { //���� ����� ��������
                            if (map[j][tempPos] == temp && visited[j][tempPos] == false) { //���� �����̰� ��ģ���� ���� ��
                                map[j][tempPos] += temp;
                                visited[j][tempPos] = true; //��ģ�� üũ
                                break;
                            } else {
                                tempPos++;
                                map[j][tempPos] = temp;
                                break;
                            }
                        }
                    }
                }
            }
        }else if (i == 1) { //��
            //System.out.println("2");
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(map[k][j] == 0) continue;
                    temp = map[k][j];
                    map[k][j] = 0;
                    tempPos = k;
                    while (true) {
                        tempPos--;
                        if (tempPos < 0) {
                            tempPos++;
                            map[tempPos][j] = temp;
                            break;
                        } //���� ��Ż
                        else if (map[tempPos][j] != 0) { //���� ����� ��������
                            if (map[tempPos][j] == temp && visited[tempPos][j] == false) { //���� �����̰� ��ģ���� ���� ��
                                map[tempPos][j] += temp;
                                visited[tempPos][j] = true; //��ģ�� üũ
                                break;
                            } else {
                                tempPos++;
                                map[tempPos][j] = temp;
                                break;
                            }
                        }
                    }
                }
            }
        } else if (i == 2) { //��
            //System.out.println("3");
            for (int j = 0; j < n; j++) {
                for (int k = n-1; k >= 0; k--) {
                    if( map[j][k] == 0) continue;
                    temp = map[j][k];
                    map[j][k] = 0;
                    tempPos = k;
                    while (true) {
                        tempPos++;
                        if (tempPos >= n) {
                            tempPos--;
                            map[j][tempPos] = temp;
                            break;
                        } //���� ��Ż
                        else if (map[j][tempPos] != 0) { //���� ����� ��������
                            if (map[j][tempPos] == temp && visited[j][tempPos] == false) { //���� �����̰� ��ģ���� ���� ��
                                map[j][tempPos] += temp;
                                visited[j][tempPos] = true; //��ģ�� üũ
                                break;
                            } else {
                                tempPos--;
                                map[j][tempPos] = temp;
                                break;
                            }
                        }
                    }
                }
            }
        } else if (i == 3) { //��
            //System.out.println("4");
            for (int j = 0; j < n; j++) {
                for (int k = n-1; k >=0; k--) {
                    if(map[k][j] == 0) continue;
                    temp = map[k][j];
                    map[k][j] = 0;
                    tempPos = k;
                    while (true) {
                        tempPos++;
                        if (tempPos >= n) {
                            tempPos--;
                            map[tempPos][j] = temp;
                            break;
                        } //���� ��Ż
                        else if (map[tempPos][j] != 0) { //���� ����� ��������
                            if (map[tempPos][j] == temp && visited[tempPos][j] == false) { //���� �����̰� ��ģ���� ���� ��
                                map[tempPos][j] += temp;
                                visited[tempPos][j] = true; //��ģ�� üũ
                                break;
                            } else {
                                tempPos--;
                                map[tempPos][j] = temp;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    static int bfs(int[][] map){
        Queue<Map> q = new LinkedList<>();
        q.add(new Map(map, 0, n));
        Map tempMap;
        int max = 0;
        int temp;


        while(!q.isEmpty()){
            tempMap = q.poll();

            for(int i = 0; i<n; i++) {
                for (int j = 0; j < n; j++) {
                    if (tempMap.map[i][j] > max) max = tempMap.map[i][j];
                }
            }

            if(tempMap.cnt<5){
                for(int i = 0; i<4; i++){ //���ϵ��� ���
                    if(i == 0){ //��
                        //System.out.println("1");
                        q.add(new Map(move(i, tempMap.map), tempMap.cnt+1, n));
                    }else if(i == 1){ //��
                        //System.out.println("2");
                        q.add(new Map(move(i, tempMap.map), tempMap.cnt+1, n));
                    }else if(i == 2){ //��
                        //System.out.println("3");
                        q.add(new Map(move(i, tempMap.map), tempMap.cnt+1, n));
                    }else if(i == 3){ //��
                        //System.out.println("4");
                        q.add(new Map(move(i, tempMap.map), tempMap.cnt+1, n));
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args){
        int input;
        int[][] map = new int[21][21];

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                input = sc.nextInt();
                map[i][j] = input;
            }
        }
        int res = bfs(map);
        System.out.println(res);
    }
}
