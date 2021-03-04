#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int toInt(string str) {
	int h, m, s, ret;

	h = stoi(str.substr(0, 2)) * 3600;
	m = stoi(str.substr(3, 2)) * 60;
	s = stoi(str.substr(6, 2));

	ret = h + m + s;

	return ret;
}

string toString(int i) {
	string h, m, s, ret;

	h = to_string(i / 3600);
	m = to_string((i % 3600) / 60);
	s = to_string(i % 60);

	h = h.size() == 1 ? "0" + h : h;
	m = m.size() == 1 ? "0" + m : m;
	s = s.size() == 1 ? "0" + s : s;
    
	ret = h + ":" + m + ":" + s;
    
	return ret;
}

string solution(string play_time, string adv_time, vector<string> logs) {
	string answer = "";
	vector<int> time(360000, 0);
	for (int i = 0; i < logs.size(); i++) {
		int start = toInt(logs[i].substr(0, 8));
		int end = toInt(logs[i].substr(9));
        
		for (int j = start; j < end; j++) {
			time[j]++;
		}
	}

	int advS = 0;
	int advE = toInt(adv_time);
	int limit = toInt(play_time);
	long long tempMax = 0;
	long long MAX;

	for (int i = advS; i < advE; i++) {
		tempMax += time[i];
	}
	
	MAX = tempMax;
	answer = "00:00:00";
    
	while (advE < limit) {
		tempMax -= time[advS++];
		tempMax += time[advE++];
		if (MAX < tempMax) {
			answer = toString(advS);
			MAX = tempMax;
		}
	}

	return answer;
}