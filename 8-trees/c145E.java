//https://codeforces.com/contest/145/problem/E

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class c145E {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String s = " ".concat(br.readLine());

        Solver_c145E solver = new Solver_c145E(br, n, m, s);
        System.out.print(solver.solve().toString());
    }
}

class Solver_c145E {

    class Node {

        int left, right;
        int fours, sevens, lis, lds;

        Node() {
            fours = 0; sevens = 0; lis = 1; lds = 1;
        }

        void swap() {
            int tmp = fours;
            fours = sevens; sevens = tmp;
            tmp = lis;
            lis = lds; lds = tmp;
        }
    }

    BufferedReader br;

    int n, m;
    String s;
    Node[] tree;
    boolean[] lazy_swap;

    Solver_c145E(BufferedReader br, int n, int m, String s) {
        this.br = br; this.n = n; this.m = m; this.s = s;
        tree = new Node[10 * n];
        lazy_swap = new boolean[10 * n];
    }

    void build_tree(int node, int left, int right) {

        Node new_node = new Node();
        new_node.left = left; new_node.right = right;

        if (left != right) {
            int left_child = node * 2; int right_child = node * 2 + 1;
            int mid = (left + right) / 2;

            build_tree(left_child, left, mid);
            build_tree(right_child, mid + 1, right);

            Node left_node = tree[left_child]; Node right_node = tree[right_child];

            new_node.fours = left_node.fours + right_node.fours;
            new_node.sevens = left_node.sevens + right_node.sevens;

            int case1 = left_node.lis + right_node.sevens;
            int case2 = left_node.fours + right_node.lis;
            new_node.lis = Math.max(case1, case2);

            case1 = left_node.lds + right_node.fours;
            case2 = left_node.sevens + right_node.lds;
            new_node.lds = Math.max(case1, case2);
        }

        else {
            if (s.charAt(left)== '4')
                new_node.fours = 1;
            else
                new_node.sevens = 1;
        }
        tree[node] = new_node;
    }


    void update(int node, int left, int right) {

        Node this_node = tree[node];

        if(lazy_swap[node]) {
            this_node.swap();
            lazy_swap[node * 2] = !lazy_swap[node * 2];
            lazy_swap[node * 2 + 1] = !lazy_swap[node * 2 + 1];
            lazy_swap[node] = false;
        }

        if (left <= this_node.left && right >= this_node.right) {
            this_node.swap();
            lazy_swap[node * 2] = !lazy_swap[node * 2];
            lazy_swap[node * 2 + 1] = !lazy_swap[node * 2 + 1];
        }
        
        else if(left > this_node.right || right < this_node.left) ;
        else {
            int left_child = node * 2; int right_child = node * 2 + 1;

            update(left_child, left, right);
            update(right_child, left, right);

            Node left_node = tree[left_child]; Node right_node = tree[right_child];

            this_node.fours = left_node.fours + right_node.fours;
            this_node.sevens = left_node.sevens + right_node.sevens;

            int case1 = left_node.lis + right_node.sevens;
            int case2 = left_node.fours + right_node.lis;
            this_node.lis = Math.max(case1, case2);

            case1 = left_node.lds + right_node.fours;
            case2 = left_node.sevens + right_node.lds;
            this_node.lds = Math.max(case1, case2);
        }
    }


    StringBuilder solve() throws IOException {
        build_tree(1, 1, n);
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= m; i++) {
            String new_line = br.readLine();

            if (new_line.equals("count")) {
                sb.append(tree[1].lis);
                sb.append("\n");
            }
            else {
                int pos = 7;
                int end = new_line.indexOf(' ', pos);
                int l = Integer.parseInt(new_line.substring(pos, end));
                int r = Integer.parseInt(new_line.substring(end + 1));
               update(1, l, r);
            }
        }
        return sb;
    }
}
