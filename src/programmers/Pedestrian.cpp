#include <vector>
#include <iostream>
using namespace std;

int MOD = 20170805;

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
int solution(int m, int n, vector<vector<int>> city_map) {
    int answer = 0;
    
    vector<vector<int> > resMap(m, vector<int>(n, 0));
    
    for(int i = 0; i<m; i++){//0번째열 모두 1로 초기화
        if(city_map[i][0]==1) break;
        resMap[i][0] = 1;
    }
    for(int i = 0; i<n; i++){//0번째행 모두 1로 초기화
        if(city_map[0][i]==1)break;
        resMap[0][i] = 1;
    }
    for(int i = 1; i<m; i++){
        for(int j = 1; j<n; j++){
            if(city_map[i][j]==1) resMap[i][j] = 0;
            else{
                int tempI = i-1;
                int tempJ = j-1;
                int up =resMap[tempI][j];
                int left =resMap[i][tempJ];
                
                while(city_map[tempI][j]==2){
                    --tempI;
                    if(tempI<0||city_map[tempI][j]==1){
                        up = 0;
                        break;    
                    }
                    up = resMap[tempI][j];
                }
                while(city_map[i][tempJ]==2){
                    --tempJ;
                    if(tempJ<0||city_map[i][tempJ]==1){
                        left = 0;
                        break;    
                    }
                    left = resMap[i][tempJ];
                }
                resMap[i][j] = (up+left)%MOD;    
            }
        }
    }
    answer = resMap[m-1][n-1]%MOD;
   
    
    return answer;
}