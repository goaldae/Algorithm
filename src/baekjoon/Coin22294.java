package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Coin22294 {
    static int[] coin = new int[101];
    static int[][] dp = new int[101][10001];
    static int N;
    static int K;
    static int IMPOSSIBLE = 100000000;

    static int coinCount(int money, int n){
        if(n == N) return (money == 0 ? 0 : IMPOSSIBLE);
        if(dp[n][money] != -1) return dp[n][money];

        int result = coinCount(money, n+1);
        if(money >= coin[n]) result = Math.min(result, coinCount(money-coin[n], n)+1);
        dp[n][money] = result;
        return result;
    }

    public  static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input, " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        for(int i = 0; i<101; i++){
            for(int j = 0; j<10001; j++){
                dp[i][j] = -1;
            }
        }
        for(int i = 0; i<N; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        int result = coinCount(K, 0);
        if(result == IMPOSSIBLE) result = -1;
        System.out.println(result);

    }
}
