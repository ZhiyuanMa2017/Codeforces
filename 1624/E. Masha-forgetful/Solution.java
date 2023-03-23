import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        int m = kattio.nextInt();
        Map<String, int[]> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String s = kattio.next();
            for (int j = 0; j < m - 1; j++) {
                map.put(s.substring(j, j + 2), new int[]{j + 1, j + 2, i});
            }
            for (int j = 0; j < m - 2; j++) {
                map.put(s.substring(j, j + 3), new int[]{j + 1, j + 3, i});
            }
        }
        String s = kattio.next();
        int[] dp = new int[m + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 2; i < m + 1; i++) {
            if (dp[i - 2] != -1 && map.containsKey(s.substring(i - 2, i))) {
                dp[i] = i - 2;
            }
            if (i >= 3 && dp[i - 3] != -1 && map.containsKey(s.substring(i - 3, i))) {
                dp[i] = i - 3;
            }
        }
        if (dp[m] == -1) {
            kattio.println(-1);
        } else {
            List<int[]> res = new ArrayList<>();
            int index = m;
            while (index > 0) {
                int prev = dp[index];
                res.add(map.get(s.substring(prev, index)));
                index = prev;
            }
            kattio.println(res.size());
            for (int i = res.size() - 1; i >= 0; i--) {
                int[] cur = res.get(i);
                kattio.println(cur[0] + " " + cur[1] + " " + cur[2]);
            }
        }
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
