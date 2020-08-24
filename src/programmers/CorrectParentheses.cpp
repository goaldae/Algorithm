#include <iostream>
#include <string>
#include <vector>
#include <stack>

using namespace std;

string isRight(string p) {
	string u, v;
	char cur;
	stack<char> s;
	
	if (p == "") {
		return "";
	}

	cur = p[0];//기준
	s.push(p[0]);
	u += p[0];
	int idx = 1;

	while(!s.empty()) {
		if (p[idx] == cur) {//같은 방향이면
			s.push(p[idx]);
		}
		else {//다른 방향이면
			s.pop();
		}
		u += p[idx++];		
	}
	v = p.substr(idx, p.size());
	
	//올바른 괄호인지 확인
	if (u[0] == '(') {//올바른 괄호
		return u + isRight(v);
	}
	else { //균형잡힌 괄호
		string temp = "(";
		temp += isRight(v);
		temp += ')';
		for (int i = 1; i < u.size() - 1; i++) {
			if (u[i] == '(') {
				temp+= ')';
			}
			else {
				temp += '(';
			}
		}
		return temp;
	}
}

string solution(string p) {
	string answer = "";

	answer = isRight(p);

	return answer;
}


int main(void) {
	string input = "(()())()";
	
	cout << solution(input) << endl;
}