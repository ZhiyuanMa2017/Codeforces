import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    static List<Integer> primes;

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int m = kattio.nextInt();
        List<Set<Character>> sets = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            sets.add(new HashSet<>());
        }
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = kattio.next();
            for (int j = 0; j < m; j++) {
                sets.get(j).add(strings[i].charAt(j));
            }
        }
        char[] chars = strings[0].toCharArray();
        for (int i = 0; i < m; i++) {
            char cc = chars[i];
            for (Character c : sets.get(i)) {
                chars[i] = c;
                boolean meet = true;
                for (int j = 0; j < n; j++) {
                    int count = 0;
                    for (int k = 0; k < m; k++) {
                        if (chars[k] != strings[j].charAt(k)) {
                            count++;
                            if (count > 1) {
                                meet = false;
                                break;
                            }
                        }
                    }
                    if (!meet) {
                        break;
                    }
                }
                if (meet) {
                    kattio.println(new String(chars));
                    return;
                }
            }
            chars[i] = cc;
        }
        kattio.println(-1);
    }

    public static List<Integer> getPrime(int max) {
        boolean[] notP = new boolean[max + 1];
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (notP[i]) {
                continue;
            }
            res.add(i);
            for (int j = 1; j * i <= max; j++) {
                notP[j * i] = true;
            }
        }
        return res;
    }

    public static int countFactors(int num) {
        int res = 0;
        for (Integer prime : primes) {
            if (prime * prime > num) {
                break;
            }
            while (num % prime == 0) {
                num /= prime;
                res++;
            }
        }
        if (num > 1) {
            res++;
        }
        return res;
    }

    public static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
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
