import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        nxt:
        for (int tc = 0; tc < testCase; tc++) {
            //수행할 함수
            String commands = br.readLine();
            //배열 크기
            int n = Integer.parseInt(br.readLine());
            //배열을 담을 데큐 선언
            Deque<Integer> que = new ArrayDeque<>();
            //우선 배열 담기
            String str = br.readLine();
            String[] arr = changeStr(str).split(",");
            for (int i = 0; i < n; i++) {
                que.offer(Integer.parseInt(arr[i]));
            }
            //함수에 따라 실행
            int way = 1; // 1 : 앞에서 부터, -1: 뒤에서 부터
            for (int i = 0; i < commands.length(); i++) {
                char command = commands.charAt(i);
                //만약 실행할 함수가 R이라면
                if (command == 'R') {
                    //방향 뒤집기
                    way *= -1;
                }
                //실행할 함수가 D라면
                else {
                    //지울 글자가 없으면 error 다음 케이스로 넘어가기
                    if (que.isEmpty()) {
                        sb.append("error").append("\n");
                        continue nxt;
                    }

                    if (way == 1) {
                        que.pollFirst();
                    } else {
                        que.pollLast();
                    }
                }

            }
            sb.append("[");
            int size = que.size();
            if (way == 1) {
                for (int i = 0; i < size; i++) {
                    sb.append(que.pollFirst());
                    if (i != size - 1) {
                        sb.append(",");
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    sb.append(que.pollLast());
                    if (i != size - 1) {
                        sb.append(",");
                    }
                }
            }
            sb.append("]").append("\n");
        }
        System.out.println(sb);
    }

    private static String changeStr(String str) {
        return str.replace("[", "")
                .replace("]", "");
    }
}
