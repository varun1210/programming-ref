import java.util.*;

public class Main
{
    public static boolean isSubsequenceOfEachOtherHelp(String s1, String s2, int i, int j) {
        if(j == s2.length()) {
            return true;
        }
        if(i == s1.length()) {
            return false;
        }
        if(s1.charAt(i) == s2.charAt(j)) {
            return isSubsequenceOfEachOtherHelp(s1, s2, i + 1, j + 1);
        }
        else {
            return isSubsequenceOfEachOtherHelp(s1, s2, i + 1, j);
        }
    }
    
    public static boolean isSubsequenceOfEachOther(String s1, String s2) {
        if(s1.length() < s2.length()) {
            return isSubsequenceOfEachOtherHelp(s2, s1, 0, 0);
        }
        else {
            return isSubsequenceOfEachOtherHelp(s1, s2, 0, 0);
        }
    }
    
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String a = input.next();
		String b = input.next();
		System.out.println(isSubsequenceOfEachOther(a, b));
	}
}
