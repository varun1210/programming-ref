/*
No. of ways LOGIC:
the number of ways we can make the change from the ith position is
in case it is possible to take the ith coin:
{
  number of ways i can make by including the ith position (since the number of coins are infinite, it is an unbounded knapsack and we can take i again and again)
  +
  number of ways i can make by excluding the ith position
}
in case it is not possible to take the ith coin 
{
  number of ways i can make by excluding the ith position
}
*/

public class Main {
    
    public static int countWaysToMakeChangeRecrusive(int[] denominations, int V, int i) {
        if(i == denominations.length) {
            if(V == 0) {
                //SPECIAL CASE IN BASE CASE WHERE by including the last coin we reach value of V
                return 1;
            }
            else {
                return 0;
            }
        }
        if(V == 0) {
            return 1;
        }
        if(denominations[i] > V) {
            return countWaysToMakeChangeRecrusive(denominations, V, i + 1);
        }
        else {
        	int includeAns = countWaysToMakeChangeRecrusive(denominations, V - denominations[i], i);
            int excludeAns = countWaysToMakeChangeRecrusive(denominations, V, i + 1);
            return includeAns + excludeAns;
        }
    }
    
    public static int countWaysToMakeChange(int denominations[], int value){
        int dp[][] = new int[denominations.length + 1][value + 1];
        for(int j = 0; j < dp[0].length; j++) {
            dp[denominations.length][j] = 0;
        }
        dp[denominations.length][0] = 1;
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for(int i = dp.length - 2; i >= 0; i--) {
            for(int v = 1; v <= value; v++) {
                if(denominations[i] > v) {
                    dp[i][v] = dp[i + 1][v];
                }
                else {
                    int includeAns = dp[i + 1][v];
                    int excludeAns = dp[i][v - denominations[i]];
                    dp[i][v] = includeAns + excludeAns;
                }
            }
        }
        return dp[0][value];
    }
    
    public static void main(String[] args) {
        int[] denominations = {1, 2, 3, 4};
        int value = 5;
        System.out.println(countWaysToMakeChangeRecrusive(denominations, value, 0));
        System.out.println(countWaysToMakeChange(denominations, value));
    }
}
