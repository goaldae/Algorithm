import sys, heapq

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

N, M = map(int, input().split())

graph = [[] for _ in range(N + 1)]

for i in range(M):
    n1, n2, c = map(int, input().split())
    graph[n1].append([n2, c])
    graph[n2].append([n1, c])


def dijkstra_fox(start):
    INF = 100000000
    d = [INF] * (N + 1)
    pq = []
    d[start] = 0
    heapq.heappush(pq, [0, start])  # 꺼냈는데 True면 빠르게 가라

    while pq:
        curr = pq[0][1]
        distance = pq[0][0]

        heapq.heappop(pq)

        if distance < d[curr]: continue

        for item in graph[curr]:
            next = item[0]
            nextDistance = item[1]

            newDist = nextDistance + distance

            if newDist < d[next]:
                d[next] = newDist
                heapq.heappush(pq, [newDist, next])

    return d


def dijkstra_wolf(start):
    INF = 100000000
    d = [[INF] * (N + 1) for _ in range(2)]  # d를 2차원으로 느리게 도착했을때, 빠르게 도착했을때로 나눔
    pq = []
    d[1][start] = 0  # 시작점은 느리게 도착했음
    heapq.heappush(pq, [0, False, start])  # 빠르게 도착하지 않았음.. 다음에 넣을땐 빠르게 넣어야겠지?

    while pq:
        distance, isFast, curr = heapq.heappop(pq)

        if (isFast and d[0][curr] < distance) \
                or (not isFast and d[1][curr] < distance):
            continue

        for next, nextDistance in graph[curr]:
            if isFast:  # 빠르게 도착했어?
                nextDistance *= 2  # 그럼 거리 두배
            else:
                nextDistance /= 2

            newDist = nextDistance + distance

            if isFast and d[1][next] > newDist:
                d[1][next] = newDist
                heapq.heappush(pq, [newDist, False, next])
            elif not isFast and d[0][next] > newDist:
                d[0][next] = newDist
                heapq.heappush(pq, [newDist, True, next])
    return d


wolf = dijkstra_wolf(1)
rwolf = []
for item in zip(wolf[1], wolf[0]):
    rwolf.append(min(item))

fox = dijkstra_fox(1)
answer = 0
for w, f in zip(rwolf, fox):
    if f<w: answer+=1
#print(rwolf)
#print(fox)
print(answer)
