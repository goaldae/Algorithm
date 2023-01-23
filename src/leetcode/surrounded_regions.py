import sys, copy

sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = 0
M = 0

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

class Solution:
    def is_edge(self, r, c):
        global N, M
        if r == 0 or r==N-1 or c==0 or c==M-1:
            return True
        return False

    def bfs(self, r, c):
        visited = [[False]*M for _ in range(N)]
        q = []
        visited[r][c] = True
        q.append((r, c))
        self.retBoard[r][c] = 'O'

        while q:
            cr, cc = q.pop(0)
            
            for k in range(4):
                nr = cr + dy[k]
                nc = cc + dx[k]

                if  nr >N-1 or nc > M-1 or nr<0 or nc<0 or self.board[nr][nc] == 'X' or visited[nr][nc]:
                    continue

                self.retBoard[nr][nc] = 'O'
                visited[nr][nc] = True
                q.append((nr, nc))

    def solve(self, board: List[List[str]]) -> None:
        global N, M 
        N = len(board)
        M = len(board[0])
        self.retBoard = [['X']*M for _ in range(N)]
        self.board = board

        for i in range(N):
            for j in range(M):
                if self.is_edge(i, j) and self.board[i][j] == 'O':
                    self.bfs(i, j)
        for i in range(N):
            for j in range(M):
                if self.retBoard[i][j] == 'X':
                    board[i][j] = "X"