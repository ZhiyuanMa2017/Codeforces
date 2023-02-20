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
        String s = kattio.next();
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int i = 0;
        int j = 25;
        while (i < j) {
            if (count[i] % 2 == 0) {
                i++;
            } else if (count[j] % 2 == 0) {
                j--;
            } else {
                count[i]++;
                count[j]--;
                i++;
                j--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < 26; k++) {
            if (count[k] == 0) {
                continue;
            }
            sb.append(String.valueOf((char) (k + 'a')).repeat(count[k] / 2));
        }
        StringBuilder res = new StringBuilder();
        res.append(sb);
        if (i == j && count[i] % 2 == 1) {
            res.append((char) (i + 'a'));
        }
        res.append(sb.reverse());
        kattio.println(res);
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
