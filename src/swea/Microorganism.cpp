#include <iostream>

using namespace std;

int direction[2][5] = { {0, 0, 0, -1, 1}, {0, -1, 1, 0, 0} }; //상하좌우

int N; //맵 크기
int M; //시간
int K; //군집 개수

struct Micro {
	int i, j, n, dir;
};

struct mapInfo {
	int m, n;
};

mapInfo map[101][101];
Micro microInfo[1001];

void move() {
	Micro cur;
	for (int i = 0; i < K; i++) {
		cur = microInfo[i];
		if (cur.dir == 5) continue; //죽은거 생략
		cur.j += direction[0][cur.dir];
		cur.i += direction[1][cur.dir];
		if (map[cur.i][cur.j].m < cur.n) { //최댓값이면 업데이트
			map[cur.i][cur.j].m = cur.n;
		}
		map[cur.i][cur.j].n += cur.n;

		microInfo[i] = cur; //위치 업데이트
	}
}

void check() {
	Micro cur;
	for (int i = 0; i < K; i++) {
		cur = microInfo[i];
		if (cur.dir == 5) continue; //죽은거 생략
		if (cur.i == 0 || cur.i == N - 1 || cur.j == 0 || cur.j == N - 1) { //갈 곳이 경계선이라면
			if (cur.dir == 1) cur.dir = 2;
			else if (cur.dir == 2) cur.dir = 1;
			else if (cur.dir == 3) cur.dir = 4;
			else if (cur.dir == 4) cur.dir = 3;
			cur.n = map[cur.i][cur.j].n / 2;
		}
		else { //경계선이 아니라면
			if (map[cur.i][cur.j].m > cur.n) cur.dir = 5;
			else {
				cur.n = map[cur.i][cur.j].n;
			}
		}
		microInfo[i] = cur;
	}

	for (int i = 0; i < 101; i++) {
		for (int j = 0; j < 101; j++) {
			map[i][j].m = 0;
			map[i][j].n = 0;
		}
	}
}

int solve() {
	int res = 0;
	for (int i = 0; i < M; i++) {
		move();
		check();
	}
	for (int i = 0; i < K; i++) {
		if (microInfo[i].dir == 5) continue;
		res += microInfo[i].n;
	}
	return res;
}

int main() {
	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		cin >> N >> M >> K;
		for (int i = 0; i < K; i++) {
			cin >> microInfo[i].i >> microInfo[i].j >> microInfo[i].n >> microInfo[i].dir;
		}
		cout << "#" << t << " " << solve() << endl;
		for (int i = 0; i < 1001; i++) {
			microInfo[i].dir = 0;
			microInfo[i].i = 0;
			microInfo[i].j = 0;
			microInfo[i].n = 0;
		}
	}

	return 0;
}