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
//        int t = kattio.nextInt();
//        while (t > 0) {
//            solve(kattio);
//            t--;
//        }
        solve(kattio);
        kattio.close();
    }

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        long[][] products = new long[n][2];
        for (int i = 0; i < n; i++) {
            products[i] = new long[]{kattio.nextLong(), kattio.nextLong()};
        }
        Arrays.sort(products, (a, b) -> Long.compare(a[1], b[1]));
        long sum = 0;
        long count = 0;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            if (products[l][1] <= count) {
                sum += products[l][0];
                count += products[l][0];
                l++;
            } else {
                long need = products[l][1] - count;
                long used = Math.min(need, products[r][0]);
                sum += 2 * used;
                count += used;
                products[r][0] -= used;
                if (products[r][0] == 0) {
                    r--;
                }
            }
        }
        kattio.println(sum);
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
