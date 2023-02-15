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
        int[][] count = new int[(int) (2e5 + 5)][20];
        int max = 0;
        for (int i = 0; i < n; i++) {
            int a = kattio.nextInt();
            count[a][0]++;
            max = Math.max(max, a);
        }
        int res = Integer.MAX_VALUE;
        for (int num = max; num >= 0; num--) {
            int next = num / 2;
            int cur = 0;
            int cnt = 0;
            for (int i = 0; i < 19; i++) {
                int value = count[num][i];
                if (num > 0) {
                    count[next][i + 1] += value;
                }
                if (cnt + value >= k) {
                    cur += i * (k - cnt);
                    res = Math.min(res, cur);
                    break;
                } else {
                    cur += i * value;
                    cnt += value;
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
