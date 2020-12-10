#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> answer;
    
    vector<int> list;
    int cnt = 0;
    for(int i = 0; i<operations.size(); i++){
        int number = stoi(operations[i].substr(1, operations[i].size()));
        if(operations[i][0]=='I'){            
            list.push_back(number);
            sort(list.begin(), list.end());
        }else{
           if(operations[i][2]=='-'&&!list.empty()){
               list.erase(list.begin());
           }else if(operations[i][2]=='1'&&!list.empty()){
               list.pop_back();
           }
        }
    }
    if(list.empty()){
        answer.push_back(0);
        answer.push_back(0);
    }else{
        answer.push_back(list[list.size()-1]);
        answer.push_back(list[0]);
    }
    
    return answer;
}