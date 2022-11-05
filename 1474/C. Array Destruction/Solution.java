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
        count = new int[10000001];
        while (t > 0) {
            solve(kattio);
            t--;
        }
        kattio.close();
    }

    static int[] dirs = new int[]{0, 1, 0, -1, 0};
    static List<Integer> primes;
    static int[] count;

    public static void solve(Kattio kattio) {
        int n = kattio.nextInt();
        int nn = 2 * n;
        int[] a = new int[nn];
        for (int i = 0; i < nn; i++) {
            a[i] = kattio.nextInt();
        }
        Arrays.sort(a);
        for (int i = 0; i < nn - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < nn; j++) {
                count[a[j]]++;
            }
            int sum = a[i] + a[nn - 1];
            int last = nn - 1;
            List<int[]> res = new ArrayList<>();
            res.add(new int[]{sum, 0});
            for (int k = 0; k < n; k++) {
                while (last > 0 && count[a[last]] == 0) {
                    last--;
                }
                res.add(new int[]{a[last], sum - a[last]});
                count[a[last]]--;
                count[sum - a[last]]--;
                if (count[a[last]] < 0 || count[sum - a[last]] < 0) {
                    count[a[last]] = 0;
                    count[sum - a[last]] = 0;
                    flag = false;
                    break;
                }
                sum = a[last];
            }
            for (int j = 0; j < nn; j++) {
                count[a[j]] = 0;
            }
            if (flag) {
                kattio.println("YES");
                kattio.println(res.get(0)[0]);
                for (int k = 1; k <= n; k++) {
                    kattio.println(res.get(k)[0] + " " + res.get(k)[1]);
                }
                return;
            }
        }
        kattio.println("NO");
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
