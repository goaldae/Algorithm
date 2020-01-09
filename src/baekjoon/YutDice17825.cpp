#include <iostream>

using namespace std;

int map[22][9];
int visited[22][9];
int dice[10];

struct Pin {
	int i, j, status;	
};

Pin pin[4];
int p[10];
int score = 0;
int maxScore = 0;

void scoreInit() {
	for (int i = 0; i < 21; i++) {
		map[i][0] = i * 2;
	}
	//10일 때
	map[5][1] = 13;
	map[5][2] = 16;
	map[5][3] = 19;
	map[5][4] = 25;
	map[5][5] = 30;
	map[5][6] = 35;
	map[5][7] = 40;
	//20일 때
	map[10][1] = 22;
	map[10][2] = 24;
	map[10][3] = 25;
	map[10][4] = 30;
	map[10][5] = 35;
	map[10][6] = 40;
	//30일 때
	map[15][1] = 28;
	map[15][2] = 27;
	map[15][3] = 26;
	map[15][4] = 25;
	map[15][5] = 30;
	map[15][6] = 35;
	map[15][7] = 40;
	
}

void visitedCheck(int i, int j, bool flag) {
	if (map[i][j] == 25) {
		visited[5][4] = flag;
		visited[10][3] = flag;
		visited[15][4] = flag;
	}else if (map[i][j] == 30&&j!=0) {
		visited[5][5] = flag;
		visited[10][4] = flag;
		visited[15][5] = flag;
	}else if (map[i][j] == 35) {
		visited[5][6] = flag;
		visited[10][5] = flag;
		visited[15][6] = flag;
	}else if (map[i][j] == 40) {
		visited[5][7] = flag;
		visited[10][6] = flag;
		visited[15][7] = flag;
		visited[20][0] = flag;
	}else {
		visited[i][j] = flag;
	}
}

void solve(int k) {
	if (k == 10) {
		int move;
		for (int i = 0; i < 10; i++) {
			if (pin[p[i]].status == 4) {
				continue;
			}
			else if (pin[p[i]].status == 1) {
				move = pin[p[i]].i + dice[i];
				if (move >= 21) {
					visitedCheck(pin[p[i]].i, pin[p[i]].j, false);
					pin[p[i]].status = 4;
					continue;
				}
				else if (visited[move][pin[p[i]].j]) {
					score = 0;
					break;
				}
				else {
					visitedCheck(pin[p[i]].i, pin[p[i]].j, false);
					pin[p[i]].i = move;
					visitedCheck(pin[p[i]].i, pin[p[i]].j, true);
					score += map[pin[p[i]].i][pin[p[i]].j];
					if (move == 5 || move == 15) {
						pin[p[i]].status = 2;
					}
					else if (move == 10) {
						pin[p[i]].status = 3;
					}
				}
			}
			else if (pin[p[i]].status == 2) {
				move = pin[p[i]].j + dice[i];
				if (move >= 8) {
					visitedCheck(pin[p[i]].i, pin[p[i]].j, false);
					pin[p[i]].status = 4;
					continue;
				}
				else if (visited[pin[p[i]].i][move]) {
					score = 0;
					break;
				}
				else {
					visitedCheck(pin[p[i]].i, pin[p[i]].j, false);
					pin[p[i]].j = move;
					visitedCheck(pin[p[i]].i, pin[p[i]].j, true);
					score += map[pin[p[i]].i][pin[p[i]].j];
				}
			}
			else if (pin[p[i]].status == 3) {
				move = pin[p[i]].j + dice[i];
				if (move >= 7) {
					visitedCheck(pin[p[i]].i, pin[p[i]].j, false);
					pin[p[i]].status = 4;
					continue;
				}
				else if (visited[pin[p[i]].i][move]) {
					score = 0;
					break;
				}
				else {
					visitedCheck(pin[p[i]].i, pin[p[i]].j, false);
					pin[p[i]].j = move;
					visitedCheck(pin[p[i]].i, pin[p[i]].j, true);
					score += map[pin[p[i]].i][pin[p[i]].j];
				}
			}
		}		
		if (maxScore < score) {
			for (int i = 0; i < 10; i++) {
				cout << p[i] << " ";
			}cout << endl;
			
			maxScore = score;
		}
		for (int i = 0; i < 4; i++) {
			pin[i].i = 0;
			pin[i].j = 0;
			pin[i].status = 1;
		}
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 9; j++) {
				visited[i][j] = false;
			}
		}
		score = 0;		
	}
	else {
		for (int i = 0; i < 4; i++) {
			p[k] = i;
			solve(k + 1);
		}
	}
}

int main() {
	scoreInit();

	for (int i = 0; i < 10; i++) {
		cin >> dice[i];
	}
	for (int i = 0; i < 4; i++) {
		pin[i].i = 0;
		pin[i].j = 0;
		pin[i].status = 1;
	}

	solve(0);
	cout << maxScore << endl;

	return 0;
}