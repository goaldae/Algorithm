#include <iostream>
#include <vector>

using namespace std;

int map[4002][4002] = {};

struct atom {
	int i, j, p, status;
	atom(int j_, int i_, int status_, int p_) {
		i = i_;
		j = j_;
		p = p_;
		status = status_;
	}
};

vector<atom> atoms;
int atomsNum;
int direction[2][4] = { {0,0,-1,1}, {1, -1, 0, 0} };
int res = 0;

void clearMap() {
	for (int i = 0; i < atoms.size(); i++) {
		if (atoms[i].i > 4000 || atoms[i].i < 0 || atoms[i].j>4000 || atoms[i].j < 0) continue;
		map[atoms[i].i][atoms[i].j] = 0;
	}
}

void solve() {
	int ci, cj;
	while (atomsNum > 0) {
		for (int b = 0; b < atoms.size(); b++) { //0.5초 후 가는곳 체크

			if (atoms[b].status == 4) continue; //죽은 원소는 생략

			ci = atoms[b].i + direction[1][atoms[b].status];
			cj = atoms[b].j + direction[0][atoms[b].status];

			if (ci > 4000 || cj > 4000 || ci < 0 || cj < 0) {

				atoms[b].status = 4;
				atomsNum--;
				continue;
			}
			map[ci][cj]++;
		}

		for (int b = 0; b < atoms.size(); b++) {
			if (atoms[b].status == 4) continue;
			ci = atoms[b].i + direction[1][atoms[b].status];
			cj = atoms[b].j + direction[0][atoms[b].status];

			if (map[ci][cj] >= 2) {
				atoms[b].status = 4;
				atomsNum--;
				atoms[b].i = ci;
				atoms[b].j = cj;
				res += atoms[b].p;
			}
			else {
				atoms[b].i = ci;
				atoms[b].j = cj;
			}
		}
		clearMap();
	}



}

int main() {

	int tc;
	int t1, t2, t3, t4;

	cin >> tc;
	for (int t = 1; t <= tc; t++) {
		cin >> atomsNum;
		for (int i = 0; i < atomsNum; i++) {
			cin >> t1 >> t2 >> t3 >> t4;
			atoms.push_back(atom((t1 + 1000) * 2, (t2 + 1000) * 2, t3, t4));
		}
		solve();
		cout << "#" << t << " " << res << endl;
		res = 0;
		atoms.clear();
	}

	return 0;
}