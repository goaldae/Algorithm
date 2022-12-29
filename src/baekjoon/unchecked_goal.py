import heapq
import sys

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

T = int(input())


def dijkstra(s):
    INF = int(1e9)
    pq = []
    heapq.heappush(pq, (0, s))
    d = [INF for _ in range(N + 1)]
    d[s] = 0
    while pq:
        curr_cost, curr = heapq.heappop(pq)

        if d[curr] < curr_cost:
            continue

        for next, next_cost in graph[curr]:
            wei = curr_cost + next_cost
            if wei < d[next]:
                d[next] = next_cost + curr_cost
                heapq.heappush(pq, (d[next], next))

    return d


for t in range(T):
    N, E, CNUM = map(int, input().split())
    start, must1, must2 = map(int, input().split())

    graph = [[] for _ in range(N + 1)]
    candi = []
    for i in range(E):
        n1, n2, dist = map(int, input().split())
        graph[n1].append((n2, dist))
        graph[n2].append((n1, dist))

    for i in range(CNUM):
        candi.append(int(input()))

    sd = dijkstra(start)
    md1 = dijkstra(must1)
    md2 = dijkstra(must2)
    answer = []

    for item in candi:
        if sd[item] == sd[must1] + md1[must2] + md2[item] or sd[item] == sd[must2] + md2[must1] + md1[item]:
            answer.append(item)
    answer.sort()
    for ee in answer:
        print(ee, end=' ')
    print()
