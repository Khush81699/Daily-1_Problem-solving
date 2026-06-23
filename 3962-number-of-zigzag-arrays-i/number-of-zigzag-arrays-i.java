class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int k = r - l + 1;

        if (n == 1) return k;

        long[] up = new long[k + 1];
        long[] down = new long[k + 1];

        // Length = 2
        for (int x = 1; x <= k; x++) {
            up[x] = x - 1;      // previous value < x
            down[x] = k - x;    // previous value > x
        }

        if (n == 2) {
            long ans = 0;
            for (int x = 1; x <= k; x++) {
                ans = (ans + up[x] + down[x]) % MOD;
            }
            return (int) ans;
        }

        for (int len = 3; len <= n; len++) {

            long[] prefUp = new long[k + 1];
            long[] prefDown = new long[k + 1];

            for (int i = 1; i <= k; i++) {
                prefUp[i] = (prefUp[i - 1] + up[i]) % MOD;
                prefDown[i] = (prefDown[i - 1] + down[i]) % MOD;
            }

            long totalUp = prefUp[k];

            long[] newUp = new long[k + 1];
            long[] newDown = new long[k + 1];

            for (int x = 1; x <= k; x++) {
                // last move becomes UP => previous move must be DOWN
                newUp[x] = prefDown[x - 1];

                // last move becomes DOWN => previous move must be UP
                newDown[x] = (totalUp - prefUp[x] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int x = 1; x <= k; x++) {
            ans = (ans + up[x] + down[x]) % MOD;
        }

        return (int) ans;
    }
}