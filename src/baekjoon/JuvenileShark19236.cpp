#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <queue>
#include <algorithm>
#include <limits.h>  
#include <unordered_map>

using namespace std;

struct Info {
	int i, j, dir;
};

struct SInfo {
	int i, j, dir, score;
};


int answer=0;

int direction[2][9] = { {0, 0, -1, -1, -1, 0, 1, 1, 1}, {0, -1, -1, 0, 1, 1, 1, 0, -1} };

void move(vector<Info>& info, vector<vector<int>>& map) {
	for (int i = 1; i <= 16; i++) {
		if (info[i].dir == -1) continue; //상어에게 이미 먹힌녀석
		int flag = 0;
		while (1) {
			if (flag == 8) {
				info[i].dir++;
				info[i].dir == 9 ? info[i].dir = 1 : info[i].dir;
				break;
			}
			
			int ni = info[i].i + direction[1][info[i].dir];
			int nj = info[i].j + direction[0][info[i].dir];
			
			if (ni < 0 || ni > 3 || nj < 0 || nj > 3|| map[ni][nj] == -1) {
				flag++;
				info[i].dir++;
				info[i].dir == 9 ? info[i].dir = 1 : info[i].dir;
				continue;
			}
			Info tempInfo = { ni, nj, info[map[ni][nj]].dir};
			int tempIdx = map[ni][nj];
			
			map[ni][nj] = i;
			info[tempIdx].i = info[i].i;
			info[tempIdx].j = info[i].j;
			

			map[info[i].i][info[i].j] = tempIdx;
			info[i].i = tempInfo.i;
			info[i].j = tempInfo.j;
			
			
			break;
		}
	}	
}

void eat(int i, int j, int score, vector<Info> info, vector<vector<int>> map) {
	
	int dir = info[map[i][j]].dir; //방향 설정하고
	
	info[map[i][j]].dir = -1; //해당 물고기 죽이고
	score += map[i][j]; //점수올리고
	map[i][j] = -1; //상어가 자리 차지
	
	move(info, map); //상어 들어오고 세팅까지
	int ni = i;
	int nj = j;	 
	
	while (1) { //다음 갈 곳 체크
		ni += direction[1][dir];
		nj += direction[0][dir];
		
		if (ni < 0 || nj < 0 || ni> 3 || nj> 3) {
			answer = max(score, answer);
			break;
		}
		if (map[ni][nj] == 0) continue;

		map[i][j] = 0;
		eat(ni, nj, score, info, map);
		map[i][j] = -1;
	}
}

int main(void) {

	vector<Info> info(17);
	vector<vector<int>> map(4, vector<int>(4));
	int idx = 0;
	int dir = 0;
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			cin >> idx>> dir;
			info[idx].i = i;
			info[idx].j = j;
			map[i][j] = idx;
			info[idx].dir = dir;
		}
	}

	eat(0, 0, 0, info, map);
	
	cout << answer;
	return 0;
}