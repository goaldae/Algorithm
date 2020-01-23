#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct user {
	int i, j, current, sum;
};
struct bc {
	int i, j, c, p, id;
};

int m, a;
int move1[101];
int move2[101];
int visited[11][11];
vector<bc> map[11][11];
queue<bc> q;
int direction[2][4] = { {-1, 0, 1, 0}, {0, 1, 0, -1} };

user move(user u, int k, int time) {
	if (k != 0) {
		u.i += direction[1][k - 1];
		u.j += direction[0][k - 1];
	}
	u.current = 0;
	return u;
}

int moveInit() {
	user u1 = { 1, 1, 0, 0 };
	user u2 = { 10, 10, 0, 0 };
	int temp;

	for (int time = 0; time <= m; time++) {
		u1 = move(u1, move1[time], time);
		u2 = move(u2, move2[time], time);
		
		int max = 0;
		if (map[u1.i][u1.j].size() != 0 && map[u2.i][u2.j].size() != 0) { //둘다 값이 있을 때
			for (int i = 0; i < map[u1.i][u1.j].size(); i++) {//돌아보면서 합이 제일 큰 값을 찾음
				for (int j = 0; j < map[u2.i][u2.j].size(); j++) { 
					if (map[u1.i][u1.j][i].id == map[u2.i][u2.j][j].id) { //같은 배터리 영역에 있을 때 
						if (max < map[u1.i][u1.j][i].p) {
							u1.current = map[u1.i][u1.j][i].p / 2; //파워 나눠서 가짐
							u2.current = map[u1.i][u1.j][i].p / 2;
							max = map[u1.i][u1.j][i].p;
						}
					}
					else {
						if (max < map[u1.i][u1.j][i].p + map[u2.i][u2.j][j].p) { //다른 영역에 있을 때
							u1.current = map[u1.i][u1.j][i].p;
							u2.current = map[u2.i][u2.j][j].p;
							max = map[u1.i][u1.j][i].p + map[u2.i][u2.j][j].p;
						}
					}
				}
			}
		}
		else if (map[u1.i][u1.j].size() != 0 && map[u2.i][u2.j].size() == 0) { //u1만 값이 있을 때
			for (int i = 0; i < map[u1.i][u1.j].size(); i++) {
				if (max < map[u1.i][u1.j][i].p) {
					u1.current = map[u1.i][u1.j][i].p;
					max = map[u1.i][u1.j][i].p;
				}
			}
		}
		else if (map[u1.i][u1.j].size() == 0 && map[u2.i][u2.j].size() != 0) { //u2만 값이 있을 때
			for (int i = 0; i < map[u2.i][u2.j].size(); i++) {
				if (max < map[u2.i][u2.j][i].p) {
					u2.current = map[u2.i][u2.j][i].p;
					max = map[u2.i][u2.j][i].p;
				}
			}
		}
		//둘다 값이 없을 땐 스킵

		u1.sum += u1.current;
		u2.sum += u2.current;
	}
	return u1.sum + u2.sum;
}

void bfs() {
	bc temp;
	temp = q.front();
	map[temp.i][temp.j].push_back(temp);
	visited[temp.i][temp.j] = true;

	while (!q.empty() && q.front().c != 0) {

		temp = q.front();q.pop();

		for (int k = 0; k < 4; k++) {
			int ni = temp.i + direction[1][k];
			int nj = temp.j + direction[0][k];

			if (ni > 10 || nj > 10 || ni < 1 || nj < 1 || visited[ni][nj]) continue;
			map[ni][nj].push_back(temp);
			visited[ni][nj] = true;
			q.push({ ni, nj, temp.c - 1, temp.p , temp.id});
		}
	}
	while (!q.empty()) {
		q.pop();
	}
	for (int i = 0; i < 11; i++) {
		for (int j = 0; j < 11; j++) {
			visited[i][j] = false;
		}
	}
}

int main(void) {
	int TC;
	int ti, tj, tc, tp;
	cin >> TC;
	for (int t = 1; t <= TC; t++) {
		cin >> m >> a;

		for (int i = 1; i <= m; i++) {
			cin >> move1[i];
		}
		for (int i = 1; i <= m; i++) {
			cin >> move2[i];
		}
		for (int i = 0; i < a; i++) {
			cin >> ti >> tj >> tc >> tp;
			q.push({ ti, tj, tc, tp , i});
			bfs();
		}

		cout << "#" << t << " " << moveInit() << endl;

		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				map[i][j].clear();
			}
		}
	}

	return 0;
}
