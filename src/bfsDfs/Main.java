package bfsDfs;

public class Main {

	public static void main(String[] args) {
		Graph g = new Graph(9);
		//한 방향만 입력
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 7);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 6);
		g.addEdge(6, 7);
		g.addEdge(6, 8);
		g.addEdge(7, 8);
		
		g.bfs(0);
	}

}
