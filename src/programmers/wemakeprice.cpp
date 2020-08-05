/*
위메프 2020년 상반기 코딩테스트 3번 문제
*/

#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <map>
#include <string>

using namespace std;

map<char, int> globalMap;

bool cmp(pair<char, int> t, pair<char, int> u) {
	if (t.second == u.second) return t.first < u.first;
	return t.second > u.second;
}

bool visited[200][200];
int direction[2][4] = { {-1,0,1,0}, {0,-1,0,1} };

void bfs(vector<string> arr, int N, int M, int i, int j) {
	queue<pair<int, int>> q;
	map<char, int> localMap;
	q.push({ i, j });
	
	visited[i][j] = true;

	pair<int, int> temp;
	while (!q.empty()) {		
		temp = q.front();q.pop();

		if (arr[temp.first][temp.second] != '-') {
			if (globalMap.find(arr[temp.first][temp.second]) == globalMap.end()) { //처음 발견했으면 생성
				globalMap.insert({ arr[temp.first][temp.second], 0 });
			}
			localMap[arr[temp.first][temp.second]]++;
		}
		
		for (int k = 0; k < 4; k++) {
			int ni = temp.first + direction[1][k];
			int nj = temp.second + direction[0][k];

			if (ni > N - 1 || nj > M - 1 || ni < 0 || nj < 0 || visited[ni][nj] || arr[ni][nj]=='*') continue;
			
			q.push({ ni, nj });
			visited[ni][nj] = true;
		}
	}

	vector<pair<char, int>> v(localMap.begin(), localMap.end());
	sort(v.begin(), v.end(), cmp);	
	globalMap[v[0].first] += v[0].second;
}

string solution(vector<string> arr, int N, int M) {
	string answer = "";

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (arr[i][j] != '-' &&arr[i][j] != '*' &&!visited[i][j]) {
				bfs(arr, N, M, i, j);
			}
		}
	}

	map<char, int>::iterator iter;

	for (iter = globalMap.begin(); iter != globalMap.end(); iter++) {
		answer += iter->first;
		answer += iter->second+48;
	}
	
	return answer;
}

int main(void) {
	int M = 7;
	int N = 5;
	vector<string> arr = { "--*AB**","AB*A*BA", "B*-A*BB", "*DA*A**", "BC*BC*C" };
	
	cout << solution(arr, N, M) << endl;;
}