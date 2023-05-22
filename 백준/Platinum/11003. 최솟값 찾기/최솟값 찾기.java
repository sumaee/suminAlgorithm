import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Deque<Num> deque = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            while (!deque.isEmpty() && deque.peekLast().num > number) {
                deque.pollLast();
            }
            deque.offer(new Num(i, number));
            if(deque.peekFirst().idx<i-(l-1)){
                deque.pollFirst();
            }

            sb.append(deque.peekFirst().num).append(" ");
        }

        System.out.println(sb);
    }
}

class Num{
    int idx;
    int num;

    public Num(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }
}