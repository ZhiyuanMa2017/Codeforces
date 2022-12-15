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
//        solve(kattio);
        kattio.close();
    }

    public static void solve(Kattio kattio) {
        String s = kattio.next();
        if (s.charAt(0) == 'R' && Character.isDigit(s.charAt(1)) && s.indexOf('C') > 1) {
            // RXCY
            int row = 0;
            int index = 1;
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                row = row * 10 + s.charAt(index) - '0';
                index++;
            }
            int col = 0;
            index++;
            while (index < s.length()) {
                col = col * 10 + s.charAt(index) - '0';
                index++;
            }
            StringBuilder res = new StringBuilder();
            while (col > 0) {
                int mod = col % 26;
                if (mod == 0) {
                    col -= 26;
                    mod = 25;
                } else {
                    mod--;
                }
                res.append((char) ('A' + mod));
                col /= 26;
            }
            res.reverse();
            res.append(row);
            kattio.println(res);
        } else {
            // BC23
            StringBuilder letter = new StringBuilder();
            int index = 0;
            while (index < s.length() && Character.isLetter(s.charAt(index))) {
                letter.append(s.charAt(index));
                index++;
            }
            StringBuilder res = new StringBuilder();
            res.append("R").append(s.substring(index));
            int col = 0;
            int pow = 1;
            for (int i = letter.length() - 1; i >= 0; i--) {
                col += (letter.charAt(i) - 'A' + 1) * pow;
                pow *= 26;
            }
            res.append("C").append(col);
            kattio.println(res);
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
