class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int sz = 2 * m;

        long[][] T = new long[sz][sz];

        // states:
        // [0..m-1]     = UP state for value x
        // [m..2m-1]    = DOWN state for value x

        for (int x = 0; x < m; x++) {
            // UP(x) -> DOWN(y) for y > x
            for (int y = x + 1; y < m; y++) {
                T[m + y][x] = 1;
            }

            // DOWN(x) -> UP(y) for y < x
            for (int y = 0; y < x; y++) {
                T[y][m + x] = 1;
            }
        }

        long[][] P = matPow(T, n - 1);

        long[] startUp = new long[sz];
        long[] startDown = new long[sz];

        for (int i = 0; i < m; i++) {
            startUp[i] = 1;
            startDown[m + i] = 1;
        }

        long ans = 0;

        long[] res1 = multiply(P, startUp);
        long[] res2 = multiply(P, startDown);

        for (long v : res1) ans = (ans + v) % MOD;
        for (long v : res2) ans = (ans + v) % MOD;

        return (int) ans;
    }

    private long[] multiply(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long cur = 0;
            for (int j = 0; j < n; j++) {
                cur = (cur + A[i][j] * v[j]) % MOD;
            }
            res[i] = cur;
        }
        return res;
    }

    private long[][] matPow(long[][] base, long exp) {
        int n = base.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;

                long aik = A[i][k];
                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;

                    C[i][j] = (C[i][j] + aik * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }
}