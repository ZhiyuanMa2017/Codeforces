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

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = kattio.nextInt();
        }
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = kattio.next();
        }
        String[] reverse = new String[n];
        for (int i = 0; i < n; i++) {
            reverse[i] = new StringBuilder(strings[i]).reverse().toString();
        }
        long[][] dp = new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        dp[0][0] = 0;
        dp[0][1] = c[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1][0] != Long.MAX_VALUE && strings[i].compareTo(strings[i - 1]) >= 0) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][0]);
            }
            if (dp[i - 1][1] != Long.MAX_VALUE && strings[i].compareTo(reverse[i - 1]) >= 0) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
            }
            if (dp[i - 1][0] != Long.MAX_VALUE && reverse[i].compareTo(strings[i - 1]) >= 0) {
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + c[i]);
            }
            if (dp[i - 1][1] != Long.MAX_VALUE && reverse[i].compareTo(reverse[i - 1]) >= 0) {
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + c[i]);
            }
        }
        long res = Math.min(dp[n - 1][0], dp[n - 1][1]);
        if (res == Long.MAX_VALUE) {
            kattio.println(-1);
        } else {
            kattio.println(res);
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
