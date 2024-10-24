#include <iostream>
using namespace std;

int main() {
    int answer = 0, row = 0, col = 0;

    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            int num;
            cin >> num;

            if (num > answer) {
                answer = num;
                row = i;
                col = j;
            }
        }
    }

    cout << answer << "\n" << row + 1 << " " << col + 1 << endl;
}