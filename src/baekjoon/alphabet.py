import sys

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

r, c = map(int, input().split())

arr = [list(input().rstrip()) for _ in range(r)]

MAX = 0
dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]


def bfs(arr):
    q = set()
    q.add((0, 0, 1, arr[0][0]))
    global MAX

    while q:
        curr = q.pop()

        # 꺼냈을 때 최댓값 갱신
        if curr[2] > MAX: MAX = curr[2]

        for k in range(4):
            nr = curr[0] + dy[k]
            nc = curr[1] + dx[k]
            if nr >= r or nc >= c or nr < 0 or nc < 0  or  arr[nr][nc] in curr[3]:
                continue

            q.add((nr, nc, curr[2] + 1, curr[3]+arr[nr][nc]))

bfs(arr)

print(MAX)
