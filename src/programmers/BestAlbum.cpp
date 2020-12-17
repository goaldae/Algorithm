#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

bool cmp(pair<string, pair<int, vector<pair<int, int>>>> a, pair<string, pair<int, vector<pair<int, int>>>> b){
    return a.second.first>b.second.first;
}

bool cmp2(pair<int, int> a, pair<int, int>b){
    if(a.second==b.second) return a.first<b.first;
    return a.second>b.second;
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    
    unordered_map<string, pair<int, vector<pair<int, int>>>> map;
    
    for(int i = 0; i<genres.size(); i++){
        map[genres[i]].first += plays[i];
        map[genres[i]].second.push_back({i, plays[i]});
    }
    
    vector<pair<string, pair<int, vector<pair<int, int>>>>> v(map.begin(), map.end());
    
    sort(v.begin(), v.end(), cmp);
    
    for(int i = 0; i<v.size(); i++){
        sort(v[i].second.second.begin(), v[i].second.second.end(), cmp2);
        for(int j = 0; j<v[i].second.second.size()&&j<2; j++){
            answer.push_back(v[i].second.second[j].first);
        }
    }
    
    return answer;
}