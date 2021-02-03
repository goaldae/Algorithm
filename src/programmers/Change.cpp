#include <string>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int answer = 0;

int solution(int n, vector<int> money) {
    vector<int> ways(n,0); // 0원을 만드는 방법의 수 : 1 (아무동전도 사용하지 않는 것) 
    
    vector<int> d(n+1);
    d[0] = 1;
    
    for(int coin : money){
        for(int i = coin; i<=n; i++){
            d[i] += d[i-coin];
        }
    }
    
    return d[n];
}