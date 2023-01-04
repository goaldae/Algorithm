import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N, S = map(int, input().split())

arr = list(map(int, input().split()))

left = 0
right = 0
tempSum = arr[left]
length = 1
answer = 100000
while True:
    if tempSum >= S:
        if length<answer: answer = length
        if left==right:
            break
        tempSum -= arr[left]
        left += 1
        length -= 1
    else:
        if right == N-1: #더이상 갈 곳이 없을 때
            break
        right+=1
        tempSum += arr[right]
        length += 1


if answer == 100000:
    print(0)
else:
    print(answer)

