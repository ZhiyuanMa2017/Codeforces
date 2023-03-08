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

    static boolean[] evil;
    static List<Integer>[] graph;
    static int[][] dis;
    static int rangeD;
    static int res;

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int m = kattio.nextInt();
        rangeD = kattio.nextInt();
        evil = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            evil[kattio.nextInt()] = true;
        }
        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int a = kattio.nextInt();
            int b = kattio.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }
        dis = new int[n + 1][];
        // firstDis, firstFrom, secondDis
        dfs1(1, 0);
        res = 0;
        dfs2(1, 0, Integer.MIN_VALUE);
        kattio.println(res);
    }

    public static void dfs2(int cur, int parent, int parentDis) {
        // from up to bottom
        if (parentDis > rangeD) {
            return;
        }
        if (dis[cur][0] <= rangeD) {
            res++;
        }
        if (evil[cur] && parentDis < 0) {
            parentDis = 0;
        }
        for (Integer next : graph[cur]) {
            if (next == parent) {
                continue;
            }
            if (next == dis[cur][1]) {
                dfs2(next, cur, Math.max(parentDis, dis[cur][2]) + 1);
            } else {
                dfs2(next, cur, Math.max(parentDis, dis[cur][0]) + 1);
            }
        }
    }


    public static int dfs1(int cur, int parent) {
        // from bottom to up
        int firstDis = Integer.MIN_VALUE;
        int firstFrom = 0;
        int secondDis = Integer.MIN_VALUE;
        for (Integer next : graph[cur]) {
            if (next == parent) {
                continue;
            }
            int d = dfs1(next, cur) + 1;
            if (d > firstDis) {
                secondDis = firstDis;
                firstDis = d;
                firstFrom = next;
            } else if (d > secondDis) {
                secondDis = d;
            }
        }
        dis[cur] = new int[]{firstDis, firstFrom, secondDis};
        if (firstDis < 0 && evil[cur]) {
            return 0;
        }
        return firstDis;
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
