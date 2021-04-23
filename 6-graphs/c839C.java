//https://codeforces.com/problemset/problem/839/c

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class c839C {
    static Scanner scan;
    static int input_n;

    public static void main(String[] args){
        scan = new Scanner(System.in);
        input_n = scan.nextInt();
        Graph_839C graph = new Graph_839C(input_n, scan);
        System.out.println(graph.dfs(1));
    }
}

class Graph_839C{
    Scanner scan;
    int n;
    LinkedList<Integer> adj[];
    int child_count[];
    boolean visited[];


    Graph_839C(int n, Scanner scan){
        this.n = n;
        adj = new LinkedList[n+1];
        child_count = new int[n+1];
        visited = new boolean[n+1];
        for(int i = 0; i < n + 1; i++){
            adj[i] = new LinkedList();
            child_count[i] = 0;
            visited[i] = false;
        }
        this.scan = scan;
        readData();
    }

    public double dfs(int v){
        visited[v] = true;

        Iterator<Integer> i = adj[v].listIterator();
        double score_sum = 0.0;

        while(i.hasNext()){
            int next = i.next();
            if(!visited[next]) {
                child_count[v]++;
                score_sum = score_sum + dfs(next) + 1.0;
            }
        }

        if(child_count[v] == 0)
            return 0.0;

        return score_sum / (double)child_count[v];
    }

    private void readData(){
        for(int i = 0; i < n - 1; i++){
            int v = scan.nextInt();
            int w = scan.nextInt();
            adj[v].add(w);
            adj[w].add(v);
        }
    }
}

