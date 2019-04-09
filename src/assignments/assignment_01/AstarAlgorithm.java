package assignments.assignment_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Graph {
    public int size;
    public double[][] edges;
    public boolean[] checked;
    public double[] g; //시작점부터 각 노드까지의 최단거리(비용) = g

    public Graph(int size) {
        this.size = size;
        this.edges = new double[size][size];
        this.checked = new boolean[size];
        this.g = new double[size];
    }

    public void insert(int v1, int v2, double weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
    }

    //시작점과 도착점, 그리고 노드들의 각 정보(휴리스틱, g, f)를 이용해 A star 알고리즘 수행
    public void doAstar(int start, int dest, Node_Info[] nodes){
        checked[start] = true;
        for(int i=0; i<size; i++){
            g[i] = Integer.MAX_VALUE;
        }
        g[start] = 0;
        nodes[start].parent = start;

        //도착점이 마킹되면 종료
        int index = start;
        while (!checked[dest]){
            //인접노드 탐색, F = G + H 값 부여, parent 부여
            for(int i=0; i<size; i++){
                if(!checked[i] && edges[index][i] != 0){
                    double tmp_g = g[i];
                    double tmp_f = 0;

                    //g를 구하기 위해
                    if(g[i] > g[index] + edges[index][i]){
                        g[i] = g[index] + edges[index][i];
                        tmp_g = g[i];
                    }

                    //인접노드까지의 최단거리(G) + 도착점까지의 휴리스틱 값(H)
                    tmp_f = tmp_g + nodes[i].heuristics[dest];

                    //해당 노드가 갖고 있던 G, H, F 값을 더 적은 F비용인 G와 H로 변경
                    if(tmp_f < nodes[i].f){
                        nodes[i].g = tmp_g;
                        nodes[i].f = tmp_f;
                        nodes[i].parent = index;
                    }
                }
            }

            //가장 적은 비용 노드 선택 & 해당 노드 마킹
            double min = Integer.MAX_VALUE;
            for(int i=0; i<size; i++){
                if(!checked[i] && nodes[i].f != Integer.MAX_VALUE){
                    if(nodes[i].f < min){
                        min = nodes[i].f;
                        index = i;
                    }
                }
            }
            checked[index] = true;
        }
    }

    public void printShortestPath(int start, int dest, ArrayList<Integer> pathLists, Node_Info[] nodes){
        if(nodes[dest].parent == start){
            pathLists.add(nodes[dest].parent);
            return;
        }

        int parentNode = nodes[dest].parent;
        pathLists.add(parentNode);

        printShortestPath(start, parentNode, pathLists, nodes);
    }
}

class Node_Info {
    public String name;
    public double[] heuristics;
    public double g;
    public double f;
    public int parent;

    public Node_Info(int size) {
        this.heuristics = new double[size];
        this.g = Integer.MAX_VALUE;
        this.f = Integer.MAX_VALUE;
    }
}

public class AstarAlgorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Insert the number of nodes: ");
        int nodeSize = Integer.parseInt(br.readLine());
        Node_Info[] nodes = new Node_Info[nodeSize];

        //각 노드에 대한 정보 초기화
        for(int i=0; i<nodeSize; i++){
            nodes[i] = new Node_Info(nodeSize);
        }

        //각 노드에 위치(이름) 부여
        nodes[0].name = "Gwangjin";
        nodes[1].name = "Seongdong";
        nodes[2].name = "Gangdong";
        nodes[3].name = "Seocho";
        nodes[4].name = "Gangnam";
        nodes[5].name = "Songpa";
        nodes[6].name = "Hanam";
        nodes[7].name = "Sujeong";
        nodes[8].name = "Jungowon";
        nodes[9].name = "Bundang";

        //각 노드에 휴리스틱 값들을 부여
        nodes[0].heuristics[0] = 0;
        nodes[0].heuristics[1] = 3.9;
        nodes[0].heuristics[2] = 5.8;
        nodes[0].heuristics[3] = 8.7;
        nodes[0].heuristics[4] = 5.6;
        nodes[0].heuristics[5] = 5.3;
        nodes[0].heuristics[6] = 10.6;
        nodes[0].heuristics[7] = 12;
        nodes[0].heuristics[8] = 13.7;
        nodes[0].heuristics[9] = 20;

        nodes[1].heuristics[0] = 3.9;
        nodes[1].heuristics[1] = 0;
        nodes[1].heuristics[2] = 9.4;
        nodes[1].heuristics[3] = 7.9;
        nodes[1].heuristics[4] = 6.2;
        nodes[1].heuristics[5] = 8.7;
        nodes[1].heuristics[6] = 14.4;
        nodes[1].heuristics[7] = 14;
        nodes[1].heuristics[8] = 16.4;
        nodes[1].heuristics[9] = 21.6;

        nodes[2].heuristics[0] = 5.8;
        nodes[2].heuristics[1] = 9.4;
        nodes[2].heuristics[2] = 0;
        nodes[2].heuristics[3] = 11.9;
        nodes[2].heuristics[4] = 8.3;
        nodes[2].heuristics[5] = 5;
        nodes[2].heuristics[6] = 5.4;
        nodes[2].heuristics[7] = 12.2;
        nodes[2].heuristics[8] = 11.5;
        nodes[2].heuristics[9] = 20.5;

        nodes[3].heuristics[0] = 8.7;
        nodes[3].heuristics[1] = 7.9;
        nodes[3].heuristics[2] = 11.9;
        nodes[3].heuristics[3] = 0;
        nodes[3].heuristics[4] = 3.5;
        nodes[3].heuristics[5] = 7.4;
        nodes[3].heuristics[6] = 11.4;
        nodes[3].heuristics[7] = 7.5;
        nodes[3].heuristics[8] = 10.7;
        nodes[3].heuristics[9] = 14.8;

        nodes[4].heuristics[0] = 5.6;
        nodes[4].heuristics[1] = 6.2;
        nodes[4].heuristics[2] = 8.3;
        nodes[4].heuristics[3] = 3.5;
        nodes[4].heuristics[4] = 0;
        nodes[4].heuristics[5] = 5.3;
        nodes[4].heuristics[6] = 11.3;
        nodes[4].heuristics[7] = 7.7;
        nodes[4].heuristics[8] = 10.4;
        nodes[4].heuristics[9] = 15.2;

        nodes[5].heuristics[0] = 5.3;
        nodes[5].heuristics[1] = 8.7;
        nodes[5].heuristics[2] = 5;
        nodes[5].heuristics[3] = 7.4;
        nodes[5].heuristics[4] = 5.3;
        nodes[5].heuristics[5] = 0;
        nodes[5].heuristics[6] = 6.6;
        nodes[5].heuristics[7] = 8.3;
        nodes[5].heuristics[8] = 8.3;
        nodes[5].heuristics[9] = 15.3;

        nodes[6].heuristics[0] = 10.6;
        nodes[6].heuristics[1] = 14.4;
        nodes[6].heuristics[2] = 5.4;
        nodes[6].heuristics[3] = 11.4;
        nodes[6].heuristics[4] = 11.3;
        nodes[6].heuristics[5] = 6.6;
        nodes[6].heuristics[6] = 0;
        nodes[6].heuristics[7] = 11.8;
        nodes[6].heuristics[8] = 8.9;
        nodes[6].heuristics[9] = 16.9;

        nodes[7].heuristics[0] = 12;
        nodes[7].heuristics[1] = 14;
        nodes[7].heuristics[2] = 12.1;
        nodes[7].heuristics[3] = 7.5;
        nodes[7].heuristics[4] = 7.7;
        nodes[7].heuristics[5] = 8.3;
        nodes[7].heuristics[6] = 11.8;
        nodes[7].heuristics[7] = 0;
        nodes[7].heuristics[8] = 5.2;
        nodes[7].heuristics[9] = 6.1;

        nodes[8].heuristics[0] = 13.7;
        nodes[8].heuristics[1] = 16.4;
        nodes[8].heuristics[2] = 11.5;
        nodes[8].heuristics[3] = 10.7;
        nodes[8].heuristics[4] = 10.4;
        nodes[8].heuristics[5] = 8.3;
        nodes[8].heuristics[6] = 8.9;
        nodes[8].heuristics[7] = 5.2;
        nodes[8].heuristics[8] = 0;
        nodes[8].heuristics[9] = 9.4;

        nodes[9].heuristics[0] = 20;
        nodes[9].heuristics[1] = 21.6;
        nodes[9].heuristics[2] = 20.5;
        nodes[9].heuristics[3] = 14.8;
        nodes[9].heuristics[4] = 15.2;
        nodes[9].heuristics[5] = 15.3;
        nodes[9].heuristics[6] = 16.9;
        nodes[9].heuristics[7] = 6.1;
        nodes[9].heuristics[8] = 9.4;
        nodes[9].heuristics[9] = 0;

        //해당 노드와 도착점이 서로 인접하면 해당 G와 H는 같음
        Graph graph = new Graph(nodeSize);
        graph.insert(0, 1, 3.9);
        graph.insert(0, 2, 5.8);
        graph.insert(0, 3, 8.7);
        graph.insert(1, 2, 9.4);
        graph.insert(1, 4, 6.2);
        graph.insert(2, 6, 5.4);
        graph.insert(3, 5, 7.4);
        graph.insert(3, 7, 7.5);
        graph.insert(4, 5, 5.3);
        graph.insert(4, 7, 7.7);
        graph.insert(6, 8, 8.9);
        graph.insert(6, 9, 16.9);
        graph.insert(7, 8, 5.2);
        graph.insert(7, 9, 6.1);


        System.out.print("Input a start node: ");
        int startNode = Integer.parseInt(br.readLine());
        System.out.print("Input a destination node: ");
        int destNode = Integer.parseInt(br.readLine());

        //doAstarAlgorithm
        graph.doAstar(startNode, destNode, nodes);

        ArrayList<Integer> pathLists = new ArrayList<>();
        pathLists.add(destNode);
        graph.printShortestPath(startNode, destNode, pathLists, nodes);

        System.out.println();
        System.out.println("======== Shortest Path ========");
        //print shortest path
        for(int i=pathLists.size()-1; i>=0; i--){
            if(i==0) System.out.println(pathLists.get(i) + "(" + nodes[pathLists.get(i)].name + ")");
            else System.out.print(pathLists.get(i) + "(" + nodes[pathLists.get(i)].name + ") " + "-> ");
        }
    }
}