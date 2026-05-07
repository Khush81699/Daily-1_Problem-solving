class Solution {
    public int specialTriplets(int[] nums) {
        int MOD = 1_000_000_007;
        int n = nums.length;

        long ans = 0;

        java.util.Map<Integer, Integer> right = new java.util.HashMap<>();
        java.util.Map<Integer, Integer> left = new java.util.HashMap<>();

        // Count all elements on right side initially
        for (int x : nums) {
            right.put(x, right.getOrDefault(x, 0) + 1);
        }

        for (int j = 0; j < n; j++) {

            // current element removed from right
            right.put(nums[j], right.get(nums[j]) - 1);

            int target = nums[j] * 2;

            long leftCount = left.getOrDefault(target, 0);
            long rightCount = right.getOrDefault(target, 0);

            ans = (ans + (leftCount * rightCount) % MOD) % MOD;

            // add current to left
            left.put(nums[j], left.getOrDefault(nums[j], 0) + 1);
        }

        return (int) ans;
    }
}