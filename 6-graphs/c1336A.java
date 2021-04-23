//https://codeforces.com/problemset/problem/1336/a

import java.util.*;

public class c1336A {

    static Scanner scan;
    static LinkedList<Integer>[] adj;

    public static void main(String[] args){
        scan = new Scanner(System.in);
        int n = scan.nextInt(); int k = scan.nextInt();

        adj = new LinkedList[n + 1];

        for(int i = 0; i < n + 1; i++){
            adj[i] = new LinkedList();
        }

        for(int i = 0; i < n - 1; i++){
            int v = scan.nextInt(); int w = scan.nextInt();
            adj[v].add(w); adj[w].add(v);
        }

        Graph_1336A graph = new Graph_1336A(n, adj);
        graph.dfs(1, -1);
        int[] scores = graph.calcScore();

        long ans = 0;
        for(int i = n; i > n - k; i--){
            ans += scores[i - 1];
        }
        System.out.println(ans);
    }
}

class Graph_1336A{
    int n;
    LinkedList<Integer>[] adj;
    Node[] nodes;
    int[] scores;

    class Node{
        int dist = 0;
        int des_count = 0;
        boolean visited = false;
    }

    Graph_1336A(int n, LinkedList[] adj){
        this.n = n; this.adj = adj;
        nodes = new Node[n + 1];

        for(int i = 0; i < n + 1; i++){
            nodes[i] = new Node();
        }
        scores = new int[n];
    }

    public int dfs(int i, int dist){
        nodes[i].visited = true;
        nodes[i].dist = dist + 1;
        Iterator<Integer> iterator = adj[i].listIterator();
        while(iterator.hasNext()){
            int next = iterator.next();
            if(!nodes[next].visited){
                nodes[i].des_count += dfs(next, dist + 1);
            }
        }
        return nodes[i].des_count + 1;
    }

    public int[] calcScore(){
        for(int i = 1; i < n + 1; i++)
            scores[i - 1] = nodes[i].dist - nodes[i].des_count;

        Arrays.sort(scores);
        return scores;
    }
}
