#include <string>
#include <vector>
#include <queue>

using namespace std;
int MAX = -1;
int answer = 0;
vector<int> graph[20001];


bool visited[20001];
void bfs(int k){
    queue<pair<int, int>> q;
    q.push({k, 0});
    visited[k] = true;
    
    pair<int, int> curr;
    while(!q.empty()){
        curr=q.front();q.pop();
        if(curr.second>MAX){
            MAX = curr.second;
            answer = 1;
        }else if(MAX==curr.second){
            answer++;
        }
        for(int k = 0; k<graph[curr.first].size(); k++){
            if(visited[graph[curr.first][k]]) continue;
            q.push({graph[curr.first][k], curr.second+1});
            visited[graph[curr.first][k]] = true;
        }
    }
}


int solution(int n, vector<vector<int>> edge) {
    
    for(int i = 0; i<edge.size(); i++){
        graph[edge[i][0]].push_back(edge[i][1]);
        graph[edge[i][1]].push_back(edge[i][0]);
    }
    
    bfs(1);
    
    return answer;
}