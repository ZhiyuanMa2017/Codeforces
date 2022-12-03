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
import java.util.TreeSet;


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
        int n = kattio.nextInt();
        int[] q = new int[n];
        int[] min = new int[n];
        int[] max = new int[n];
        boolean[] used = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            q[i] = kattio.nextInt();
            used[q[i]] = true;
        }
        int j = n;
        for (int i = n - 1; i > 0; i--) {
            if (q[i] != q[i - 1]) {
                min[i] = q[i];
            } else {
                while (j > 0 && used[j]) {
                    j--;
                }
                min[i] = j;
                j--;
            }
        }
        min[0] = q[0];
        TreeSet<Integer> notUsed = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                notUsed.add(i);
            }
        }
        max[0] = q[0];
        for (int i = 1; i < n; i++) {
            if (q[i] != q[i - 1]) {
                max[i] = q[i];
            } else {
                Integer maxLessThan = notUsed.floor(q[i]);
                max[i] = maxLessThan;
                notUsed.remove(maxLessThan);
            }
        }
        StringBuilder minP = new StringBuilder();
        StringBuilder maxP = new StringBuilder();
        for (int i = 0; i < n; i++) {
            minP.append(min[i]).append(" ");
            maxP.append(max[i]).append(" ");
        }
        kattio.println(minP);
        kattio.println(maxP);
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

    public static TreeSet<Long> primeFactorization(long n) {
        TreeSet<Long> factorization = new TreeSet<>();
        while (n % 2 == 0) {
            factorization.add(2L);
            n /= 2;
        }
        for (long d = 3; d * d <= n; d += 2) {
            while (n % d == 0) {
                factorization.add(d);
                n /= d;
            }
        }
        if (n > 1) {
            factorization.add(n);
        }
        return factorization;
    }

    public static long gcd(long a, long b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
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
