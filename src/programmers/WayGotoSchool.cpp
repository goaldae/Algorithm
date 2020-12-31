#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(int m, int n, vector<vector<int>> puddles) {
    int answer = 0;
    vector<vector<int> > arr(n, vector<int>(m, -1));
    for(int i = 0; i<n; i++){
        arr[i][m-1] = 1;        
    }
    for(int j = 0; j<m; j++){
        arr[n-1][j] = 1;        
    }
    for(int i = 0; i<puddles.size(); i++){
        arr[puddles[i][1]-1][puddles[i][0]-1] = 0;
        if(puddles[i][1]==n){
            for(int j = 0; j<puddles[i][0]; j++){
                arr[n-1][j]= 0;
            }
        }else if(puddles[i][0]==m){
            for(int j = 0; j<puddles[i][1]; j++){
                arr[j][m-1]= 0;
            }
        }
    }
    
    for(int i = n-2; i>=0; i--){
        for(int j = m-2; j>=0; j--){
            if(arr[i][j]==0) continue;
            arr[i][j] = (arr[i+1][j]+arr[i][j+1])%1000000007;
        }
    }   
 
    answer = arr[0][0];
    
    return answer;
}