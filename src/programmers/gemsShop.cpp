#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

vector<int> solution(vector<string> gems) {
	vector<int> answer;
	int l = 0;
	int r = 0; //왼쪽과 오른쪽을 가리키는 것
	int gemKind = 0; //보석 종류 개수
	int temp;
	int ret = 100001;
	unordered_map<string, int> map;

	for (int i = 0; i < gems.size(); i++) {
		map[gems[i]]++;
	}
	gemKind = map.size();
	map.clear();
	
	map[gems[r]]++;

	while (r < gems.size()) {
		
		if (map.size() == gemKind) { //모든 종류의 보석이 다 포함됐을 때
			if (r - l + 1 == gemKind) { //길이가 딱 종류와 맞을때는 더 볼필요가 없음
				answer.clear();
				answer.push_back(l + 1);
				answer.push_back(r + 1);
				return answer;
			}
			else {
				temp = r - l + 1;

				if (temp < ret) {
					ret = temp;
					answer.clear();
					answer.push_back(l + 1);
					answer.push_back(r + 1);
				}
			}
			map[gems[l]]--;
			if (map[gems[l]] == 0) map.erase(gems[l]);
			l++;			
		}
		else {
			r++;
			if (r == gems.size()) break;
			map[gems[r]]++;

		}
	}

	return answer;
}

int main(void) {
	vector<string> input = { "a","c","c","c","c","c","c","d","c","a" };
	
	vector<int> v = solution(input);

	cout << v[0] << " " << v[1] << endl;
	
}