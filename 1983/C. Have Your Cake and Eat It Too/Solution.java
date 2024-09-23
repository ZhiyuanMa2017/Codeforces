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
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        long tot = 0;
        for (int i = 0; i < n; i++) {
            a[i] = kattio.nextInt();
            tot += a[i];
        }
        for (int i = 0; i < n; i++) {
            b[i] = kattio.nextInt();
        }
        for (int i = 0; i < n; i++) {
            c[i] = kattio.nextInt();
        }
        long bound = (tot + 2) / 3;
        int[] res = helper(a, b, c, bound);
        if (res.length > 0) {
            kattio.println(res[0] + " " + res[1] + " " + res[2] + " " + res[3] + " " + res[4] + " " + res[5]);
            return;
        }
        res = helper(a, c, b, bound);
        if (res.length > 0) {
            kattio.println(res[0] + " " + res[1] + " " + res[4] + " " + res[5] + " " + res[2] + " " + res[3]);
            return;
        }
        res = helper(b, a, c, bound);
        if (res.length > 0) {
            kattio.println(res[2] + " " + res[3] + " " + res[0] + " " + res[1] + " " + res[4] + " " + res[5]);
            return;
        }
        res = helper(b, c, a, bound);
        if (res.length > 0) {
            kattio.println(res[4] + " " + res[5] + " " + res[0] + " " + res[1] + " " + res[2] + " " + res[3]);
            return;
        }
        res = helper(c, a, b, bound);
        if (res.length > 0) {
            kattio.println(res[2] + " " + res[3] + " " + res[4] + " " + res[5] + " " + res[0] + " " + res[1]);
            return;
        }
        res = helper(c, b, a, bound);
        if (res.length > 0) {
            kattio.println(res[4] + " " + res[5] + " " + res[2] + " " + res[3] + " " + res[0] + " " + res[1]);
            return;
        }
        kattio.println(-1);
    }

    private static int[] helper(int[] a, int[] b, int[] c, long bound) {
        int n = a.length;
        int index = 0;
        long cur = 0;
        while (index < n && cur < bound) {
            cur += a[index];
            index++;
        }
        int ra = index - 1;
        if (cur < bound) {
            return new int[]{};
        }
        cur = 0;
        while (index < n && cur < bound) {
            cur += b[index];
            index++;
        }
        int rb = index - 1;
        if (cur < bound) {
            return new int[]{};
        }
        cur = 0;
        while (index < n && cur < bound) {
            cur += c[index];
            index++;
        }
        if (cur < bound) {
            return new int[]{};
        }
        return new int[]{1, ra + 1, ra + 2, rb + 1, rb + 2, n};
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
