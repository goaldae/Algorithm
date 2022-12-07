import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

class Solution:
    def longestSubstring(self, s: str, k: int) -> int:
        #같은 문자가 k번 이상 반복되는 가장 긴 부분 문자열
        if len(s)==0: return 0

        index = 0
        while True:
            if s.count(s[index]) < k: #조건에 해당하지 않는 부분: 끊어지는 기준
                break
            if index == len(s)-1: #끝까지 조건을 통과했다면
                return len(s)
            index += 1

        return max(self.longestSubstring(s[:index], k), self.longestSubstring(s[index+1:], k))
