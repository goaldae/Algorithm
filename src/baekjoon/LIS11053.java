package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS11053 {
    static int N;
    static int[] arr = new int[1001];
    static int[] dp = new int[1001];

    static int maxValue(int k){
        int max;
        int res = 0;
        for(int i = 0; i <= k; i++){
            max = 0;
            for(int j = 0; j<i; j++){
                if(arr[i]>arr[j] && max<dp[j]){
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
            if(dp[i]>res) res = dp[i];
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        String input = br.readLine();
        st = new StringTokenizer(input, " ");
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i<N; i++){ //dp ÃÊ±âÈ­
            dp[i] = 1;
        }

        System.out.println(maxValue(N-1));
    }
}
