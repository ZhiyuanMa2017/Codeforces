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
        String a = kattio.next();
        String b = kattio.next();
        List<Integer> opA = new ArrayList<>();
        List<Integer> opB = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (a.charAt(i) != a.charAt(i + 1)) {
                opA.add(i + 1);
            }
            if (b.charAt(i) != b.charAt(i + 1)) {
                opB.add(i + 1);
            }
        }
        if (a.charAt(n - 1) != b.charAt(n - 1)) {
            opA.add(n);
        }
        StringBuilder res = new StringBuilder();
        res.append(opA.size() + opB.size()).append(" ");
        for (Integer op : opA) {
            res.append(op).append(" ");
        }
        for (int i = opB.size() - 1; i >= 0; i--) {
            res.append(opB.get(i)).append(" ");
        }
        res.setLength(res.length() - 1);
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
