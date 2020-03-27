#include <iostream>
#include <algorithm>
using namespace std;

int plan[12];
int fee[4];
int charge = 0;
int res = 100000000;

void solve(int k) {
	if (k >= 12) {
		//cout << charge << endl;
		res = min(charge, res);
	}
	else {
		if (plan[k] == 0) solve(k + 1); //해당 달에 수업이 없으면 넘어가기
		
		charge += fee[0] * plan[k]; //일일이용
		solve(k + 1);
		charge -= fee[0] * plan[k];
		
		charge += fee[1]; //한달이용
		solve(k + 1);
		charge -= fee[1]; 

		charge += fee[2]; //세달이용
		solve(k + 3);
		charge -= fee[2]; 

		charge += fee[3]; //일년이용
		solve(12);
		charge -= fee[3];
	}
}

int main() {
	int tc;

	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		for (int i = 0; i < 4; i++) {
			cin >> fee[i];
		}
		for (int j = 0; j < 12; j++) {
			cin >> plan[j];
		}

		solve(0);
		cout << "#" << t << " " << res << endl;
		charge = 0;
		res = 100000000;
	}

	return 0;
}