//https://codeforces.com/problemset/problem/455/A

import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;

public class c455A {

    public static final int MAX = 100001;
    public static Scanner scan = new Scanner(System.in);
    public static int n;
    public static int[] as = new int[MAX];
    public static int[] count = new int[MAX];
    public static long[][] sum = new long[MAX][2];

    public static void read(){
        n = scan.nextInt();
        for(int i = 1; i <= n; i++)
            as[i] = scan.nextInt();
        Arrays.sort(as);
    }

    public static int count(){
        int cur = as[1];
        int cur_count = 1;
        for(int i = 2; i < MAX; i++){
            if(as[i] == cur)
                cur_count++;
            else{
                count[cur] = cur_count;
                cur = as[i];
                cur_count = 1;
            }
        }
        count[cur] = cur_count;
        return cur;
    }

    public static void main(String[] args){
        read();
        int m = count();
        for(int i = 1; i <= m; i++){
            sum[i][0] = Math.max(sum[i - 1][0], sum[i - 1][1]);
            sum[i][1] = sum[i - 1][0] + (long) i * count[i];
        }
        System.out.println(Math.max(sum[m][0], sum[m][1]));
    }
}
