#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

bool isPossible(vector<int>& stones, int& k, int& mid){
    int cnt=0;
    for(int i = 0; i<stones.size(); i++){
        if(stones[i]-mid+1<=0){
            if(++cnt>=k) return false;
        }else{
            cnt = 0;
        }
    }
    return true;
}

int solution(vector<int> stones, int k) {
    int left = 1;
    int right = *max_element(stones.begin(), stones.end());
    int mid;
    int answer = 1;
    while(left<=right){
        
        mid = (left+right)/2;
        
        if(isPossible(stones, k, mid)){ //가능하면 높은 수로 해보기
            cout<<mid<<"가능"<<endl;
            answer = mid;
            left = mid+1;            
        }else{ //불가능하면 낮은 수로 해보기
            cout<<mid<<"불가능"<<endl;
            right = mid-1;
        }
    }
    return answer;
}