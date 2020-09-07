#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

struct Info {
	int stg;
	double fail;
};

vector<Info> res;

bool cmp(Info a, Info b) {
	if (a.fail == b.fail)return a.stg < b.stg;
	return a.fail > b.fail;
}

vector<int> solution(int N, vector<int> stages) {
	vector<int> answer;
	
	double failRate;
	for (int cur = 1; cur <= N; cur++) {
		int chg = 0;
		int f = 0;
		for (int i = 0; i < stages.size(); i++) {
			if (cur <= stages[i]) { //도전
				chg++;
				if (cur == stages[i]) { //실패
					f++;
				}
			}
		}
		if (chg == 0) res.push_back({ cur, 0 });
		else {
			res.push_back({ cur, (double)f / chg });
		}		
	}
	sort(res.begin(), res.end(), cmp);
	
	for (int i = 0; i < res.size(); i++) {
		
		answer.push_back(res[i].stg);
	}
	
	return answer;
}

int main(void) {	
	int input1 = 5;
	vector<int> input2 = { 2, 1, 2, 6, 2, 4, 3, 3 };

	vector<int> ans;

	ans = solution(input1, input2);

	for (int i = 0; i < ans.size(); i++) {
		cout << ans[i] << " ";
	}cout << endl;
	
	
	return 0;
}