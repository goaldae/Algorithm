#include <iostream>
#include <vector>
#include <queue>
#include <set>
#include <algorithm>

using namespace std;

struct a {
	int start;
	int end;
};

struct cmp {
	bool operator()(a t, a u) {
		return t.start > u.start;
	}
};

bool fcmp(a &t, a &u) {
	return t.start < u.start;
}

vector<a> v;
priority_queue<a, vector<a>, cmp> pq;
set<a, cmp> s;

int main(void) {
	v.push_back({ 2, 3 });
	v.push_back({ 3, 1 });
	v.push_back({ 1, 2 });

	pq.push({ 2, 3 });
	pq.push({ 3, 1 });
	pq.push({ 1, 2 });
	
	s.insert({ 2, 3 });
	s.insert({ 3, 1 });
	s.insert({ 1, 2 });
	
	sort(v.begin(), v.end(), fcmp);
	
	for (int i = 0; i < 3; i++) {
		cout << v[i].start << " " << v[i].end << endl;
	}cout << endl;

	for (int i = 0; i < 3; i++) {
		cout << pq.top().start << " " << pq.top().end << endl;
		pq.pop();
	}cout << endl;
	
	set<a>::iterator it;
	for (it = s.begin(); it != s.end(); it++) {
		cout << it->start << " " << it->end << endl;
	}cout << endl;

	return 0;
}
