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
        int k = kattio.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = kattio.nextInt();
        }
        int l = 1;
        int r = (int) 1e9;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(a, mid, k)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        kattio.println(l);
    }

    public static boolean check(int[] a, int mid, int k) {
        int count1 = 0;
        for (int i = 1; i < a.length; i++) {
            if (count1 % 2 == 1) {
                count1++;
            } else {
                if (a[i] <= mid) {
                    count1++;
                }
            }
        }
        if (count1 >= k) {
            return true;
        }
        int count2 = 1;
        for (int i = 2; i < a.length; i++) {
            if (count2 % 2 == 0) {
                count2++;
            } else {
                if (a[i] <= mid) {
                    count2++;
                }
            }
        }
        return count2 >= k;
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
