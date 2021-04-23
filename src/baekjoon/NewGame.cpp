#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <limits.h>

using namespace std;

struct MapInfo {
	int color;
	vector<int> points;
};

struct PointInfo {
	int r, c;
	int dir;
	int h;
};
int direction[2][5] = { {0, 1, -1, 0, 0}, {0, 0, 0, -1, 1} };

int main(void) {
	int N, K;
	int answer = 0;
	cin >> N >> K;

	vector<vector<MapInfo>> map(N + 1, vector<MapInfo>(N + 1));
	vector<PointInfo> infos(K + 1);
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> map[i][j].color;
		}
	}
	for (int i = 1; i <= K; i++) {
		cin >> infos[i].r >> infos[i].c >> infos[i].dir;
		map[infos[i].r][infos[i].c].points.push_back(i);
	}

	int turn = 0;
	while (1) {
		int ni, nj;
		turn++;
		if (turn > 1000) {
			answer = -1;
			break;
		}
		if (answer != 0) break;
		for (int i = 1; i <= K; i++) {
			ni = infos[i].r + direction[1][infos[i].dir];
			nj = infos[i].c + direction[0][infos[i].dir];

			if (ni > N || ni<1 || nj>N || nj < 1 || map[ni][nj].color == 2) { //파란색이거나 바깥으로 나갈 때
				//방향 반대로 바꿔주기
				if (infos[i].dir == 1) infos[i].dir = 2;
				else if (infos[i].dir == 2) infos[i].dir = 1;
				else if (infos[i].dir == 3) infos[i].dir = 4;
				else infos[i].dir = 3;

				ni = infos[i].r + direction[1][infos[i].dir];
				nj = infos[i].c + direction[0][infos[i].dir];
				if (ni > N || ni<1 || nj>N || nj < 1 || map[ni][nj].color == 2) continue; //새로 가는 곳이 또 파랑 바깥일 때 그대로
				else {//새로 가는 곳이 파랑이 아닐 때
					vector<int> temp;
					int idx = infos[i].h;
					int length = map[infos[i].r][infos[i].c].points.size() - idx;
					for (int j = 0; j < length; j++) {
						temp.push_back(map[infos[i].r][infos[i].c].points[idx]);
						map[infos[i].r][infos[i].c].points.erase(map[infos[i].r][infos[i].c].points.begin() + idx);

					}

					//빨강이면 반대로 뒤집기
					if (map[ni][nj].color == 1) reverse(temp.begin(), temp.end());

					for (int j = 0; j < temp.size(); j++) {
						infos[temp[j]].h = map[ni][nj].points.size();
						map[ni][nj].points.push_back(temp[j]);
						infos[temp[j]].r = ni;
						infos[temp[j]].c = nj;
						

						if (infos[temp[j]].h >= 3) answer = turn;
					}
				}
			}
			else if (map[ni][nj].color == 0 || map[ni][nj].color == 1) { //가는 곳이 빨강 혹은 흰색일 때 -> 모든 돌 다 들기			
				vector<int> temp;
				int idx = infos[i].h;
				int length = map[infos[i].r][infos[i].c].points.size() - idx;
				for (int j = 0; j < length; j++) {
					temp.push_back(map[infos[i].r][infos[i].c].points[idx]);
					//cout << "a" << endl;
					map[infos[i].r][infos[i].c].points.erase(map[infos[i].r][infos[i].c].points.begin() + idx);
					//cout << "b" << endl;

				}

				//빨강이면 반대로 뒤집기
				if (map[ni][nj].color == 1) reverse(temp.begin(), temp.end());

				for (int j = 0; j < temp.size(); j++) {
					infos[temp[j]].h = map[ni][nj].points.size();
					map[ni][nj].points.push_back(temp[j]);
					infos[temp[j]].r = ni;
					infos[temp[j]].c = nj;
					
					if (infos[temp[j]].h >= 3) answer = turn;
				}
			}
		}
	}

	cout << answer;
	return 0;
}