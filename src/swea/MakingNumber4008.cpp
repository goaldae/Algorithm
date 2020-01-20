#include<iostream>
#include<algorithm>

using namespace std;

int nums[12];
int n;
int oper[4];

int MAX = -100000000;
int MIN = 100000000;
int ans;

void solve(int k, int num) {
	if (k == n) {
		MIN = min(num, MIN);
		MAX = max(num, MAX);
	}
	else {
		if (oper[0] != 0) {
			oper[0]--;
			solve(k + 1, num + nums[k]);
			oper[0]++;
		}
		if (oper[1] != 0) {
			oper[1]--;
			solve(k + 1, num - nums[k]);
			oper[1]++;
		}
		if (oper[2] != 0) {
			oper[2]--;
			solve(k + 1, num * nums[k]);
			oper[2]++;
		}
		if (oper[3] != 0) {
			oper[3]--;
			solve(k + 1, num / nums[k]);
			oper[3]++;
		}
	}
}

int main(void){
	int tc;

	cin >> tc;
	for (int t = 1; t <= tc; t++) {
		cin >> n;
		for (int i = 0; i < 4; i++) {
			cin >> oper[i];
		}
		for (int i = 0; i < n; i++) {
			cin >> nums[i];
		}
		
		solve(1, nums[0]);
		ans = MAX - MIN;
		cout <<"#"<<t<<" "<<ans<< endl;
		ans = 0;
		MAX = -100000000;
		MIN = 100000000;
	}

	return 0;
}
