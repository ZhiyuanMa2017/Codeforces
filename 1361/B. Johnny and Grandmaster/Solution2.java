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
        int mod = (int) (1e9 + 7);
        int mod2 = 0x3f3f3f3f;
        int n = kattio.nextInt();
        int p = kattio.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = kattio.nextInt();
        }
        if (p == 1) {
            kattio.println(n % 2);
            return;
        }
        Arrays.sort(a);
        long res = 0;
        long res2 = 0;
        for (int i = n - 1; i >= 0; i--) {
            long one = binPow(p, a[i], mod);
            long two = binPow(p, a[i], mod2);
            if (res == 0 && res2 == 0) {
                res = one;
                res2 = two;
            } else {
                res = (res - one + mod) % mod;
                res2 = (res2 - two + mod2) % mod2;
            }
        }
        kattio.println(res);
    }

    public static long binPow(long a, long b, long m) {
        a %= m;
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = res * a % m;
            }
            a = a * a % m;
            b >>= 1;
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
