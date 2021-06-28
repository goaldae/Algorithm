#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>
#include <iostream>

using namespace std;

int cnt = 0;

void check(vector<string> &user_id, vector<string> &banned_id, vector<bool> &visited, unordered_map<string, bool>& chk,int k, string chkNumber){
    if(k==banned_id.size()){ //모든 불량 사용자를 체크함
        sort(chkNumber.begin(), chkNumber.end());
        if(!chk[chkNumber]){
            cout<<chkNumber<<endl;
            chk[chkNumber] = true;
            cnt++;    
        }
        
    }else{
        for(int i=0 ;i<user_id.size(); i++){ //모든 응모자 아이디를 체크해보기
            if(banned_id[k].size()!=user_id[i].size()||visited[i]) {
                continue;
            }
            
            bool flag = true;
            for(int j = 0; j<user_id[i].size(); j++){ //글자 하나씩 대조해보기
                if(banned_id[k][j]=='*'||(banned_id[k][j]==user_id[i][j])){}
                else{
                    flag = false;
                    break;
                }
            }
            
            if(flag){
                visited[i] = true;
                check(user_id, banned_id,visited,chk,k+1, chkNumber+to_string(i));
                visited[i] = false;
            }
        }
    }
}

int solution(vector<string> user_id, vector<string> banned_id) {
    vector<bool> visited(user_id.size(), false);
    unordered_map<string, bool> chk;
    check(user_id, banned_id, visited, chk,0, "");
    
    return cnt;
}