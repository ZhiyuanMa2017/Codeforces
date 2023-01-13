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
        int n = kattio.nextInt();
        String[] tags = new String[n];
        for (int i = 0; i < n; i++) {
            tags[i] = kattio.next();
        }
        for (int i = n - 1; i > 0; i--) {
            String next = tags[i];
            String cur = tags[i - 1];
            int j = 1;
            for (; j <= Math.min(next.length(), cur.length()); j++) {
                if (j == next.length() || j == cur.length()) {
                    break;
                }
                if (cur.charAt(j) < next.charAt(j)) {
                    j = cur.length();
                    break;
                }
                if (cur.charAt(j) > next.charAt(j)) {
                    break;
                }
            }
            tags[i - 1] = cur.substring(0, j);
        }
        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            sb.append(tag).append("\n");
        }
        sb.setLength(sb.length() - 1);
        kattio.println(sb);
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
