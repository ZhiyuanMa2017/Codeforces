import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

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
        int[] valueToIndex = new int[n + 1];
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            skills[i] = kattio.nextInt();
            valueToIndex[skills[i]] = i;
            treeSet.add(i);
        }
        int[] res = new int[n];
        int cur = 1;
        int skill = n;
        boolean[] used = new boolean[n + 1];
        while (skill > 0) {
            if (used[skill]) {
                skill--;
            } else {
                used[skill] = true;
                int index = valueToIndex[skill];
                treeSet.remove(index);
                res[index] = cur;
                Integer next = treeSet.higher(index);
                int count = k;
                while (next != null && count > 0) {
                    used[skills[next]] = true;
                    treeSet.remove(next);
                    res[next] = cur;
                    count--;
                    next = treeSet.higher(index);
                }
                Integer prev = treeSet.lower(index);
                count = k;
                while (prev != null && count > 0) {
                    used[skills[prev]] = true;
                    treeSet.remove(prev);
                    res[prev] = cur;
                    count--;
                    prev = treeSet.lower(index);
                }
                cur = 3 - cur;
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
