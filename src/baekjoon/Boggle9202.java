package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Boggle9202 {
    static int w,n;
    static char[][] words;
    static char[][][] boggle;

    static int point;
    static boolean find;

    static int sumPoint = 0;
    static int longindex = -1;
    static int findNum = 0;

    static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        w = Integer.parseInt(br.readLine());
        words = new char[w][8];
        String input;
        for(int i = 0; i<w; i++){
            input = br.readLine();
            words[i] = input.toCharArray();
        }
        br.readLine();

        n = Integer.parseInt(br.readLine());
        boggle = new char[n][4][4];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<4; j++){
                input = br.readLine();
                boggle[i][j] = input.toCharArray();
            }
            if(i!=n-1)br.readLine();
        }
    }

    static boolean findWord(int i, int j, int k, int wordNum, int c) {
        //System.out.printf("%d %c %c\n", k, boggle[0][i][j], words[2][k]);
        if(i>3||j>3||i<0||j<0) return false;

        int[][] direct = {
                {-1,-1,-1, 0, 1, 1, 1, 0},
                {-1, 0, 1, 1, 1, 0, -1, -1}};
        if ((words[wordNum][k] == boggle[c][i][j]) && k == words[wordNum].length - 1) { //마지막 글자에 다다랐을 때
            if(words[wordNum].length == 2 || words[wordNum].length == 1){
                point = 0;
            }else if(words[wordNum].length == 4 || words.length == 3){
                point = 1;
            }else if(words[wordNum].length == 5){
                point = 2;
            }else if(words[wordNum].length == 6){
                point = 3;
            }else if(words[wordNum].length == 7){
                point = 5;
            }else if(words[wordNum].length == 8){
                point = 11;
            }
            find = true; //단어가 있음
            findNum++; //찾은 개수 올리기
            sumPoint += point; //점수합산
            if(longindex == -1) longindex = wordNum;
            else if(words[wordNum].length>words[longindex].length) longindex = wordNum;//긴글자면 갱신
            else if(words[wordNum].length == words[longindex].length){ //길이가 같을 때
                int compare = String.valueOf(words[wordNum]).compareTo(String.valueOf(words[longindex])); //두 문자열 사전순서 비교
                if(compare<0){ //사전 순서가 앞이면 갱신
                    longindex = wordNum;
                }
            }

            return true;
        } else if (words[wordNum][k] == boggle[c][i][j]) { //현재 위치가 이어지는 글자일때
            //System.out.printf("%d %c %c\n", k, boggle[c][i][j], words[wordNum][k]);
            k++;
            if(findWord(i + direct[0][1], j + direct[1][1], k, wordNum, c)|| findWord(i + direct[0][2], j + direct[1][2], k, wordNum, c)||
                    findWord(i + direct[0][3], j + direct[1][3], k, wordNum, c)||findWord(i + direct[0][4], j + direct[1][4], k, wordNum, c)||
                    findWord(i + direct[0][5], j + direct[1][5], k, wordNum, c)||findWord(i + direct[0][6], j + direct[1][6], k, wordNum, c)||
                    findWord(i + direct[0][7], j + direct[1][7], k, wordNum, c)||findWord(i + direct[0][0], j + direct[1][0], k, wordNum, c)){
                return true;
            } //팔방을 탐색
        } else { //이어지는 글자가 아닐 때
            return false;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        init();
        int i = 0;
        int j = 0;

        int wordsNum = 0;
        for(int c = 0; c<n; c++){
            while (true) {
                while (true) {
                    if((i == 3 && j == 3)) break;
                    if (findWord(i, j, 0, wordsNum, c)) {
                        break;
                    }
                    j++;
                    if (j == 4) {
                        j = 0;
                        i++;
                    }
                }
                wordsNum++;
                i=0;
                j=0;
                if(wordsNum==w) {
                    wordsNum = 0;
                    break;
                }
            }
            if(find){
                System.out.print(sumPoint+" ");
                for (int k = 0; k<words[longindex].length; k++){
                    System.out.print(words[longindex][k]);
                }
                System.out.println(" "+findNum);
                find = false;
            }
            sumPoint = 0;
            findNum = 0;
            longindex = -1;
        }




        /*for(int i = 0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(boggle[1][i][j]);
            }
            System.out.println();
        }*/
    }
}
