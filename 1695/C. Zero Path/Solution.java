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
        int m = kattio.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = kattio.nextInt();
            }
        }
        if ((n + m) % 2 == 0) {
            kattio.println("NO");
        } else {
            int[][][] dp = new int[n][m][2];
            dp[0][0][0] = grid[0][0];
            dp[0][0][1] = grid[0][0];
            for (int i = 1; i < n; i++) {
                dp[i][0][0] = dp[i - 1][0][0] + grid[i][0];
                dp[i][0][1] = dp[i - 1][0][1] + grid[i][0];
            }
            for (int i = 1; i < m; i++) {
                dp[0][i][0] = dp[0][i - 1][0] + grid[0][i];
                dp[0][i][1] = dp[0][i - 1][1] + grid[0][i];
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    dp[i][j][0] = Math.min(dp[i - 1][j][0], dp[i][j - 1][0]) + grid[i][j];
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i][j - 1][1]) + grid[i][j];
                }
            }
            if (dp[n - 1][m - 1][0] <= 0 && dp[n - 1][m - 1][1] >= 0) {
                kattio.println("YES");
            } else {
                kattio.println("NO");
            }
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
