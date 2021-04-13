//https://codeforces.com/contest/607/problem/A
import java.util.*;

public class c607A {
    static Scanner scan = new Scanner(System.in);
    static int[] pos, pow, count;
    static Map<Integer, Integer> map = new TreeMap<>();

    static int getIndex(int result){
        if(result == -1)
            return -1;
        if(result >= 0)
            return result - 1;
        return (result + 2) * -1;
    }

    static int[] convert(Integer[] in){
        int[] out = new int[in.length + 1];
        for(int i = 0; i <in.length; i++){
            out[i+1] = in[i];
        }
        return out;
    }

    public static void main(String[] args){
        int n = scan.nextInt();
        count = new int[n+1];

        for(int i = 1; i <= n; i++){
            int pos = scan.nextInt();
            int pow = scan.nextInt();
            map.put(pos, pow);
        }

        Integer[] key = map.keySet().toArray(new Integer[n]);
        Integer[] value = map.values().toArray(new Integer[n]);
        pos = convert(key); pow = convert(value);

        count[1] = 1;
        for(int i = 2; i <= n; i++){
            int search_key = pos[i] - pow[i];
            int index = getIndex(Arrays.binarySearch(pos, search_key));
            if(index == -1)
                count[i] = 0;
            else
                count[i] = count[index] + 1;
        }

        int max = 1;
        for(int i = 2; i <= n; i++){
            if(count[i] > max)
                max = count[i];
        }
        System.out.println(n - max);
    }
}
