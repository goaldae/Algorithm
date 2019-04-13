package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Ecology4358 implements Comparator<String>{
    @Override
    public int compare(String o1, String o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        return o1.compareTo(o2);
    }
    static String[] trees;
    static int[] treesNum;
    static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        trees = new String[1000000];
        treesNum = new int[10000];

        String input;
        int i = 0;

        while((input=br.readLine()) != null && input.length()!=0){
            trees[i++] = input;
        }
        total = i;

        Arrays.sort(trees);

        System.out.println(trees[0]==trees[1]);
        i=0;
        int count = 1;
        int j = 0;
        while(trees[i] != null && i < total){
            while(trees[i].compareTo(trees[i+1])==0){

                count++;
                i++;
            }
            System.out.println(count);
            treesNum[j] = count;
            i++;
            j++;
            count = 1;
        }

        /*
        i = 0;
        j = 0;
        while(trees[i] != null){
            if(trees[i] != trees[i+1]){
                System.out.printf("%s %.4f\n", trees[i], (double)treesNum[j]/total);
                j++;
                i++;
            }
            i++;
        }*/
        i = 0;
        System.out.println(trees[0].compareTo(trees[1]));

        while (trees[i]!=null){
            System.out.println(trees[i]);
            i++;
        }





    }
}




