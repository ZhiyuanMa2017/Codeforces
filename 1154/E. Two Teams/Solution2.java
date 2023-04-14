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
        int[] skills = new int[n];
        int[] order = new int[n + 1];
        for (int i = 0; i < n; i++) {
            skills[i] = kattio.nextInt();
            order[skills[i]] = i;
        }
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
        }
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = i + 1;
        }
        int[] res = new int[n];
        int team = 1;
        for (int i = n; i >= 1; i--) {
            int index = order[i];
            if (res[index] == 0) {
                res[index] = team;
                Deque<Integer> q = new ArrayDeque<>();
                q.addLast(index);
                int cur = index;
                for (int j = 0; j < k; j++) {
                    cur = next[cur];
                    if (cur >= n) {
                        break;
                    }
                    q.addLast(cur);
                }
                cur = index;
                for (int j = 0; j < k; j++) {
                    cur = prev[cur];
                    if (cur < 0) {
                        break;
                    }
                    q.addFirst(cur);
                }
                int right = q.getLast() < n ? next[q.getLast()] : n;
                int left = q.getFirst() >= 0 ? prev[q.getFirst()] : 0;
                if (right < n) {
                    prev[right] = left;
                }
                if (left >= 0) {
                    next[left] = right;
                }
                for (Integer ii : q) {
                    next[ii] = right;
                    prev[ii] = left;
                    res[ii] = team;
                }
                team = 3 - team;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : res) {
            sb.append(i);
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
