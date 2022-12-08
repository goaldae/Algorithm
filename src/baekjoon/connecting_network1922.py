import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input()) #정점 수
M = int(input()) #간선 수
parent = [_ for _ in range(N+1)] #0,1,2,3 ... 순차 배열 생성
edges = []

for i in range(M):
    edges.append(list(map(int, input().split())))

edges.sort(key=lambda x: (x[2]))

def unionParent(x, y):
    x = getParent(x)
    y = getParent(y)

    if x < y:
        parent[y] = x
    else:
        parent[x] = y

def getParent(x):
    if parent[x] == x: return x
    parent[x] = getParent(parent[x])
    return parent[x]


def isUnion(x, y):
    x = getParent(x)
    y = getParent(y)

    if x==y: return True
    return False

answer = 0
for i in range(M):
    if not isUnion(edges[i][0], edges[i][1]): #연결돼있지 않다면
        unionParent(edges[i][0], edges[i][1])
        answer += edges[i][2]

print(answer)