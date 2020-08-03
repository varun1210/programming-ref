import java.util.*;
import java.io.*;

public class Main{
    
    public static void heapSort(int[] a) {
        // CREATING THE HEAP
        int heapPos = 0; //position to which heap is maintained
        while(heapPos < a.length) {
            int child = heapPos;
            int parent = (child - 1) / 2;
            while(parent > -1) {
                if(a[parent] > a[child]) {
                    int temp = a[parent];
                    a[parent] = a[child];
                    a[child] = temp;
                    child = parent;
                    parent = (child - 1) / 2;
                }
                else {
                    break;
                }
            }
            heapPos++;
        }
        
        //REMOVING FROM HEAP
        int heapSize = a.length; //current size of the heap
        int sortPos = a.length - 1; //this is the index of the array where we will put the element after removing from the heap
        while(heapSize > 0) {
            int temp = a[0];
            a[0] = a[sortPos];
            a[sortPos] = temp;
            int parent = 0;
            int child1 = (parent * 2) + 1;
            int child2 = (parent * 2) + 2;
            while(child1 < sortPos) {
                if(child2 >= sortPos) {
                    if(a[parent] > a[child1]) {
                        int tempx = a[parent];
                        a[parent] = a[child1];
                        a[child1] = tempx;
                    }
                    else {
                        break;
                    }
                }
                else {
                    int min = Math.min(a[parent], Math.min(a[child1], a[child2]));
                    if(min == a[parent]) {
                        break;
                    }
                    else {
                        if(min == a[child1]) {
                            int tempx = a[child1];
                            a[child1] = a[parent];
                            a[parent] = tempx;
                            parent = child1;
                            child1 = (parent * 2) + 1;
                            child2 = (parent * 2) + 2;
                        }
                        else {
                            int tempx = a[parent];
                            a[parent] = a[child2];
                            a[child2] = tempx;
                            parent = child2;
                            child1 = (parent * 2) + 1;
                            child2 = (parent * 2) + 2;
                        }
                    }
                }
            }
            heapSize--;
            sortPos--;
        }
    }
    
    
    public static void main(String[] args) {
        int x[] = new int[10];
        for(int i = 0; i < 10; i++) {
            x[i] = (int)(Math.random()*100);
        }
        System.out.println(Arrays.toString(x));
        heapSort(x);
        System.out.println(Arrays.toString(x));
    }
}
