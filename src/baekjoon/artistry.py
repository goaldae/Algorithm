N = int(input())

field = [list(map(int, input().split())) for _ in range(N)]

dx = (-1, 0, 1, 0)
dy = (0, -1, 0, 1)


def isOut(i, j):
    if i >= N or i < 0 or j < 0 or j >= N:
        return True
    return False


def bfs(i, j, cnt):
    visited = [[False] * N for _ in range(N)]
    q = []
    visited[i][j] = True
    q.append([i, j])
    size = 1
    gField[i][j] = cnt
    while q:
        ti, tj = q.pop(0)

        for k in range(4):
            ni = ti + dy[k]
            nj = tj + dx[k]
            if isOut(ni, nj) or visited[ni][nj]: continue  # 바운더리를 넘었거나 방문한 곳이면 컨티뉴
            if field[ni][nj] != field[i][j]: continue

            visited[ni][nj] = True
            q.append([ni, nj])
            gField[ni][nj] = cnt  # 그룹 표시 필드에 그룹넘버 입력
            size += 1

    return cnt + 1, size


def makeGroup():
    global cnt
    cnt = 0
    for i in range(N):
        for j in range(N):
            if gField[i][j] == -1:
                cnt, size = bfs(i, j, cnt)
                groupPos.append([i, j, size])  # n번 그룹의 좌상단 좌표와 개수 정보


def makeCombi():
    retCombi = []
    for i in range(cnt - 1):
        for j in range(i + 1, cnt):
            retCombi.append([i, j])
    return retCombi


def bfs2(gNum1, gNum2):
    i, j, size1, size2 = groupPos[gNum1][0], groupPos[gNum1][1], groupPos[gNum1][2], groupPos[gNum2][2]
    num1, num2 = field[i][j], field[groupPos[gNum2][0]][groupPos[gNum2][1]]
    cross = 0
    visited = [[False] * N for _ in range(N)]
    q = []
    visited[i][j] = True
    q.append([i, j])

    while q:
        ti, tj = q.pop(0)

        for k in range(4):
            ni = ti + dy[k]
            nj = tj + dx[k]
            if isOut(ni, nj) or visited[ni][nj]: continue  # 바운더리를 넘었거나 방문한 곳이면 컨티뉴
            if gField[ni][nj] == gNum1:
                visited[ni][nj] = True
                q.append([ni, nj])
            if gField[ni][nj] == gNum2:
                cross += 1
    sum = ((size1 + size2) * num1 * num2 * cross)
    # print(sum, size1, size2, num1, num2, cross)
    return sum


def getCombiScore(gNum1, gNum2):  # 조화점수를 구할 그룹 번호
    ret = bfs2(gNum1, gNum2)
    return ret


def location2(r, c):
    l = N // 2

    temp = [[0] * l for _ in range(l)]

    for i in range(r, r + l):
        for j in range(c, c + l):
            temp[i - r][j - c] = field[i][j]

    temp.reverse()  # 오른쪽으로 90도 회전
    temp = list(zip(*temp))

    for i in range(r, r + l):
        for j in range(c, c + l):
            field[i][j] = temp[i - r][j - c]


def location():
    center = N // 2

    temp = []
    for i in range(center):  # 윗방향 임시저장
        temp.append(field[i][center])

    for i in range(center):  # 오른쪽 -> 윗방향
        field[i][center] = field[center][N - 1 - i]

    for i in range(center + 1, N):  # 아래 -> 오른쪽방향
        field[center][i] = field[i][center]

    for i in range(center):  # 왼쪽 -> 아래방향
        field[center + 1 + i][center] = field[center][center - 1 - i]

    for i in range(center):
        field[center][i] = temp[i]

    location2(0, 0)
    location2(0, N // 2 + 1)
    location2(N // 2 + 1, 0)
    location2(N // 2 + 1, N // 2 + 1)


answer = 0

for _ in range(4):
    gField = [[-1] * N for _ in range(N)]
    combi = []  # 1,2/ 1,3/ 1,4/ 2,3/ 2,4/ .... 조합구하기
    groupPos = []  # 그룹별로 대표 좌표 위치

    makeGroup()  # 1. 그룹 만들기

    combi = makeCombi()  # 조합 배열 만들기

    for i in range(len(combi)):
        answer += getCombiScore(combi[i][0], combi[i][1])  # 2. 조합별로 조화 점수 추출하기

    location()
"""
location()
for i in range(N):
    print(field[i])
"""
print(answer)


