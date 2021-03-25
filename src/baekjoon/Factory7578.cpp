#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

typedef long long ll;

void update(vector<ll> &tree, int node, int idx, int start, int end, int diff) {
	if (!(start <= idx && idx <= end)) return;
	
	tree[node] += diff;
	if (start != end) {
		int mid = (start + end) / 2;
		update(tree,  node*2,  idx, start, mid, diff);
		update(tree, node*2+1, idx, mid+1, end, diff);
	}
}

ll sum(vector<ll> &tree, int node, int start, int end,  int left, int right){
	if (left > end || right < start) return 0;

	if (left <= start && end <= right)
		return tree[node];

	int mid = (start + end) / 2;
	return sum(tree, node * 2, start, mid, left, right) + sum(tree, node * 2 + 1, mid + 1, end, left, right);
}

int main(void) {
	int N;
	ll answer = 0;
	cin >> N;
	vector<int> arr(N);
	vector<ll> tree(N * 4);
	vector<int> indexArr(1000001);
	
	for (int i = 0; i < N; i++) {
			cin >> arr[i];
	}
	for (int i = 0; i < N; i++) {
		int idx;
		cin >> idx;
		indexArr[idx] = i;
	}

	int idx;
	for (int i = 0; i < N; i++) {
		update(tree, 1, indexArr[arr[i]], 0, N-1, 1);
		if (indexArr[arr[i]] == N - 1) continue;
		answer += sum(tree, 1, 0, N - 1, indexArr[arr[i]] + 1, N - 1);
		
	}
	
	cout << answer;
	return 0;
}