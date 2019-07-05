package baekjoon;

import java.util.Scanner;

public class Tiling1793 {
    int[] arr = new int[10000];

    int dp(int x){
        if(x == 1) return 1;
        if(x == 2) return 2;
        if(arr[x] != 0) return arr[x];

        return arr[x] = (dp(x-1) + dp(x-2))%10007;
    }

    public static void main(String[] args) {
        Tiling1793 t = new Tiling1793();
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        System.out.println(t.dp(input));
    }
}
