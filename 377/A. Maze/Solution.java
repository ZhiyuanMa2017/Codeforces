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

    static int mod = 1000000007;
    static int total;

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int m = kattio.nextInt();
        int k = kattio.nextInt();
        total = 0;
        char[][] grid = new char[n][m];
        int x = 0;
        int y = 0;
        for (int i = 0; i < n; i++) {
            String s = kattio.next();
            for (int j = 0; j < m; j++) {
                grid[i][j] = s.charAt(j);
                if (grid[i][j] == '.') {
                    total++;
                }
            }
        }
        boolean find = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.') {
                    x = i;
                    y = j;
                    find = true;
                    break;
                }
            }
            if (find) {
                break;
            }
        }
        boolean[][] remain = new boolean[n][m];
        dfs(x, y, grid, remain, k);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.' && !remain[i][j]) {
                    grid[i][j] = 'X';
                }
            }
        }
        for (int i = 0; i < n; i++) {
            kattio.println(String.valueOf(grid[i]));
        }
    }

    public static void dfs(int x, int y, char[][] grid, boolean[][] remain, int k) {
        if (total == k) {
            return;
        }
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length 
                || grid[x][y] != '.' || remain[x][y]) {
            return;
        }
        remain[x][y] = true;
        total--;
        dfs(x + 1, y, grid, remain, k);
        dfs(x - 1, y, grid, remain, k);
        dfs(x, y + 1, grid, remain, k);
        dfs(x, y - 1, grid, remain, k);
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
