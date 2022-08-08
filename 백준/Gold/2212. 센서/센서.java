import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		//우선 센서들의 위치를 오름차순 정렬
		//정렬 후 각 센서들 사이 거리를 배열에 넣기
		//거리 배열을 오름차순으로 정렬 후 큰 수를 K-1만큼 삭제
		// 남은 거리를 더함
		
		int N= sc.nextInt(); // 센서 개수
		int K= sc.nextInt(); // 집중국 개수
		int result=0; 
		
		int [] arr=new int [N]; // 센서 위치
		for(int i=0; i<N; i++) {
			arr[i]=sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		int[] len=new int[N-1];
		
		for(int i=0; i<N-1; i++) {
			len[i]=arr[i+1]-arr[i];
		}
		
		
		Arrays.sort(len);
		
		for(int i=0; i<(N-1)-(K-1);i++) {
			result+=len[i];
		}
		
		System.out.println(result);
	}
}
