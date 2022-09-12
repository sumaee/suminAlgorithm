import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] rgb;
	static int[][] sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		
		// rgb[][0] : red
		// rgb[][1] : green
		// rgb[][2] : blue
		rgb=new int[N][3];
		sum=new int[N][3];
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			
			for(int j=0; j<3; j++) {
				rgb[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		sum[N-1][0]=rgb[N-1][0];
		sum[N-1][1]=rgb[N-1][1];
		sum[N-1][2]=rgb[N-1][2];
		
		
		int result=Math.min(Math.min(findMin(0, 0), findMin(0, 1)), findMin(0, 2));
		
		System.out.println(result);
		
	}//main
	
	static int findMin(int row, int col) {
		
		if(row==N-1||sum[row][col]!=0) {
			return sum[row][col];
		}
		
		if(sum[row][col]==0) {
			if(col==0) {
				sum[row][col]=Math.min(findMin(row+1, col+1), findMin(row+1, col+2))+rgb[row][col];
				
			}else if(col==1) {
				sum[row][col]=Math.min(findMin(row+1, col-1), findMin(row+1, col+1))+rgb[row][col];
				
			}else if(col==2){
				sum[row][col]=Math.min(findMin(row+1, col-2), findMin(row+1, col-1))+rgb[row][col];
			}
		}
		return sum[row][col];
	}
}
