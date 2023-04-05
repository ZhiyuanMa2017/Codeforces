import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        int m = kattio.nextInt();
        String s = kattio.next();
        String[] abc = new String[]{"abc", "acb", "bca", "bac", "cab", "cba"};
        int[][] dp = new int[6][n + 1];
        for (int i = 0; i < 6; i++) {
            String pattern = abc[i];
            for (int j = 0; j < n; j++) {
                if (pattern.charAt(j % 3) != s.charAt(j)) {
                    dp[i][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i][j + 1] = dp[i][j];
                }
            }
        }
        for (int i = 0; i < m; i++) {
            int l = kattio.nextInt();
            int r = kattio.nextInt();
            int res = (int) 2e5;gs
            for (int j = 0; j < 6; j++) {
                res = Math.min(res, dp[j][r] - dp[j][l - 1]);
            }
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
