import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

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
        String s = kattio.next();
        int q = kattio.nextInt();
        TreeSet<Integer>[] sets = new TreeSet[26];
        for (int i = 0; i < 26; i++) {
            sets[i] = new TreeSet<>();
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            sets[chars[i] - 'a'].add(i);
        }
        for (int i = 0; i < q; i++) {
            int first = kattio.nextInt();
            if (first == 1) {
                int pos = kattio.nextInt() - 1;
                char c = kattio.next().charAt(0);
                char prev = chars[pos];
                chars[pos] = c;
                sets[prev - 'a'].remove(pos);
                sets[c - 'a'].add(pos);
            } else if (first == 2) {
                int l = kattio.nextInt() - 1;
                int r = kattio.nextInt() - 1;
                int res = 0;
                for (int j = 0; j < 26; j++) {
                    Integer prev = sets[j].floor(r);
                    if (prev != null && prev >= l) {
                        res++;
                    }
                }
                kattio.println(res);
            }
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
