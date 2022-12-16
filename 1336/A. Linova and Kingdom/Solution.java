import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Solution {
    public static void main(String[] args) {
        Kattio kattio = new Kattio();
//        int t = kattio.nextInt();
//        while (t > 0) {
//            solve(kattio);
//            t--;
//        }
        solve(kattio);
        kattio.close();
    }

    static int mod = 1000000007;

    static ArrayList<Integer>[] tree;
    static int[] dep;
    static int[] sub;


    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int k = kattio.nextInt();
        tree = new ArrayList[n + 1];
        dep = new int[n + 1];
        sub = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int a = kattio.nextInt();
            int b = kattio.nextInt();
            tree[a].add(b);
            tree[b].add(a);
        }
        dfs(1, 0);
        List<Integer> contributions = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            contributions.add(sub[i] - 1 - (dep[i] - 1));
        }
        Collections.sort(contributions, (a, b) -> b - a);
        long res = 0;
        for (int i = 0; i < n - k; i++) {
            res += contributions.get(i);
        }
        kattio.println(res);
    }

    public static int dfs(int cur, int parent) {
        if (parent == 0) {
            dep[cur] = 1;
        } else {
            dep[cur] = dep[parent] + 1;
        }
        sub[cur] = 1;
        for (Integer next : tree[cur]) {
            if (next == parent) {
                continue;
            }
            sub[cur] += dfs(next, cur);
        }
        return sub[cur];
    }

    static class Kattio extends PrintWriter {
        BufferedReader r;
        StringTokenizer st;

        // 标准 IO
        Kattio() {
            this(System.in, System.out);
        }

        Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }

        // 文件 IO
        Kattio(String input, String output) throws IOException {
            super(output);
            r = new BufferedReader(new FileReader(input));
        }

        // 在没有其他输入时返回 null
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(r.readLine());
                }
                return st.nextToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
