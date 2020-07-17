import java.util.*;

public class Main {

    public static int editDistanceR(String s1, String s2, int i, int j) {
        
        /*
        LOGIC:
        Assume we are at position i on str1 and j on str2
        We are going to look at the question from either one's "POV"
        Let's say we look at the question from POV of s1
        If str1(i) == str2(j) then no change and the edit distance is the edit distance of the remaining chars of str1 & str2
        If not there are three options:
        1) delete a char from str1. Operation cost - 1. ED becomes edit distance of remaining str1 and original str2
        2) add a char to the beginning of str1. Operation cost - 1. ED becomes edit distance of str1 same size and remaining str2
        3) substitute a char at str1. Operation cost - 1. ED becomes edit distance of remaining str1 and str2.
        We take min of the above 3 options
        Base Case: The base case is when we reach the end of any one string. Then the ED is number of remaining chars in second string
        */

        if(i == s1.length() || j == s2.length()) {
            if(i == s1.length()) {
                return s2.length() - j;
            }
            else {
                return s1.length() - i;
            }
        }
        
        if(s1.charAt(i) == s2.charAt(j)) {
            return editDistanceR(s1, s2, i + 1, j + 1);
        }
        else {
            int delDistance = 1 + editDistanceR(s1, s2, i + 1, j); //deleting char at beginning
            int addDistance = 1 + editDistanceR(s1, s2, i, j + 1); //adding char at beginning
            int subDistance = 1 + editDistanceR(s1, s2, i + 1, j + 1); //substituting char at beginning
            return Math.min(delDistance, Math.min(addDistance, subDistance));
        }        
    }
    
    public static int editDistanceMemo(String s1, String s2, int i, int j, int[][] dp) {
        /*
        LOGIC: 
        Here we use the same logic from recursion
        At every stage there are three possible routes, ie, 
        1) (i + 1) & (j + 1) for cases when str1(i) == str2(j) or substitution
        2) (i + 1) & j for cases when we delete from str1
        3) i & (j + 1) for cases when we add to beginning of str1
        Now, if we draw the subproblem tree we will be able to visualize the overlapping subproblems in form of repetetive (i, j) pairs
        So we construct 2D matrix to store the value of answer of (i, j) pair. 
        - Size of 2D: str1.len + 1 x str2.length + 1 to handle edge cases
        - Fill matrix with -1s since that's not a possible answer
        */
	
	    if(i == s1.length() || j == s2.length()) {
            if(i == s1.length()) {
                if(dp[i][j] == -1) {
                    dp[i][j] = s2.length() - j;
                    return dp[i][j];
                }
                else {
                    return dp[i][j];
                }
            }
            else {
                if(dp[i][j] == -1) {
                    dp[i][j] = s1.length() - i;
                    return dp[i][j];
                }
                else {
                    return dp[i][j];
                }
            }
        }
        if(dp[i][j] == -1) {
            if(s1.charAt(i) == s2.charAt(j)) {
                dp[i][j] = editDistanceMemo(s1, s2, i + 1, j + 1, dp);
                return dp[i][j];
            }
            else {
                int delDistance = 1 + editDistanceMemo(s1, s2, i + 1, j, dp);
                int addDistance = 1 + editDistanceMemo(s1, s2, i, j + 1, dp);
                int subDistance = 1 + editDistanceMemo(s1, s2, i + 1, j + 1, dp);
                dp[i][j] = Math.min(delDistance, Math.min(addDistance, subDistance));
                return dp[i][j];
            }
        }
        else {
            return dp[i][j];
        }        
    }

    public static int editDistanceIterative(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for(int j = 0; j < dp[0].length; j++) {
            dp[s1.length()][j] = s2.length() - j;
        }
        for(int i = 0; i < dp.length; i++) {
            dp[i][s2.length()] = s1.length() - i;
        }
        for(int i = s1.length() - 1; i >= 0; i--) {
            for(int j = s2.length() - 1; j >= 0; j--) {
                if(s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], Math.min(dp[i + 1][j + 1], dp[i][j + 1]));
                }
            }
        }
        return dp[0][0];
    }
    
    public static void main(String[] args) {
        String a = "sunday";
        String b = "saturday";
        int[][] ans = new int[a.length() + 1][b.length() + 1];
        for(int i = 0; i < a.length(); i++) {
            for(int j = 0; j < b.length(); j++) {
                ans[i][j] = -1;
            }
        }
        System.out.println(editDistanceR(a, b, 0, 0));
        System.out.println(editDistanceMemo(a, b, 0, 0, ans));
        System.out.println(editDistanceIterative(a, b));
    }
}
