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
        String hamburger = kattio.next();
        int b = 0;
        int s = 0;
        int c = 0;
        for (int i = 0; i < hamburger.length(); i++) {
            if (hamburger.charAt(i) == 'B') {
                b++;
            } else if (hamburger.charAt(i) == 'S') {
                s++;
            } else {
                c++;
            }
        }
        int nb = kattio.nextInt();
        int ns = kattio.nextInt();
        int nc = kattio.nextInt();
        int pb = kattio.nextInt();
        int ps = kattio.nextInt();
        int pc = kattio.nextInt();
        long r = kattio.nextLong();
        long l = 0;
        long h = (long) 1e12 + 1000;
        while (l < h) {
            long mid = l + (h - l + 1) / 2;
            long bNeed = mid * b;
            long sNeed = mid * s;
            long cNeed = mid * c;
            long bPurchase = Math.max(0, bNeed - nb);
            long sPurchase = Math.max(0, sNeed - ns);
            long cPurchase = Math.max(0, cNeed - nc);
            long money = bPurchase * pb + sPurchase * ps + cPurchase * pc;
            if (money <= r) {
                l = mid;
            } else {
                h = mid - 1;
            }
        }
        kattio.println(h);
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
