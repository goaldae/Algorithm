#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>

using namespace std;

struct cmp {
	bool operator()(vector<int> &a, vector<int> &b) {
		if (a[1] == b[1]) return a[0] > b[0];
		return a[1] > b[1];
	}
};

int solution(vector<vector<int>> jobs) {
	int answer = 0;
	int time = 0;
	priority_queue<vector<int>, vector<vector<int>>, cmp> pq;
	int totalTime = 0;
	int jobNum = jobs.size();
	sort(jobs.begin(), jobs.end());

	while (!jobs.empty()||!pq.empty()) {
		for (int i = 0; i < jobs.size()&& jobs[i][0] <= time; i++) {
			pq.push(jobs[i]);
			jobs.erase(jobs.begin()+i);
			i--;
		}
		if(pq.empty())time++;
		else {
			if (!pq.empty()) {
				vector<int> cur = pq.top();
				pq.pop();
				time += cur[1];
				totalTime += time - cur[0];
			}
		}
	}
	answer = totalTime / jobNum;
	
	return answer;
}

int main(void) {
		
	vector<vector<int>> input = {{0, 9},{0, 4},{0, 5},{0, 7},{0, 3}};
	cout<<solution(input);
	return 0;
}	