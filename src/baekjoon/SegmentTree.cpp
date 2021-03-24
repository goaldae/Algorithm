#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

int init(vector<int>& arr, vector<int>& tree, int left, int right, int node) {
	if (left == right) return tree[node] = arr[left];
	
	int mid = (left + right) / 2;
	return tree[node] = init(arr, tree, left, mid, node * 2) + init(arr, tree, mid + 1, right, node * 2 + 1);
}

int sum(vector<int> &tree, int node, int start, int end,  int left, int right){
	if (left > end || right < start) return 0;

	if (left <= start && end <= right)
		return tree[node];

	int mid = (start + end) / 2;
	return sum(tree, node * 2, start, mid, left, right) + sum(tree, node * 2 + 1, mid + 1, end, left, right);
}

int main(void) {
	int N;
	vector<int> arr = { 1,12,37,2,25,6,11,2,7,6,6,9,12 };
	
	N = arr.size();
	
	vector<int> tree(N * 4);

	init(arr, tree, 0, N - 1, 1);
	
	cout << sum(tree, 1, 0, N - 1, 0, 1);

	return 0;
}