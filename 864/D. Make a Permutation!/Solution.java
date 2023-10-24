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
        int[] count = new int[n + 1];
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kattio.nextInt();
            count[arr[i]]++;
        }
        int change = 0;
        int missing = 1;
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (count[arr[i]] > 1) {
                while (count[missing] != 0) {
                    missing++;
                }
                if (arr[i] > missing || seen.contains(arr[i])) {
                    count[arr[i]]--;
                    arr[i] = missing;
                    count[missing]++;
                    change++;
                    missing++;
                } else {
                    seen.add(arr[i]);
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            res.append(arr[i]).append(" ");
        }
        kattio.println(change);
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
