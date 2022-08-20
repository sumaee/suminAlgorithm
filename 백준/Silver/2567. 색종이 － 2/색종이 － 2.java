import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//색종이 수
		int cnt=Integer.parseInt(br.readLine());
		
		int[][] paper=new int[100][100];
		
		for(int i=0; i<cnt; i++) {
			st=new StringTokenizer(br.readLine());
			
			int x=Integer.parseInt(st.nextToken()); //가로 위치
			int y=Integer.parseInt(st.nextToken()); //세로 위치
			
			for(int j=x; j<x+10; j++) {
				for(int k=y; k<y+10; k++) {
					if(paper[j][k]==0) {
						paper[j][k]=1;
					}
				}
			}
		}// 종이 붙은 위치 확인  for문
		
		//상하좌우 탐색
		int[] dr= {-1, 1, 0, 0};
		int[] dc= {0, 0, -1, 1};
		
		//둘레 길이
		int len=0;
		
		for(int i=0; i<paper.length; i++) {
			for(int j=0; j<paper[i].length; j++) {
				//4방 탐색시 0의 개수, value=1 길이 하나 증가, value=2 구석위치므로 2개 증가
				int value=0;
				if(paper[i][j]==1) {
					for(int k=0; k<4; k++) {
						int row=i+dr[k];
						int col=j+dc[k];
						
						//경계 벗어나는 경우
						if(row<0||row>paper.length-1||col<0||col>paper.length-1) {
							value++;
							continue;
						}
						
						if(paper[row][col]==0) {
							value++;
						}
						
					}// 4방 탐색 for문
					
					len+=value;
				}// 해당 위치가 1일 때 
			}
		}
		System.out.println(len);
		
	}//main
}
