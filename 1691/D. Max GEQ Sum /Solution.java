import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
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
        Deque<long[]> stack = new ArrayDeque<>();
        long preSum = 0;
        for (int i = 0; i < n; i++) {
            int v = a[i];
            while (!stack.isEmpty() && stack.peek()[0] <= v) {
                if (stack.peek()[1] < preSum) {
                    kattio.println("NO");
                    return;
                }
                stack.pop();
            }
            stack.push(new long[]{v, preSum});
            preSum += v;
        }
        stack.clear();
        preSum = 0;
        for (int i = n - 1; i >= 0; i--) {
            int v = a[i];
            while (!stack.isEmpty() && stack.peek()[0] <= v) {
                if (stack.peek()[1] < preSum) {
                    kattio.println("NO");
                    return;
                }
                stack.pop();
            }
            stack.push(new long[]{v, preSum});
            preSum += v;
        }
        kattio.println("YES");
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
