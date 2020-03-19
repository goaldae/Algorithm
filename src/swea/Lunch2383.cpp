#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Person {
	int time;
	int i, j;
	int status;
	int curHeight;
};

struct Step {
	int i, j;
	int height;
};

int map[11][11];
int n;

vector<Person> people;
vector<Step> stepInfo;
int pNum = 0;
int res = 10000;

bool cmp(Person a, Person b) {
	if (a.time < b.time) {
		return true;
	}
	return false;
}

vector<Person> fPeople;
vector<Person> sPeople;
vector<Person> fStep;
vector<Person> sStep;

void classifyPeople(int k) {
	if (k == pNum) {
		fPeople.clear();
		sPeople.clear();
		fStep.clear();
		sStep.clear();

		for (int i = 0; i < people.size(); i++) { //상태에 따라 분류하기
			if (people[i].status == 1) {
				people[i].time = abs(people[i].i - stepInfo[0].i) + abs(people[i].j - stepInfo[0].j); //도착시간
				fPeople.push_back(people[i]);

			}
			else {
				people[i].time = abs(people[i].i - stepInfo[1].i) + abs(people[i].j - stepInfo[1].j); //도착시간
				sPeople.push_back(people[i]);
			}
		}
		sort(fPeople.begin(), fPeople.end(), cmp);
		sort(sPeople.begin(), sPeople.end(), cmp);
		int time = 0;
		int fNum = 0;
		int sNum = 0;
		while (1) {
			time++;

			if (fPeople.empty() && fStep.empty() && sStep.empty() && sPeople.empty()) break;
			
			for (int i = 0; i < fStep.size(); i++) {
				if (++fStep[i].curHeight == stepInfo[0].height) {
					fStep[i].status = 5; //다다르면 죽임;
				}
			}
			while (!fStep.empty() && fStep.front().status == 5) {
				fStep.erase(fStep.begin());
				fNum--;
			} //계단 사람 처리 끝

			for (int i = 0; i < sStep.size(); i++) {
				if (++sStep[i].curHeight == stepInfo[1].height) {
					sStep[i].status = 5; //다다르면 죽임;
				}
			}
			while (!sStep.empty() && sStep.front().status == 5) {
				sStep.erase(sStep.begin());
				sNum--;
			} //계단 사람 처리 끝

			while (!fPeople.empty() && fPeople.front().time <= time && fNum < 3) {
				 //계단에 갈 수 있을 때
				fStep.push_back(fPeople.front());
				fPeople.erase(fPeople.begin());
				fNum++;
			}

			while (!sPeople.empty() && sPeople.front().time <= time && sNum < 3) {
				//계단에 갈 수 있을 때
				sStep.push_back(sPeople.front());
				sPeople.erase(sPeople.begin());
				sNum++;
			}			
		}
		res = min(res, time);
	}
	else {
		people[k].status = 1;
		classifyPeople(k + 1);
		people[k].status = 2;
		classifyPeople(k + 1);
	}
}
void classify() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == 1) {
				people.push_back({ 0, i, j, 0, 0 });
				pNum++;
			}
			else if (map[i][j] != 0) {
				stepInfo.push_back({ i, j, map[i][j] });
			}
		}
	}
	classifyPeople(0);
}

void initSolve() {
	classify();
}

int main() {
	int tc;

	cin >> tc;
	for (int t = 1; t <= tc; t++) {
		cin >> n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}

		initSolve();
		cout <<"#"<<t<<" "<<res+1 << endl;
		people.clear();
		stepInfo.clear();
		pNum = 0;
		res = 10000;
	}

	return 0;
}