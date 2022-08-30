import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
	
		Stack<Float> drink =new Stack<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			drink.add((float) Integer.parseInt(st.nextToken()));
		}

		Collections.sort(drink);

		while(drink.size()!=1) {
		drink.add(drink.pop() +  drink.get(0)/ 2);
		drink.remove(0);
		}
		System.out.println(drink.pop());
	}
}
