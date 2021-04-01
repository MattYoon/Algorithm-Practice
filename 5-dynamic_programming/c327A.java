//https://codeforces.com/problemset/problem/327/A

import java.util.Scanner;
import java.lang.Math;

public class c327A {

    public static Scanner scan = new Scanner(System.in);
    public static int n;
    public static int[] as = new int[101];
    public static int[][] ans = new int[101][2];

    public static void read(){
        n = scan.nextInt();
        for(int i = 1; i <= n; i++){
            as[i] = scan.nextInt();
        }
    }

    public static int reverse(int k){
        return 1 - k;
    }

    public static void check(int i){
        ans[i][0] = Math.max(ans[i - 1][0], ans[i - 1][1]) + as[i];
        int sum = 0;
        for(int j = 1; j < i; j++)
            sum += as[j];
        ans[i][1] = Math.max(ans[i - 1][1] + reverse(as[i]), sum + reverse(as[i]));
    }


    public static void main(String[] args){
        read();

        boolean has0 = false;
        for(int i = 1; i <= n; i++){
            if(as[i] == 0) {
                has0 = true;
                break;
            }
        }

        if(!has0)
            System.out.println(n - 1);
        else {
            for (int i = 1; i <= n; i++) {
                check(i);
            }
            System.out.println(Math.max(ans[n][0], ans[n][1]));
        }
    }
}
