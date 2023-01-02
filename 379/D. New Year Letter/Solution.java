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
        int k = kattio.nextInt();
        int x = kattio.nextInt();
        int n = kattio.nextInt();
        int m = kattio.nextInt();
        for (int c1 = 0; c1 < 2; c1++) {
            for (int a1 = 0; a1 < 2; a1++) {
                if (c1 + a1 > n) {
                    continue;
                }
                for (int ac1 = 0; ac1 <= (n - a1 - c1) / 2; ac1++) {
                    for (int c2 = 0; c2 < 2; c2++) {
                        for (int a2 = 0; a2 < 2; a2++) {
                            if (c2 + a2 > m) {
                                continue;
                            }
                            for (int ac2 = 0; ac2 <= (m - a2 - c2) / 2; ac2++) {
                                long[] w1 = new long[]{c1, ac1, a1};
                                long[] w2 = new long[]{c2, ac2, a2};
                                for (int i = 2; i < k; i++) {
                                    long[] w3 = new long[]{
                                            w1[0],
                                            w1[1] + w2[1] + (w1[2] & w2[0]),
                                            w2[2]};
                                    w1 = w2;
                                    w2 = w3;
                                }
                                if (w2[1] == x) {
                                    StringBuilder sb1 = new StringBuilder();
                                    sb1.append("C".repeat(c1))
                                            .append("AC".repeat(ac1))
                                            .append("B".repeat(n - a1 - ac1 * 2 - c1))
                                            .append("A".repeat(a1));
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("C".repeat(c2))
                                            .append("AC".repeat(ac2))
                                            .append("B".repeat(m - a2 - ac2 * 2 - c2))
                                            .append("A".repeat(a2));
                                    kattio.println(sb1);
                                    kattio.println(sb2);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        kattio.println("Happy new year!");
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
