package baekjoon;

import java.util.*;

public class Square1699 {
    static int num;
    static int s = 1;
    static int[] dp = new int[100001];
    static int IMPOSSIBLE = 1000000;

    static int square(int num, int s){
        if (s*s > num) return IMPOSSIBLE;
        if (s*s == num) return 1;
        if(dp[num] != -1) return dp[num];

        int res = square(num-s*s, s) + 1;
        res = Math.min(res, square(num, s+1));
        dp[num] = res;
        return res;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        for(int i = 0; i<100001; i++){
            dp[i] = -1;
        }

        System.out.println(square(num, s));
    }
}
