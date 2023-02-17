import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = kattio.nextInt();
            pre[i + 1] = a[i] + pre[i];
        }
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                int cur = pre[i + 1] - pre[j];
                if (!map.containsKey(cur)) {
                    map.put(cur, new ArrayList<>());
                }
                map.get(cur).add(new int[]{j + 1, i + 1});
            }
        }
        List<int[]> res = new ArrayList<>();
        for (Integer key : map.keySet()) {
            List<int[]> list = map.get(key);
            if (list.size() < res.size()) {
                continue;
            }
            List<int[]> cur = new ArrayList<>();
            cur.add(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                if (cur.get(cur.size() - 1)[1] < list.get(i)[0]) {
                    cur.add(list.get(i));
                }
            }
            if (cur.size() > res.size()) {
                res = cur;
            }
        }
        kattio.println(res.size());
        for (int[] lr : res) {
            kattio.println(lr[0] + " " + lr[1]);
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