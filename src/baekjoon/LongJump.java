class Solution {
    static long dp[] = new long[2001];
    static public long getStep(int n){
        if(n==1) return 1;
        if(n==2) return 2;
        
        if(dp[n]!=0) return dp[n]%1234567;
        
        return dp[n] = (getStep(n-1) + getStep(n-2))%1234567;
    }
    
    public long solution(int n) {
        long answer = 0;
        
        answer = getStep(n);
        
        return answer;
    }
}