package training;

import java.util.*;

public class MatrixProduct {
    public static void main(String[] args) {
        int m, n, l;

        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();
        int[][] arr1 = new int[m][n];

        for(int i =0; i<m; i++){
            for(int j=0; j<n; j++){
                arr1[i][j] = sc.nextInt();
            }
        }

        n = sc.nextInt();
        l = sc.nextInt();
        int[][] arr2 = new int[n][l];

        for(int i =0; i<n; i++){
            for(int j=0; j<l; j++){
                arr2[i][j] = sc.nextInt();
            }
        }

        int[][] res = new int[m][l];
        for(int i =0; i<m; i++){
            for(int j=0; j<l; j++){
                for(int k = 0; k<n; k++){
                    res[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        for(int i =0; i<m; i++) {
            for (int j = 0; j < l; j++) {
                System.out.print(res[i][j]);
            }
            System.out.println();
        }

    }
}
