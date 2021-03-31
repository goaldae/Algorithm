#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <queue>
#include <algorithm>

using namespace std;

vector<int> cards;
vector<vector<pair<int, int>>> cord(7, vector<pair<int, int>>(2));
vector<vector<int>> cardKind(4, vector<int>(4, 0));
int answer = 2100000000;

int dir[2][4] = { {-1,0,1,0},{0, -1, 0, 1} };

struct info {
	int r, c, dist;
};

vector<int> getEnterCord(int r, int c, vector<vector<int>>& board, int k) {
	int nR = r;
	int nC = c;

	while (1) {
		if (nR + dir[1][k] < 0 || nR + dir[1][k]>3 || nC + dir[0][k] < 0 || nC + dir[0][k]>3) { //범위를 넘을 때
			return { nR, nC };
		}
		else if (board[nR + dir[1][k]][nC + dir[0][k]] != 0) { //카드일 때
			return { nR + dir[1][k], nC + dir[0][k] };
		}
		nR += dir[1][k];
		nC += dir[0][k];
	}
}

void dfs(vector<int>& tempAnswer, int card, int kind, vector<vector<int>>& board) {
	queue<info> q;
	vector<vector<bool>> visited(4, vector<bool>(4, false));

	q.push({ tempAnswer[1], tempAnswer[2], 0 });
	visited[tempAnswer[1]][tempAnswer[2]] = true;

	info temp;
	while (!q.empty()) {
		temp = q.front();q.pop();
		
		if (board[temp.r][temp.c] == card && cardKind[temp.r][temp.c] == kind) {
			board[temp.r][temp.c] = 0;
			//cout << "결과:" << card << " " << kind << " ";
			tempAnswer[0] += temp.dist+1;
			tempAnswer[1] = temp.r;
			tempAnswer[2] = temp.c;
			
			return;
		}

		vector<int> newCord(2);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					newCord = getEnterCord(temp.r, temp.c, board, i);
				}
				else {
					newCord[0] = temp.r+dir[1][i];
					newCord[1] = temp.c+dir[0][i];
				}

				if (newCord[0] < 0 || newCord[1] < 0 || newCord[0]>3 || newCord[1]>3
					|| visited[newCord[0]][newCord[1]]) continue;

				q.push({ newCord[0], newCord[1], temp.dist + 1 });
				visited[newCord[0]][newCord[1]] = true;
			}
		}
	}

}

void getPermutation(int k, int cardSize, vector<bool>& perVisited, vector<vector<int>>& per, int r, int c, vector<vector<int>> board) {
	if (k == cardSize) {
		vector<int> tempAnswer = { 0, r, c };
		for (int i = 0; i < cardSize; i++) {
			for (int j = 0; j < 2; j++) { //첫번째 카드 찾고 두번째 카드 찾고
				
				dfs(tempAnswer, cards[per[0][i]], (per[1][i] + j) % 2, board);
				//cout << cards[per[0][i]] << "/" << (per[1][i] + j) % 2 <<"/"<<tempAnswer[0]<<" ";

			}
		}
		//cout << tempAnswer[0] << endl;
		
		answer = min(tempAnswer[0], answer);
	}
	else {
		for (int i = 0; i < cardSize; i++) {
			for (int j = 0; j < 2; j++) {
				if (perVisited[i]) continue;
				perVisited[i] = true;
				per[0][k] = i; //{1,3,2,4,5 ....}
				per[1][k] = j; //{0,1,0,1,1 ....}

				getPermutation(k + 1, cardSize, perVisited, per, r, c, board);
				perVisited[i] = false;
			}
		}
	}
}

int solution(vector<vector<int>> board, int r, int c) {
	vector<vector<int>> per(2, vector<int>(7));
	vector<bool> visited(7);

	int picked;
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			picked = board[i][j];
			if (picked != 0) {
				if (!visited[picked]) {//처음 본 카드
					cord[picked][0] = { i, j };
					cards.push_back(picked);
					visited[picked] = true;
				}
				else { //두 번째 본 카드
					cord[picked][1] = { i, j };
					cardKind[i][j] = 1;
				}
			}
		}
	}

	vector<bool> perVisited(7);
	getPermutation(0, cards.size(), perVisited, per, r, c, board);

	return answer;
}