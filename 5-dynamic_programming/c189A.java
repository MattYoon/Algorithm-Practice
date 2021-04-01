//https://codeforces.com/problemset/problem/189/A

import java.util.Scanner;
import java.lang.Math;

public class c189A {

    public static int pieces[] = new int[4001];
    public static int n, a, b, c;
    public static Scanner scan = new Scanner(System.in);

    public static void read(){
        n = scan.nextInt();
        a = scan.nextInt();
        b = scan.nextInt();
        c = scan.nextInt();
    }

    public static int check(int i, int k){
        if(i < k)
            return -1;
        if(pieces[i - k] == -1)
            return -1;
        else return pieces[i - k] + 1;
    }

    public static void main(String args[]){
        read();
        for(int i = 1; i <= n; i++){
            pieces[i] = Math.max(Math.max(check(i, a), check(i, b)), check(i, c));
        }
        System.out.println(pieces[n]);
    }
}
