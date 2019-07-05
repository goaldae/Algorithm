package training.DP;


public class Fibonacci {
    int[] d = new int[10000];

    int fibo(int x){
        if(x == 1) return 1;
        if(x == 2) return 1;
        if(d[x] != 0) return d[x];
        return d[x] = fibo(x-1) + fibo(x-2);
    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        System.out.print(f.fibo(50));
    }
}
