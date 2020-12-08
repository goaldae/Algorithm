#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

int parent[101];

struct cmp{
    bool operator()(vector<int> a, vector<int> b){
        return a[2]>b[2];
    }
};

int getParent(int a){
    if(a==parent[a]) return a;
    return parent[a] = getParent(parent[a]);
}

void unionParent(int a, int b){
    a = getParent(a);
    b = getParent(b);
    if(a<b){
        parent[b] = a;
    }else{
        parent[a] = b;
    }
}

int isUnion(int a, int b){
    a = getParent(a);
    b = getParent(b);
    return a==b?1:0;
}

int solution(int n, vector<vector<int>> costs) {
    int answer = 0;
    
    priority_queue<vector<int>, vector<vector<int>>, cmp> pq;
    
    for(int i = 0; i<costs.size(); i++){
        pq.push({costs[i][0],costs[i][1],costs[i][2]});
    }
    for(int i = 0; i < n; i++){
        parent[i] = i;
    }
    
    while(!pq.empty()){
        vector<int> curEdge = pq.top();
        pq.pop();
        if(!isUnion(curEdge[0], curEdge[1])){ //연결돼있지 않으면
            unionParent(curEdge[0], curEdge[1]);
            answer+=curEdge[2];
        } 
    }
    
    return answer;
}