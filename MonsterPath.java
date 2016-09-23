import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.StringTokenizer;

import java.text.DecimalFormat;

public class MonsterPath {
	private double maxProb = 0.0;
	private double P;
	private double Q;
	private int R;
	private int C;
	private int Rs;
	private int Cs;
	private int S;
	private double[][] grid;
	private double[][] backup;
	
	public MonsterPath(int r, int c, int rs, int cs, int s, double p, double q, String[] ss){
		R = r;
		C = c;
		Rs = rs;
		Cs = cs;
		S = s;
		P = p;
		Q = q;
		grid = parseInput(ss);
		backup = new double[R][C];
		for(int i=0; i<R; i++){
			for(int j=0; j<C; j++){
				backup[i][j] = grid[i][j];
			}
		}
		
	}
	
	private double[][] parseInput(String[] s){
		double[][] grid = new double[R][C];
		for(int i=0; i<s.length; i++){
			StringTokenizer st = new StringTokenizer(s[i], " ");
			int j=0;
			while(st.hasMoreTokens()){
				String ss = st.nextToken();
				if(ss.equals(".")){
					grid[i][j] = Q;
				} else {
					grid[i][j] = P;
				}
				j++;
			}
		}
		return grid;
	}
	
	public double catachMonster(){
		if(S == 0){
			maxProb = 0;
			return maxProb;
		}
		dfsCatchMonster(Rs, Cs, S, 0.0);
		return maxProb;
	}
	
	private void dfsCatchMonster(int r, int c, int s, double sumProb){
		if(s == 0){
			sumProb += grid[r][c];
			if(maxProb < sumProb){
				maxProb = sumProb;
			}
			return;
		} 
		
		double tmp = grid[r][c];
		if(s < S){
			sumProb += grid[r][c];
			grid[r][c] = (1-backup[r][c])*grid[r][c];
		}
		//visit r-1,c;
		if((r-1) >= 0){
			dfsCatchMonster(r-1, c, s-1, sumProb);
		}
		//visit r+1,c;
		if((r+1) < R){
			dfsCatchMonster(r+1, c, s-1, sumProb);
		}
		//visit r,c+1;
		if((c+1)<C){
			dfsCatchMonster(r, c+1, s-1, sumProb);
		}
		//visit r,c-1;
		if((c-1)>=0){
			dfsCatchMonster(r, c-1, s-1, sumProb);
		}
		
		grid[r][c] = tmp;
	}
	
	public static void main(String[] args) throws IOException{
		String path = "";
		String inputFile = path + "A-large-practice.in";

		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++){
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s, " ");
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int Rs = Integer.parseInt(st.nextToken());
			int Cs = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			double P = Double.parseDouble(st.nextToken());
			double Q = Double.parseDouble(st.nextToken());
			String[] ss = new String[R];
			for(int j=0; j<R; j++){
				ss[j] = br.readLine();
			}
			MonsterPath mp = new MonsterPath(R, C, Rs, Cs, S, P, Q, ss);
			double maxProb = mp.catachMonster();
			System.out.print("Case #" + i + ": ");
			System.out.printf("%.7f", maxProb);
			System.out.println();
		}
	}
	
}
