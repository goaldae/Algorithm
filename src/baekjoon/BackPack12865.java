package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BackPack12865 {
    static int N;
    static int K;
    static int W;
    static int V;

    static int[][] dp = new int[101][100001];
    static int[][] value = new int[101][100001];
    static int[] weight = new int[100001];

    static int MaxValue(int w, int k){
        if(k == N) return 0;
        if(w < weight[k]) {
            if(k==N-1) return 0;
            else return MaxValue(w, k+1);
        }
        if(dp[k][w] != -1){
            return dp[k][w];
        }

        int res = MaxValue(w-weight[k], k+1) + value[k][weight[k]]; //포함O + 다음값
        res = Math.max(res, MaxValue(w, k+1)); // 포함X + 다음값
        dp[k][w] = res;
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringTokenizer st  = new StringTokenizer(input, " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            input = br.readLine();
            st  = new StringTokenizer(input, " ");
            W = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            weight[i] = W;
            value[i][W] = V;
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 100001; j++){
                dp[i][j] = -1;
            }
        }
        System.out.println(MaxValue(K, 0));
    }
}