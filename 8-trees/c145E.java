import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class c145E {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String s = " ".concat(br.readLine());

        Solver_c145E solver = new Solver_c145E(br, n, m, s);
        System.out.print(solver.solve());
    }
}

class Solver_c145E {

    class Node {

        int left, right;
        int fours, sevens, lis, lds;

        Node() {
            fours = 0; sevens = 0; lis = 1; lds = 1;
        }

        Node swap() {
            Node swapped = new Node();
            swapped.fours = sevens; swapped.sevens = fours;
            swapped.lis = lds; swapped.lds = lis;
            return swapped;
        }
    }

    BufferedReader br;
    StringTokenizer st;

    int n, m;
    String s;
    Node[] tree;

    Solver_c145E(BufferedReader br, int n, int m, String s) {
        this.br = br; this.n = n; this.m = m; this.s = s;
        tree = new Node[10 * n];
    }

    void build_tree(int node, int left, int right) {

        Node new_node = new Node();
        new_node.left = left; new_node.right = right;

        if (left != right) {
            int left_child = node * 2; int right_child = node * 2 + 1;
            int mid = (left + right) / 2;

            build_tree(left_child, left, mid);
            build_tree(right_child, mid + 1, right);

            new_node.fours = tree[left_child].fours + tree[right_child].fours;
            new_node.sevens = tree[left_child].sevens + tree[right_child].sevens;

            int case1 = tree[left_child].lis + tree[right_child].sevens;
            int case2 = tree[left_child].fours + tree[right_child].lis;
            int case3 = tree[left_child].fours + tree[right_child].sevens;

            new_node.lis = Math.max(Math.max(case1, case2), case3);

            case1 = tree[left_child].lds + tree[right_child].fours;
            case2 = tree[left_child].sevens + tree[right_child].lds;
            case3 = tree[left_child].sevens + tree[right_child].fours;

            new_node.lds = Math.max(Math.max(case1, case2), case3);
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
        if (left <= tree[node].left && right >= tree[node].right) {
            Node swapped = tree[node].swap();
            tree[node] = swapped;
        }
        else if(left > tree[node].right || right < tree[node].left) ;
        else {
            int left_child = node * 2; int right_child = node * 2 + 1;

            update(left_child, left, right);
            update(right_child, left, right);

            int case1 = tree[left_child].lis + tree[right_child].sevens;
            int case2 = tree[left_child].fours + tree[right_child].lis;
            int case3 = tree[left_child].fours + tree[right_child].sevens;

            tree[node].lis = Math.max(Math.max(case1, case2), case3);

            case1 = tree[left_child].lds + tree[right_child].fours;
            case2 = tree[left_child].sevens + tree[right_child].lds;
            case3 = tree[left_child].sevens + tree[right_child].fours;

            tree[node].lds = Math.max(Math.max(case1, case2), case3);
        }
    }


    String solve() throws IOException {
        build_tree(1, 1, n);
        String ans = "";

        for(int i = 1; i <= m; i++) {
            String new_line = br.readLine();

            if (new_line.equals("count")) {
                ans = ans.concat(String.valueOf(tree[1].lis));
                ans = ans.concat("\n");
            }
            else {
                st = new StringTokenizer(new_line);
                st.nextToken();
                update(1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
        }
        return ans;
    }
}

