#include <iostream>
#include <algorithm>
using namespace std;

int main() {
  int n;
  cin >> n;

  int arr[n + 1];
  fill(arr, arr + n + 1, 0);
  for (int i = 0; i < n; i++) {
    int num;
    cin >> num;
    arr[num] = arr[num - 1] + 1;
  }

  sort(arr, arr + n + 1);
  cout << n - arr[n] << endl;
  return 0;
}