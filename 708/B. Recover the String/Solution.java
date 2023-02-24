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
        int a00 = kattio.nextInt();
        int a01 = kattio.nextInt();
        int a10 = kattio.nextInt();
        int a11 = kattio.nextInt();
        int zero = (int) quadraticEquation(a00);
        if (zero == -1) {
            kattio.println("Impossible");
            return;
        }
        int one = (int) quadraticEquation(a11);
        if (one == -1) {
            kattio.println("Impossible");
            return;
        }
        if (a01 == 0 && a10 == 0) {
            if (a00 > 0 && a11 > 0) {
                kattio.println("Impossible");
            } else if (a00 == 0) {
                kattio.println("1".repeat(one));
            } else if (a11 == 0) {
                kattio.println("0".repeat(zero));
            }
            return;
        }
        if (a01 + a10 != one * zero) {
            kattio.println("Impossible");
            return;
        }
        //  1111  0000   1   0000    1111
        // left1  left0     right0  right1
        // left1 * zero + 1 * right0 = a10
        int left1 = a10 / zero;
        int right0 = a10 % zero;
        int left0 = zero - right0;
        int right1 = one - left1;
        StringBuilder res = new StringBuilder();
        res.append("1".repeat(left1)).append("0".repeat(left0));
        if (right0 > 0) {
            right1--;
            res.append("1").append("0".repeat(right0));
        }
        res.append("1".repeat(right1));
        kattio.println(res);
    }

    public static long quadraticEquation(long target) {
        long x = (1 + (long) (Math.sqrt(1 + 8 * target))) / 2;
        if (x * (x - 1) == 2 * target) {
            return x;
        } else {
            return -1;
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
