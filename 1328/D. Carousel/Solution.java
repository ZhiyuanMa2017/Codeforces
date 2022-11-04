import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        int[] t = new int[n];
        boolean allSame = true;
        for (int i = 0; i < n; i++) {
            t[i] = kattio.nextInt();
            if (i > 0 && t[i] != t[i - 1]) {
                allSame = false;
            }
        }
        if (allSame) {
            kattio.println(1);
            for (int i = 0; i < n; i++) {
                kattio.print(1 + " ");
            }
            kattio.println();
            return;
        }
        if (n % 2 == 0) {
            kattio.println(2);
            for (int i = 0; i < n; i++) {
                kattio.print(i % 2 + 1 + " ");
            }
            kattio.println();
            return;
        }
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (t[i] == t[(i + 1) % n]) {
                index = i + 1;
            }
        }
        if (index == -1) {
            kattio.println(3);
            for (int i = 0; i < n - 1; i++) {
                kattio.print(i % 2 + 1 + " ");
            }
            kattio.print(3);
            kattio.println();
        } else {
            kattio.println(2);
            for (int i = 0; i < index; i++) {
                kattio.print(i % 2 + 1 + " ");
            }
            for (int i = index; i < n; i++) {
                kattio.print((i + 1) % 2 + 1 + " ");
            }
            kattio.println();
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
