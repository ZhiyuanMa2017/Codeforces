import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
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
        Set<String> set = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = kattio.next();
        }
        for (int i = 0; i < n; i++) {
            String s = strings[i];
            if (s.charAt(0) == s.charAt(s.length() - 1)) {
                kattio.println("YES");
                return;
            }
            if (s.length() == 2) {
                String r = new StringBuilder(s).reverse().toString();
                if (set.contains(r) || set2.contains(r)) {
                    kattio.println("YES");
                    return;
                }
                set.add(s);
            } else if (s.length() == 3) {
                String r = new StringBuilder(s).reverse().toString();
                if (set.contains(r) || set.contains(r.substring(0, 2))) {
                    kattio.println("YES");
                    return;
                }
                set.add(s);
                set2.add(s.substring(0, 2));
            }
        }
        kattio.println("NO");
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
