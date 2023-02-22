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
        int t = kattio.nextInt();
        String s1 = kattio.next();
        String s2 = kattio.next();
        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        if ((diff + 1) / 2 > t) {
            kattio.println(-1);
            return;
        }
        char[] res = new char[n];
        int sameToChange = t - diff;
        int diffToRemain1 = diff - t;
        int diffToRemain2 = diff - t;
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == c2) {
                if (sameToChange > 0) {
                    res[i] = 'a';
                    if (res[i] == c1) {
                        res[i]++;
                    }
                    sameToChange--;
                } else {
                    res[i] = c1;
                }
            } else {
                if (diffToRemain1 > 0) {
                    res[i] = c1;
                    diffToRemain1--;
                } else if (diffToRemain2 > 0) {
                    res[i] = c2;
                    diffToRemain2--;
                } else {
                    res[i] = 'a';
                    while (res[i] == c1 || res[i] == c2) {
                        res[i]++;
                    }
                }
            }
        }
        kattio.println(new String(res));
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
