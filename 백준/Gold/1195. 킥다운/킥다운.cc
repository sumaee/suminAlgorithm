#include <iostream>
using namespace std;

int move(string first, string second) {
    int answer = first.length() + second.length();

    for (int i = 0; i < second.length(); i++) {
        bool flag = true;
        for (int j = 0; j <= min(first.length(), second.length() - i); j++) {
            if ((first[j] - '0') + (second[j + i] - '0') > 3) {
                flag = false;
                break;
            }
        }

        if (flag) {
            answer = min(answer, (int) max(first.length() + i, second.length()));
        }
    }
    return answer;
}


int main() {
    string first, second;
    cin >> first >> second;

    cout << min(move(first, second), move(second, first)) << endl;
    return 0;
}