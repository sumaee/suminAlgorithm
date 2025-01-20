import java.lang.*;
import java.util.*;
class Solution {
    static Shell[] shells;
    public String[] solution(String[] commands) {
        shells = new Shell[2500];
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < 2500; i++) {
            shells[i] = new Shell(i, null);
        }

        StringTokenizer st;
        for (int i = 0; i < commands.length; i++) {
            st = new StringTokenizer(commands[i]);

            String command = st.nextToken();
            int r, c;
            if (command.equals("UPDATE")) {
                String v1 = st.nextToken();
                String v2 = st.nextToken();

                if (st.hasMoreTokens()) {
                    String v3 = st.nextToken();
                    r = Integer.parseInt(v1) - 1;
                    c = Integer.parseInt(v2) - 1;

                    shells[find(r * 50 + c)].info = v3;
                } else {
                    for (int j = 0; j < 2500; j++) {
                        if (shells[j].info != null && shells[find(j)].info.equals(v1)) {
                            shells[find(j)].info = v2;
                        }
                    }
                }
            } else if (command.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int c1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;
                int c2 = Integer.parseInt(st.nextToken()) - 1;

                int first = r1 * 50 + c1;
                int second = r2 * 50 + c2;

                if (shells[find(first)].info == null && shells[find(second)].info != null) {
                    int temp = first;
                    first = second;
                    second = temp;
                }

                union(first, second);
            } else if (command.equals("UNMERGE")) {
                r = Integer.parseInt(st.nextToken()) - 1;
                c = Integer.parseInt(st.nextToken()) - 1;

                int root = find(r * 50 + c);
                String value = shells[root].info;

                for (int j = 0; j < 2500; j++) {
                    find(j);
                }

                for (int j = 0; j < 2500; j++) {
                    if (find(j) == root) {
                        shells[j].parent = j;

                        if (j == r * 50 + c) {
                            shells[find(j)].info = value;
                        } else {
                            shells[find(j)].info = null;
                        }
                    }
                }

            } else if (command.equals("PRINT")) {
                r = Integer.parseInt(st.nextToken()) - 1;
                c = Integer.parseInt(st.nextToken()) - 1;

                String value = shells[find(r * 50 + c)].info;
                answer.add(value == null ? "EMPTY" : value);
            }
        }

        return answer.toArray(new String[answer.size()]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }

        shells[b].info = null;
        shells[b].parent = a;
    }


    private static int find(int num) {
        if (shells[num].parent == num) {
            return num;
        }

        return shells[num].parent = find(shells[num].parent);
    }

    static class Shell {
        int parent;
        String info;

        public Shell(int parent, String info) {
            this.parent = parent;
            this.info = info;
        }
    }
}