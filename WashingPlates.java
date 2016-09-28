import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class WashingPlates {
    
    public static long maxMoney(int n, int k, long[] p, long[] d){
        long[] sum = new long[n];
        for(int i=0; i<n; i++){
            sum[i] = p[i] + d[i];
        }
        
        Arrays.sort(sum);
        
        long initSum = 0;
        for(int i=0; i<n; i++){
            initSum -= d[i];
        }
        
        int i=0;
        while((i < k)&&(n-1-i >= 0)){
            initSum += sum[n-1-i];
            i++;
        }
        
        if(initSum < 0){
            return 0;
        } else {
            return initSum;
        }
    }

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] p = new long[n];
        long[] d = new long[n];
     
        for(int i=0; i<n; i++){
            s = br.readLine();
            st = new StringTokenizer(s, " ");
            p[i] = Long.parseLong(st.nextToken());
            d[i] = Long.parseLong(st.nextToken());
        }
        long maxMoney = WashingPlates.maxMoney(n, k, p, d);
        System.out.println(maxMoney);
    }
}
