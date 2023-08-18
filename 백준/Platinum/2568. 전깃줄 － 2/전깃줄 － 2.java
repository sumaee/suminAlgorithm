import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Wire[] wires = new Wire[n];
        int[] answer = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            wires[i] = new Wire(a, b);
        }

        //전깃줄 하나를 잡고 오름차순으로 정리
        Arrays.sort(wires, (o1, o2) -> o1.a - o2.a);

        //첫번째 값 넣기
        answer[0] = wires[0].b;
        temp[0] = 1; // 연속된 전깃줄 개수를 저장
        int len = 1;

        //다음에 나오는 b의 전깃줄이 넣은 마지막 수보다 크다면 추가하고
        //작다면 넣을 곳을 탐색해 대치
        for (int i = 1; i < n; i++) {
            int value = wires[i].b;

            //만약 넣으려는 전깃줄이 이전거보다 크다면 뒤에 추가
            if (answer[len - 1] < value) {
                answer[len] = value;
                len++;
                temp[i] = len;
            }

            //아니라면 넣을 곳 탐색
            else {
                int start = 0;
                int end = len;

                while (start < end) {
                    int mid = (start + end) / 2;

                    if (answer[mid] < value) {
                        start = mid + 1;
                    } else {
                        end = mid;
                    }
                }

                answer[start] = value;
                temp[i] = start + 1;
            }
        }
        
        Set<Integer> result = new HashSet<>();
        for (int i = temp.length - 1; i >= 0; i--) {
            if (temp[i] == len) {
                result.add(wires[i].a);
                len--;
            }
        }

        answer = new int[n - result.size()];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!result.contains(wires[i].a)) {
                answer[idx++] = wires[i].a;
            }
        }

        sb.append(answer.length).append("\n");
        for (int i : answer) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);

    }

    static class Wire {
        int a, b;

        public Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
