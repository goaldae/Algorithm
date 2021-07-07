package baekjoon.uyoo;

/*
* for문으로 단지 수를 카운팅 && 각 단지를 돌 때 집 개수 카운팅
* 배열 써서 stl 없이 정렬 사용 ex. 선택정렬, 버블정렬, 삽입정렬, mergeSort, quicksort
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Dot_2667 {
    public int y, x;

    public Dot_2667(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class EstateNum_2667 {

    static int size;
    static int[][] map;
    static boolean[][] checked;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int estateNum = 0;   //단지 수
    static int[] houseNums;     //각 단지 별 집(가구) 수
    static int houseNumsSize;

    private static int doBfs(int y, int x) {
        Queue<Dot_2667> queue = new LinkedList<>();
        checked[y][x] = true;
        queue.offer(new Dot_2667(y, x));

        int house = 1;
        Dot_2667 q;
        while (!queue.isEmpty()){
            q = queue.poll();

            for(int i=0; i<4; i++){
                int adj_Y = q.y + dy[i];
                int adj_X = q.x + dx[i];

                if(isRanged(adj_Y, adj_X) && !checked[adj_Y][adj_X]){
                    if(map[adj_Y][adj_X] == 1){
                        checked[adj_Y][adj_X] = true;
                        queue.offer(new Dot_2667(adj_Y, adj_X));
                        house++;
                    }
                }
            }
        }
        return house;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        checked = new boolean[size][size];

        houseNumsSize = ((size+1) / 2) * size;
        houseNums = new int[houseNumsSize];

        for(int i=0; i<size; i++){
            String input = br.readLine();
            for(int j=0; j<size; j++){
                map[i][j] = Integer.parseInt(input.substring(j, j+1));
            }
        }

        int sp = 0;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(!checked[i][j] && map[i][j] == 1){
                    houseNums[sp++] = doBfs(i, j);
                    estateNum++;
                }
            }
        }

        //각 단지 수
        System.out.println(estateNum);

        //선택정렬을 통한 오름차순
        for(int i=0; i<houseNumsSize-1; i++){
            for(int j=i; j<houseNumsSize; j++){
                if(houseNums[i] > houseNums[j]){
                    int tmp = houseNums[i];
                    houseNums[i] = houseNums[j];
                    houseNums[j] = tmp;
                }
            }
        }

        for(int res : houseNums){
            if(res == 0) continue;
            System.out.println(res);
        }
    }

    private static boolean isRanged(int y, int x) {
        if(y >= 0 && y < size && x >= 0 && x < size) return true;
        return false;
    }
}
