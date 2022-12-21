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
        int n1 = kattio.nextInt();
        int n2 = kattio.nextInt();
        int k1 = kattio.nextInt();
        int k2 = kattio.nextInt();
        int mod = 100000000;
        int[][][] dp = new int[n1 + 1][n2 + 1][2];
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                for (int diff = 1; diff <= Math.min(i, k1); diff++) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - diff][j][1]) % mod;
                }
                for (int diff = 1; diff <= Math.min(j, k2); diff++) {
                    dp[i][j][1] = (dp[i][j][1] + dp[i][j - diff][0]) % mod;
                }
            }
        }
        kattio.println((dp[n1][n2][0] + dp[n1][n2][1]) % mod);
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
