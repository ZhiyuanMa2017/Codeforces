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
        int l = 0;
        int r = 0;
        int res = 0;
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                l++;
            } else if (s.charAt(i) == ')') {
                r++;
                if (l == r) {
                    int cur = 2 * l;
                    if (cur > res) {
                        res = cur;
                        count = 1;
                    } else if (cur == res) {
                        count++;
                    }
                } else if (l < r) {
                    l = 0;
                    r = 0;
                }
            }
        }
        l = 0;
        r = 0;
        int res2 = 0;
        int count2 = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                r++;
            } else if (s.charAt(i) == '(') {
                l++;
                if (l == r) {
                    int cur = 2 * l;
                    if (cur > res2) {
                        res2 = cur;
                        count2 = 1;
                    } else if (cur == res2) {
                        count2++;
                    }
                } else if (l > r) {
                    l = 0;
                    r = 0;
                }
            }
        }
        if (res2 > res) {
            res = res2;
            count = count2;
        } else if (res2 == res) {
            count = Math.max(count, count2);
        }
        kattio.println(res + " " + count);
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
