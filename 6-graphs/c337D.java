//https://codeforces.com/problemset/problem/337/D

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class c337D {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        boolean[] p = new boolean[n + 1];

        LinkedList<Integer>[] adj = new LinkedList[n + 1];
        for(int i = 0; i <= n; i++)
            adj[i] = new LinkedList();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= m; i++) {
            int j = Integer.parseInt(st.nextToken());
            p[j] = true;
        }

        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        Graph_337D graph = new Graph_337D(n, m, d, p, adj);
        int ans = graph.solve();
        System.out.println(ans);
    }
}

class Graph_337D{

    int n, m, d;
    boolean[] p;
    LinkedList[] adj;
    Node[] nodes;

    int max_dist;

    Set<Integer> set_a;
    Set<Integer> set_b;

    Graph_337D(int n, int m, int d, boolean[] p, LinkedList[] adj){
        this.n = n; this.m = m; this.d = d; this.p = p; this.adj = adj;
        nodes = new Node[n + 1];

        for(int i = 1; i <= n; i++)
            nodes[i] = new Node();

        max_dist = -1;
        set_a = new HashSet();
        set_b = new HashSet();
    }

    class Node{
        boolean visited = false;
        boolean is_p = false;

        int dist = 0;
    }

    int dfs(int v, int dist, int ab){
        nodes[v].visited = true;
        nodes[v].dist = dist;

        if(dist > max_dist && p[v]){
            ab = v;
            max_dist = dist;
        }

        nodes[v].is_p = p[v];

        ListIterator<Integer> it = adj[v].listIterator();

        while(it.hasNext()){
            int next_v = it.next();

            if(!nodes[next_v].visited){
                ab = dfs(next_v, dist + 1, ab);
            }
        }
        return ab;
    }

    void dfs2(int v, int dist, Set<Integer> set){
        nodes[v].visited = true;
        set.add(v);

        if(dist + 1 <= d) {
            ListIterator<Integer> it = adj[v].listIterator();

            while (it.hasNext()) {
                int next_v = it.next();

                if (!nodes[next_v].visited) {
                    dfs2(next_v, dist + 1, set);
                }
            }
        }
    }

    void resetVisited(){
        for(int i = 1; i <= n; i++)
            nodes[i].visited = false;
    }

    int solve(){

        int a = dfs(1, 0, -1);
        resetVisited();
        max_dist = -1;

        dfs2(a, 0, set_a);
        resetVisited();

        int b = dfs(a, 0, -1);
        resetVisited();

        if(b != a) {
            dfs2(b, 0, set_b);
            set_a.retainAll(set_b);
        }
        return set_a.size();
    }

}
