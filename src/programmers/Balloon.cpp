#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(pair<int, int> a, pair<int, int> b){
    return a.second<b.second;
}

int solution(vector<int> a) {
    int answer = 0;
    if (a.size()<=2) return a.size(); //개수가 2이하면 다 살릴수있음
    answer = 2; //숫자가 3개 이상이면 최소한 2개는 살림
    pair<int, int> temp;
    vector<pair<int, int> > list;
    
    for(int i = 0; i<a.size(); i++){
        temp.first = i;
        temp.second = a[i];
        list.push_back(temp);        
    }
    sort(list.begin(), list.end(), cmp);

    int l, r;
    if(list[0].first<list[1].first){
        l = list[0].first;
        r = list[1].first;
    }else{
        l = list[1].first;
        r = list[0].first;
    }
    //제일 작은 숫자 인덱스 l
    //두번째 작은 숫자 인덱스 r
    
    for(int i = 2; i<list.size(); i++){
        int m = list[i].first;
        if(m<l){ //m<l<r 살림
            answer++;
            l = m;
        }else if(m>r){ //m<l<r 살림
            answer++;
            r = m;
        }
    }
    
    return answer;
}