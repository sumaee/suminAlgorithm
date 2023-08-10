import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Integer> nums;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        nums = new ArrayList<>();
        String str = "";
        while ((str = br.readLine()) != null && !str.isEmpty()) {
            nums.add(Integer.parseInt(str));
        }
        postOrder(0, nums.size() - 1);
        System.out.println(sb);
    }

    private static void postOrder(int idx, int limit) {
        if (idx > limit) {
            return;
        }

        int root = nums.get(idx);
        int nxtIdx = 0;
        for (int i = idx; i <= limit; i++) {
            //현재 루트보다 큰 수를 찾으면 for문 끝
            if (nums.get(i) > root) {
                nxtIdx = i;
                break;
            }
        }

        if (nxtIdx == 0) {
            nxtIdx = limit + 1;
        }

        postOrder(idx + 1, nxtIdx - 1);
        postOrder(nxtIdx, limit);
        sb.append(root).append("\n");
    }
}
