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
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = kattio.nextInt();
        }
        int count = 0;
        int res = n;
        for (int i = 0; i < n; i++) {
            count++;
            if (i == n - 1 || a[i] != a[i + 1]) {
                int endIndex = i + 1;
                if (endIndex < n) {
                    count++;
                }
                int l = endIndex + 1;
                int r = n;
                while (true) {
                    while (l < r) {
                        int mid = l + (r - l) / 2;
                        if (a[mid] - a[endIndex] >= a[endIndex] - a[i]) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    if (l < n) {
                        count++;
                        endIndex = l;
                        l = endIndex + 1;
                        r = n;
                    } else {
                        break;
                    }
                }
                res = Math.min(res, n - count);
                count = 0;
            }
        }
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
