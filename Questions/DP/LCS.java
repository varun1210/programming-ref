import java.util.*;

public class Main{
    
    public static int lcsIterative(String s1, String s2) {
        /*
        LOGIC:
        We use the same logic from the memoization solution by initializing a 2D array of size str1.len + 1 and str2.len + 1
        We fill from bottom up approach and return [0][0]
        */
        
        int dpI[][] = new int[s1.length() + 1][s2.length() + 1];
        for(int i = s1.length() - 1; i >= 0; i--) {
            for(int j = s2.length() - 1; j >= 0; j--) {
                if(s1.charAt(i) == s2.charAt(j)) {
                    dpI[i][j] = dpI[i + 1][j + 1] + 1;
                }
                else {
                    dpI[i][j] = Math.max(dpI[i + 1][j], dpI[i][j + 1]);
                }
            }
        }
        return dpI[0][0];
    }
    
    
    public static int lcsMemo(String s1, String s2, int i, int j, int[][] answers) {
        /*
        LOCIC:
        The overlapping subproblems are in the i, j form. ex.: what if ive already explored str(i) && str(j)?
        Initialized 2D matrix is the one with impossible values, ie, -1, in the driver code
        We use a 2D matrix to store these answers and then return them when needed
        
        Note: 
        the size of the 2D matrix is one more than the str1.length and str2.length. 
        This is so that we dont have to handle the edge cases when we call the i+1 & j+1 when they dont go out of range of matrix
        */
        if(i == s1.length() || j == s2.length()) {
            answers[i][j] = 0;
            return 0;
        }
        int smallAns1 = 0;
        int smallAns2 = 0;
        
        if(s1.charAt(i) == s2.charAt(j)) {
            if(answers[i + 1][j + 1] == -1) {
                smallAns1 = lcsMemo(s1, s2, i + 1, j + 1, answers);
                answers[i + 1][j + 1] = smallAns1;
                return answers[i + 1][j + 1] + 1;
            }
            else {
                return answers[i + 1][j + 1] + 1;
            }
        }
        
        else {
            if(answers[i][j + 1] == -1) {
                smallAns1 = lcsMemo(s1, s2, i, j + 1, answers);
                answers[i][j + 1] = smallAns1;
            }
            else {
                smallAns1 = answers[i][j + 1];
            }
            if(answers[i + 1][j] == -1) {
                smallAns2 = lcsMemo(s1, s2, i + 1, j, answers);
                answers[i + 1][j] = smallAns2;
            }
            else {
                smallAns2 = answers[i + 1][j];
            }
            return Math.max(smallAns1, smallAns2);
        }
        
    }
    
    
    public static int lcsRecursive(String s1, String s2, int i, int j) {
        /*
        LOGIC:
        We assume that we are standing at ith position in str1 and jth position in str2.
        if(str1.i == str2.j) then the lcs will be 1 + the lcs of same string at i + 1 and j + 1
        else if they are not equal then we will first explore ith & j + 1 th and jth and i + 1 th, whichever is bigger, return that value
        
        Our base case is that if we reach the end of either str1 or str2 the lcs will be that of a string with a null string so the answer is 0
        */
        
        if(i == s1.length() || j == s2.length()) {
            return 0;
        }
        int returnAns = 0;
        if(s1.charAt(i) == s2.charAt(j)) {
            returnAns = lcsRecursive(s1, s2, i + 1, j + 1) + 1;
        }
        else {
            returnAns = Math.max(lcsRecursive(s1, s2, i, j + 1), lcsRecursive(s1, s2, i + 1, j));
        }
        return returnAns;
    }
    
    public static void main(String[] args) {
        String a = "abdklagappcexzl";
        String b = "bfdmgjcxtool";
        int ans[][] = new int[a.length() + 1][b.length() + 1];
        for(int i=0; i<a.length() + 1; i++) {
            for(int j=0; j<b.length() + 1; j++) {
                ans[i][j] = -1;
            }
        }
        System.out.println(lcsMemo(a, b, 0, 0, ans));
        System.out.println(lcsIterative(a, b));
    }
}
