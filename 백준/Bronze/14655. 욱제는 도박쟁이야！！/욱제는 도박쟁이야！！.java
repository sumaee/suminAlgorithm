import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		int[] arr1=new int[n];
		int[] arr2=new int[n];
		//arr1 배열 채우기
		for(int i=0 ; i<n; i++) {
			arr1[i]=sc.nextInt();
		}
		//arr2 배열 채우기
		for(int i=0; i<n;i++) {
			arr2[i]=sc.nextInt();
		}
		
		int total=0;
        //양쪽을 이어서 뒤집을 수 있으므로 결국 배열 전부를 
        //arr1을 양수, arr2는 음수로 만들수 있다.
        //그렇기 때문에 절댓값으로 만든 두 배열을 더해주면 된다.
		for(int i=0; i<n; i++) {
			arr1[i]=Math.abs(arr1[i]);
			arr2[i]=Math.abs(arr2[i]);
			
			total+=(arr1[i]+arr2[i]);
		}
		
		System.out.println(total);
		sc.close();
	}

}