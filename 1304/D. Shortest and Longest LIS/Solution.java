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
        String s = kattio.next();
        int[] shortest = new int[n];
        int max = n;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '>') {
                shortest[i] = max;
                max--;
            }
        }
        int min = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (shortest[i] > 0) {
                continue;
            }
            int j = i;
            while (j >= 0 && shortest[j] == 0) {
                j--;
            }
            for (int k = j + 1; k <= i; k++) {
                shortest[k] = min;
                min++;
            }
            i = j + 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : shortest) {
            sb.append(i).append(" ");
        }
        kattio.println(sb);
        int[] longest = new int[n];
        max = n;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '<') {
                longest[i + 1] = max;
                max--;
            }
        }
        min = 1;
        for (int i = 0; i < n; i++) {
            if (longest[i] > 0) {
                continue;
            }
            int j = i;
            while (j < n && longest[j] == 0) {
                j++;
            }
            for (int k = j - 1; k >= i; k--) {
                longest[k] = min;
                min++;
            }
            i = j - 1;
        }
        sb = new StringBuilder();
        for (int i : longest) {
            sb.append(i).append(" ");
        }
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
