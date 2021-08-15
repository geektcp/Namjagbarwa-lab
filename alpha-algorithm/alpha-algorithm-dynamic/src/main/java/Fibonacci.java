import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tanghaiyang on 2019/3/21.
 */
/*
* 斐波那契数列的动态规划算法思想
* */
@Slf4j
public class Fibonacci {

    // 统计每个递归的重复计算次数
    private static ConcurrentHashMap<Integer,Integer> count = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int target = 6;
        log.info("fib0(10): ", fib0(target));
        log.info("count1: {}", count);count.clear();

        log.info("fibonacci(10): ", fibonacci(target));
        log.info("count2: {}", count);count.clear();

        log.info("fib1(10): ", fib1(target));
        log.info("count3: {}", count);count.clear();

        log.info("fib2(10): ", fib2(target));
        log.info("count4: {}", count);count.clear();
    }

    public static int fib0(int n) {
        log.info("fib0执行次数:{}", n);
        count(n);
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        return fib0(n - 1) + fib0(n - 2);
    }

    public static int fibonacci(int n) {
        if (n <= 0)
            return n;
        int[] Memo = new int[n + 1];
        for (int i = 0; i <= n; i++)
            Memo[i] = -1;
        return fib_inner(n, Memo);
    }

    private static int fib_inner(int n, int[] Memo) {
        log.info("fib_inner执行次数:{}", n);
        count(n);
        if (Memo[n] != -1)
            return Memo[n];
        //如果已经求出了fib（n）的值直接返回，否则将求出的值保存在Memo备忘录中。
        if (n <= 2)
            Memo[n] = 1;

        else Memo[n] = fib_inner(n - 1, Memo) + fib_inner(n - 2, Memo);

        return Memo[n];
    }

    public static int fib1(int n) {
        log.info("fib1:{}", n);
        count(n);

        if (n <= 0)
            return n;
        int[] Memo = new int[n + 1];
        Memo[0] = 0;
        Memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            Memo[i] = Memo[i - 1] + Memo[i - 2];
        }
        return Memo[n];
    }

    /*
    * 这招最巧妙
    * 根据斐波那契数列本身的语义是很难理解这个算法的思想
    * 但是根据递归的特征进行理解就比较容易
    * */
    public static int fib2(int n) {
        log.info("fib2:{}", n);
        count(n);
        if (n <= 1)
            return n;

        int Memo_i_2 = 0;
        int Memo_i_1 = 1;
        int Memo_i = 1;
        for (int i = 2; i <= n; i++) {
            Memo_i = Memo_i_2 + Memo_i_1;
            Memo_i_2 = Memo_i_1;
            Memo_i_1 = Memo_i;
        }
        return Memo_i;
    }


    private static void count(int n){
        int seq =  count.getOrDefault(n,0);
        seq ++;
        count.put(n, seq);
    }

}
