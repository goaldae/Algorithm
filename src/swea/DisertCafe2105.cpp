#include <iostream>

using namespace std;

int n;
int map[21][21];
int disertVisited[101];
int visited[21][21];
int direction[2][4] = { {-1, 1, 1, -1}, {-1, -1, 1, 1} };
int ans = -1;

void bruteForce(int status, int count, int i, int j, int si, int sj, int r) {
	if (r > 4) return; //방향 전환을 5번 이상 했으면 종료
	if (count != 1 && i == si && j == sj && count > ans) { //시작점이고 최대길이야?!
		ans = count;
		return;
	}	

	int ni, nj;
	
	for (int k = 0; k < 2; k++) { //그냥 가느냐, 오른쪽으로 꺾느냐
		status = status + k > 3 ? 0 : status + k; 
		
		ni = i + direction[1][status]; //다음 좌표
		nj = j + direction[0][status];

		if (ni == si && nj == sj) { //다음이 시작점이라면 무조건 들어가기
			bruteForce(status, count, ni, nj, si, sj, r+k); //방문체크 안해도 됨
		}
		else if (ni > n - 1 || nj > n - 1 || ni < 0 || nj < 0 || visited[ni][nj] || disertVisited[map[ni][nj]]) {} //들어갈 수 없는곳은 생략
		else { //들어가면서 체크
			visited[ni][nj] = true;
			disertVisited[map[ni][nj]] = true;
			bruteForce(status, count + 1, ni, nj, si, sj, r + k); //종류+1, 회전횟수+1
			visited[ni][nj] = false; //나오면서 해제
			disertVisited[map[ni][nj]] = false;
		}
	}
}

int main(void) {
	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		cin >> n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {		
				visited[i][j] = true;
				disertVisited[map[i][j]] = true;
				bruteForce(2, 1, i, j, i, j, 0);
				visited[i][j] = false;
				disertVisited[map[i][j]] = false;
			}
		}
		
		cout << "#"<<t<<" "<<ans << endl;
		ans = -1;
	}
	return 0;
}