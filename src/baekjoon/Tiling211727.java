package baekjoon;

import java.util.Scanner;

public class Tiling211727 {
    static int arr[] = new int[10000];

    static int dp(int x){
        if(x == 1) return 1;
        if(x == 2 ) return 3;
        if(arr[x] != 0) return arr[x];

        return arr[x] = (dp(x-1) + 2*dp(x-2)) % 10007;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        System.out.println(dp(input));
    }
}
