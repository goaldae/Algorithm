#include <string>
#include <vector>
#include <limits.h>
#include <queue>
#include <iostream>
using namespace std;

int N;
int all;
int getResult(int node, int start, int status, vector<vector<int>>& graph, vector<vector<int>>& dp) {	
	if (node == start && status == all) return dp[all][start] = 0;	
	
	int ret = 100000000;
	int temp = 100000000;
	
	for (int j = 0; j < N; j++) {
		
		if (graph[node][j] != 0&& ((status&(1 << j)) == 0) ) {
			status |= (1 << j); //다음 노드 킴
			if (dp[status][j] != 0) {
				temp = graph[node][j] + dp[status][j];
			}
			else {
				dp[status][j] = getResult(j, start, status, graph, dp);
				temp = graph[node][j] + dp[status][j];
			}
			status &= ~(1 << j); //다음 노드 끔
		}
		if (ret > temp) ret = temp;
	};

	return dp[status][node] = ret;
}

int main() { 
	cin >> N;
	all = 0;
	for (int i = 0;i < N; i++) {
		all += (1 << i);
	}
	vector<vector<int>> graph(N, vector<int>(N));
	vector<vector<int>> dp(all + 1, vector<int>(N));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> graph[i][j];
		}
	}
	
	int answer = getResult(0, 0, 0, graph, dp);
	cout << answer<<endl;
	
	return 0;
}
