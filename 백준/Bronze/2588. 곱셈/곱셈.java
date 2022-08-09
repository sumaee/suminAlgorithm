import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int num1=sc.nextInt();
		int num2=sc.nextInt();
		
		int result3=num1*(num2%10);
		int result4=num1*((num2%100)/10);
		int result5=num1*(num2/100);
		
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
		System.out.println(num1*num2);
	}
}
