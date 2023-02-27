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
        int[] c = new int[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            c[i] = kattio.nextInt();
            sum += c[i];
        }
        int ones = (int) (sum / n);
        int[] b = new int[n];
        int curDiff = 0;
        int[] diff = new int[n];
        // Use diff array to handle interval subtraction
        // curDiff => current value - ones (removed)
        // diff array => recover curDiff after the interval subtraction
        // remove b[n] from c everytime
        for (int i = n - 1; i >= 0; i--) {
            curDiff += diff[i];
            if (c[i] + curDiff == i + 1) {
                b[i] = 1;
            }
            curDiff--;
            if (i - ones >= 0) {
                diff[i - ones]++;
            }
            if (b[i] == 1) {
                ones--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : b) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
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
