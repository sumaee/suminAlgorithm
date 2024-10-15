import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Map<Integer, Integer> idxMap = new HashMap<>();

        int[] arrTmp = new int[n];
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            idxMap.put(value, i);
            arrTmp[i] = value;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            arr[i] = idxMap.get(value);
        }

        int[] lis = new int[n];
        int[] tmp = new int[n];

        lis[0] = arr[0];
        tmp[0] = 1;
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            int value = arr[i];

            if (lis[cnt - 1] < value) {
                lis[cnt] = value;
                cnt++;
                tmp[i] = cnt;
            } else {
                int left = 0;
                int right = cnt;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (lis[mid] < value) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                lis[left] = value;
                tmp[i] = left + 1;
            }
        }

        sb.append(cnt + "\n");

        List<Integer> answerTmp = new ArrayList<>();
        for (int i = tmp.length -1 ; i >= 0; i--) {
            if (tmp[i] == cnt) {
                cnt--;
                answerTmp.add(arrTmp[arr[i]]);
            }
        }
        answerTmp.sort((o1, o2) -> o1 - o2);

        for (int num : answerTmp) {
            sb.append(num + " ");
        }
        System.out.println(sb);
    }
}