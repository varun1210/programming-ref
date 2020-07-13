/*
NOTES:
-Recursive soln
logic:
Return all subarrays at (i + 1)th position and from all of these subarrays, find all that start with a[i + 1].
Make a copy of each of those arrays and add a[i] to the start of each of them.
Return all
-Issues:
1)uses ArrayList instead of arrays, wasted space
2)order of subarrays isn't maintained
-COMPLEXITY: O(n ^ 2)
-cannot be further optimized
*/

import java.util.*;

public class Main {
    
    public static ArrayList<ArrayList<Integer>> subArraysHelp(int[] a, int i) {
        if(i == a.length - 1) {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(a[i]);
            ArrayList<ArrayList<Integer>> start = new ArrayList<ArrayList<Integer>>();
            start.add(al);
            return start;
        }
        ArrayList<ArrayList<Integer>> nextAns = subArraysHelp(a, i + 1);
        ArrayList<Integer> thisEle = new ArrayList<Integer>();
        thisEle.add(a[i]);
        nextAns.add(thisEle);
        for(int j=0; j<nextAns.size(); j++) {
            ArrayList<Integer> temp = nextAns.get(j);
            if(temp.get(0) == a[i + 1]) {
                ArrayList<Integer> newAl = new ArrayList<Integer>();
                newAl.add(a[i]);
                for(int x = 0; x < temp.size(); x++) {
                    newAl.add(temp.get(x));
                }
                nextAns.add(newAl);
            }
        }
        return nextAns;
    }
    
    public static ArrayList<ArrayList<Integer>> subArrays(int[] a) {
        return subArraysHelp(a, 0);
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(subArrays(arr));
    }
}
