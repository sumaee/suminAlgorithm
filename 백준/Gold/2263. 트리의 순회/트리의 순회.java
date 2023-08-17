import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] inOrder, postOrder, preOrder;
    static int n, idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        inOrder = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        postOrder = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        preOrder = new int[n];
        toPreOrder(0, n - 1, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(preOrder[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void toPreOrder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart <= inEnd && postStart <= postEnd) {
            //포스트 오더의 맨 끝은 루트 노드임
            preOrder[idx++] = postOrder[postEnd];

            int temp = inStart;
            for (int i = inStart; i <= inEnd; i++) {
                if (inOrder[i] == postOrder[postEnd]) {
                    temp = i;
                    break;
                }
            }

            //가운데를 기준으로 왼쪽 자식 트리로 이동
            toPreOrder(inStart, temp - 1, postStart, temp + postStart - inStart - 1);
            //가운데를 기준으로 오른쪽 자식 트리로 이동
            toPreOrder(temp + 1, inEnd, temp + postStart - inStart, postEnd - 1);
        }

    }
}
