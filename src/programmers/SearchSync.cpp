#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

struct Trie {
	Trie *node[26];
	unordered_map<int, int> m; //문자열 길이:단어수
	
	Trie() {
		for (int i = 0; i < 26; i++) {
			node[i] = NULL;
		}
	}

	int toInt(char c) {
		return c - 'a';
	}

	void insert(string s, Trie *t) {
		int next;
		Trie *cur = t;

		for (int i = 0; i < s.size(); i++) {
			next = toInt(s[i]);
			
			if (!cur->node[next]) { //없으면
				cur->node[next] = new Trie();
			}
			cur = cur->node[next];
			cur->m[s.size()]++;
		}		
	}

	int findQuery(string s, Trie *t) {
		int next;
		Trie *cur = t;

		for (int i = 0; i < s.size(); i++) {
			if (s[i] == '?') {
				return cur->m[s.size()];
			}
			else {
				next = toInt(s[i]);
				if (!cur->node[next]) { //없는 글자다
					return 0;
				}
				else {
					cur = cur->node[next];
				}
			}
			if (i == s.size() - 1) {
				if (cur->m[s.size()] != 0) {
					return cur->m[s.size()];
				}
				else {
					return 0;
				}
			}
		}		
	}
};

vector<int> solution(vector<string> words, vector<string> queries) {
	vector<int> answer;
	Trie *trie = new Trie();
	Trie *reTrie = new Trie();

	for (int i = 0; i < words.size(); i++) {
		trie->insert(words[i], trie);
	}
	for (int i = 0; i < words.size(); i++) {
		reverse(words[i].begin(), words[i].end());
		reTrie->insert(words[i], reTrie);
	}
	for (int i = 0; i < queries.size(); i++) {
		if (queries[i][0] == '?') {
			if (queries[i][queries[i].size() - 1] == '?') { //모든문자가 ?일때
				int cnt = 0;
				for (int j = 0; j < 26; j++) {
					if (trie->node[j]) {
						cnt += trie->node[j]->m[queries[i].size()];
					}
				}
				answer.push_back(cnt);				
			}
			else {
				reverse(queries[i].begin(), queries[i].end());
				answer.push_back(reTrie->findQuery(queries[i], reTrie));
			}			
		}
		else {
			answer.push_back(trie->findQuery(queries[i], trie));
		}
	}

	return answer;
}

int main(void) {	
	vector<string> input1 = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
	vector<string> input2 = { "fro??", "????o", "fr???", "fro???", "pro?", "?????" };
	
	vector<int> ans = solution(input1, input2);

	for (int i = 0; i < ans.size(); i++) {
		cout << ans[i] << " ";
	}

	return 0;
}