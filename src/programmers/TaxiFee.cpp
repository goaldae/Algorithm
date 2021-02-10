#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    int answer = 0;
    
    vector<vector<int>> map(n+1, vector<int>(n+1, 20000000));
    
    for(int i = 1; i<=n; i++){
        map[i][i] = 0;
    }
    
    for(int i = 0; i<fares.size(); i++){
        map[fares[i][0]][fares[i][1]] = fares[i][2];
        map[fares[i][1]][fares[i][0]] = fares[i][2];
    }
    
    for(int k = 1; k<=n; k++){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                map[i][j] = min(map[i][k]+map[k][j], map[i][j]);
            }
        }
    }
    
    
    answer = 1e9;
    for(int i = 1; i<=n; i++){
        answer= min(map[i][s]+map[i][a]+map[i][b],answer);
    }
    
    
    return answer;
}