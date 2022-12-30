import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        int v = kattio.nextInt();
        int[] a = new int[10];
        for (int i = 1; i <= 9; i++) {
            a[i] = kattio.nextInt();
        }
        int num = 1;
        int cost = a[1];
        for (int i = 2; i <= 9; i++) {
            if (a[i] <= cost) {
                cost = a[i];
                num = i;
            }
        }
        if (v < cost) {
            kattio.println(-1);
            return;
        }
        int len = v / cost;
        v = v % cost;
        int[] res = new int[len];
        Arrays.fill(res, num);
        for (int i = 0; i < len; i++) {
            int cur = res[i];
            int next = cur;
            for (int j = 9; j > cur; j--) {
                int diff = a[j] - a[cur];
                if (diff <= v) {
                    next = j;
                    break;
                }
            }
            if (next > cur) {
                v -= (a[next] - a[cur]);
                res[i] = next;
            } else {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(res[i]);
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
