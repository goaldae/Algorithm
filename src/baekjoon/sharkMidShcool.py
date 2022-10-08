import copy

N, M = list(map(int, input().split()))

field = [list(map(int, input().split())) for _ in range(N)]

answer = 0

dx = (-1, 0, 1, 0)
dy = (0, -1, 0, 1)

def dfs(i, j):
    size = 1 # 블록 사이즈
    rainbow = 0 # 무지개 블록 개수
    cr = i # 기준블록 행
    cc = j # 기준블록 열

    q = []
    visited = [[False] * N for _ in range(N)]
    q.append([i, j])
    visited[i][j] = True

    while q:
        tempInfo = q.pop(0)

        for k in range(4):
            nx = dx[k] + tempInfo[1]
            ny = dy[k] + tempInfo[0]

            if nx >= N or nx<0 or ny >= N or ny < 0: continue
            if visited[ny][nx] or field[ny][nx] == -2 or field[ny][nx] == -1 or ((field[ny][nx]!=0)and(field[i][j] != field[ny][nx])): continue

            visited[ny][nx] = True
            q.append([ny, nx])

            if field[ny][nx] == 0:
                rainbow+=1
            else: # 무지개 블록이 아니면 기준블록 업데이트
                if ny<cr: # 새로운 블록 행이 작으면 무조건 기준 행열 업데이트
                    cr = ny
                    cc = nx
                elif ny==cr: #새로운 블록 행이랑 기존이랑 같으면
                    if nx < cc: # 새로운게 열이 더 작다면 열 업데이트
                        cc = nx
            size += 1

    return [size, rainbow, cr, cc, field[i][j]]

def removeBlock(r, c, color):
    q = []
    visited = [[False]*N for _ in range(N)]
    visited[r][c] = True
    q.append([r, c])

    while q:
        tempInfo = q.pop(0)

        for k in range(4):
            nx = dx[k] + tempInfo[1]
            ny = dy[k] + tempInfo[0]

            if nx >= N or nx < 0 or ny >= N or ny < 0: continue
            if visited[ny][nx] or field[ny][nx] == -2 or field[ny][nx] == -1 or (
                    (field[ny][nx] != 0) and (color != field[ny][nx])): continue

            visited[ny][nx] = True
            q.append([ny, nx])

        for i in range(N):
            for j in range(N):
                if visited[i][j]:
                    field[i][j] = -2

def gravity():
    for i in range(N-1, 0, -1):
        for j in range(N):
            if field[i][j] == -2:
                ni = i-1
                while ni>=0:
                    if field[ni][j] == -1:
                        break #블랙이면 그냥 끝내기
                    elif field[ni][j] == -2: # 빈칸이 아니면 더 올라가기
                        ni-= 1
                    else: # 색깔 블록이면
                        field[i][j] = field[ni][j]
                        field[ni][j] = -2
                        break

def location():
    tempField = [[0]*N for _ in range(N)]

    for i in range(N):
        for j in range(N):
            tempField[N-1-j][i] = field[i][j]

    return tempField


while True:
    #크기, 무지개 개수, 행, 열, 번호
    getMost = []
    for i in range(N): # 모든 필드를 돌면서 가장 우선순위 블록 본다
        for j in range(N):
            if field[i][j] == -1 or field[i][j] == -2 or field[i][j] == -0: # 블랙, 빈칸, 무지개이면 생략
               continue
            getMost.append(dfs(i, j))

    getMost.sort(key=lambda x:(-x[0],-x[1], -x[2],-x[3],x[4])) # 가장 우선순위 블록 앞으로

    if len(getMost) == 0 or getMost[0][0] == 1: break # 제일 큰 블록 크기가 1이면 게임 종료

    answer += getMost[0][0]**2 # 없애는 블록 사이즈^2 만큼 점수올리기

    removeBlock(getMost[0][2],getMost[0][3],getMost[0][4]) # 해당 열의 번호 번호 bfs로 삭제

    gravity() #중력작용

    field = location() #회전

    gravity()  # 중력작용

print(answer)