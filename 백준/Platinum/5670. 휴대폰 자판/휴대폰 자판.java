import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            try {
                TrieNode trieNode = new TrieNode();
                int n = Integer.parseInt(line);
                String[] strArr = new String[n];
                for (int i = 0; i < n; i++) {
                    line = br.readLine();
                    strArr[i] = line;
                    trieNode.insert(line);
                }

                int sum = 0;
                for (String str : strArr) {
                    sum += trieNode.search(str);
                }

                double answer = Math.round((double) sum / strArr.length * 100) / 100.00;
                sb.append(String.format("%.2f", answer)).append("\n");
            } catch (NumberFormatException e) {
                break;
            }
        }
        System.out.println(sb);
    }

    static class Node {
        Map<Character, Node> child;
        boolean isEnd;

        public Node() {
            child = new HashMap<>();
            isEnd = false;
        }
    }

    static class TrieNode {
        Node root;

        public TrieNode() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node node = root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }

            node.isEnd = true;
        }

        public int search(String word) {
            Node node = root;
            int cnt = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (i == 0 || node.isEnd || node.child.size() > 1) {
                    cnt++;
                }

                node = node.child.get(c);
            }

            return cnt;
        }
    }
}
