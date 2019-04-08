package assignments.assignment_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Graph_Test {
    public int size;
    public double[][] edges;
    public boolean[] checked;
    public double[] g; //���������� �� �������� �ִܰŸ�(���) = g

    public Graph_Test(int size) {
        this.size = size;
        this.edges = new double[size][size];
        this.checked = new boolean[size];
        this.g = new double[size];
    }

    public void insert(int v1, int v2, double weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
    }

    //�������� ������, �׸��� ������ �� ����(�޸���ƽ, g, f)�� �̿��� A star �˰��� ����
    public void doAstar(int start, int dest, Node_Info[] nodes){
        checked[start] = true;
        for(int i=0; i<size; i++){
            g[i] = Integer.MAX_VALUE;
        }
        g[start] = 0;
        nodes[start].parent = start;

        int index = start;
        while (true){
            //�������� ��ŷ�Ǹ� ����
            if(checked[dest]){
                return;
            }

            //������� Ž��, F = G + H �� �ο�, parent �ο�
            for(int i=0; i<size; i++){
                if(!checked[i] && edges[index][i] != 0){
                    double tmp_g = g[i];
                    double tmp_f = 0;

                    //g�� ���ϱ� ����
                    if(g[i] > g[index] + edges[index][i]){
                        g[i] = g[index] + edges[index][i];
                        tmp_g = g[i];
                    }

                    //������������ �ִܰŸ�(G) + ������������ �޸���ƽ ��(H)
                    tmp_f = tmp_g + nodes[i].heuristics[dest];

                    //�ش� ��尡 ���� �ִ� G, H, F ���� �� ���� F����� G�� H�� ����
                    if(tmp_f < nodes[i].f){
                        nodes[i].g = tmp_g;
                        nodes[i].f = tmp_f;
                        nodes[i].parent = index;
                    }
                }
            }

            //���� ���� ��� ��� ���� & �ش� ��� ��ŷ
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
        System.out.print("��� ������ �Է��ϼ���: ");
        int nodeSize = Integer.parseInt(br.readLine());
        Node_Info[] nodes = new Node_Info[nodeSize];

        //�� ��忡 ���� ���� �ʱ�ȭ
        for(int i=0; i<nodeSize; i++){
            nodes[i] = new Node_Info(nodeSize);
        }

        //�� ��忡 �޸���ƽ ������ �ο�
        nodes[0].heuristics[0] = 0;
        nodes[0].heuristics[1] = 10;
        nodes[0].heuristics[2] = 12.5;
        nodes[0].heuristics[3] = 14;
        nodes[0].heuristics[4] = 13;
        nodes[0].heuristics[5] = 25;

        nodes[1].heuristics[0] = 10;
        nodes[1].heuristics[1] = 0;
        nodes[1].heuristics[2] = 6;
        nodes[1].heuristics[3] = 7.5;
        nodes[1].heuristics[4] = 8.5;
        nodes[1].heuristics[5] = 9;

        nodes[2].heuristics[0] = 12.5;
        nodes[2].heuristics[1] = 6;
        nodes[2].heuristics[2] = 0;
        nodes[2].heuristics[3] = 11.5;
        nodes[2].heuristics[4] = 5.5;
        nodes[2].heuristics[5] = 19.5;

        nodes[3].heuristics[0] = 14;
        nodes[3].heuristics[1] = 7.5;
        nodes[3].heuristics[2] = 11.5;
        nodes[3].heuristics[3] = 0;
        nodes[3].heuristics[4] = 18;
        nodes[3].heuristics[5] = 3;

        nodes[4].heuristics[0] = 13;
        nodes[4].heuristics[1] = 8.5;
        nodes[4].heuristics[2] = 5.5;
        nodes[4].heuristics[3] = 18;
        nodes[4].heuristics[4] = 0;
        nodes[4].heuristics[5] = 20;

        nodes[5].heuristics[0] = 25;
        nodes[5].heuristics[1] = 9;
        nodes[5].heuristics[2] = 19.5;
        nodes[5].heuristics[3] = 3;
        nodes[5].heuristics[4] = 20;
        nodes[5].heuristics[5] = 0;


        //�ش� ���� �������� ���� �����ϸ� �ش� G�� H�� ����
        Graph_Test graph = new Graph_Test(nodeSize);
        graph.insert(0, 1, 10);
        graph.insert(0,2, 12.5);
        graph.insert(1,2, 6);
        graph.insert(1,3, 7.5);
        graph.insert(1,4, 8.5);
        graph.insert(2,4, 5.5);
        graph.insert(3,5, 3);
        graph.insert(4,5, 20);

        System.out.print("���� ��ġ�� �Է��ϼ���: ");
        int startNode = Integer.parseInt(br.readLine());
        System.out.print("���� ��ġ�� �Է��ϼ���: ");
        int destNode = Integer.parseInt(br.readLine());

        //doAstarAlgorithm
        graph.doAstar(startNode, destNode, nodes);

        ArrayList<Integer> pathLists = new ArrayList<>();
        pathLists.add(destNode);
        graph.printShortestPath(startNode, destNode, pathLists, nodes);

        //print shortest path
        for(int i=pathLists.size()-1; i>=0; i--){
            if(i==0) System.out.println(pathLists.get(i));
            else System.out.print(pathLists.get(i) + "-> ");
        }
    }
}