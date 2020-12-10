#include <string>
#include <vector>
#include <algorithm>
#include <limits.h>

using namespace std;

long long solution(int n, vector<int> times) {
    long long answer = LLONG_MAX;
    
    sort(times.begin(), times.end());
    long long l = 1;
    long long r = (long long)times[times.size()-1]*n;
    long long m;
    long long sum;
    while(l<=r){
        m = (l+r)/2;
        sum = 0;
        for(int i = 0; i<times.size(); i++){
            sum += m/times[i];
        }
        if(sum<n){ //시간안에 다 못끝내서 시간을 늘려봐야함
            l = m + 1;        
        }else{ //시간안에 다 끝낼수 있다 하지만 시간이 남을 수 있음 
            //따라서 최솟값을 업데이트하고 더 작은 값을 탐색해보기
            answer = min(answer, m);
            r = m - 1;
        }
    }
    
    return answer;
}