import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = kattio.nextInt();
        }
        int[] res = new int[n];
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int prev = i - a[i];
            int next = i + a[i];
            int odd = a[i] % 2;
            if ((prev >= 0 && a[prev] % 2 != odd) || (next < n && a[next] % 2 != odd)) {
                res[i] = 1;
                queue.offer(i);
            } else {
                res[i] = -1;
            }
            if (prev >= 0 && a[prev] % 2 == odd) {
                graph[prev].add(i);
            }
            if (next < n && a[next] % 2 == odd) {
                graph[next].add(i);
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int id = queue.poll();
                for (Integer next : graph[id]) {
                    if (res[next] < 0) {
                        res[next] = res[id] + 1;
                        queue.offer(next);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int re : res) {
            sb.append(re).append(" ");
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
