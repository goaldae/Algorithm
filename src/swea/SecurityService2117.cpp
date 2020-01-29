#include <iostream>
#include <queue>

using namespace std;

int n, m;
int map[21][21];
int visited[21][21];

struct info {
	int i, j, a;
};
queue<info> q;
int direction[2][4] = { {-1, 0, 1, 0}, {0, -1, 0, 1} };

int MAX = 0;

int getBuget(int n) {
	if (n == 1) return 1;
	return getBuget(n - 1) + 4 * (n - 1);
}

void bfs(int i, int j, int area) {
	int count = 0;
	info temp;

	q.push({ i, j, 1});
	visited[i][j] = true;
	
	while (!q.empty()) {
		temp = q.front();q.pop();
		if (map[temp.i][temp.j] == 1) count++; //꺼내봤는데 집이면
		if (temp.a == area) continue;
		for (int k = 0; k < 4; k++) {
			int ni = temp.i + direction[1][k];
			int nj = temp.j + direction[0][k];
			if (ni >= n || nj >= n || ni < 0 || nj < 0||visited[ni][nj]) continue;
			
			visited[ni][nj] = true;
			q.push({ ni, nj, temp.a + 1 });
		}
	}
	if (getBuget(area) <= count * m) {
		if (count > MAX) MAX = count;
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			visited[i][j] = false;
		}
	}
	while (!q.empty()) { q.pop(); }	
}

void solve() {
	int maxPay = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == 1) maxPay++;
		}
	}
	maxPay *= m;

	int area = 1;
	while (getBuget(area) <= maxPay) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				bfs(i, j, area);
			}
		}
		area++;
	}
}

int main(void) {
	int tc;
	cin >> tc;
	for (int t = 1; t <= tc; t++) {
		cin >> n >> m;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}
		solve();
		cout<<"#"<<t<<" "<<MAX<<endl;
		MAX = 0;
	}
	
	return 0;
}