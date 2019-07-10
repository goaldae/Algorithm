package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sticker9465 {
    static int[][] value = new int[2][100000];
    static int[][] dp = new int[3][100000];
    static int s;

    static int getValue(int c, int state){
        if(c == s) return 0;

        if(dp[state][c] != -1) return dp[state][c];

        int result = getValue(c+1, 0);
        if(state != 1) result = Math.max(result, getValue(c+1, 1) + value[0][c]);
        if(state != 2) result = Math.max(result, getValue(c+1, 2) + value[1][c]);

        dp[state][c] = result;
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i<n; i++){
            Arrays.fill(dp[0], -1);
            Arrays.fill(dp[1], -1);
            Arrays.fill(dp[2], -1);
            value = new int[2][100000];
            s = Integer.parseInt(br.readLine());
            for(int j = 0; j < 2; j++){
                String input = br.readLine();
                st = new StringTokenizer(input, " ");
                for(int k = 0; k<s; k++){
                    value[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(getValue(0,0));
        }
    }
}