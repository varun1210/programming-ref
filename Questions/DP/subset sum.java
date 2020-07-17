import java.util.*;

public class Main {
    //knapsack variant
    //all elements must be +ve
    //special condition for 0
    //ANOTHER VARIANT: COUNT SUBSETS THAT SUM TO K
    public static boolean subSetPresentRHelp(int[] a, int k, int i) {
        if(i == a.length) {
            if(k == 0) {
                return true;
            }
            else {
                return false;
            }
        }
        if(k == 0) {
            return true;
        }
        if(a[i] > k) {
            return subSetPresentRHelp(a, k, i + 1);
        }
        else {
            boolean includeAns = subSetPresentRHelp(a, k - a[i], i + 1);
            boolean excludeAns = subSetPresentRHelp(a, k, i + 1);
            return includeAns || excludeAns;
        }
    }
    
    public static boolean subSetPresentR(int[] a, int k) {
        if(k == 0) {
            return false;
        }
        return subSetPresentRHelp(a, k, 0);
    }
    
    public static boolean subSetPresentIterative(int[] a, int k) {
        if(k == 0) {
            return false;
        }
        boolean[][] dp = new boolean[a.length + 1][k + 1];
        dp[a.length][0] = true;
        for(int j = 1; j < dp[0].length; j++) {
            dp[a.length][j] = false;
        }
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for(int i = a.length - 1; i >= 0; i--) {
            for(int currK = 0; currK <= k; currK++) {
                if(a[i] > currK) {
                    dp[i][currK] = dp[i + 1][currK];
                }
                else {
                    boolean includeAns = dp[i + 1][currK - a[i]];
                    boolean excludeAns = dp[i + 1][currK];
                    dp[i][currK] = includeAns || excludeAns;
                }
            }
        }
        return dp[0][k];
    }
    
    //SUBSET COUNT
    //our new induction hypothesis: number of subsets that sum to k is comprised of
    //subset count that includes ith ele and subset count that excludes ith ele
    public static int subsetCounttoK(int[] a, int k) {
        if(k == 0) {
            return 0;
        }
        int[][] dp = new int[a.length + 1][k + 1];
        dp[a.length][0] = 1;
        
        //# of subsets for an index that is out of bounds is obviously 0
        for(int j = 1; j < dp[0].length; j++) {
            dp[a.length][j] = 0;
        }
        
        //# of subsets in which sum to k = 0 is 1 (because of {})
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        
        for(int i = a.length - 1; i >= 0; i--) {
            for(int currK = 0; currK <= k; currK++) {
                if(a[i] > currK) {
                    dp[i][currK] = dp[i + 1][currK];
                }
                else {
                    int includeAns = dp[i + 1][currK - a[i]];
                    int excludeAns = dp[i + 1][currK];
                    dp[i][currK] = includeAns + excludeAns;
                }
            }
        }
        return dp[0][k];
    }
    
	public static void main(String[] args) {
	    int set[] = {2, 3, 5, 6, 8, 10}; 
        int sum = 10;
        System.out.println(subSetPresentR(set, sum));
        System.out.println(subSetPresentIterative(set, sum));
        System.out.println(subsetCounttoK(set, sum));
	}
}
