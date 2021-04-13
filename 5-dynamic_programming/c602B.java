//https://codeforces.com/problemset/problem/602/B
import java.util.Scanner;

public class c602B {
    static Scanner scan = new Scanner(System.in);
    static int[] data;

    public static void main(String[] args){
        int n = scan.nextInt();
        data = new int[n + 1];

        for(int i = 1; i <= n; i++){
            data[i] = scan.nextInt();
        }

        int lp = 1; int rp = 2;
        int flag_diff = 0; int flag_pos = 1;
        int ans = 2;
        while(rp <= n){
            int diff = data[rp] - data[rp-1];
            if (diff != 0){
                if(flag_diff == diff){
                    ans = Math.max(ans, rp - lp);
                    lp = flag_pos;
                }
                flag_diff = diff;
                flag_pos = rp;
            }
            rp++;
        }
        ans = Math.max(ans, rp - lp);

        if(lp == 1)
            System.out.println(n);
        else
            System.out.println(ans);
    }
}
