#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <map>
#include <string>

using namespace std;

string ans = "";


using namespace std;

int shortest(int cutNum, string s) {
	
	int n = s.size() / cutNum; //잘린 개수
	
	vector<string> temp;
	for (int i = 0; i < n; i++) {
		
		temp.push_back(s.substr(i*cutNum, cutNum));
	}
	if (s.size() % cutNum != 0) {
		
		temp.push_back(s.substr(n*cutNum, s.size() % cutNum));
	}

	string cur = temp[0];
	int cnt = 1;
	string answer = "";
	for (int i = 1; i < temp.size(); i++) {
		
		if (cur != temp[i]) {
			if (cnt != 1) {
				answer += to_string(cnt);
			}
			answer += cur;

			cnt = 1;
			cur = temp[i];
		}
		else {
			cnt++;
		}
	}
	if (cnt != 1) {
		answer += to_string(cnt);
	}
	answer += cur;
	
	return answer.size();
}

int solution(string s) {
	int answer = 10000;

	if (s.size() == 1) return 1;

	for (int i = 1; i <= s.size() / 2; i++) {
		
		answer = min(answer, shortest(i, s));
	}

	return answer;
}

int main(void) {
	cout << solution("abcabcabcabcdededededede");
	
	
}