import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] tempArr;
    static long cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        tempArr = new int[n];
        cnt = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(0, n - 1);
        System.out.println(cnt);

    }

    private static void mergeSort(int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);

        merge(left, mid, right);
    }

    private static void merge(int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                tempArr[idx++] = arr[l++];
            } else {
                tempArr[idx++] = arr[r++];
                cnt += (mid - l + 1);
            }
        }

        while (l <= mid) {
            tempArr[idx++] = arr[l++];
        }

        while (r <= right) {
            tempArr[idx++] = arr[r++];
        }

        for (int i = left; i <= right; i++) {
            arr[i] = tempArr[i];
        }
    }
}