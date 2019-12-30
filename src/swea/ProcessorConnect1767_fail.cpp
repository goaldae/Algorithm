#include <iostream>
#include <algorithm>
#include <queue>
#include <string>

using namespace std;
int map[20][20];
int tc, n;
int LINE = 2;
int res;
int coreCount;
int coreMax;

void removeLine(int k, int i, int j) {
	int dir;
	if (k == 0) { //서 
		dir = -1;
		while (j > 0) {
			j += dir;
			map[i][j] = 0;
		}
	}if (k == 1) { //북
		dir = -1;
		while (i > 0) {
			i += dir;
			map[i][j] = 0;
		}
	}if (k == 2) { //동
		dir = 1;
		while (j < n - 1) {
			j += dir;
			map[i][j] = 0;
		}
	}if (k == 3) { //남
		dir = 1;
		while (i < n - 1) {
			i += dir;
			map[i][j] = 0;
		}
	}
}

void drawLine(int k, int i, int j) {
	int dir;
	if (k == 0) { //서 
		dir = -1;
		while (j > 0) {
			j += dir;
			map[i][j] = LINE;			
		}
	}if (k == 1) { //북
		dir = -1;
		while (i > 0) {
			i += dir;
			map[i][j] = LINE;
		}
	}if (k == 2) { //동
		dir = 1;
		while (j < n - 1) {
			j += dir;
			map[i][j] = LINE;
		}
	}if (k == 3) { //남
		dir = 1;
		while (i < n - 1) {
			i += dir;
			map[i][j] = LINE;
		}
	}
}

bool check(int k, int i, int j) { //전선을 놓을 곳에 다른 전선이나 코어가 있는지 확인
	int dir;
	if (k == 0) { //서 
		dir = -1;
		while (j > 0) {
			j += dir;
			if (map[i][j] == LINE || map[i][j] == 1) {
				return false;
			}
		}
	}if (k == 1) { //북
		dir = -1;
		while (i > 0) {
			i += dir;
			if (map[i][j] == LINE || map[i][j] == 1) {
				return false;
			}
		}
	}if (k == 2) { //동
		dir = 1;
		while (j < n-1) {
			j += dir;
			if (map[i][j] == LINE || map[i][j] == 1) {
				return false;
			}
		}
	}if (k == 3) { //남
		dir = 1;
		while (i < n-1) {
			i += dir;
			if (map[i][j] == LINE || map[i][j] == 1) {
				return false;
			}
		}
	}
	return true;
}

int countLine() {
	int ans = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == 2) {
				ans++;
			}
		}
	}
	return ans;
}

void printMap() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << map[i][j] << " ";
		}
		cout << endl;
	}
	cout << endl;
}

void bruteForce(int i, int j) {
	if (j == n) {
		j = 0;
		i++;
	}
	if (i == n - 1 && j == n - 1) { //마지막에 다다르면
		if (coreMax < coreCount) {
			coreMax = coreCount;
			res = countLine();
		}
		else if (coreMax == coreCount) {
			res = min(countLine(), res);
		}
		return;
	}
	if (map[i][j] != 1) { //코어가 아니면
		bruteForce(i, j+1);
		return;
	} 
	//코어일때
	if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
		coreCount++;
		bruteForce(i, j + 1);
		coreCount--;
		return;
	}
	for (int k = 0; k < 5; k++) {
		if (k == 4) {
			bruteForce(i, j + 1);
			break;
		}
		if (check(k, i, j)) {
			coreCount++;
			drawLine(k, i, j);
			bruteForce(i, j + 1);
			removeLine(k, i, j);
			coreCount--;
		}
		else{
			continue;
		}	

	}	
}

void clearMap() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			map[i][j] = 0;
		}
	}
}

int main() {
	cin >> tc;
	
	for (int i = 0; i < tc; i++) {
		cin >> n;
		clearMap();
		res = 100000;
		coreCount = 0;
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < n; k++) {
				cin >> map[j][k];
			}			
		}		
		bruteForce(0, 0);
		cout << "#"<<i+1<<" "<<res<<endl;		
	}
	
	return 0;
}