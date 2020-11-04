#include <string>
#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<vector<int>> routes) {
    int answer = 1;
    sort(routes.begin(), routes.end());
    
    int cPos = routes[0][1]; //첫번째 자동차 끝나는 지점 설치
    
    for(int i = 1; i<routes.size(); i++){
        if(cPos>routes[i][1]){//다음 차의 끝나는 시점이 카메라보다 작을때
            cPos = routes[i][1];            
        }else if(cPos<routes[i][0]){//아예 카메라범위를 벗어날때
            cPos = routes[i][1];
            answer++;
        }
    }
    return answer;
}