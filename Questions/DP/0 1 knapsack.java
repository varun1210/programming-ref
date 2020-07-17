class Main {
    /*
    LOGIC:
    1) Traverse every item starting from 0th.
    2) check if the item can be put in the bag
        2.1) if it can't: check if the next item can be put in the bag knapSack(wt, val, i + 1, maxWeight)
            return
        2.2) if it can:
            2.2.1) see what happens if I decide to take the item -- val[i] + knapSack(wt, val, i + 1, maxWeight - w[i])
            2.2.2) see what happens if I don't decide to take the item -- knapSack(wt, val, i + 1, maxWeight)
            Max of both is my return
    3) memoization: 
        Notice that the overlapping subproblems are of the form of the two changing variables(i, maxWeight) in every recursive call
        Maintain a 2D array with (i, maxWeight) (dimentions: 0 <= i <= value.length, 0 <= w <= maxWeight)
    4) iterative:
        w - maxweight at that cell allowed; maxweight - maxweight in the whole question
        fill the base cases, ie, dp[i = wt.length][] is 0;
        to fill any cell in the dp array, we need 
        dp[i + 1][w] - incase we decide not to take the item
        dp[i + 1][w - wt[i]] - incase we decide to take the item
        so we have to fill the array from i = val.length - 1(index of last item), w = 0 (maxweigt when maxweight at that point is 0)
    */
    
    public static int knapSackR(int[] wt, int[] val, int maxWeight) {
        return knapSackRHelp(wt, val, maxWeight, 0);
    }
    
    public static int knapSackRHelp(int[] wt, int[] val, int maxWeight, int i) {
        if(i == wt.length) {
            return 0;
        }
        if(wt[i] > maxWeight) {
            return knapSackRHelp(wt, val, maxWeight, i + 1);
        }
        else {
            int includeAns = val[i] + knapSackRHelp(wt, val, maxWeight - wt[i], i + 1);
            int excludeAns = knapSackRHelp(wt, val, maxWeight, i + 1);
            return Math.max(includeAns, excludeAns);
        }
    }
    
    public static int knapSackMemo(int[] wt, int[] val, int maxWeight, int i, int[][] dp) {
        //basecase
        if(i == wt.length) {
            if(dp[i][maxWeight] == -1) {
                dp[i][maxWeight] = 0;
                return dp[i][maxWeight];
            }
            else {
                return dp[i][maxWeight];
            }
        }
        //we first check do i have the answer of this problem that i just entered?
        if(dp[i][maxWeight] == -1) {
            if(wt[i] > maxWeight) {
                //we dont need to check for the next subproblem right away, just call it 
                //recursion will do the work for us in the next check
                dp[i][maxWeight] = knapSackMemo(wt, val, maxWeight, i + 1, dp);
                return dp[i][maxWeight];
            }
            else {
                int includeAns = val[i] + knapSackMemo(wt, val, maxWeight - wt[i], i + 1, dp);
                int excludeAns = knapSackMemo(wt, val, maxWeight, i + 1, dp);
                dp[i][maxWeight] = Math.max(includeAns, excludeAns);
                return dp[i][maxWeight];
            }
        }
        else {
            return dp[i][maxWeight];
        }
    } 
    
    public static int knapSackIterative(int[] wt, int[] val, int maxWeight) {
        int dp[][] = new int[wt.length + 1][maxWeight + 1];
        for(int i = dp.length - 2; i >= 0; i--) {
            for(int w = 0; w <= maxWeight; w++) {
                if(wt[i] > w) {
                    dp[i][w] = dp[i + 1][w];
                }
                else {
                    int includeAns = val[i] + dp[i + 1][w - wt[i]];
                    int excludeAns = dp[i + 1][w];
                    dp[i][w] = Math.max(includeAns, excludeAns);
                }
            }
        }
        return dp[0][maxWeight];
    }
    
    public static void main(String[] args) {
        int[] val = {5, 4, 8, 6};
        int[] wt = {1, 2, 4, 5};
        int W = 5;
        int[][] storage = new int[wt.length + 1][W + 1];
        for(int i = 0; i < storage.length; i++) {
            for(int j = 0; j < storage[0].length; j++) {
                storage[i][j] = -1;
            }
        }
        System.out.println(knapSackR(wt, val, W);
        System.out.println(knapSackMemo(wt, val, W, 0, storage));
        System.out.println(knapSackIterative(wt, val, W));
    }
}
