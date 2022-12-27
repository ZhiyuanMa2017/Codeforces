import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
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
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = kattio.next();
        }
        ArrayList<Integer>[] graph = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            graph[i] = new ArrayList();
        }
        int[] inDegree = new int[26];
        for (int i = 0; i < n - 1; i++) {
            String s1 = strings[i];
            String s2 = strings[i + 1];
            for (int j = 0; j < Math.max(s1.length(), s2.length()); j++) {
                if (j == s2.length()) {
                    kattio.println("Impossible");
                    return;
                } else if (j == s1.length()) {
                    break;
                } else if (s1.charAt(j) != s2.charAt(j)) {
                    char c1 = s1.charAt(j);
                    char c2 = s2.charAt(j);
                    graph[c1 - 'a'].add(c2 - 'a');
                    inDegree[c2 - 'a']++;
                    break;
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer next : graph[cur]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                } else if (inDegree[next] < 0) {
                    kattio.println("Impossible");
                }
            }
            sb.append((char) (cur + 'a'));
        }
        if (sb.length() != 26) {
            kattio.println("Impossible");
        } else {
            kattio.println(sb);
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
