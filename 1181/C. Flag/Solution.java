import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
        int m = kattio.nextInt();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = kattio.next();
            for (int j = 0; j < m; j++) {
                grid[i][j] = s.charAt(j);
            }
        }
        int[] lengthToLeft = new int[n];
        int res = 0;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (j > 0 && grid[i][j] == grid[i][j - 1]) {
                    lengthToLeft[i] += 1;
                } else {
                    lengthToLeft[i] = 1;
                }
            }
            List<Integer> heights = new ArrayList<>();
            int i = 0;
            while (i < n) {
                int h = 1;
                while (i + 1 < n && grid[i + 1][j] == grid[i][j]) {
                    i++;
                    h++;
                }
                heights.add(h);
                i++;
            }
            int preSum = heights.get(0);
            for (int index = 1; index < heights.size() - 1; index++) {
                if (heights.get(index - 1) >= heights.get(index)
                        && heights.get(index) <= heights.get(index + 1)) {
                    int cur = Integer.MAX_VALUE;
                    int start = preSum - heights.get(index);
                    int end = preSum + heights.get(index) * 2;
                    for (int id = start; id < end; id++) {
                        cur = Math.min(cur, lengthToLeft[id]);
                    }
                    res += cur;
                }
                preSum += heights.get(index);
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
