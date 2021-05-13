//https://codeforces.com/contest/52/problem/c

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class c486E {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        Solver_c486E solver = new Solver_c486E(n ,a);
        int[] ans = solver.solve();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(ans[i]);
        }

        System.out.print(sb.toString());
    }
}

class Solver_c486E {

    int n;
    int[] a, b, al, bl, ans;

    Solver_c486E(int n, int[] a){
        this.n = n;
        this.a = a;
        this.b = flip(a);
        al = new int[n + 1];
        bl = new int[n + 1];
        ans = new int[n + 1];
        for(int i = 1; i <=n; i++)
            ans[i] = 1;
    }

    int[] flip(int[] a){
        int[] b = new int[n + 1];
        for(int i = 1; i <= n; i++){
            b[n + 1 - i] = a[i] * (-1);
        }
        return b;
    }

    void lis(int[] arg, int[] argl) {
        ArrayList<Integer> last = new ArrayList();
        last.add(arg[1]);
        argl[1] = 1;
        for(int i = 2; i <= n; i++) {
            int pos;
            if (last.get(last.size() - 1) < arg[i]) {
                last.add(arg[i]);
                pos = last.size() - 1;
            }
            else {
                pos = Collections.binarySearch(last, arg[i]);
                if (pos < 0)
                    pos = pos * (-1) - 1;
                last.set(pos, arg[i]);
            }
            argl[i] = pos + 1;
        }
    }

    int[] solve(){
        lis(a, al);
        lis(b, bl);
        bl = flip(bl);

        for(int i = 1; i <= n; i++)
            bl[i] = bl[i] * (-1);

        int max_seq = 1;
        for(int i = 1; i <= n; i++)
            max_seq = Math.max(max_seq, al[i]);

        for(int i = 1; i <= n; i++){
            if(al[i] + bl[i] - 1 == max_seq)
                ans[i] = 3;
        }

        int left_max = 0;
        for(int i = 1; i <= n; i++){
            if(ans[i] != 1){
                if(a[i] <= left_max)
                    ans[i] = 2;
                left_max = Math.max(left_max, a[i]);
            }
        }

        int right_min = 1000000;
        for(int i = n; i >= 1; i--){
            if(ans[i] != 1){
                if(a[i] >= right_min)
                    ans[i] = 2;
                right_min = Math.min(right_min, a[i]);
            }
        }

        return ans;
    }
}

