package practice_java;

import java.util.ArrayList;
import java.util.Stack;

class Graph{
    ArrayList<ArrayList<Integer>> g;
    boolean[] visited;
    int n;

    Graph(int n){
        this.n = n;
        this.g = new ArrayList<>();
        this.visited = new boolean[n];

        for(int i = 0; i<n; i++){
            this.g.add(new ArrayList<>());
        }
    }

    void addEdge(int v, int w){
        this.g.get(v).add(w);
        this.g.get(w).add(v);
    }

    void dfs(int v){
        Stack<Integer> s = new Stack<>();
        int temp;
        visited[v] = true;
        s.push(v);

        while(!s.empty()){
            temp = s.pop();
            System.out.print(temp+" ");

            for(int i: this.g.get(temp)){
                if(!visited[i]) {
                    s.push(i);
                    visited[i] = true;
                }
            }
        }

    }
}

public class PracticeDFS {
    public static void main(String[] args){
        Graph g = new Graph(5);
        g.addEdge(3, 4);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(1, 2);
        g.addEdge(0, 4);
        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.dfs(0);
    }
}
