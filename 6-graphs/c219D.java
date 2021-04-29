//https://codeforces.com/problemset/problem/219/d

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class c219D {

    public static void main(String[] args) throws IOException {

        LinkedList<Integer>[] adj;
        LinkedList<Boolean>[] is_way;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        adj = new LinkedList[n + 1];
        is_way = new LinkedList[n + 1];

        for(int i = 0; i <= n; i++){
            adj[i] = new LinkedList();
            is_way[i] = new LinkedList();
        }

        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[s].add(t); is_way[s].add(true);
            adj[t].add(s); is_way[t].add(false);
        }

        Graph_219D graph = new Graph_219D(n, adj, is_way);
        graph.dfs(1);
        graph.printAnswer();
    }
}

class Graph_219D{

    int n;
    LinkedList<Integer>[] adj;
    LinkedList<Boolean>[] is_way;

    Node[] nodes;
    int total_reds;

    Graph_219D(int n, LinkedList[] adj, LinkedList[] is_way){
        this.n = n;
        this.adj = adj;
        this.is_way = is_way;

        nodes = new Node[n + 1];
        total_reds = 0;

        for(int i = 0; i <= n; i++)
            nodes[i] = new Node();

    }

    class Node{
        int red_count = 0;
        int root_distance = 0;
        boolean visited = false;

        int score = 0;
        int calcScore(){
            score = total_reds - 2 * red_count + root_distance;
            return score;
        }
    }

    void dfs(int v){
        nodes[v].visited = true;
        ListIterator<Integer> it1 = adj[v].listIterator();
        ListIterator<Boolean> it2 = is_way[v].listIterator();

        while(it1.hasNext()){
            int next_v = it1.next();
            boolean reachable = it2.next();

            if(!nodes[next_v].visited){
                nodes[next_v].root_distance += nodes[v].root_distance + 1;
                nodes[next_v].red_count = nodes[v].red_count;
                if(!reachable) {
                    nodes[next_v].red_count++;
                    total_reds++;
                }
                dfs(next_v);
            }
        }
    }

    void printAnswer(){
        int ans = nodes[1].calcScore();
        for(int i = 2; i <= n; i++)
            ans = Math.min(ans, nodes[i].calcScore());

        System.out.println(ans);

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++)
            if(nodes[i].score == ans) {
                sb.append(i).append(" ");
            }

        System.out.println(sb.toString());
    }
}
