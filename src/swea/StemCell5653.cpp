#include <iostream>
#include <queue>

using namespace std;
int n, m, k;

struct Cell {
	int i, j, status, alive, clive, time;
	Cell(int i_, int j_, int status_, int alive_, int clive_, int time_) {
		i = i_;
		j = j_;
		status = status_;
		alive = alive_;
		clive = clive_;
		time = time_;
	}
};

int visited[380][380];
int current[380][380];
int time2[380][380];
queue<Cell> q;
int direction[2][4] = { {-1,0,1,0}, {0,-1,0,1} };

void solve() {
	while (!q.empty() && q.front().time <= k) { //큐 안에있는거중에 해당하는 시간거만 꺼내기		
		Cell temp(0, 0, 0, 0, 0, 0);
		temp = q.front(); q.pop();
		if (temp.status == 1) { //비활성화 상태일 때
			if (current[temp.i][temp.j] > temp.alive) continue;
			
			visited[temp.i][temp.j] = true;
			if (temp.clive <= 1) { //비활성화 시간이 나 지났을 때
				q.push(Cell(temp.i, temp.j, 2, temp.alive, temp.clive - 1, temp.time + 1));
			} else {
				q.push(Cell(temp.i, temp.j, 1, temp.alive, temp.clive - 1, temp.time + 1));
			}
		}
		else if (temp.status == 2) { //활성 상태일 때
			if (temp.clive == 0) {				
				for (int i = 0; i < 4; i++) {
					int newi = temp.i + direction[1][i];
					int newj = temp.j + direction[0][i];
					if (visited[newi][newj]) continue; //자리 차지한곳은 무조건 패스

					if (time2[newi][newj] == 0) { //시간 표시가 안된경우
						current[newi][newj] = temp.alive;
						time2[newi][newj] = temp.time;
						q.push(Cell(newi, newj, 1, temp.alive, temp.alive, temp.time + 1));
					} else if (time2[newi][newj] < temp.time) continue;
					else if(current[newi][newj] < temp.alive){ //이전 시간에 표시된경우
						current[newi][newj] = temp.alive;
						time2[newi][newj] = temp.time;
						q.push(Cell(newi, newj, 1, temp.alive, temp.alive, temp.time + 1));
					}					
				}
			}
			if (temp.clive + 1 == temp.alive) {
				q.push(Cell(temp.i, temp.j, 3, temp.alive, temp.clive + 1, temp.time + 1));
			}
			else {
				q.push(Cell(temp.i, temp.j, 2, temp.alive, temp.clive + 1, temp.time + 1));
			}			
		}
		else if (temp.status == 3) { //활성 상태일 때
			current[temp.i][temp.j] = -1;
		}
	}
}

void reset() {
	for (int i = 0; i < 380; i++) {
		for (int j = 0; j < 380; j++) {
			visited[i][j] = false;
			current[i][j] = 0;
			time2[i][j] = 0;
		}
	}
	while (!q.empty()) {
		q.pop();
	}
}

int main() {
	int tc;
	int ns, ms;
	int tempLive;

	cin >> tc;
	for (int t = 1; t <= tc; t++) {
		cin >> n >> m >> k;
		ns = 180 - n / 2;
		ms = 180 - m / 2;
		for (int i = ns; i < ns + n; i++) {
			for (int j = ms; j < ms + m; j++) {
				cin >> tempLive;
				if (tempLive != 0) {
					current[i][j] = tempLive;
					q.push(Cell(i, j, 1, tempLive, tempLive, 0));
				}
			}
		}

		solve();
		int count = 0;
		for (int i = 0; i < 380; i++) {
			for (int j = 0; j < 380; j++) {
				if (visited[i][j] && current[i][j] != -1)count++;
			}
		}
		cout << "#" << t << " " << count << endl;
		reset();
	}

	return 0;
}