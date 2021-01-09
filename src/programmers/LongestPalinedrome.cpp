#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int MAX = -1;

int evenCheck(string s, int k) {
	int ret , idx = 0, r = k+1, l = k;
	while (1) {
		if (r >= s.size() || l < 0 || s[r] != s[l]) {
			break;
		}
		idx++;
		l--;
		r++;
	}
	ret = idx * 2;
	return ret;
}

int oddCheck(string s, int k) {
	int ret = 0, idx = 0;
	while (1) {
		if (idx + k >= s.size() || k - idx < 0 || s[idx + k] != s[k - idx]) {
			break;
		}
		idx++;
	}
	ret = idx * 2 - 1;
	return ret;
}

int solution(string s) {
	int answer = 0;

	for (int i = 0; i < s.size(); i++) {
		MAX = max(oddCheck(s, i), MAX);
		MAX = max(evenCheck(s, i), MAX);
	}

	answer = MAX;

	return answer;
}