import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//0: 여자, 1: 남자
	//N: 인원수, K: 한방에 배정되는 인원수
	//S: 성별, Y: 학년
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken()); // 인원수
		int K=Integer.parseInt(st.nextToken()); // 한방의 인원수
		
		int[][] classNum=new int[2][7]; // 반에 인원수를 세기위한 배열 선언
		//classNum[0][] : 여학생 수
		//classNum[1][] : 남학생 수
		
		//입력받기
		for(int i=0; i< N; i++) {
			st=new StringTokenizer(br.readLine());
			
			int GorB=Integer.parseInt(st.nextToken()); // 성별
			int classN=Integer.parseInt(st.nextToken()); // 반
			
			classNum[GorB][classN]++;
		}
		
		int room=0;
		for(int i=1; i<7; i++) {
			//여학생 방 개수부터 정하기
			if(classNum[0][i]!=0) {
				if(classNum[0][i]<=K) {
					// 인원수가 K보다 작다면 방 1개 증가
					room++;
				}else if(classNum[0][i]%K==0) {
					//인원수가 K의 배수라면 몫만큼 증가
					room+=classNum[0][i]/K;
				}else {
					//인원수가 K의 배수가 아니고 K보다 크다면 몫에다 방 1개 더 필요
					room+=classNum[0][i]/K+1;
				}
			}
			
			if(classNum[1][i]!=0) {
				//남학생 방 개수 정하기
				if(classNum[1][i]<=K) {
					// 인원수가 K보다 작다면 방 1개 증가
					room++;
				}else if(classNum[1][i]%K==0) {
					//인원수가 K의 배수라면 몫만큼 증가
					room+=classNum[1][i]/K;
				}else {
					//인원수가 K의 배수가 아니고 K보다 크다면 몫에다 방 1개 더 필요
					room+=classNum[1][i]/K+1;
				}
			}
		}
		
		System.out.println(room);
		
	}//main
}
