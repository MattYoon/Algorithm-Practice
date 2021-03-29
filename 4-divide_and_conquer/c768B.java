//https://codeforces.com/contest/768/problem/B

import java.util.Scanner;
import java.lang.Math;
 
public class Main {
 
    private static Scanner scan = new Scanner(System.in);
 
    public static long calc_len(long num){
        long tmp = (long) (Math.log(num) / Math.log(2));
        return (long) Math.pow(2, tmp + 1) - 1;
    }
 
    public static long solve(long pos, long target, long num){
        if (num < 2)
            return num;
        if (target * 2 == pos + 1)
            return num % 2;
 
        pos /= 2;
        num /= 2;
 
        if (target > pos + 1)
            target -= (pos + 1);
        return solve(pos, target, num);
    }
 
    public static void main(String[] args) {
        long num, len, l, r;
        num = scan.nextLong();
        len = calc_len(num);
        l = scan.nextLong();
        r = scan.nextLong();
 
        int ans = 0;
        for (long i = l; i <= r; i++)
            ans += solve(len, i, num);
        System.out.println(ans);
    }
}