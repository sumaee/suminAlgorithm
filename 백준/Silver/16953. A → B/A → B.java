import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int A=sc.nextInt();
		int B=sc.nextInt();
		int cnt=1;
		//만약 B의 뒤에 1이 붙어있으면 1을 제거
		//1이 없다면 2로 나눔
		//위를 반복해서 A가 나오는지 확인
		while(B>A) {
			if(B%10==1) {
				B/=10;
				cnt++;
			}else if(B%2==0) {
				B/=2;
                cnt++;
            }else{
                break;
            }
        }
        if(A==B){
            System.out.println(cnt);
        }else{
            System.out.println(-1);
        }
    	
	}
}
