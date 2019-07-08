package baekjoon;

import java.util.Scanner;

public class Tiling314852 {
    static long[][] arr = new long[2][1000001];

    static long dp(int x){
        arr[0][1] = 2;
        arr[0][2] = 7;
        arr[0][0] = 1;
        arr[1][3] = 0;
        for(int i = 3; i<=x; i++){
            arr[0][i] = (arr[0][i-1] * 2 + arr[0][i-2] * 3 + arr[0][i-3] * 2 + arr[1][i]) % 1000000007;
            arr[1][i+1] = (arr[0][i-3] * 2 + arr[1][i])% 1000000007;
        }
        return arr[0][x];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        System.out.println(dp(input));

    }
}
