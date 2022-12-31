import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
        int k = kattio.nextInt();
        List<Integer> alice = new ArrayList<>();
        List<Integer> bob = new ArrayList<>();
        List<Integer> both = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int t = kattio.nextInt();
            int a = kattio.nextInt();
            int b = kattio.nextInt();
            if (a == 1 && b == 1) {
                both.add(t);
            } else if (a == 1) {
                alice.add(t);
            } else if (b == 1) {
                bob.add(t);
            }
        }
        if (Math.min(alice.size(), bob.size()) + both.size() < k) {
            kattio.println(-1);
            return;
        }
        Collections.sort(alice);
        Collections.sort(bob);
        Collections.sort(both);
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < Math.min(alice.size(), bob.size()) && j < both.size() && k > 0) {
            if (alice.get(i) + bob.get(i) < both.get(j)) {
                res += alice.get(i) + bob.get(i);
                i++;
            } else {
                res += both.get(j);
                j++;
            }
            k--;
        }
        while (i < Math.min(alice.size(), bob.size()) && k > 0) {
            res += alice.get(i) + bob.get(i);
            i++;
            k--;
        }
        while (j < both.size() && k > 0) {
            res += both.get(j);
            j++;
            k--;
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
