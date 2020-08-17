#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <limits.h>

using namespace std;

int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, -1, 0, 1 };
int n;
int map[25][25];

struct info {
	int r, c, preDir, cost;
};

int calCost(int preDir, int posDir) {
	if (preDir == 0) {
		if (posDir == 1 || posDir == 3) {
			return 600;
		}
	}
	else if (preDir == 1) {
		if (posDir == 0 || posDir == 2) {
			return 600;
		}
	}
	else if (preDir == 2) {
		if (posDir == 1 || posDir == 3) {
			return 600;
		}
	}
	else if (preDir == 3) {
		if (posDir == 0 || posDir == 2) {
			return 600;
		}
	}
	return 100;
}

int dfs(int preDir, int cost, int r, int c, int answer, vector<vector<int>> board) {
	queue<info> q;
	q.push({ r, c, preDir, cost });
	board[r][c] = 1; //시작점 못가게 만듦

	while(!q.empty()) {
		info temp = q.front();q.pop();

		if (temp.r == n - 1 && temp.c == n - 1) { //도착지점이면
			if (answer > temp.cost) {
				answer = temp.cost;
				map[temp.r][temp.c] = temp.cost;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						printf("%5d", map[i][j]);
					}cout << endl;
				}
			}
			continue;
		}

		for (int k = 0; k < 4; k++) { //네 방향으로 전진
			int nr = temp.r + dy[k];
			int nc = temp.c + dx[k];
			if (nr >= n || nc >= n || nr < 0 || nc < 0 || board[nr][nc]==1||(map[nr][nc]<temp.cost&&map[nr][nc]!=0)) continue;
			int nCost = calCost(temp.preDir, k) + temp.cost;
						
			map[nr][nc] = nCost;
			q.push({ nr, nc, k, nCost });			
		}
	}
	return answer;
}

int solution(vector<vector<int>> board) {
	int answer = INT_MAX;
	n = board.size();

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			map[i][j] = board[i][j];
		}
	}
	
	answer =  dfs(-1, 0, 0, 0, answer, board);
	return answer;
}
	



int main(void) {
	/*vector<vector<int>> input = {
		{0,0,1,0},
		{0,0,0,0},
		{0,1,0,1},
		{1,0,0,0}
	};

	vector<vector<int>> input = {
		{0,0,0,0,0,0,0,1},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,1,0,0},
		{0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,1},
		{0,0,1,0,0,0,1,0},
		{0,1,0,0,0,1,0,0},
		{1,0,0,0,0,0,0,0}
	};*/

	
	vector<vector<int>> input = {
		{0, 0, 0, 0, 0},
		{0, 1, 1, 1, 0},
		{0, 0, 1, 0, 0},
		{1, 0, 0, 0, 1},
		{0, 1, 1, 0, 0}
	};
	
	cout << solution(input) << endl;
}