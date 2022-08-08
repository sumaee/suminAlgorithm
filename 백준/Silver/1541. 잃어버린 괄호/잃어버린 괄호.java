import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 입력받은 문자열을 - 기호를 기준으로 나누기
		String[] str = sc.next().split("-");
		
		int result=Integer.MAX_VALUE;
		
		for(int i=0; i<str.length; i++) {
			int sum=0;
			// 위에서 나눈 문자 배열을 + 기준으로 나누기
			String[] temp=str[i].split("\\+");
			
			for(int j=0; j<temp.length; j++) {
				sum+=Integer.parseInt(temp[j]);
			}
			
			
			if(result==Integer.MAX_VALUE) {
				result=sum; // 첫번째 값을 먼저 넣어주는 방식
			}
			else {
				result-=sum;
			}
		}
	
		System.out.println(result);
	}
}
