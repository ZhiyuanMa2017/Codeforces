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

    static int mod = 1000000007;

    public static void solve(Kattio kattio) {
        int t = kattio.nextInt();
        int k = kattio.nextInt();
        int[] dp = new int[100001];
        dp[0] = 1;
        for (int i = 1; i <= 100000; i++) {
            dp[i] = (dp[i] + dp[i - 1]) % mod;
            if (i >= k) {
                dp[i] = (dp[i] + dp[i - k]) % mod;
            }
        }
        int[] preSum = new int[100001];
        preSum[0] = dp[0];
        for (int i = 1; i <= 100000; i++) {
            preSum[i] = (preSum[i - 1] + dp[i]) % mod;
        }
        while (t > 0) {
            int a = kattio.nextInt();
            int b = kattio.nextInt();
            kattio.println((preSum[b] - preSum[a - 1] + mod) % mod);
            t--;
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
