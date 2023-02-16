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
        String s = kattio.next();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = s.charAt(i - 1) == '(' ? 1 : -1;
        }
        int[] left = new int[n + 5];
        boolean[] leftCan = new boolean[n + 5];
        leftCan[0] = true;
        for (int i = 1; i <= n; i++) {
            left[i] = left[i - 1] + a[i];
            leftCan[i] = leftCan[i - 1] && left[i] >= 0;
        }
        int[] right = new int[n + 5];
        boolean[] rightCan = new boolean[n + 5];
        rightCan[n + 1] = true;
        for (int i = n; i >= 1; i--) {
            right[i] = right[i + 1] - a[i];
            rightCan[i] = rightCan[i + 1] && right[i] >= 0;
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (leftCan[i - 1] && rightCan[i + 1]) {
                if (a[i] == 1) {
                    if (left[i - 1] - 1 == right[i + 1]) {
                        res++;
                    }
                } else if (a[i] == -1) {
                    if (left[i - 1] == right[i + 1] - 1) {
                        res++;
                    }
                }
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
