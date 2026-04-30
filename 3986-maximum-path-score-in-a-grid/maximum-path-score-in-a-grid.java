class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        
        // dp[i][j][c] = max score at (i,j) with cost c
        int[][][] dp = new int[m][n][k + 1];
        
        // initialize with -1 (invalid)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    dp[i][j][c] = -1;
                }
            }
        }

        int startCost = (grid[0][0] == 0) ? 0 : 1;
        int startScore = grid[0][0];

        if (startCost <= k)
            dp[0][0][startCost] = startScore;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    if (dp[i][j][c] == -1) continue;

                    // move right
                    if (j + 1 < n) {
                        int newCost = c + (grid[i][j + 1] == 0 ? 0 : 1);
                        if (newCost <= k) {
                            dp[i][j + 1][newCost] = Math.max(
                                dp[i][j + 1][newCost],
                                dp[i][j][c] + grid[i][j + 1]
                            );
                        }
                    }

                    // move down
                    if (i + 1 < m) {
                        int newCost = c + (grid[i + 1][j] == 0 ? 0 : 1);
                        if (newCost <= k) {
                            dp[i + 1][j][newCost] = Math.max(
                                dp[i + 1][j][newCost],
                                dp[i][j][c] + grid[i + 1][j]
                            );
                        }
                    }
                }
            }
        }

        int ans = -1;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, dp[m - 1][n - 1][c]);
        }

        return ans;
    }
}