#include <iostream>

using namespace std;

int map[101][101];
int direction[2][4] = { {-1,0,1,0}, {0, -1, 0, 1} };
int n;
int status;
int ri, rj;

int res = 0;
int cnt = 0;
bool flag = true;

void move() {
	ri += direction[1][status];
	rj += direction[0][status];
	if (ri < 0) {
		status = 3;
		ri = 0;
		cnt++;
	}
	else if (ri >= n) {
		status = 1;
		ri = n - 1;
		cnt++;
	}
	else if (rj < 0) {
		status = 2;
		rj = 0;
		cnt++;
	}
	else if (rj >= n) {
		status = 0;
		rj = n - 1;
		cnt++;
	}
}

void findWormhole() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == map[ri][rj]) {
				if (ri == i && rj == j) continue;
				ri = i;
				rj = j;
				return;
			}
		}
	}
}

void check(int i, int j) {
	if((ri==i&&rj==j)||map[ri][rj] == -1){ //시작점이나 블랙홀인 경우
		if (res < cnt) res = cnt;
		flag = false;
		return;
	}else if(map[ri][rj] == 0) { //그냥 길인 경우
		return;
	}
	else if(map[ri][rj]>=6&&map[ri][rj]<=10){ //웜홀일 때
		findWormhole();
	}
	else { //블럭일 때		
		if (map[ri][rj] == 1) {			
			if (status == 0) {
				status = 1;
			}else if(status == 1) {
				status = 3;
			}
			else if (status == 2) {
				status = 0;
			}
			else if (status == 3) {
				status = 2;
			}
		}
		else if (map[ri][rj] == 2) {
			if (status == 0) {
				status = 3;
			}
			else if (status == 1) {
				status = 2;
			}
			else if (status == 2) {
				status = 0;
			}
			else if (status == 3) {
				status = 1;
			}
		}
		else if (map[ri][rj] == 3) {
			if (status == 0) {
				status = 2;
			}
			else if (status == 1) {
				status = 0;
			}
			else if (status == 2) {
				status = 3;
			}
			else if (status == 3) {
				status = 1;
			}
		}
		else if (map[ri][rj] == 4) {
			if (status == 0) {
				status = 2;
			}
			else if (status == 1) {
				status = 3;
			}
			else if (status == 2) {
				status = 1;
			}
			else if (status == 3) {
				status = 0;
			}
		}
		else if (map[ri][rj] == 5) {
			if (status == 0) {
				status = 2;
			}
			else if (status == 1) {
				status = 3;
			}
			else if (status == 2) {
				status = 0;
			}
			else if (status == 3) {
				status = 1;
			}
		}
		cnt++;
	}
}

void solve() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] != 0) continue;
			for (int k = 0; k < 4; k++) {
				status = k;
				ri = i;
				rj = j;
				while (flag) {
					move();
					check(i, j);
				}
				cnt = 0;
				flag = true;				
			}
		}
	}
}

int main() {
	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		cin >> n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}
		solve();
		cout << "#" << t << " " << res << endl;
		res = 0;
	}

	return 0;
}