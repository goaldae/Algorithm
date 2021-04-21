#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <limits.h>
using namespace std;

vector<vector<int>> v;

int answer = INT_MAX;
int direction[2][4] = { {-1, 1, 1, -1}, {1, 1, -1, -1} };
int main(void) {
	int N;
	
	cin >> N;
	vector<vector<int>> map(N, vector<int>(N));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
		}
	}
	
	priority_queue<int, vector<int>, greater<int>> pqg;
	priority_queue<int> pql;
	for (int i = 0; i < N - 2; i++) { //x좌표 행
		for (int j = 1; j < N - 1; j++) { //y좌표 열
			for (int x = 1; x < N; x++) { //dx
				if (j - x < 0 || i + x >= N)break;
				for (int y = 1; y < N; y++) {//dy
					if (y + x + i >= N || j + y >= N) break;
					
					vector<vector<int>> mapCheck(N, vector<int>(N));

					int ni = i;
					int nj = j;
					int temp = 0;
					for (int k = 0; k < 4; k++) {
						if (k == 0 || k == 2) {
							for (int l = 0; l < x; l++) {
								ni += direction[1][k];
								nj += direction[0][k];
								mapCheck[ni][nj] = 5;
								temp += map[ni][nj];

							}
						}
						else {
							for (int l = 0; l < y; l++) {
								ni += direction[1][k];
								nj += direction[0][k];
								mapCheck[ni][nj] = 5;
								temp += map[ni][nj];

							}
						}
					}
					int flag;
					for (int m = i + 1; m < i + x + y; m++) {
						flag = 0;
						for (int n = j - x; n <= j + y; n++) {
							if (mapCheck[m][n] == 5) {
								if (flag == 1) break;
								flag++;
								continue;
							}
							if (flag == 1) {
								mapCheck[m][n] = 5;
								temp += map[m][n];

							}
						}
					}
					pqg.push(temp);
					pql.push(temp);
					
					temp = 0;
					for (int a = 0; a < i + x;a++) {						
						for (int b = 0; b <= j; b++) {
							if (mapCheck[a][b] == 5) continue;
							temp += map[a][b];
							mapCheck[a][b] = 1;
						}
					}
					pqg.push(temp);
					pql.push(temp);

					temp = 0;
					for (int a = 0; a <= i + y;a++) {
						for (int b = j+1; b < N; b++) {
							if (mapCheck[a][b] == 5) continue;
							temp += map[a][b];
							mapCheck[a][b] = 2;
						}
					}
					pqg.push(temp);
					pql.push(temp);

					temp = 0;
					for (int a = i+x; a < N; a++) {
						for (int b = 0; b < j-x+y; b++) {
							if (mapCheck[a][b] == 5) continue;
							temp += map[a][b];
							mapCheck[a][b] = 3;
						}
					}
					pqg.push(temp);
					pql.push(temp);

					temp = 0;
					for (int a = i+y+1; a < N;a++) {
						for (int b = j-x+y; b < N; b++) {
							if (mapCheck[a][b] == 5) continue;
							temp += map[a][b];
							mapCheck[a][b] = 4;
						}
					}
					pqg.push(temp);
					pql.push(temp);

					if ((pql.top() - pqg.top()) < answer) {
						answer = pql.top() - pqg.top();
					}

					while (!pqg.empty()) { pqg.pop(); }
					while (!pql.empty()) { pql.pop(); }
				}
			}
		}
	}
	cout << answer;

	return 0;
}