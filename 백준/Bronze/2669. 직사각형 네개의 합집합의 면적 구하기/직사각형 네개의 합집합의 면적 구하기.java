import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//종이가 붙는 부분을 겹치든 말든 상관없이 무조건 1로 변경
		int[][] paper=new int[101][101];
		int result=0;
		for(int n=0; n<4; n++) {
			st=new StringTokenizer(br.readLine());
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			
			for(int i=x1; i<x2; i++) {
				for(int j=y1; j<y2; j++) {
					paper[i][j]=1;
				}
			}
			
		}
		
		//paper배열을 돌며 1의 개수 확인 == 넓이가됨
		for(int i=0; i<paper.length; i++) {
			for(int j=0; j<paper[i].length; j++) {
				if(paper[i][j]==1) {
					result++;
				}
			}
		}
		
		System.out.println(result);
		
	}
}
