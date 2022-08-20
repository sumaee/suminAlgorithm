import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 도화지 크기만큼의 2차원 배열에 종이가 붙는 부분을 1씩 증가시켜 겹치는 부분 확인
		// 1이 이미 된 곳은 넓이를 증가 하지 않음
		// 0인 부분이 1이 될때만 넓이 증가
		
		//색종이의 수
		int cnt=Integer.parseInt(br.readLine()); 
		//넓이
		int result=0;
		int[][] paper=new int[100][100]; //도화지 크기
		
		for(int i=0; i<cnt; i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			for(int j=x; j<x+10; j++) {
				for(int k=y; k<y+10; k++) {
					if(paper[j][k]==0) {
						paper[j][k]=1;
						result++;
					}
				}
			}
			
		}
		
		System.out.println(result);
		
	}//main
}
