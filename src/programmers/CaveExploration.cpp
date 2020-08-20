#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

bool visited[200000];
bool visitStack[200000];
vector<int> graph[200000];

bool cycleCheck(int cur) {
	visitStack[cur] = true;
	visited[cur] = true;

	for (int i = 0; i < graph[cur].size(); i++) {
		if (visitStack[graph[cur][i]]) {
			return false;
		}
		else if (visited[graph[cur][i]]) continue;
		else {			
			if(!cycleCheck(graph[cur][i])) return false;
			visitStack[graph[cur][i]] = false;
		}		
	}
	return true;	
}


void makeDirGraph(vector<vector<int>>& path, vector<vector<int>>& order, int cur) {
	visitStack[cur] = true;
	visited[cur] = true;

	for (int i = 0; i < graph[cur].size(); i++) {
		if (visitStack[graph[cur][i]]) {
			graph[cur].erase(graph[cur].begin() + i);
			i--;
		}
		else if (visited[graph[cur][i]]) continue;
		else {			
			makeDirGraph(path, order, graph[cur][i]);
			visitStack[graph[cur][i]] = false;
		}		
	}
}

bool solution(int n, vector<vector<int>> path, vector<vector<int>> order) {
	for (int i = 0; i < path.size(); i++) { //무방향 그래프 만들기
		graph[path[i][0]].push_back(path[i][1]);
		graph[path[i][1]].push_back(path[i][0]);
	}
	
	makeDirGraph(path, order, 0); //0을 기준으로 방향 그래프 만들기
	for (int i = 0; i < order.size(); i++) {
		graph[order[i][0]].push_back(order[i][1]);
	}
	
	for (int i = 0; i < n; i++) {
		visited[i] = false;
		visitStack[i] = false;
	}
	
	bool answer = cycleCheck(0);

	return answer;
}

int main(void) {
	vector<vector<int>> path = { {8,1}, {0,1}, {1,2}, {0,7},
	{4,7}, {0,3}, {7,5}, {3,6} };
	vector<vector<int>> order = { {4,1}, {8, 7}, {6, 5} };
	int n = 9;

	cout<<solution(n, path, order)<<endl;	
}