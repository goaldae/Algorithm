#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int getParent(int parent[], int a) {
	if (parent[a] == a) return a;
	else return parent[a]=getParent(parent, parent[a]);
}

void unionParent(int parent[], int a, int b) {
	a = getParent(parent, a);
	b = getParent(parent, b);
	if (a < b) parent[b] = a;
	else parent[a] = b;
}


bool isSameParent(int parent[], int a, int b) {
	a = getParent(parent, a);
	b = getParent(parent, b);
	if (a == b) return true;
	return false;
}

int main(void) {

	int parent[10];

	for (int i = 1; i < 9; i++) {
		parent[i] = i;
	}

	unionParent(parent, 2, 3);
	unionParent(parent, 1, 2);
	unionParent(parent, 3, 4);
	unionParent(parent, 5, 6);
	unionParent(parent, 6, 7);
	unionParent(parent, 7, 8);
	printf("1과 5는 연결되어있나요? %d\n", isSameParent(parent, 1, 5));

	for (int i = 1; i < 9; i++) {
		cout << parent[i] << " ";
	}

	return 0;
}

