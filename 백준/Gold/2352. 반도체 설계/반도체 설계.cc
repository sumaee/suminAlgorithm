#include <iostream>

using namespace std;

int main() {
    int n;
    cin >> n;

    int arr[n];
    int lis[n];
    cin >> arr[0];
    lis[0] = arr[0];

    int idx = 1;
    for (int i = 1; i < n; i++) {
        cin >> arr[i];

        if (lis[idx - 1] < arr[i]) {
            lis[idx++] = arr[i];
        } else {
            int start = 0;
            int end = idx;

            while (start < end) {
                int mid = (start + end) / 2;

                if (lis[mid] < arr[i]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            lis[start] = arr[i];
        }
    }

    cout << idx << endl;
    return 0;
}