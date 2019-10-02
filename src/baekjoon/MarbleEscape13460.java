package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class MarbleEscape13460 {
    static int m, n;
    static char[][] maze = new char[11][11];
    static boolean[][][][] visited = new boolean[11][11][11][11];
    static int[][] direction = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
    static Queue<Pos8> q = new LinkedList<>();

    static class Pos8 {
        int rx, ry, bx, by;
        int depth;

        Pos8(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.depth = depth;
        }
    }
    static int[] move(int ch, int rx, int ry, int bx, int by){
        int[] location = new int[4];
        if(ch == 0){ //서
            while (true){
                rx--;
                if(maze[ry][rx] == '#'){
                    rx++;
                    break;
                }else if (maze[ry][rx] == 'O'){
                    break;
                }
            }
            while (true){
                bx--;
                if(maze[by][bx] == '#'){
                    bx++;
                    break;
                }else if (maze[by][bx] == 'O'){
                    break;
                }
            }
        }else if(ch == 1){ //북
            while (true){
                ry--;
                if(maze[ry][rx] == '#'){
                    ry++;
                    break;
                }else if (maze[ry][rx] == 'O'){
                    break;
                }
            }
            while (true){
                by--;
                if(maze[by][bx] == '#'){
                    by++;
                    break;
                }else if (maze[by][bx] == 'O'){
                    break;
                }
            }
        }else if(ch == 2){ //동
            while (true){
                rx++;
                if(maze[ry][rx] == '#'){
                    rx--;
                    break;
                }else if (maze[ry][rx] == 'O'){
                    break;
                }
            }
            while (true){
                bx++;
                if(maze[by][bx] == '#'){
                    bx--;
                    break;
                }else if (maze[by][bx] == 'O'){
                    break;
                }
            }
        }else if(ch == 3){ //남
            while (true){
                ry++;
                if(maze[ry][rx] == '#'){
                    ry--;
                    break;
                }else if (maze[ry][rx] == 'O'){
                    break;
                }
            }
            while (true){
                by++;
                if(maze[by][bx] == '#'){
                    by--;
                    break;
                }else if (maze[by][bx] == 'O'){
                    break;
                }
            }
        }

        location[0] = rx;
        location[1] = ry;
        location[2] = bx;
        location[3] = by;

        return location;
    }
    static int bfs(){
        Pos8 temp;
        int[] location = new int[4]; // rx, ry, bx, by

        for(int i = 0; i<m; i++) {  //초기 R, B 위치 큐에 넣기
            for (int j = 0; j < n; j++) {
                if (maze[i][j] == 'R') {
                    location[1] = i;
                    location[0] = j;
                } else if (maze[i][j] == 'B') {
                    location[3] = i;
                    location[2] = j;
                }
            }
        }
        q.add(new Pos8(location[0],location[1],location[2],location[3], 0));
        visited[location[1]][location[0]][location[3]][location[2]] = true;

        while(!q.isEmpty()){
            temp = q.poll();
            if(temp.depth > 10) {
                return -1; //10번 이상 이동
            }
            if(maze[temp.by][temp.bx] == 'O' && maze[temp.ry][temp.rx]== 'O'){ //한 행위에 파란공과 빨간공이 둘다 빠졌을때
                return -1;
            }else if(maze[temp.ry][temp.rx]== 'O'){ //빨간공만 목적지에 도착
                return temp.depth;
            }

            for(int i = 0; i<4; i++){ //서, 북, 동, 남
                location = move(i, temp.rx, temp.ry, temp.bx, temp.by);

                if(location[3]==location[1] && location[2] == location[0] && maze[location[1]][location[0]] !='O') { //서로 공이 겹칠때
                    if (i == 0) {
                        if (temp.bx > temp.rx) location[2]++;
                        else location[0]++;
                    } else if (i == 1) {
                        if (temp.by > temp.ry) location[3]++;
                        else location[1]++;
                    } else if (i == 2) {
                        if (temp.bx > temp.rx) location[0]--;
                        else location[2]--;
                    } else if (i == 3) {
                        if (temp.by > temp.ry) location[1]--;
                        else location[3]--;
                    }
                }
                if(visited[location[1]][location[0]][location[3]][location[2]] == false){ //방문하지 않았다면 큐에 넣기
                    if(maze[location[3]][location[2]]!='O') q.add(new Pos8(location[0], location[1], location[2],location[3], temp.depth+1));
                    visited[location[1]][location[0]][location[3]][location[2]] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        StringTokenizer st = new StringTokenizer(input, " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for(int i = 0; i<m; i++){
            input = br.readLine();
            for(int j = 0; j<n; j++){
                maze[i][j] = input.charAt(j);
            }
        }

        System.out.print(bfs());
    }
}
