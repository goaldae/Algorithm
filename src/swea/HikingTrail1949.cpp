#include <iostream>

using namespace std;

int n, k;
int highest = 0;
int map[9][9];
int res = 0;
int direction[2][4] = { {-1,0,1,0}, {0,-1,0,1} };
bool visited[9][9];

void findHighest() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] > highest) highest = map[i][j];
		}
	}
}

void bfs(int i, int j, int count, bool flag) {
	int newi, newj;
	if (res < count) res = count;

	for (int l = 0; l < 4; l++) {
		newi = i + direction[1][l];
		newj = j + direction[0][l];
		if (newi >= n || newj >= n || newj < 0 || newi < 0|| visited[newi][newj]) continue;
		for (int m = 0; m <= k; m++) { //깎는 횟수
			if (m == 0) { //지형을 안 깎고 지나갈 때
				if (map[newi][newj] < map[i][j]) {
					visited[newi][newj] = true;
					bfs(newi, newj, count + 1, flag);
					visited[newi][newj] = false;
				}
			} else { //지형을 깎고 지나갈 때
				if (flag) { //이미 이전에 깎았을 때
					break;
				} else { //처음 깎는 것일 때
					if (map[newi][newj] - m < map[i][j]) {
						visited[newi][newj] = true;
						map[newi][newj] -= m;
						bfs(newi, newj, count + 1, true);					
						map[newi][newj] += m;
						visited[newi][newj] = false;
					}					
				}
			}
		}		
	}
}

void solve() {
	findHighest();
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == highest) {
				visited[i][j] = true;
				bfs(i, j, 1, false);
				visited[i][j] = false;
			}
		}
	}
}

int main() {
	
	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		cin >> n >> k;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}
		solve();
		cout << "#" << t << " " << res << endl;
		res = 0;
		highest = 0;
	}
	return 0;
}