#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int N;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, -1, 0, 1 };

vector<vector<int>> glbMap;
bool visited[101][101][101][101];
struct Info {
	pair<int, int> robot[2];
	int time;
};

void rotate(pair<int, int> a, pair<int, int> b, int l, queue<Info>& q, int time) {
	if (l == 0) { //시계방향일 때
		if (b.first - a.first == 0) { //양옆에 붙어있을 때
			if (b.second - a.second == -1) { //왼쪽에 붙어있을 때
				if (b.first - 1 < 0 || glbMap[b.first - 1][b.second] == 1|| glbMap[a.first - 1][a.second] == 1||visited[a.first][a.second][b.first-1][b.second+1]) return;
				b.first--;
				b.second++;
			}
			else { //오른쪽에 붙어있을 때
				if (b.first + 1 > N - 1 || glbMap[b.first + 1][b.second] == 1 || glbMap[a.first + 1][a.second] == 1 || visited[a.first][a.second][b.first+1][b.second-1]) return;
				b.first++;
				b.second--;
			}
		}
		else { //위아래로 붙어있을 때
			if (b.first - a.first == -1) { //위에 붙어있을 때
				if (b.second + 1 > N - 1 || glbMap[b.first][b.second + 1] == 1 || glbMap[a.first][a.second + 1] == 1 || visited[a.first][a.second][b.first+1][b.second+1]) return;
				b.first++;
				b.second++;
			}
			else { //아래에 붙어있을 때
				if (b.second - 1 < 0 || glbMap[b.first][b.second - 1] == 1 || glbMap[a.first][a.second - 1] == 1 || visited[a.first][a.second][b.first-1][b.second-1]) return;
				b.first--;
				b.second--;
			}
		}
	}
	else { //반시계방향일 때
		if (b.first - a.first == 0) { //양옆에 붙어있을 때
			if (b.second - a.second == -1) { //왼쪽에 붙어있을 때
				if (b.first + 1 > N - 1 || glbMap[b.first + 1][b.second] == 1 || glbMap[a.first + 1][a.second] == 1 || visited[a.first][a.second][b.first+1][b.second+1]) return;
				b.first++;
				b.second++;
			}
			else { //오른쪽에 붙어있을 때
				if (b.first - 1 < 0 || glbMap[b.first - 1][b.second] == 1 || glbMap[a.first - 1][a.second] == 1 || visited[a.first][a.second][b.first-1][b.second-1]) return;
				b.first--;
				b.second--;
			}
		}
		else { //위아래로 붙어있을 때
			if (b.first - a.first == -1) { //위에 붙어있을 때
				if (b.second - 1 < 0 || glbMap[b.first][b.second - 1] == 1 || glbMap[a.first][a.second - 1] == 1 || visited[a.first][a.second][b.first+1][b.second-1]) return;
				b.first++;
				b.second--;
			}
			else { //아래에 붙어있을 때
				if (b.second + 1 > N - 1  || glbMap[b.first][b.second + 1] == 1 || glbMap[a.first][a.second + 1] == 1 || visited[a.first][a.second][b.first-1][b.second+1]) return;
				b.first--;
				b.second++;
			}
		}
	}
	visited[a.first][a.second][b.first][b.second] = true;
	q.push({ {a, {b.first, b.second}}, time + 1 });
}

int bfs() {
	queue<Info> q;
	q.push({ { {0,0}, {0,1} }, 0});
	Info cur;
	while (!q.empty()) {
		cur = q.front();q.pop();	

		for (int l = 0; l < 2; l++) {
			if (cur.robot[l].first == N - 1 && cur.robot[l].second == N - 1) return cur.time;	
		}

		int ai, aj, bi, bj;
		//상하좌우 이동
		for (int k = 0; k < 4; k++) {
			ai = dy[k] + cur.robot[0].first;
			aj = dx[k] + cur.robot[0].second;
			bi = dy[k] + cur.robot[1].first;
			bj = dx[k] + cur.robot[1].second;
			if (ai > N - 1 || aj > N - 1 || ai < 0 || aj < 0 || bi > N - 1 || bj > N - 1 || bi < 0 
				|| bj < 0||visited[ai][aj][bi][bj]||glbMap[ai][aj]==1||glbMap[bi][bj]==1) continue;
			visited[ai][aj][bi][bj] = true;
			q.push({ {{ai, aj}, {bi, bj}}, cur.time + 1 });			
		}

		//시계, 반시계 회전
		for (int k = 0; k < 2; k++) { //두 개의 점 각각 중심
			pair<int, int> a = cur.robot[k];
			pair<int, int> b = cur.robot[1-k];
			for (int l = 0; l < 2; l++) { //시계방향 반시계방향
				rotate(a, b, l, q, cur.time);
			}
		}
	}

	return 0;
}

int solution(vector<vector<int>> board) {
	int answer = 0;
	glbMap = board;
	N = board.size();

	answer = bfs();

	return answer;
}

int main(void) {	
	vector<vector<int>> board = { {0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 0, 0},
		{1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 0, 0},
		{0, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 0} };

	cout<<solution(board);	
	
	return 0;
}