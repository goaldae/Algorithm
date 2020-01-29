#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct question {
	int r, k;
};

int magnet[4][8];
int n;
vector<question> q;

int sum() {
	int ans = 0;
	for (int i = 0; i < 4; i++) {
		ans += magnet[i][0]*pow(2, i);
	}
	return ans;
}

void rotate(){
	int temp;
	for (int i = 0; i < q.size(); i++) {
		if (q[i].r == 1) {
			temp = magnet[q[i].k][7];
			for (int j = 6; j >= 0; j--) {
				magnet[q[i].k][j + 1] = magnet[q[i].k][j];
			}
			magnet[q[i].k][0] = temp;
		}
		else {
			temp = magnet[q[i].k][0];
			for (int j = 1; j < 8; j++) {
				magnet[q[i].k][j - 1] = magnet[q[i].k][j];
			}
			magnet[q[i].k][7] = temp;
		}
	}
	q.clear();
}

void simul(int s, int r, int k) {
	if (k > 3 || k < 0) return;
	if (s == 1) { //위로 올라가면서 확인
		if (magnet[k][2] == magnet[k + 1][6]) return;
		if (r == 1) { //시계방향 회전
			q.push_back({ -1, k });
			simul(1, -1, k - 1);
		}
		else { //반시계 회전
			q.push_back({ 1, k });
			simul(1, 1, k - 1);
		}
	}
	else if(s == 2) { //아래로 내려가면서 확인
		if (magnet[k][6] == magnet[k - 1][2]) return;
		if (r == 1) { //시계방향 회전
			q.push_back({ -1, k });
			simul(2, -1, k + 1);
		}
		else { //반시계 회전
			q.push_back({ 1, k });
			simul(2, 1, k + 1);
		}
	}
}

int main(void) {
	int tc;
	cin >> tc;
	int input1, input2;
	for (int t = 1; t <= tc; t++) {
		cin >> n;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				cin >> magnet[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			cin >> input1 >> input2;
			input1--;
			q.push_back({ input2, input1 });
			simul(1, input2, input1 - 1);
			simul(2, input2, input1 + 1);
			rotate();
			
		}		
		cout<<"#"<<t<<" "<< sum() <<endl;		
	}

	return 0;
}