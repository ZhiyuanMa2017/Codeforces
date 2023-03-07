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
        int[] dp = new int[m + 1];
        int total = 0;
        for (int i = 0; i < n; i++) {
            int number = kattio.nextInt();
            total += number;
            int[] preSum = new int[number + 1];
            for (int j = 1; j <= number; j++) {
                preSum[j] = kattio.nextInt();
                preSum[j] += preSum[j - 1];
            }
            int[] max = new int[number + 1];
            // choose j, max value,
            // prefix + suffix
            for (int j = 1; j <= number; j++) {
                // choose k prefix, j - k suffix
                // j - k suffix = total prefix - (number - j + k) prefix
                for (int k = 0; k <= j; k++) {
                    max[j] = Math.max(max[j], preSum[k] + preSum[number] - preSum[number - j + k]);
                }
            }
            total = Math.min(total, m);
            for (int j = total; j >= 0; j--) {
                for (int choose = 1; choose <= number; choose++) {
                    if (choose > j) {
                        break;
                    }
                    dp[j] = Math.max(dp[j], dp[j - choose] + max[choose]);
                }
            }
        }
        kattio.println(dp[m]);
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
