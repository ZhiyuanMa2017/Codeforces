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

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i] = new int[]{kattio.nextInt(), kattio.nextInt()};
        }
        if (n < 5) {
            kattio.println("YES");
            return;
        }
        if (check(points[0], points[1], points) || check(points[0], points[2], points)
                || check(points[1], points[2], points)) {
            kattio.println("YES");
        } else {
            kattio.println("NO");
        }
    }

    public static boolean check(int[] a, int[] b, int[][] points) {
        List<int[]> others = new ArrayList<>();
        for (int[] point : points) {
            if (!line(a, b, point)) {
                if (others.size() < 2) {
                    others.add(point);
                } else {
                    if (!line(others.get(0), others.get(1), point)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean line(int[] a, int[] b, int[] c) {
        return (long) (b[1] - a[1]) * (c[0] - a[0]) == (long) (b[0] - a[0]) * (c[1] - a[1]);
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
