#include <iostream>
#include <queue>

using namespace std;

int direction[2][4] = { {0, 0, -1, 1}, {-1, 1, 0, 0} }; //상하좌우

int map[51][51];
int M, N, R, C, L;

struct info {
	int i, j, n;
};

queue<info> q;
bool visited[51][51];

bool isConnected(int ti, int tj, int ni, int nj, int s) {
	if (map[ti][tj] == 1) {
		if (s == 0) {
			if (map[ni][nj] == 2 || map[ni][nj] == 5 || map[ni][nj] == 6 || map[ni][nj] == 1) return true;
		}
		else if (s == 1) {
			if (map[ni][nj] == 2 || map[ni][nj] == 4 || map[ni][nj] == 7 || map[ni][nj] == 1) return true;
		}
		else if (s == 2) {
			if (map[ni][nj] == 3 || map[ni][nj] == 5 || map[ni][nj] == 4 || map[ni][nj] == 1) return true;
		}
		else if (s == 3) {
			if (map[ni][nj] == 3 || map[ni][nj] == 7 || map[ni][nj] == 6 || map[ni][nj] == 1) return true;
		}
	}
	else if (map[ti][tj] == 2) {
		if (s == 0) {
			if (map[ni][nj] == 2 || map[ni][nj] == 5 || map[ni][nj] == 6 || map[ni][nj] == 1) return true;
		}
		else if (s == 1) {
			if (map[ni][nj] == 2 || map[ni][nj] == 4 || map[ni][nj] == 7 || map[ni][nj] == 1) return true;
		}
	}
	else if (map[ti][tj] == 3) {
		if (s == 2) {
			if (map[ni][nj] == 1 || map[ni][nj] == 3 || map[ni][nj] == 4 || map[ni][nj] == 5) return true;
		}
		else if (s == 3) {
			if (map[ni][nj] == 1 || map[ni][nj] == 3 || map[ni][nj] == 7 || map[ni][nj] == 6) return true;
		}
	}
	else if (map[ti][tj] == 4) {
		if (s == 0) {
			if (map[ni][nj] == 1 || map[ni][nj] == 2 || map[ni][nj] == 6 || map[ni][nj] == 5) return true;
		}
		else if (s == 3) {
			if (map[ni][nj] == 1 || map[ni][nj] == 3 || map[ni][nj] == 7 || map[ni][nj] == 6) return true;
		}
	}
	else if (map[ti][tj] == 5) {
		if (s == 1) {
			if (map[ni][nj] == 2 || map[ni][nj] == 4 || map[ni][nj] == 7 || map[ni][nj] == 1) return true;
		}
		else if (s == 3) {
			if (map[ni][nj] == 3 || map[ni][nj] == 7 || map[ni][nj] == 6 || map[ni][nj] == 1) return true;
		}
	}
	else if (map[ti][tj] == 6) {
		if (s == 1) {
			if (map[ni][nj] == 2 || map[ni][nj] == 4 || map[ni][nj] == 7 || map[ni][nj] == 1) return true;
		}
		else if (s == 2) {
			if (map[ni][nj] == 3 || map[ni][nj] == 5 || map[ni][nj] == 4 || map[ni][nj] == 1) return true;
		}
	}
	else if (map[ti][tj] == 7) {
		if (s == 0) {
			if (map[ni][nj] == 2 || map[ni][nj] == 5 || map[ni][nj] == 6 || map[ni][nj] == 1) return true;
		}
		else if (s == 2) {
			if (map[ni][nj] == 3 || map[ni][nj] == 5 || map[ni][nj] == 4 || map[ni][nj] == 1) return true;
		}
	}

	return false;
}

int solve() {
	q.push({ R, C, 1 });
	visited[R][C] = true;

	info temp;
	int ni, nj;
	int m = 0;
	while (!q.empty() && q.front().n <= L) {
		m++;
		temp = q.front();q.pop();
		
		for (int k = 0; k < 4; k++) {
			ni = temp.i + direction[1][k];
			nj = temp.j + direction[0][k];
			if (ni > N - 1 || nj > M - 1 || ni < 0 || nj < 0 || visited[ni][nj]) continue;
			if (isConnected(temp.i, temp.j, ni, nj, k)) { //연결돼있으면 큐에 넣기
				visited[ni][nj] = true;
				q.push({ ni, nj, temp.n + 1 });
			}
		}
	}
	return m;
}

int main() {
	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		cin >> N >> M >> R >> C >> L;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cin >> map[i][j];
			}
		}
		cout << "#" << t << " " << solve() << endl;
		while (!q.empty()) q.pop();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = false;
			}
		}
	}

	return 0;
}