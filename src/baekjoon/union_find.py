# union-find 합집합 찾는 문제
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N, M = list(map(int, input().split()))

# 합치기
# 서로 합쳐있는지
# 루트 노드 구하기

graph = []
for i in range(N + 1):
    graph.append(i)

# 루트 노드 구하기
def get_parent(a):
    if a == graph[a]: # 자기 자신이 루트 노드이면 a 반환
        return a
    p = get_parent(graph[a]) # a의 루트 노드 찾기
    graph[a] = p # 부모 테이블 갱신
    return graph[a] # a의 루트 노드 반환


# 서로 합쳐져 있는지 구하기
def is_same_parent(first, second):
    if get_parent(first) == get_parent(second):
        return True
    return False


def union_parent(first, second):
    firstParent = get_parent(first)
    secondParent = get_parent(second)

    if firstParent == secondParent:
        return
    elif firstParent>secondParent:
        graph[firstParent] = secondParent
    else:
        graph[secondParent] = firstParent

for k in range(M):
    m, first, second = list(map(int, input().split()))
    if m == 0:
        union_parent(first, second)
    else:
        if get_parent(first) == get_parent(second):
            print("YES")
        else:
            print("NO")
