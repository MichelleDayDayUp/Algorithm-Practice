import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;

public class Vote {
	
	public static double probabilityAlwaysWin(int N, int M){
		if(M == 0){
			return 1.0;
		}
		
		double[][] p = new double[N+1][M+1];
		p[0][0] = 0;
		p[1][0] = 1.0;
		for(int i = 2; i<=N; i++){
			
			for(int j=0; (j<i)&&(j <= M); j++){
				p[i][j] = 0;
				p[i][j] += p[i-1][j] * ((double)i/(double)(i+j)) ;
				if((i > (j-1))&&((j-1)>=0)){
					p[i][j] += p[i][j-1] * ((double)j/(double)(i+j));
				}
			}
		}
		
		return p[N][M];
	}
	
	public static void main(String[] args) throws IOException{
		PrintStream ps = new PrintStream(new FileOutputStream("A-large.out"));
		System.setOut(ps);
		
		FileInputStream fis = new FileInputStream("A-large.in");
		System.setIn(fis);
		Scanner sc = new Scanner(System.in);
	
		int T = Integer.parseInt(sc.nextLine()); 
		for(int i=1; i<=T; i++){
			String s = sc.nextLine();
			StringTokenizer st = new StringTokenizer(s, " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			double p = Vote.probabilityAlwaysWin(N, M);
			System.out.print("Case #" + i + ": ");
			System.out.printf("%.8f", p);
			System.out.println();
		}
	}

}
