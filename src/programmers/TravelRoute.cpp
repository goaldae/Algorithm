#include <string>
#include <vector>
#include <algorithm>

using namespace std;
int num;
vector<string> answer;

bool dfs(string from, vector<bool>& visited, int cnt, vector<vector<string>>& tickets){
    if(cnt==num) return true;
    
    for(int i = 0; i<num; i++){
        if(tickets[i][0]==from && !visited[i]){
            answer.push_back(tickets[i][1]);
            visited[i] = true;
            if(dfs(tickets[i][1], visited, cnt+1, tickets)){
                return true;
            }else{
                answer.pop_back();
                visited[i] = false;
            }
        }
    }
    return false;
    
}

vector<string> solution(vector<vector<string>> tickets) {
    answer.push_back("ICN");
    sort(tickets.begin(), tickets.end());
    vector<bool> visited(tickets.size(), false);
    num = tickets.size();
    
    dfs("ICN", visited, 0, tickets);
    
    return answer;
}