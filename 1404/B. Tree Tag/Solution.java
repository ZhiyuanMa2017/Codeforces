import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        Kattio kattio = new Kattio();
        int t = kattio.nextInt();
        while (t > 0) {
            solve(kattio);
            t--;
        }
//        solve(kattio);
        kattio.close();
    }

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int a = kattio.nextInt() - 1;
        int b = kattio.nextInt() - 1;
        int da = kattio.nextInt();
        int db = kattio.nextInt();
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int v = kattio.nextInt() - 1;
            int u = kattio.nextInt() - 1;
            tree[v].add(u);
            tree[u].add(v);
        }
        if (2 * da >= db) {
            kattio.println("Alice");
        } else {
            int[] dis = new int[n];
            dfs(a, -1, 0, dis, tree);
            if (dis[b] <= da) {
                kattio.println("Alice");
            } else {
                int maxDis = 0;
                int dia = 0;
                for (int i = 0; i < n; i++) {
                    if (dis[i] > maxDis) {
                        maxDis = dis[i];
                        dia = i;
                    }
                }
                dis = new int[n];
                dfs(dia, -1, 0, dis, tree);
                for (int i = 0; i < n; i++) {
                    maxDis = Math.max(maxDis, dis[i]);
                }
                if (2 * da >= maxDis) {
                    kattio.println("Alice");
                } else {
                    kattio.println("Bob");
                }
            }
        }
    }

    public static void dfs(int cur, int par, int d, int[] dis, List<Integer>[] tree) {
        dis[cur] = d;
        for (Integer next : tree[cur]) {
            if (next == par) {
                continue;
            }
            dfs(next, cur, d + 1, dis, tree);
        }
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
