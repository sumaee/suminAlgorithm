import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Queue que = new Queue();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            switch (command) {
                case "push":
                    que.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(que.pop());
                    break;
                case "size":
                    sb.append(que.size());
                    break;
                case "empty":
                    sb.append(que.arrIsEmpty() ? 1 : 0);
                    break;
                case "front":
                    sb.append(que.front());
                    break;
                case "back":
                    sb.append(que.back());
                    break;
            }

            if (!command.equals("push")) {
                sb.append("\n");
            }

        }

        System.out.println(sb);
    }
}

class Queue {
    int[] arr;
    int frontIdx;
    int rearIdx;

    public Queue() {
        frontIdx = 0;
        rearIdx = 0;
        arr = new int[2000001];
    }

    public boolean arrIsEmpty() {
        return frontIdx == rearIdx;
    }

    public void push(int num) {
        arr[rearIdx++] = num;
    }

    public int pop() {
        if (arrIsEmpty()) {
            return -1;
        }

        return arr[frontIdx++];
    }

    public int front() {
        if (arrIsEmpty()) {
            return -1;
        }
        return arr[frontIdx];
    }

    public int back() {
        if (arrIsEmpty()) {
            return -1;
        }

        return arr[rearIdx - 1];
    }

    public int size() {
        return rearIdx - frontIdx;
    }

}