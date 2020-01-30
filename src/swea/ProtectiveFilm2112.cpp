#include <iostream>

using namespace std;
int	n, m, k;
int map[14][21];
int c[14];
int ans = -1;

int check(int j) {	
	int count = 1;
	int res = count;
	int status = map[0][j]; //현재 상태
	for (int i = 1; i < n; i++) {
		if (status == map[i][j]) { //이전 상태와 같다면
			count++; //하나 올려주고
			if (res < count) res = count;//최댓값이면 업데이트
		}
		else { //아니면
			count = 1; //현재 상태로 초기화
			status = map[i][j];
		}
	}
	return res; //이어져있는 부분의 최대 길이를 반환
}

int checkInit() {
	for (int j = 0; j < m; j++) { //모든 열에 대해서
		if (check(j) < k) { // 한 열이라도 만약 통과되지 않으면
			return 0; //바로 0으로 종료해버리기
		}
	}
	return 1; //다 통과 됐다면 1로 종료하기
}

void changeStatus(int i, int cnt) {
	int temp[21]; //원래 상태를 잠깐 담아둘 배열
	
	if (i == cnt) { //상태를 다 바꿨다면
		if (checkInit() == 1) { //테스트에 통과 된다면!
			ans = i; //상태 바꾼 개수 업데이트
		}
	}
	else { //바꿔야할게 있다면
		if (ans != -1) return;
		for (int j = 0; j < m; j++) { //원래 상태 저장해두기
			temp[j] = map[c[i]][j]; 
		}
		for (int j = 0; j < m; j++) { //B로 상태 다 바꾸기
			map[c[i]][j] = 1;
		}

		changeStatus(i + 1, cnt); //다음으로 넘어가기

		for (int j = 0; j < m; j++) { // A로 상태 다 바꾸기
			map[c[i]][j] = 0;
		}

		changeStatus(i + 1, cnt); //다음으로 넘어가기

		for (int j = 0; j < m; j++) { //원래대로 돌려놓기
			map[c[i]][j] = temp[j]; 
		}
	}
}

void combination(int k, int index, int count) { //0부터 n까지 숫자중에 k개를 뽑아내는 경우
	if (ans != -1) return;
	if (count == k) { //다 뽑았다면
		changeStatus(0, k); //c[0]부터 c[k-1]까지 A, B 상태 바꿔보기
	}
	else {
		for (int i = index; i < n; i++) {
			c[count] = i;
			combination(k, i + 1, count + 1);
		}
	}
}

int main(void) {
	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		cin >> n >> m >> k;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cin >> map[i][j];
			}
		}
		for (int i = 0; i <= n; i++) { //바꾸는 행을 0개부터 n개까지 늘려본다
			if (ans != -1) break; //만약 이미 성공했다면 탈출
			combination(i, 0, 0); 
		}
		cout << "#"<<t<<" "<<ans << endl;
		ans = - 1;
	}
	return 0;
}