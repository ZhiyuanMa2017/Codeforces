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
        int[] a = new int[n];
        int[] queries = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = kattio.nextInt();
        }
        for (int i = 0; i < m; i++) {
            queries[i] = kattio.nextInt();
        }
        long[] preSum = new long[n];
        preSum[0] = a[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + a[i];
        }
        long[] max = new long[n];
        max[0] = preSum[0];
        for (int i = 1; i < n; i++) {
            max[i] = Math.max(preSum[i], max[i - 1]);
        }
        StringBuilder res = new StringBuilder();
        for (int query : queries) {
            if (query <= max[n - 1]) {
                int l = 0;
                int r = n;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (max[mid] >= query) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                res.append(l).append(" ");
            } else if (preSum[n - 1] <= 0) {
                res.append(-1).append(" ");
            } else {
                long cycles = (query - max[n - 1] + preSum[n - 1] - 1) / preSum[n - 1];
                query -= cycles * preSum[n - 1];
                long cur = cycles * n;
                int l = 0;
                int r = n;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (max[mid] >= query) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                cur += l;
                res.append(cur).append(" ");
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
