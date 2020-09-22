#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <unordered_map>
#include <stack>

using namespace std;

int N;

int solution(vector<vector<int>> board, vector<int> moves) {
	int answer = 0;
	N = board.size();
	stack<int> s;
	for (int i = 0; i < moves.size(); i++) {
		int curC = moves[i]-1;
		int curR = 0;
		int item = -1;
		while (curR < N) {
			
			if (board[curR][curC] != 0) {
				item = board[curR][curC];
				
				break;
			}
			else {
				curR++;
			}
		}
		
		if (item == -1) continue; //잡은게없으면 생략
		
		board[curR][curC] = 0;
		
		if (s.empty()) { //스텍 비어있면
			s.push(item);
		}
		else { //스텍에 먼가 있으면
			if (s.top() == item) { //맨 위랑 같은녀석이면
				answer+=2;
				s.pop();
			}else{
				s.push(item);
			}
		}
	}

	return answer;
}

int main(void) {
	vector<vector<int>> board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
	vector<int> moves = {1, 5, 3, 5, 1, 2, 1, 4};


	cout<< solution(board, moves);
	
	
	return 0;
}