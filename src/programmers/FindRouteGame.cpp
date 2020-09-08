#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <unordered_map>

using namespace std;

struct Info {
	int id;
	int x, y;
};

struct Node {
	int id;
	int x;
	Node* left;
	Node* right;
	Node() {
		left = NULL;
		right = NULL;
	}
};

bool cmp(Info a, Info b) {
	if (a.y == b.y) return a.x < b.x;
	return a.y > b.y;
}

void addEdge(Node* node, Info info) {
	if (node->x > info.x) {
		if (node->left == NULL) { //없다면 만들기
			Node* newNode = new Node();
			newNode->id = info.id;
			newNode->x = info.x;
			node->left = newNode;
		}
		else { //한단계 들어가기
			addEdge(node->left, info);
		}
	}
	else {
		if (node->right == NULL) { //없다면 만들기
			Node* newNode = new Node();
			newNode->id = info.id;
			newNode->x = info.x;
			node->right = newNode;
		}
		else { //한단계 들어가기
			addEdge(node->right, info);
		}
	}
}

void preOrder(Node* node, vector<int>& answer) {
	if (node == NULL) return;
	answer.push_back(node->id);
	preOrder(node->left, answer);
	preOrder(node->right, answer);
}

void posOrder(Node* node, vector<int>& answer) {
	if (node == NULL) return;
	posOrder(node->left, answer);
	posOrder(node->right, answer);
	answer.push_back(node->id);
}


vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
	vector<vector<int>> answer;
	vector<Info> graphInfo;

	for (int i = 0; i < nodeinfo.size(); i++) {
		Info info;
		info.id = i + 1;
		info.x = nodeinfo[i][0];
		info.y = nodeinfo[i][1];
		graphInfo.push_back(info);
	}
	sort(graphInfo.begin(), graphInfo.end(), cmp);
	
	Node* root = new Node();
	root->id = graphInfo[0].id;
	root->x = graphInfo[0].x;
	
	for (int i = 1; i < graphInfo.size(); i++) {		
		addEdge(root, graphInfo[i]);
	}

	vector<int> res;
	preOrder(root, res);
	answer.push_back(res);
	res.clear();
	posOrder(root, res);	
	answer.push_back(res);

	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < res.size(); j++) {
			cout << answer[i][j] << " ";
		}cout << endl;
	}

	return answer;
}

int main(void) {
	vector<vector<int>> input = { {5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2} };
	solution(input);
	
	
	return 0;
}