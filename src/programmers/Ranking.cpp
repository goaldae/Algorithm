#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(int n, vector<vector<int>> results) {
    int answer = 0;
    vector<vector<int>> arr(n+1, vector<int>(n+1, 0));
    
    for(int i = 0; i<results.size(); i++){        
        arr[results[i][0]][results[i][1]] = 1;
        arr[results[i][1]][results[i][0]] = -1;
    }
    
    for(int k = 1; k<=n; k++){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(arr[i][k]==1&&arr[k][j]==1) arr[i][j] = 1;
                if(arr[i][k]==-1&&arr[k][j]==-1) arr[i][j] = -1;
            }
        }
    }
    
    for(int i = 1; i<=n; i++){
        int f = 0;
        for(int j = 1; j<=n; j++){
            if(arr[i][j]==0&&i!=j){
                f=1;  
                continue;
            } 
        }
        if(f==0)answer++;        
    }
    
    return answer;
}