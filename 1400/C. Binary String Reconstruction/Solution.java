import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
//        solve(kattio);
        kattio.close();
    }

    static int[] dirs = new int[]{0, 1, 0, -1, 0};
    static List<Integer> primes;
    static int[] primeFactor;
    static int[] preSum;


    public static void solve(Kattio kattio) {
        String s = kattio.next();
        int x = kattio.nextInt();
        int n = s.length();
        int[] a = new int[n];
        Arrays.fill(a, 1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (i - x >= 0) {
                    a[i - x] = 0;
                }
                if (i + x < n) {
                    a[i + x] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (i - x >= 0 && a[i - x] == 1) {
                    continue;
                }
                if (i + x < n && a[i + x] == 1) {
                    continue;
                }
                kattio.println(-1);
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(a[i]);
        }
        kattio.println(sb);
    }


    public static int[] getPrimeFactorPreSum() {
        int n = primeFactor.length;
        int[] count = new int[n];
        for (int i = 2; i < n; i++) {
            if (primeFactor[i] == i) {
                count[i] = 1;
            } else {
                count[i] = 1 + count[i / primeFactor[i]];
            }
        }
//        for (int i = 2; i < n; i++) {
//            int cur = i;
//            while (cur != primeFactor[cur]) {
//                cur = cur / primeFactor[cur];
//                count[i]++;
//            }
//            count[i]++;
//        }
        preSum = new int[n];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + count[i];
        }
        return preSum;
    }

    public static int[] getPrimeFactor(int max) {
        int[] res = new int[max + 1];
        for (int i = 2; i <= max; i++) {
            if (res[i] != 0) {
                continue;
            }
            for (int j = 1; j * i <= max; j++) {
                res[j * i] = i;
            }
        }
        return res;
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

    public static String toBinary(int num) {
        return String.format("%16s", Integer.toBinaryString(num)).replace(" ", "0");
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
