#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<char> oriOpers = { '+','-','*' };
bool visited[3];
long long MAX = 0;
vector<int> p = { 0,0,0 };
vector<long long> nums;
vector<char> opers;

void calMax(vector<int>& p) {
	vector<long long> tnums;
	vector<char> topers;

	for (int i = 0; i < opers.size();i++) {
		tnums.push_back(nums[i]);
		topers.push_back(opers[i]);
	}

	tnums.push_back(nums[nums.size()-1]);

	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < topers.size(); j++) {
			if (topers[j] == oriOpers[p[i]]) {
				long long temp;
				if (topers[j] == '*') {
					temp = tnums[j] * tnums[j + 1];
				}
				else if (topers[j] == '-') {
					temp = tnums[j] - tnums[j + 1];
				}
				else {
					temp = tnums[j] + tnums[j + 1];
				}
				tnums.erase(tnums.begin() + j);
				tnums.erase(tnums.begin() + j);
				tnums.insert(tnums.begin() + j, temp);
				topers.erase(topers.begin() + j);
				j--;		
			}			
		}
	}
	if (MAX < abs(tnums[0])) MAX = abs(tnums[0]);
}


void permutation(int k) {	
	if (k == 3) {
		cout<<p[0]<< p[1]<< p[2]<<endl;
		calMax(p);
	}
	else {
		for (int i = 0; i < 3;i++) {			
			if (visited[i]) continue;
			p[k] = i;
			visited[i] = true;
			permutation(k + 1);
			visited[i] = false;
		}
	}
}

void split(string input) {
	int idx = 0;
	
	string tempS = "";

	while (idx < input.size()) {
		if (input[idx] >= 48 && input[idx] <= 57) {
			tempS += input[idx++];
		}
		else {
			opers.push_back(input[idx++]);
			nums.push_back(stoi(tempS));
			tempS = "";
		}
	}
	nums.push_back(stoi(tempS));
}

long long solution(string expression) {	
	split(expression);
	permutation(0);
	return MAX;
}

int main(void) {	
	string input = "100-200*300-500+20";

	cout << solution(input) << endl;
}