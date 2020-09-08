#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <unordered_map>

using namespace std;

int N;
vector<vector<string>> glbRelation;
vector<vector<int>> canKeys[9];
unordered_map<string, int> chk;
int answer = 0;

void permutation(int cnt, int cur, int k, vector<int>& p) {
	if (k == cnt) {
		//유일성 체크
		for (int i = 0; i < glbRelation.size(); i++) { //튜플 순회
			string tmpStr = "";
			for (int j = 0; j < p.size(); j++) { //컬럼 순회
				tmpStr += glbRelation[i][p[j]];
			}
			
			if (chk[tmpStr] != 0) {
				chk.clear();
				
				return; //유일성이 깨진다				
			}
			else chk[tmpStr]++;
		}
		chk.clear();
		
		//최소성 체크
		//k: 현재 찾는 개수
		int tmpCnt = 0;
		for (int i = 1; i < k;i++) { //한개짜리부터 그 미만까지						
				for (int j = 0; j < canKeys[i].size(); j++) { //맞춰볼 순서쌍		
					for (int a = 0; a < p.size(); a++) { //후보 순서쌍		
						for (int l = 0; l < i; l++) { //맞춰볼 순서쌍 한글자씩
							if (canKeys[i][j][l] == p[a]) {
								tmpCnt++;
							}
						}
					}
				if (tmpCnt == i) return;
				tmpCnt = 0;
			}
		}

		vector<int> corr;

		for (int b = 0; b < p.size(); b++) {
			corr.push_back(p[b]);
		}
		canKeys[k].push_back(corr);
		answer++;		
	}
	else {
		for (int i = cur; i < N; i++) {
			p.push_back(i);
			permutation(cnt+1,i+1, k, p);
			p.pop_back();
		}
	}
}

int solution(vector<vector<string>> relation) {	
	N = relation[0].size(); //컬럼수
	glbRelation = relation;
	
	vector<int> p;
	for (int i = 1; i <= N; i++) { //컬럼수 1개일때, 2개일때, 3개일때 ...
		permutation(0, 0, i, p);
	}

	return answer;
}


int main(void) {
	vector<vector<string>> input = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
		{"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"},
		{"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};

	cout<<solution(input);
	
	return 0;
}