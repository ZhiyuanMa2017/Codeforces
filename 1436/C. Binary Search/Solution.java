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

    private static final int MOD = (int) (1e9 + 7);

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int x = kattio.nextInt();
        int pos = kattio.nextInt();
        int greater = n - x;
        int smaller = x - 1;
        int g = 0;
        int s = 0;
        int l = 0;
        int r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mid <= pos) {
                s++;
                l = mid + 1;
            } else {
                g++;
                r = mid;
            }
        }
        s--;
        long res = A(smaller, s) * A(greater, g) % MOD * A(n - s - g - 1, n - s - g - 1) % MOD;
        kattio.println(res);
    }

    public static long A(int n, int m) {
        if (m > n) {
            return 0;
        }
        long res = 1;
        for (int i = 0; i < m; i++) {
            res = res * n % MOD;
            n--;
        }
        return res;
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
