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
        String s = kattio.next();
        int[] preSum = new int[n + 1];
        int[] preMin = new int[n + 1];
        int[] preMax = new int[n + 1];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                preSum[i + 1] = preSum[i] + 1;
            } else {
                preSum[i + 1] = preSum[i] - 1;
            }
            preMin[i + 1] = Math.min(preMin[i], preSum[i + 1]);
            preMax[i + 1] = Math.max(preMax[i], preSum[i + 1]);
        }
        int[] suffixMin = new int[n + 1];
        int[] suffixMax = new int[n + 1];
        suffixMin[n] = preSum[n];
        suffixMax[n] = preSum[n];
        for (int i = n - 1; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], preSum[i]);
            suffixMax[i] = Math.max(suffixMax[i + 1], preSum[i]);
        }
        for (int i = 0; i < m; i++) {
            int l = kattio.nextInt() - 1;
            int r = kattio.nextInt() - 1;
            int curMin = preMin[l];
            int curMax = preMax[l];
            if (r == n - 1) {
                kattio.println(curMax - curMin + 1);
            } else {
                int midContribution = preSum[r + 1] - preSum[l];
                int rightMin = suffixMin[r + 2] - midContribution;
                int rightMax = suffixMax[r + 2] - midContribution;
                kattio.println(Math.max(curMax, rightMax) - Math.min(curMin, rightMin) + 1);
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
