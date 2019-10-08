package baekjoon;

import java.util.Scanner;

public class Dice14499 {
    public static void main(String[] args){
        int[][] map = new int[21][21]; //����
        int[] dicePos = new int[2]; //�ֻ��� ��ġ
        int[] question = new int[10000]; //���
        int[][] dice = { //�ֻ��� ����
                {0,0,0},
                {0,0,0},
                {0,0,0},
                {0,0,0},
        };
        int[] head =  {1,1};//�ֻ��� ���������� ���� ��ġ
        int[] tail =  {3,1};//�ֻ��� ���������� �Ʒ��� ��ġ
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
            if(question[i] == 1){ //��
                dicePos[1]++;
                if(dicePos[1]>= N) { //���� �ٴٸ���
                    dicePos[1]--;
                    continue;
                }
                temp = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = temp;
                if(map[dicePos[0]][dicePos[1]] == 0){ //���� 0�� ��� �ֻ��� �ٴ��� ����
                    map[dicePos[0]][dicePos[1]] = dice[tail[0]][tail[1]];
                    System.out.println(dice[head[0]][head[1]]);
                }else { //���� 0�� �ƴѰ�� �ֻ����� ����
                    dice[tail[0]][tail[1]] = map[dicePos[0]][dicePos[1]];
                    map[dicePos[0]][dicePos[1]] = 0;
                    System.out.println(dice[head[0]][head[1]]);
                }
            }else if(question[i] == 2){ //��
                dicePos[1]--;
                if(dicePos[1] < 0) { //���� �ٴٸ���
                    dicePos[1]++;
                    continue;
                }
                temp = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = temp;
                if(map[dicePos[0]][dicePos[1]] == 0){ //���� 0�� ��� �ֻ��� �ٴ��� ����
                    map[dicePos[0]][dicePos[1]] = dice[tail[0]][tail[1]];
                    System.out.println(dice[head[0]][head[1]]);
                }else { //���� 0�� �ƴѰ�� �ֻ����� ����
                    dice[tail[0]][tail[1]] = map[dicePos[0]][dicePos[1]];
                    map[dicePos[0]][dicePos[1]] = 0;
                    System.out.println(dice[head[0]][head[1]]);
                }
            }else if(question[i] == 3){ //��
                dicePos[0]--;
                if(dicePos[0] < 0) { //���� �ٴٸ���
                    dicePos[0]++;
                    continue;
                }
                temp = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = temp;
                if(map[dicePos[0]][dicePos[1]] == 0){ //���� 0�� ��� �ֻ��� �ٴ��� ����
                    map[dicePos[0]][dicePos[1]] = dice[tail[0]][tail[1]];
                    System.out.println(dice[head[0]][head[1]]);
                }else { //���� 0�� �ƴѰ�� �ֻ����� ����
                    dice[tail[0]][tail[1]] = map[dicePos[0]][dicePos[1]];
                    map[dicePos[0]][dicePos[1]] = 0;
                    System.out.println(dice[head[0]][head[1]]);
                }
            }else if(question[i] == 4){ //��
                dicePos[0]++;
                if(dicePos[0] >=M) { //���� �ٴٸ���
                    dicePos[0]--;
                    continue;
                }
                temp = dice[0][1];
                dice[0][1] = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = temp;
                if(map[dicePos[0]][dicePos[1]] == 0){ //���� 0�� ��� �ֻ��� �ٴ��� ����
                    map[dicePos[0]][dicePos[1]] = dice[tail[0]][tail[1]];
                    System.out.println(dice[head[0]][head[1]]);
                }else { //���� 0�� �ƴѰ�� �ֻ����� ����
                    dice[tail[0]][tail[1]] = map[dicePos[0]][dicePos[1]];
                    map[dicePos[0]][dicePos[1]] = 0;
                    System.out.println(dice[head[0]][head[1]]);
                }
            }
        }
    }
}
