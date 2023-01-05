import sys

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

N = int(input())


def get_prime(num):
    #arr1 = [1] * (num+1) #일단 모두 소수야 ->틀린 방법
    arr1 = [False, False] + [True] *(N-1)
    ret = []
    
    for i in range(2, num+1):
        if arr1[i]: ret.append(i)

        for j in range(i*2, num+1, i): #해당 수의 *2부터 시작해서 쭉쭉 소거
            if not arr1[j] : continue
            arr1[j] = False
            
    return ret



prime = get_prime(N)

left, right = 0, 0

answer = 0

while right<=len(prime): #마지막 인덱스+1까지 체크
    tempSum = sum(prime[left:right])
    if tempSum == N:
        answer+=1
        right+=1
    elif tempSum<N:
        right+=1
    else:
        left+=1

print(answer)