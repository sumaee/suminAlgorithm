#include <iostream>
#include <vector>
using namespace std;

struct Chess {
    int row, col, dir;
};

int n, k;
int board[12][12];
Chess chess[10];
vector<int> boardTmp[12][12];
int dr[] = {0, 0, -1, 1};
int dc[] = {1, -1, 0, 0};

int findCurrLocate(int row, int col, int idx) {
    for (int i = 0; i < boardTmp[row][col].size(); i++) {
        if (boardTmp[row][col][i] == idx) {
            return i;
        }
    }
}

int reverse(int idx) {
    int dir = chess[idx].dir;
    if (dir == 0) return 1;
    if (dir == 1) return 0;
    if (dir == 2) return 3;
    if (dir == 3) return 2;
}

int findDeleteIdx(int row, int col, int chessIdx) {
    int idx = 0;
    for (int i = boardTmp[row][col].size() - 1; i >= 0; i--) {
        if (boardTmp[row][col][i] == chessIdx) break;
        idx++;
    }

    return idx + 1;
}

void moveChess(int row, int col, int drow, int dcol, int chessIdx, int currIdx, int color) {
    if (color == 0) {
        for (int i = currIdx; i < boardTmp[row][col].size(); i++) {
            boardTmp[drow][dcol].push_back(boardTmp[row][col][i]);
            chess[boardTmp[row][col][i]].row = drow;
            chess[boardTmp[row][col][i]].col = dcol;
        }

        int deleteIdx = findDeleteIdx(row, col, chessIdx);
        for (int i = 0; i < deleteIdx; i++) {
            boardTmp[row][col].pop_back();
        }
    } else if (color == 1) {
        for (int i = boardTmp[row][col].size() - 1; i >= currIdx; i--) {
            boardTmp[drow][dcol].push_back(boardTmp[row][col][i]);
            chess[boardTmp[row][col][i]].row = drow;
            chess[boardTmp[row][col][i]].col = dcol;
        }

        int deleteIdx = findDeleteIdx(row, col, chessIdx);
        for (int i = 0; i < deleteIdx; i++) {
            boardTmp[row][col].pop_back();
        }
    } else {
        int dir = reverse(chessIdx);
        chess[chessIdx].dir = dir;

        int ddrow = row + dr[dir];
        int ddcol = col + dc[dir];

        if (ddrow >= 0 && ddcol >= 0 && ddrow < n && ddcol < n && board[ddrow][ddcol] != 2) {
            moveChess(row, col, ddrow, ddcol, chessIdx, currIdx, board[ddrow][ddcol]);
        }
    }
}

bool check() {
    for (int i = 0; i < k; i++) {
        if (boardTmp[chess[i].row][chess[i].col].size() >= 4) return true;
    }
    return false;
}

int main() {
    cin >> n >> k;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> board[i][j];
        }
    }

    for (int i = 0; i < k; i++) {
        int row, col, dir;
        cin >> row >> col >> dir;
        row--;
        col--;
        dir--;
        chess[i] = {row, col, dir};
        boardTmp[row][col].push_back(i);
    }

    int cnt = 0;

    while (cnt <= 1000) {
        cnt++;
        for (int i = 0; i < k; i++) {
            //현재 말 위치 반환
            int currIdx = findCurrLocate(chess[i].row, chess[i].col, i);

            int drow = chess[i].row + dr[chess[i].dir];
            int dcol = chess[i].col + dc[chess[i].dir];

            //범위를 벗어난다면 파란색 움직임
            if (drow < 0 || dcol < 0 || drow >= n || dcol >= n) {
                moveChess(chess[i].row, chess[i].col, drow, dcol, i, currIdx, 2);
            }
            //움직일 수 있다면 움직이기
            else {
                moveChess(chess[i].row, chess[i].col, drow, dcol, i, currIdx, board[drow][dcol]);
            }

            if (check()) {
                cout << cnt << endl;
                return 0;
            }
        }
    }

    cout << -1 << endl;
    return 0;
}