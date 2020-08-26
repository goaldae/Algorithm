#include <string>
#include <vector>
#include <iostream>

using namespace std;

int N, M;

void rotate(vector<vector<int>> &key) {
	vector<int> temp[30];

	for (int i = 0; i < key.size(); i++) {
		for (int j = key.size() - 1; j >= 0; j--) {
			temp[i].push_back(key[j][i]);
		}
	}
	for (int i = 0; i < key.size(); i++) {
		for (int j = 0; j < key.size(); j++) {
			key[i][j] = temp[i][j];
		}
	}	
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
	M = key.size();
	N = lock.size();
	bool answer = true;
	int lockMap[60][60] = { 0, };
	
	bool f = true; 
	for (int i = 0; i < lock.size(); i++) { //처음부터 다 1일때
		for (int j = 0; j < lock.size(); j++) {
			if (lock[i][j] == 0) f = false;
		}
		if (!f) break;
	}
	if (f) return true;

	for (int o = 0; o < 4; o++) {
		for (int i = M - 1; i < M + N - 1; i++) {
			for (int j = M - 1; j < M + N - 1; j++) {
				lockMap[i][j] = lock[i - M + 1][j - M + 1];
			}
		}

		for (int i = 0; i < M + N - 1; i++) {
			for (int j = 0; j < M + N - 1; j++) {

				for (int k = i; k < M + i; k++) {
					for (int l = j; l < M + j; l++) {
						lockMap[k][l] += key[k - i][l - j];
					}
				}

				bool flag = true;
				for (int i = M - 1; i < M + N - 1; i++) {
					for (int j = M - 1; j < M + N - 1; j++) {
						if (lockMap[i][j] == 0 || lockMap[i][j] >= 2) {
							flag = false;
						}
					}
					if (!flag) break;
				}
				if (flag) return true;

				for (int k = i; k < M + i; k++) {
					for (int l = j; l < M + j; l++) {
						lockMap[k][l] -= key[k - i][l - j];
					}
				}
			}
		}
		rotate(key);
	}

	return false;
}

int main(void) {
	vector<vector<int>> key = { {0, 0, 0}, {1, 0, 0}, {0, 1, 1} };
	vector<vector<int>> lock = { {1, 1, 1}, {1, 1, 0}, {1, 0, 1} };

	cout << solution(key, lock) << endl;
}