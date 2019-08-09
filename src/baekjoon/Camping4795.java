package baekjoon;

import java.util.*;

public class Camping4795 {
    public static void main(String[] args){
        int L, P, V, k;
        int i = 1;
        Scanner sc = new Scanner(System.in);
        int res = 0;

        while(true){
            L = sc.nextInt();
            P = sc.nextInt();
            V = sc.nextInt();
            k = P - L;
            if(P==0 &&L==0 &&V==0) break;

            while(V>=0){
                if(L>=V){
                    res += V;
                    break;
                }else {
                    res += L;
                    V -= L;
                }
                V -= k;
            }
            System.out.println("Case "+i+":"+" "+res);
            i++;
            res = 0;
        }
    }
}
