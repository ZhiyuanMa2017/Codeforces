import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
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
        int targetK = a[n - 1];
        // [k,c], k and the # of k
        Deque<int[]> s = new ArrayDeque<>();
        for (int i = n - 2; i >= 0; i--) {
            int k = a[i];
            // p^k  +...+ p^k
            // # of p^k = p
            // then p^k  +...+ p^k   = p^k * p = p^(k+1)
            // so k++
            while (!s.isEmpty() && s.getLast()[0] == k && s.getLast()[1] == p - 1) {
                s.pollLast();
                k++;
            }
            // equal, so two targetK can be divided to two sets
            // move to next value and update targetK
            if (k == targetK) {
                if (i == 0) {
                    kattio.println(0);
                    return;
                }
                i--;
                targetK = a[i];

                // can not remove targetK, then record this k
                // first case, have k, then count++
            } else if (!s.isEmpty() && s.getLast()[0] == k) {
                s.getLast()[1]++;
                // second case, not have k, add a new array
            } else {
                s.addLast(new int[]{k, 1});
            }
        }
        // res = largest - all other
        long res = binPow(p, targetK, mod);
        for (int[] ints : s) {
            res -= binPow(p, ints[0], mod) * ints[1];
        }
        res = (res % mod + mod) % mod;
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
