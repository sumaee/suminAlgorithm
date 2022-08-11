import java.util.Scanner;

public class Main {

	static int connect[][];
	static boolean check[];
	static int com, K, virus;
	static int cnt=0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		com = sc.nextInt();
		K = sc.nextInt();
		virus = 1; // 바이러스 시작시점

		connect = new int[com + 1][com + 1]; // 탐색 경로 저장 배열
		check = new boolean[com + 1]; // 경로 지나갔는지 여부 체크

		for (int i = 0; i < K; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			connect[a][b] = 1;
			connect[b][a] = 1;
		}
		
		dfs(1);
		System.out.println(cnt);
	}
	
	public static void dfs(int i) {
		check[i]=true;
		
		for(int j=0; j<=com; j++) {
			if(connect[i][j]==1 && check[j]==false) {
				cnt++;
				dfs(j);
			}
		}
	}
}
