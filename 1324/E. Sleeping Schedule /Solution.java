import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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

    static int n;
    static int h;
    static int l;
    static int r;
    static int[] a;
    static int[][] dp;

    public static void solve(Kattio kattio) {
        n = kattio.nextInt();
        h = kattio.nextInt();
        l = kattio.nextInt();
        r = kattio.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = kattio.nextInt();
        }
        dp = new int[n][h];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        kattio.println(dfs(0, 0));
    }

    public static int dfs(int i, int t) {
        if (i == dp.length) {
            if (l <= t && t <= r) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[i][t] != -1) {
            return dp[i][t];
        }
        int res = Math.max(dfs(i + 1, (t + a[i]) % h), dfs(i + 1, (t + a[i] - 1) % h));
        if (i > 0 && l <= t && t <= r) {
            res++;
        }
        dp[i][t] = res;
        return res;
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
