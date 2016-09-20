import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class SafeSquares {
	private int[][] grid;
	private int R;
	private int C;
	private int K;
	
	
	public SafeSquares(int R, int C, int K, String[] s){
		this.R = R;
		this.C = C;
		this.K = K;
		grid = new int[R][C];
		for(int i=0; i<R; i++){
			for(int j=0; j<C; j++){
				grid[i][j] = 0;
			}
		}
		
		for(int i=0; i<K; i++){
			StringTokenizer st = new StringTokenizer(s[i], " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			grid[x][y] = 1;
		}	
	}
	
	public long countSquare(int R, int C){
		long count = 0;
		int[][] s = new int[R][C];
		for(int i=0; i<R; i++){
			for(int j=0; j<C; j++){
				if(grid[i][j] == 0){
					s[i][j] = 1;
					count += 1;
				}
			}
		}
		
		for(int i=1; i<R; i++){
			for(int j=1; j<C; j++){
				if(s[i][j] == 0){
					continue;
				}
				int val = Math.min(s[i-1][j], s[i][j-1]);
				val = Math.min(s[i-1][j-1], val);
				s[i][j] = val + 1;
				count += val;
			}
		}
		return count;
	}
	
	
	public static void main(String[] args) throws IOException{
		String path = "";
		String inputFile = path + "B-large-practice.in";

		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++){
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s, " ");
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String[] ss = new String[K];
			for(int j=0; j<K; j++){
				ss[j] = br.readLine();
			}
			SafeSquares sfs = new SafeSquares(R, C, K, ss);
			long count = sfs.countSquare(R, C);
			System.out.println("Case #" + i + ": " + count);
		}
		
	}

}
