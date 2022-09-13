import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	// 자신이 얼리어답터인지 아닌지의 경우를 나누어 최솟값을 구하기
	// 자신이 얼리어답터라면 인접 노드들은 얼리어답터일수도 아닐수도 있음
	// 자신이 얼리어답터가 아니면 인접 노드가 모두 얼리어답터여야함
	static ArrayList<Integer>[] list;
	static boolean[] check;
	static int[][] tree;
	static int N;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		check = new boolean[N + 1];
		tree = new int[N + 1][2]; // tree[][0] : 얼리어답터가 아닐때
									// tree[][1] : 얼리어답터일때

//		배열 안에 리스트 넣기
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

//		연결된 애들끼리 묶어 배열 넣기
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			list[parent].add(child);
			list[child].add(parent);
		}

		findMin(1);
		result=Math.min(tree[1][0], tree[1][1]);
		System.out.println(result);

	}// main

	static void findMin(int start) {
		check[start] = true; // 방문 표시
		tree[start][0] = 0; // 해당 숫자가 얼리어답터가 아닐 경우
		tree[start][1] = 1; // 해당 숫자가 얼리어답터일 경우

		for (int a : list[start]) {
			if (!check[a]) { //만약 가지고 있는 숫자가 방문을 안했다면 실행
				findMin(a); //우선 자식노드 먼저 방문해서 얼리어답터 수 구하기
				tree[start][0]+=tree[a][1]; // 해당 자신이 얼리어답터가 아니라면 자식은 무조건 얼리어답터
				tree[start][1]+=Math.min(tree[a][0], tree[a][1]);//자식노드중 최솟값
			}
		}
	}//findMin
}
