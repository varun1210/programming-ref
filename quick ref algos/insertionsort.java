import java.util.*;

class Main {
    static void insertionSort2(int n, int[] arr) {
        int j = 1;
        
        /*
        OUTER LOOP HANDLES THE POSITION POINTER
        first we check if position 1 is sorted, then position 2 and so on, until we reach the last index.
        */
        
        for(j = 1; j < arr.length; j++) {
            int temp = arr[j]; //store the current element in a variable
            boolean flag = false;
            /*
            Why do we have a flag?
            consider the scenarios: 
            1) 
            1 13 14 2 1 && j == 2.
            2 is at the wrong position, but i want to see how many times i should move the elements before finally inserting 2 into its right positon.
            when we compare 2 with 14, flag is initially false, but gets marked true, so the loop knows to continue to execute, and that we have to explore further to find swap pos
            
            2)
            1 3 7 4 8 && j == 2
            7 is at the correct position, but i still have to check if its possible to move till the end. as long as the flag is false, the code knows it can keep exploring further 
            and still know if it should swap or not (flag remains false, so loop exits on its own after reaching i = -1.
            */
            for(int i=j-1; i>=-1; i--) {
                if(i == -1) { //Special case for when the element is to be inserted into the first position
                    if(flag == false) {
                    //already sorted (ex. 1 3 4 6, j == 2)
                        break;
                    }
                    else {
                    //ele needs to be inserted at arr[0] (ex. 3 4 1 5, j == 2)
                        arr[i + 1] = temp;
                        break;
                    }
                }
                if(arr[i] < temp && flag == false) {
                //since flag is false we keep exploring
                    continue;
                }
                else if(arr[i] >= temp) {
                    arr[i + 1] = arr[i];
                    flag = true;
                }
                else {
                // this condition is basically to say arr[i] < temp BUT flag is true, so we have to put the element here
                    arr[i + 1] = temp;
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int a[] = new int[n];
        for(int i=0; i<n; i++) {
            a[i] = (int)(Math.random()*100);
        }
        System.out.println(Arrays.toString(a));
        insertionSort2(n, a);
        System.out.println(Arrays.toString(a));
    }
}
