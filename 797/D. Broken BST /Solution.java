import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int[] v = new int[n + 1];
        int[] l = new int[n + 1];
        int[] r = new int[n + 1];
        boolean[] in = new boolean[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            v[i] = kattio.nextInt();
            map.put(v[i], map.getOrDefault(v[i], 0) + 1);
            l[i] = kattio.nextInt();
            r[i] = kattio.nextInt();
            if (l[i] != -1) {
                in[l[i]] = true;
            }
            if (r[i] != -1) {
                in[r[i]] = true;
            }
        }
        int root = 0;
        for (int i = 1; i <= n; i++) {
            if (!in[i]) {
                root = i;
                break;
            }
        }
        int[] res = new int[]{n};
        dfs(v, l, r, map, res, 0, (int) 1e9, root);
        kattio.println(res[0]);
    }

    public static void dfs(int[] v, int[] l, int[] r, Map<Integer, Integer> map,
                           int[] res, int min, int max, int cur) {
        if (cur == -1) {
            return;
        }
        if (min <= v[cur] && v[cur] <= max) {
            res[0] -= map.get(v[cur]);
        }
        if (min < v[cur]) {
            dfs(v, l, r, map, res, min, Math.min(max, v[cur] - 1), l[cur]);
        }
        if (v[cur] < max) {
            dfs(v, l, r, map, res, Math.max(min, v[cur] + 1), max, r[cur]);
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
