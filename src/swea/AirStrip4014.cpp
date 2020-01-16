#include <iostream>

using namespace std;

int n, x;
int map[40][20];
int tempMap[20][20];
int result = 0;
bool visited[40];
void solve(int k, int slen, int status, int r) { //열, 경사로 유지 길이, 현재 상태
	if (visited[r]) return;
	if (k == n - 1) { //마지막에 도착
		if (status == 1) {
			result++;
			visited[r] = true;
		}
		else if (status == 2 || status == 3) {
			if (slen == x) {
				result++;
				visited[r] = true;
			}
		}		
	}
	else if (status == 2 || status == 3) { //경사로인 상태
		if (slen == x) { //마지막 경사로라면
			if (status == 2) { //올라가는 경사로
				if (map[r][k] + 1 == map[r][k + 1]) { //이어진다면
					solve(k + 1, 0, 1, r); //평지로 다음상태넘어가기
				}
				else return;
			}
			else { //내려가는 경사로
				if (map[r][k] - 1 == map[r][k + 1]) { //다음칸이 하나 작다면
					solve(k + 1, 1, 3, r); //"경사로로 시작"해서 넘어가기 ->아래
				}
				else if (map[r][k] == map[r][k + 1]) {//다음칸이랑 같다면
					solve(k + 1, 0, 1, r); //다음으로 그대로 넘어가기
					solve(k + 1, 1, 2, r); //"경사로로 시작"해서 제자리 ->위
				}
				else {
					return;
				}
			}
		}
		else { //마지막 경사로가 아니라면
			if (map[r][k + 1] != map[r][k]) { //다음이 차이가 난다
				return;
			}
			else { //동일한 높이다
				solve(k + 1, slen + 1 , status, r); //경사로 이어서 넘어가기
			}
		}
	}
	else { //아무것도 아닌 상태
		if (map[r][k + 1] - 1 == map[r][k]) { //다음이 한칸 크다
			solve(k, 1, 2, r); //"경사로로 시작"해서 제자리  ->위
		}
		else if (map[r][k + 1] == map[r][k] - 1) { //다음칸이 한칸 작다
			solve(k + 1, 1, 3, r); //"경사로로 시작"해서 넘어가기 ->아래
		}
		else if (map[r][k + 1] == map[r][k]) { //다음이 같으면
			solve(k + 1, 0, status, r); //다음으로 그대로 넘어가기
			solve(k, 1, 2, r); //"경사로로 시작"해서 제자리 ->위
		}
		else { //높이 차이가 많이 나는경우
			return;
		}
	}
}

int main() {
	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		cin >> n >> x;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tempMap[i][j] = map[j][i];
			}
		}
		for (int i = n; i < 2*n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = tempMap[i-n][j];
			}
		}
		for (int j = 0; j < 2*n; j++) {
			solve(0, 0, 1, j);
		}


		cout <<"#"<<t<<" "<<result << endl;
		result = 0;
		for (int j = 0; j < 40; j++) {
			visited[j] = false;
		}
	}

	
	return 0;
}