package baekjoon;

import java.util.Scanner;

public class MakeOne1463 {
    static int[] arr = new int[1000001];
    static int dp(int x) {
        if (x == 1) return 0;
        if (arr[x] != 0) return arr[x];

        int result = dp(x - 1) + 1;
        if (x % 2 == 0) result = Math.min(result, dp(x / 2) + 1);
        if (x % 3 == 0) result = Math.min(result, dp(x / 3) + 1);
        arr[x] = result;

        return arr[x];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        System.out.println(dp(input));
    }
}
