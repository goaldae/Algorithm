#include <string>
#include <vector>
#include <iostream>
#include <unordered_map>
#include <algorithm>

using namespace std;

vector<string> split(string s, string token){
    int r =0, l = 0;
    vector<string> tokenized;
    while(r<s.size()){
        l = r;
        r = s.find(token, r);
        
        if(l==r){ //또 토큰을 찾았을 때
            r+=token.size();
            continue;
        }else if(r==-1){ // 찾는게 없을 때
            tokenized.push_back(s.substr(l, s.size()-l+1));
            break;
        }
        tokenized.push_back(s.substr(l, r-l));
    }
    return tokenized;
}

void bruteForce(int k, vector<string>& tokenized, unordered_map<string, vector<int>>& chart){
    if(k==4){ //마지막 점수에 다다름
        string temp="";
        for(int i= 0; i<4; i++){
            temp+=tokenized[i];
        }
        chart[temp].push_back(stoi(tokenized[4]));
    }else{
        string temp= tokenized[k];
        bruteForce(k+1, tokenized, chart);        
        tokenized[k]="-";
        bruteForce(k+1, tokenized, chart);        
        tokenized[k] = temp;
    }
}

void inputInfo(unordered_map<string, vector<int>>& chart, string& info){
    vector<string> tokenized;
    
    tokenized = split(info, " ");
    
    bruteForce(0, tokenized, chart);    
    
}

int findAnswer(string& query, unordered_map<string, vector<int>>& chart){
    int ret = 0;
    vector<string> tokenized1;
    vector<string> tokenized2;
    
    tokenized1 = split(query, " and ");
    tokenized2 = split(tokenized1[3], " ");
    tokenized1.pop_back();
    tokenized1.push_back(tokenized2[0]);
    
    int score = stoi(tokenized2[1]);
    
    string temp = "";

    for(int i = 0; i<4; i++){
        temp+=tokenized1[i];
    }
    
    ret = chart[temp].size()-(lower_bound(chart[temp].begin(), chart[temp].end(), score)-chart[temp].begin());
    
    return ret;
}

vector<int> solution(vector<string> info, vector<string> query) {
    vector<int> answer;
    unordered_map<string, vector<int>> chart;
    
    for(int i = 0; i<info.size(); i++){
        inputInfo(chart, info[i]);
    }
    
    for(auto it = chart.begin(); it!=chart.end(); it++){
        sort(it->second.begin(), it->second.end());
    }
    
    for(int i = 0; i<query.size(); i++){
        answer.push_back(findAnswer(query[i], chart));    
    }    
    
    return answer;
}