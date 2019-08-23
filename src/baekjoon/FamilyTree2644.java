package baekjoon;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Info{
    int n;
    int p;

    Info(int n, int p){
        this.n = n;
        this.p = p;
    }
}
public class FamilyTree2644 {
    static LinkedList<Integer>[] graph = new LinkedList[101];
    static int N;
    static int a, b;
    static int m;
    static Queue<Info> queue = new LinkedList<>();
    static boolean[] visited = new boolean[101];

    static int bfs(int a, int b){
        Info i;
        queue.add(new Info(a, 0));
        visited[a] = true;
        if(a == b) return 0;

        while(!queue.isEmpty()){
            i = queue.poll();
            if(i.n == b) return i.p;
            for(int j = 0; j<graph[i.n].size(); j++){
                if(visited[graph[i.n].get(j)]==false){
                    visited[graph[i.n].get(j)]=true;
                    queue.add(new Info(graph[i.n].get(j), i.p+1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();

        for(int i = 1; i<=N; i++){
            graph[i] = new LinkedList();
        }

        m = sc.nextInt();
        int input1;
        int input2;
        for(int i = 0; i<m; i++){
            input1 = sc.nextInt();
            input2 = sc.nextInt();
            graph[input1].add(input2);
            graph[input2].add(input1);
        }

        System.out.println(bfs(a, b));


    }
}
