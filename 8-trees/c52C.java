//https://codeforces.com/problemset/problem/52/C

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class c52C {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        Solver_c52C solver = new Solver_c52C(br, n , a, m);
        System.out.print(solver.solve());
    }
}

class Node {
    public int left, right;
    public long min;
    public long inc;

    Node(int left, int right, int min, long inc){
        this.left = left; this.right = right;
        this.min = min; this.inc = inc;
    }
}

class Solver_c52C {

    BufferedReader br;
    StringTokenizer st;
    StringBuilder sb;
    int n, m;
    int[] a;
    Node[] tree;

    Solver_c52C(BufferedReader br, int n, int[] a, int m) {
        this.br = br;
        sb = new StringBuilder();
        this.n = n; this.m = m;
        this.a = a;
        tree = new Node[10 * n + 2];
    }

    int build_tree(int node, int left, int right){
        int min;

        if (left == right)
            min = a[left];
        else {
            int mid = (left + right) / 2;
            int minl = build_tree(node * 2, left, mid);
            int minr = build_tree(node * 2 + 1, mid + 1, right);
            min = Math.min(minl, minr);
        }

        tree[node] = new Node(left, right, min, 0);
        return min;
    }

    long query(int node, int lf, int rg) {
        if(lf <= tree[node].left && rg >= tree[node].right)
            return tree[node].min + tree[node].inc;
        if(lf > tree[node].right || rg < tree[node].left)
            return Long.MAX_VALUE;
        return Math.min(query(node * 2, lf, rg), query(node * 2 + 1, lf, rg)) + tree[node].inc;
    }

    void update(int node, int lf, int rg, int val) {
        if(lf <= tree[node].left && rg >= tree[node].right)
            tree[node].inc += val;
        else if(lf > tree[node].right || rg < tree[node].left)
            ;
        else{
            update(node * 2, lf, rg, val);
            update(node * 2 + 1, lf, rg, val);
            tree[node].min
                    = Math.min(tree[node * 2].min + tree[node * 2].inc, tree[node * 2 + 1].min + tree[node * 2+ 1].inc);
        }
    }

    String solve() throws IOException {

        build_tree(1, 1, n);

        for(int i = 1; i <= m; i++) {
            int lf, rg, v;
            st = new StringTokenizer(br.readLine());
            lf = Integer.parseInt(st.nextToken()) + 1;
            rg = Integer.parseInt(st.nextToken()) + 1;
            if(st.countTokens() == 0) {
                if (lf <= rg)
                    sb.append(query(1, lf, rg));
                else
                    sb.append(Math.min(query(1, 1, rg), query(1, lf, n)));
                sb.append('\n');
            }
            else{
                v = Integer.parseInt(st.nextToken());
                if (lf <= rg)
                    update(1, lf, rg, v);
                else{
                    update(1, 1, rg, v);
                    update(1, lf, n, v);
                }
            }
        }
        return sb.toString();
    }
}
