#include <iostream>

using namespace std;

int main() {
    int n;
    cin >> n;

    int weights[n];
    for (int i = 0; i < n; i++) {
        cin >> weights[i];
    }

    sort(weights, weights + n);
    int sum = 1;
    for (int w: weights) {
        if (sum < w) break;
        sum += w;
    }
    cout << sum << endl;
    return 0;
}