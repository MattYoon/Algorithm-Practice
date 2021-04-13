//https://codeforces.com/problemset/problem/706/C
import java.util.Scanner;

public class c706C {

    static Scanner scan = new Scanner(System.in);
    static int[] cost;
    static long[] flip, no_flip;

    static String[] strings;

    public static String flip(String string){
        char[] tmp = string.toCharArray();
        char[] flipped = new char[tmp.length];

        for (int i = 0; i < tmp.length; i++)
            flipped[i] = tmp[tmp.length - 1 - i];

        return new String(flipped);
    }

    public static long compLong(long a, long b){
        if(a >= 0 & b >= 0)
            return Math.min(a, b);
        if(a >= 0)
            return a;
        if(b >= 0)
            return b;
        return -1;
    }

    public static void main(String[] args){
        int n = scan.nextInt();
        cost = new int[n + 1]; flip = new long[n + 1]; no_flip = new long[n + 1];
        strings = new String[n + 1];

        for(int i = 1; i <= n; i++){
            cost[i] = scan.nextInt();
        }
        scan.nextLine();
        for(int i = 1; i <= n; i++){
            strings[i] = scan.nextLine();
        }

        flip[1] = cost[1];
        no_flip[1] = 0;
        for(int i = 2; i <=n; i ++){
            String prev = strings[i - 1];
            String prev_f = flip(prev);

            String cur = strings[i];
            String cur_f = flip(cur);

            //flip
            long cost1 = -1; long cost2 = -1;
            if(cur_f.compareTo(prev) >= 0 & no_flip[i - 1] != -1)
                cost1 = no_flip[i - 1] + cost[i];
            if(cur_f.compareTo(prev_f) >= 0 & flip[i - 1] != -1)
                cost2 = flip[i - 1] + cost[i];
            flip[i] = compLong(cost1, cost2);

            //no flip
            cost1 = -1; cost2 = -1;
            if(cur.compareTo(prev) >= 0 & no_flip[i - 1] != -1)
                cost1 = no_flip[i - 1];
            if(cur.compareTo(prev_f) >= 0 & flip[i - 1] != -1)
                cost2 = flip[i - 1];
            no_flip[i] = compLong(cost1, cost2);
        }
        System.out.println(compLong(flip[n], no_flip[n]));
    }
}
