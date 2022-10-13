N, M = list(map(int, input().split()))

numField = [[-1] * N for _ in range(N)]  # 번호 표시 위한 필드
field = [list(map(int, input().split())) for _ in range(N)]  # 실제 로직이 일일어나는 필드

magic = [list(map(int, input().split())) for _ in range(M)]  # 마법 담기

chgDirArr = [0] * (N ** 2)
dirArr = []

dx = (0, 0, 0, -1, 1)  # 마법 방향에 쓰이는 방향
dy = (0, -1, 1, 0, 0)

cx = (-1, 0, 1, 0)  # 좌표에서 이동할때 쓰이는 방향
cy = (0, 1, 0, -1)

chgDirArr[1] = 1
answer = 0

for i in range(2, N ** 2):
    if i % 2 == 0:
        chgDirArr[i] = chgDirArr[i - 1] + i // 2
    else:
        chgDirArr[i] = chgDirArr[i - 1] + (i + 1) // 2

pos = 0
i = 0
while True:
    if chgDirArr[i] > (N ** 2): break
    while chgDirArr[i + 1] > pos:
        dirArr.append(i % 4)
        pos += 1
    i += 1

r, c = N // 2, N // 2
for i in range(N ** 2):
    numField[r][c] = i
    r += cy[dirArr[i]]
    c += cx[dirArr[i]]


def magicFunc(d, s):  # 방향, 거리
    r, c = N // 2, N // 2

    for i in range(s + 1):
        field[r][c] = 0
        r += dy[d]
        c += dx[d]


def move():
    r = N // 2
    c = N // 2 - 1
    for i in range(1, N ** 2):
        if field[r][c] == 0:
            nr, nc = r, c
            for j in range(i, N ** 2):
                nr += cy[dirArr[j]]
                nc += cx[dirArr[j]]
                if field[nr][nc] != 0:
                    field[r][c] = field[nr][nc]
                    field[nr][nc] = 0
                    break
        r += cy[dirArr[i]]
        c += cx[dirArr[i]]




answer = 0
def bomb():
    global answer
    flag = False
    r, c = N // 2, N // 2

    for goaldae in range(0, N ** 2 - 3):
        # print(i, r, c)
        if field[r][c] != 0:
            nr, nc = r, c
            j = goaldae
            size = 0
            while True:  # 해당 점부터 이후에 다른지 같은지 체크
                if field[r][c] == field[nr][nc]:  # 같으면 일단 사이즈 업
                    size += 1
                    nr += cy[dirArr[j]]
                    nc += cx[dirArr[j]]
                    j += 1
                else:
                    if size >= 4:  # 4이상이면 끝난 시점에서 4 이상이면
                        flag = True
                        mr, mc = r, c
                        answer += (field[r][c] * size)
                        for k in range(goaldae, goaldae + size, 1):
                            field[mr][mc] = 0
                            mr += cy[dirArr[k]]
                            mc += cx[dirArr[k]]

                    break
        r += cy[dirArr[goaldae]]
        c += cx[dirArr[goaldae]]

    return flag


def group():
    r, c = N // 2, N // 2 -1
    visited = [[False]*N for _ in range(N)]
    newField = [[0]*N for _ in range(N)]

    nr, nc = r, c
    nidx = 1
    for idx in range(1, N ** 2):

        if not visited[r][c] :
            size = 0
            number = field[r][c]
            rr, cc = r, c
            if nidx>=N**2: break #범위를 넘어서면 종료
            while True:

                if number == field[rr][cc] and field[rr][cc]!=0: #같으면 사이즈 늘리기
                    visited[rr][cc] = True
                    rr += cy[dirArr[idx+size]]
                    cc += cx[dirArr[idx+size]]
                    size += 1

                else:

                    if nidx<N**2:
                        newField[nr][nc] = size
                    nr += cy[dirArr[nidx]]
                    nc += cx[dirArr[nidx]]
                    nidx+=1
                    if nidx<N**2:
                        newField[nr][nc] = number
                    nr += cy[dirArr[nidx]]
                    nc += cx[dirArr[nidx]]
                    nidx += 1

                    break
        r += cy[dirArr[idx]]
        c += cx[dirArr[idx]]

    return newField


for i in range(M):

    magicFunc(magic[i][0], magic[i][1])
    move()

    while bomb():
        move()

    field = group()

print(answer)
