package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ecology4358 {
    static String[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        trees = new String[2];

        trees[0] = br.readLine();
        trees[1] = br.readLine();
        for(String temp: trees){
            System.out.println(temp);
        }
    }
}
