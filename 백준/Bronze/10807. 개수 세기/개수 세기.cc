#include <iostream>
using namespace std;

int main() {
  int n;
  cin >> n;

  int arr[n];
  for (int i = 0; i < n; i++) {
    cin >> arr[i];
  }

  int v;
  cin >> v;

  int answer = 0;
  for (int i = 0; i < n; i++) {
    if (arr[i] == v) {
      answer++;
    }
  }

  cout << answer << endl;
  return 0;
}