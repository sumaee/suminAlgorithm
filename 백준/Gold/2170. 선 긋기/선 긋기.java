import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        list.sort(Comparator.comparingInt(o -> o.start));
        int start = -1000000000;
        int end = -1000000000;
        int answer = 0;
        for (Node curr : list) {
            if (curr.start > end) {
                answer += (end - start);
                end = curr.end;
                start = curr.start;
            } else {
                end = Math.max(end, curr.end);
            }
        }
        System.out.println(answer + end - start);

    }

    static class Node {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}