//https://www.hackerrank.com/challenges/almost-sorted

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AlmostSorted {
    
    public static void canSort(int[] a){
         
        int left=0;
        
        while((left < a.length-1)&&(a[left] <= a[left+1])){
            left++;
        }
        
        if(left == a.length-1){
            System.out.println("yes");
            return;
        }
        
        int right = left + 1;
        while((right < (a.length-1)) && (a[right] <= a[right + 1])){
            right++;
        }
        
        if(right == a.length-1){
            swap(a, left, left+1);
            if(isSorted(a)){
                System.out.println("yes");
                System.out.println("swap " + (left+1) + " " + (left+2));
                return;
            }
        } else {
            if(right == left+1){
                while((right < (a.length-1)) &&(a[right] >= a[right + 1])){
                    right++;
                }
                swap(a, left, right);
                if(isSorted(a)){
                    System.out.println("yes");
                    System.out.println("swap " + (left+1) + " " + (right+1));
                    return;
                } else {
                    swap(a, left, right);
                    reverse(a, left, right);
                    if(isSorted(a)){
                        System.out.println("yes");
                        System.out.println("reverse " + (left+1) + " " + (right+1));
                        return;
                    }
                }
                
            } else {
                swap(a, left, right+1);
                if(isSorted(a)){
                    System.out.println("yes");
                    System.out.println("swap " + (left+1) + " " + (right+2));
                    return;
                }
            }
        }
        
        System.out.println("no");
        
        
        
    }
    
    public static void swap(int[] a, int left, int right){
        if((left < 0)||(left >= a.length)||(right < 0)||(right >= a.length)){
            return;
        }
        int tmp = a[left];
        a[left] = a[right];
        a[right] = tmp;
        
    }
    
    public static void reverse(int[] a, int i, int j){
        if((i < 0)||(i >= a.length)||(j < 0)||(j >= a.length)){
            return;
        }
        int left = Math.min(i, j);
        int right = Math.max(i, j);
        while(left < right){
            swap(a, left, right);
            left++;
            right--;
        }
    }
    
    public static boolean isSorted(int[] a){
        
        int i=1;
        while((i < a.length)&&(a[i] >= a[i-1])){
            i++;
        }
        
        if(i == a.length){
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");
        int i=0;
        while(st.hasMoreTokens()){
            a[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        AlmostSorted.canSort(a);
    }
}
