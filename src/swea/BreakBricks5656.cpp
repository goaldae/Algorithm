#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int tc, n, w, h;
int res = 1000000;
int p[15];
int direction[2][4] = { {-1, 0, 1, 0}, {0, 1, 0, -1} };
int map[30][30];
struct pos {
	int i, j, p;
	pos(int i_, int j_, int p_) {
		i = i_;
		j = j_;
		p = p_;
	}
};
queue<pos> q;

int drop(int x, int map[][30]) {
	int y = 0;
	while (y < h && map[y][x] == 0) {
		y++;
	}
	return y;
}

void stick(int i, int j, int map[][30]) {
	int temp = map[i][j];
	map[i][j] = 0;
	while (i + 1 < h && map[i + 1][j] == 0) {
		i++;
	}
	map[i][j] = temp;
}

void stickInit(int map[][30]) {
	for (int j = 0; j < w; j++) {
		for (int i = h - 1; i >= 0; i--) {
			if (map[i][j] != 0) {
				stick(i, j, map);
			}
		}
	}
}

int countBricks(int arr[][30]) {
	int ans = 0;
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			if (arr[i][j] != 0) ans++;
		}
	}
	return ans;
}

void copyArr(int arr1[][30], int arr2[][30]) {
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			arr2[i][j] = arr1[i][j];
		}
	}
}

void bruteForce(int count) {
	if (count == n) {		
		if (res == 0) return;
		int x;
		int tempArr[30][30];
		copyArr(map, tempArr);
		for (int i = 0; i < n; i++) {
			x = p[i];
			int y = drop(x, tempArr);
			if (tempArr[y][x] == 0) continue;
			q.push(pos(y, x, tempArr[y][x]));
			pos temp(0, 0, 0);
			while (!q.empty()) {
				temp = q.front();q.pop();
				tempArr[temp.i][temp.j] = 0;
				for (int i = 0; i < 4; i++) {
					int newi = temp.i;
					int newj = temp.j;
					for (int l = 0; l < temp.p - 1; l++) {
						newi += direction[1][i];
						newj += direction[0][i];
						if (newj < 0 || newi < 0 || newj >= w || newi >= h) break;
						if (tempArr[newi][newj] != 0) {
							q.push(pos(newi, newj, tempArr[newi][newj]));
							tempArr[newi][newj] = 0;
						}
					}
				}
			}
			stickInit(tempArr);
		}			
		res = min(countBricks(tempArr), res);
	}else {
		for (int i = 0; i < w; i++) {
			p[count] = i;
			bruteForce(count + 1);
		}
	}	
}

int main() {
	cin >> tc;
	
	for (int t = 1; t <= tc; t++) {
		cin >> n >> w >> h;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				cin >> map[i][j];
			}
		}
		bruteForce(0);
		cout << "#" << t << " " << res << endl;
		res = 100000;
	}

	return 0;
}