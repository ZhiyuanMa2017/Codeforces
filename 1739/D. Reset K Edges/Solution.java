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
        int k = kattio.nextInt();
        int[] p = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            p[i] = kattio.nextInt();
        }
        int l = 1;
        int h = n - 1;
        while (l < h) {
            int mid = l + (h - l) / 2;
            int step = 0;
            int[] depth = new int[n + 1];
            for (int i = n; i > 1; i--) {
                if (depth[i] == mid - 1 && p[i] != 1) {
                    step++;
                } else {
                    depth[p[i]] = Math.max(depth[p[i]], depth[i] + 1);
                }
            }
            if (step <= k) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        kattio.println(l);
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
