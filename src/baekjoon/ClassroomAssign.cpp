#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
#include <queue>

using namespace std;

int main(void) {
	int input;
	int N;
	cin >> N;
	vector<pair<int, int>> v(N, pair<int, int>());

	for (int i = 0; i < N; i++) {
		cin >> v[i].first >> v[i].second;
	}
	
	sort(v.begin(), v.end(), greater<pair<int, int>>());
	
	pair<int, int> temp;
	priority_queue<pair<int, int>> teaching;
	int MAX = 0;

	while (!v.empty()) {
		temp = v.back();
		v.pop_back();
		
		int start = temp.first;
		int end = temp.second;

		while (!teaching.empty()) {
			if (-teaching.top().first <= start) teaching.pop();
			else break;
		}
		teaching.push({ -end, start });
		int size = teaching.size();
		MAX = max(MAX, size);
	}
	cout << MAX;

	return 0;
}