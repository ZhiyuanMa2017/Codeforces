import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
        int x = kattio.nextInt();
        int y = kattio.nextInt();
        String a = kattio.next();
        String b = kattio.next();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                list.add(i);
            }
        }
        int m = list.size();
        if (m == 0) {
            kattio.println(0);
        } else if (m % 2 == 1) {
            kattio.println(-1);
        } else {
            if (y <= x) {
                if (m == 2 && list.get(0) + 1 == list.get(1)) {
                    kattio.println(Math.min(y * 2, x));
                } else {
                    kattio.println((long) m / 2 * y);
                }
            } else {
                long[] dp = new long[m + 1];
                dp[1] = y;
                for (int i = 1; i < m; i++) {
                    dp[i + 1] = Math.min(dp[i] + y,
                            dp[i - 1] + (long) (list.get(i) - list.get(i - 1)) * x * 2);
                }
                kattio.println(dp[m] / 2);
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
