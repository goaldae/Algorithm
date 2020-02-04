#include <iostream>
#include <algorithm>

using namespace std;

int n, m, c;
int map[11][11];
bool visited[11][11];

int tempMax = 0;
int temp[11];
bool tempVisited[11];

int MAX = 0;
int ans = 0;

void bruteForce2(int j) {	
	if (j == m) { //조건이 부합해서 왔으면 바로실행
		int sum = 0;
		int powSum = 0;
		for (int i = 0; i < m; i++) {
			if (tempVisited[i]) {
				sum += temp[i];
				powSum += pow(temp[i], 2);
			}			
		}
		if (sum > c) {
			return;
		}
		else {
			if (powSum > tempMax) tempMax = powSum;
		}
	}
	else {
		tempVisited[j] = true;
		bruteForce2(j + 1);
		tempVisited[j] = false;
		bruteForce2(j + 1);
	}
}

void bruteForce(int k, int i, int j) {	
	if (k == 2) { //묻지도 따지지도 않고 조건이 부합해서 들어왔으면 작업실행
		int res;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j]) {
					for (int l = 0; l < m; l++) {
						temp[l] = map[i][j + l];
					}
					bruteForce2(0);
					MAX += tempMax;
					tempMax = 0;		
				}				
			}
		}
		if (ans < MAX) {
			ans = MAX;
		}
		MAX = 0;
		return;
	}

	if (j > n - 1) { //현재좌표 조정
		if (i == n - 1) {
			return;
		}
		else {
			j = 0;
			i++;
		}
	}
	
	if (j + m - 1 >= n) { //선택할 수 없을 때
		bruteForce(k, i, j + 1);
	}
	else { //선택할 수 있을 때
		visited[i][j] = true;
		bruteForce(k + 1, i, j + m);
		visited[i][j] = false;
		bruteForce(k, i, j + 1);
	}	
}

int main(void) {
	int tc;

	cin >> tc;
	for (int t = 1; t <= tc; t++) {
		cin >> n >> m >> c;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}

		bruteForce(0, 0, 0);
		cout <<"#" <<t<<" "<<ans << endl;
		MAX = 0;
		ans = 0;
		tempMax = 0;
	}
	return 0;
}