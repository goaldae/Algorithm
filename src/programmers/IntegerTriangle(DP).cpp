#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>

using namespace std;

int solution(vector<vector<int>> triangle) {
	int answer = 0;
	int dp[500][500];
	for (int i = 0; i < triangle.size(); i++) {
		for (int j = 0; j < triangle[i].size(); j++) {
			if (i == 0) { //맨 위꼭지점은 저장
				dp[0][0] = triangle[i][j];
			}
			else if(j==0) { //맨 앞에일때
				dp[i][j] = dp[i - 1][j] + triangle[i][j];
			}
			else if (j == triangle[i].size() - 1) { //맨 뒤에일때
				dp[i][j] = dp[i - 1][j-1] + triangle[i][j];
			}
			else {
				dp[i][j] = max(dp[i - 1][j] + triangle[i][j], dp[i - 1][j - 1] + triangle[i][j]);
			}

			if (i == triangle.size() - 1) {
				answer = max(dp[i][j], answer);
			}
		}
		
	}

	return answer;
}

int main(void) {
	vector<vector<int>> triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
	cout << solution(triangle);
	return 0;
}	

