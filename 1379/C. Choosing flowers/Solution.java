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
        int[][] flowers = new int[m][2];
        //[a, b]
        for (int i = 0; i < m; i++) {
            flowers[i] = new int[]{kattio.nextInt(), kattio.nextInt()};
        }
        Arrays.sort(flowers, (a, b) -> b[0] - a[0]);
        long[] preSum = new long[m];
        // preSum of a
        preSum[0] = flowers[0][0];
        for (int i = 1; i < m; i++) {
            preSum[i] = preSum[i - 1] + flowers[i][0];
        }
        long res = 0;
        for (int i = 0; i < m; i++) {
            int a = flowers[i][0];
            int b = flowers[i][1];
            // search all others to find a > current b
            if (b >= flowers[0][0]) {
                // no other a > current b, use only this flower
                res = Math.max(res, a + (long) b * (n - 1));
            } else {
                // binary search to find last a >= b
                int l = 0;
                int h = m - 1;
                while (l < h) {
                    int mid = l + (h - l + 1) / 2;
                    if (flowers[mid][0] >= b) {
                        l = mid;
                    } else {
                        h = mid - 1;
                    }
                }
                // different cases to use preSum
                // index!!! 0-based
                if (l >= n - 1) {
                    res = Math.max(res, preSum[n - 1]);
                } else {
                    if (l >= i) {
                        res = Math.max(res, preSum[l] + (long) b * (n - l - 1));
                    } else {
                        res = Math.max(res, preSum[l] + a + (long) b * (n - l - 2));
                    }
                }
            }
        }
        kattio.println(res);
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
