import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        Kattio kattio = new Kattio();
        int t = kattio.nextInt();
        while (t > 0) {
            solve(kattio);
            t--;
        }
        kattio.close();
    }

    static int[] dirs = new int[]{0, 1, 0, -1, 0};

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int m = kattio.nextInt();
        char[][] grid = new char[n][m];
        int g = 0;
        for (int i = 0; i < n; i++) {
            String line = kattio.next();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'G') {
                    g++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'B') {
                    for (int p = 0; p < 4; p++) {
                        int x = i + dirs[p];
                        int y = j + dirs[p + 1];
                        if (x >= 0 && x < n && y >= 0 && y < m) {
                            if (grid[x][y] == 'G') {
                                kattio.println("No");
                                return;
                            } else if (grid[x][y] != 'B') {
                                grid[x][y] = '#';
                            }
                        }
                    }
                }
            }
        }
        if (grid[n - 1][m - 1] == '#') {
            if (g == 0) {
                kattio.println("Yes");
            } else {
                kattio.println("No");
            }
            return;
        }
        int g2 = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n - 1, m - 1});
        grid[n - 1][m - 1] = '#';
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = cur[0] + dirs[i];
                int y = cur[1] + dirs[i + 1];
                if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] != '#') {
                    if (grid[x][y] == 'G') {
                        g2++;
                    }
                    queue.offer(new int[]{x, y});
                    grid[x][y] = '#';
                }
            }
        }
        if (g == g2) {
            kattio.println("Yes");
        } else {
            kattio.println("No");
        }
    }


    static class Kattio extends PrintWriter {
        BufferedReader r;
        StringTokenizer st;

        // 标准 IO
        public Kattio() {
            this(System.in, System.out);
        }

        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }

        // 文件 IO
        public Kattio(String input, String output) throws IOException {
            super(output);
            r = new BufferedReader(new FileReader(input));
        }

        // 在没有其他输入时返回 null
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) {
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
