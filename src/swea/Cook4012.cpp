#include <iostream>
#include<algorithm>

using namespace std;

bool visited[17];
int MIN = 1000000000;

int res1 = 0;
int res2 = 0;
int ans;
int n;
int arr[17][17];

void bruteForce(int count, int k) {
	if (k == n) return; //바운더리 넘어가면 종료
	if (count == n / 2) { //체크한 것이 절반이 됐으면
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i] == true && visited[j] == true) {
					res1 += arr[i][j];					
				}
				else if(visited[i] ==false&& visited[j]==false){
					res2 += arr[i][j];					
				}
			}
		}
		ans = abs(res1 - res2);
		MIN = min(ans, MIN);
		res1 = 0;
		res2 = 0;
	}
	else {
		visited[k] = true;
		bruteForce(count + 1, k + 1);
		visited[k] = false;
		bruteForce(count, k + 1);
	}
}

int main(void) {
	int tc;
	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		cin >> n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> arr[i][j];
			}
		}

		bruteForce(0,0);

		cout << "#" << t << " " << MIN << endl;

		for (int j = 0; j < 17; j++) {
			visited[j] = false;
		}
		MIN = 1000000000;
	}

	return 0;
}