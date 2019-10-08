package baekjoon;

import java.util.Scanner;

public class Plus123_9095 {
    static int count = 0;

    static void bruteForce(int num) {
        if (num == 0) {
            count++;
            return;
        }
        if (num < 0) {
            return;
        }

        bruteForce(num - 1);
        bruteForce(num - 2);
        bruteForce(num - 3);
    }

    public static void main(String[] args) {
        int n;
        int input;
        int[] arr = new int[100];

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            input = sc.nextInt();
            arr[i] = input;
        }

        for (int i = 0; i < n; i++) {
            bruteForce(arr[i]);
            System.out.println(count);
            count = 0;
        }
    }
}
