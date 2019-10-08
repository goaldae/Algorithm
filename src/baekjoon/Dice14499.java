package baekjoon;

import java.util.Scanner;

public class Dice14499 {
    public static void main(String[] args){
        int[][] map = new int[21][21]; //지도
        int[] dicePos = new int[2]; //주사위 위치
        int[] question = new int[10000]; //명령
        int[][] dice = { //주사위 숫자
                {0,0,0},
                {0,0,0},
                {0,0,0},
                {0,0,0},
        };
        int[] head =  {1,1};//주사위 전개도에서 위쪽 위치
        int[] tail =  {3,1};//주사위 전개도에서 아래쪽 위치
        int N;
        int M;
        int qn;

        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        dicePos[0] = sc.nextInt();
        dicePos[1] = sc.nextInt();
        qn = sc.nextInt();

        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                map[i][j] = sc.nextInt();
            }
        }
        for(int i = 0; i<qn; i++) {
            question[i] = sc.nextInt();
        }
        int temp;

        for(int i = 0; i<qn; i++) {
            if(question[i] == 1){ //동
                dicePos[1]++;
                if(dicePos[1]>= N) { //끝에 다다르면
                    dicePos[1]--;
                    continue;
                }
                temp = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = temp;
                if(map[dicePos[0]][dicePos[1]] == 0){ //맵이 0인 경우 주사위 바닥을 복사
                    map[dicePos[0]][dicePos[1]] = dice[tail[0]][tail[1]];
                    System.out.println(dice[head[0]][head[1]]);
                }else { //맵이 0이 아닌경우 주사위에 복사
                    dice[tail[0]][tail[1]] = map[dicePos[0]][dicePos[1]];
                    map[dicePos[0]][dicePos[1]] = 0;
                    System.out.println(dice[head[0]][head[1]]);
                }
            }else if(question[i] == 2){ //서
                dicePos[1]--;
                if(dicePos[1] < 0) { //끝에 다다르면
                    dicePos[1]++;
                    continue;
                }
                temp = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = temp;
                if(map[dicePos[0]][dicePos[1]] == 0){ //맵이 0인 경우 주사위 바닥을 복사
                    map[dicePos[0]][dicePos[1]] = dice[tail[0]][tail[1]];
                    System.out.println(dice[head[0]][head[1]]);
                }else { //맵이 0이 아닌경우 주사위에 복사
                    dice[tail[0]][tail[1]] = map[dicePos[0]][dicePos[1]];
                    map[dicePos[0]][dicePos[1]] = 0;
                    System.out.println(dice[head[0]][head[1]]);
                }
            }else if(question[i] == 3){ //북
                dicePos[0]--;
                if(dicePos[0] < 0) { //끝에 다다르면
                    dicePos[0]++;
                    continue;
                }
                temp = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = temp;
                if(map[dicePos[0]][dicePos[1]] == 0){ //맵이 0인 경우 주사위 바닥을 복사
                    map[dicePos[0]][dicePos[1]] = dice[tail[0]][tail[1]];
                    System.out.println(dice[head[0]][head[1]]);
                }else { //맵이 0이 아닌경우 주사위에 복사
                    dice[tail[0]][tail[1]] = map[dicePos[0]][dicePos[1]];
                    map[dicePos[0]][dicePos[1]] = 0;
                    System.out.println(dice[head[0]][head[1]]);
                }
            }else if(question[i] == 4){ //남
                dicePos[0]++;
                if(dicePos[0] >=M) { //끝에 다다르면
                    dicePos[0]--;
                    continue;
                }
                temp = dice[0][1];
                dice[0][1] = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = temp;
                if(map[dicePos[0]][dicePos[1]] == 0){ //맵이 0인 경우 주사위 바닥을 복사
                    map[dicePos[0]][dicePos[1]] = dice[tail[0]][tail[1]];
                    System.out.println(dice[head[0]][head[1]]);
                }else { //맵이 0이 아닌경우 주사위에 복사
                    dice[tail[0]][tail[1]] = map[dicePos[0]][dicePos[1]];
                    map[dicePos[0]][dicePos[1]] = 0;
                    System.out.println(dice[head[0]][head[1]]);
                }
            }
        }
    }
}
