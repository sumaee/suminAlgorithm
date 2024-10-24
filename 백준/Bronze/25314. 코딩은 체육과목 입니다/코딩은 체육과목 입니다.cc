#include <iostream>
using namespace std;

int main() {
    int n;
    cin >> n;

    string str;
    for (int i = 0; i < n / 4; i++) {
        str += "long ";
    }

    cout << str << "int" << endl;
    return 0;
}