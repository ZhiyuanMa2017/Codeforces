import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
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
        Set<Long> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int num = kattio.nextInt();
            max = Math.max(max, num);
            set.add((long) num);
        }
        boolean findTwo = false;
        long x1 = 0;
        long x2 = 0;
        for (Long num : set) {
            long pow = 1;
            while (pow + num <= max) {
                if (set.contains(pow + num)) {
                    x1 = num;
                    x2 = num + pow;
                    findTwo = true;
                    if (set.contains(num + pow * 2)) {
                        kattio.println(3);
                        kattio.println(x1 + " " + x2 + " " + (x2 + pow));
                        return;
                    } else {
                        pow *= 4;
                        continue;
                    }
                }
                pow *= 2;
            }
        }
        if (findTwo) {
            kattio.println(2);
            kattio.println(x1 + " " + x2);
        } else {
            kattio.println(1);
            kattio.println(max);
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
