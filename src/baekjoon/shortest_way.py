import sys
import heapq

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

INF = 100000000

V, E = list(map(int, input().split()))
start = int(input())

graph = [[] for _ in range(V + 1)]  # 그래프 정보 배열
d = [INF] * (V + 1)  # 최단거리 저장할 배열

for _ in range(E):
    n1, n2, cost = list(map(int, input().split()))
    graph[n1].append((cost, n2))


def dijkstra(s):
    d[s] = 0
    pq = []
    heapq.heappush(pq, (0, start))  # heapq는 기본이 최소힙

    while pq:
        curr = pq[0][1]  # 꺼낼 노드
        distance = pq[0][0]

        heapq.heappop(pq)

        if d[curr] < distance:  # 그 사이에 더 작은 것으로 업데이트 되었을 때 볼 필요 없음
            continue

        for item in graph[curr]:

            nextDistance = item[0]
            next = item[1]

            if distance + nextDistance < d[next]:
                d[next] = distance + nextDistance
                heapq.heappush(pq, (d[next], next))

    return d


answer = dijkstra(start)
for i in range(1, V + 1):
    if answer[i] == INF:
        print('INF')
    else:
        print(answer[i])




