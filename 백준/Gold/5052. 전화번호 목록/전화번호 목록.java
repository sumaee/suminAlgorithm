import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCases = Integer.parseInt(br.readLine());
        nxt:
        while (testCases-- > 0) {
            int n = Integer.parseInt(br.readLine());
            List<String> words = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                words.add(br.readLine());
            }

            words.sort(Comparator.comparingInt(String::length));

            TrieNode trieNode = new TrieNode();
            for (String word : words) {
                if (!trieNode.search(word)) {
                    sb.append("NO").append("\n");
                    continue nxt;
                }
                trieNode.insert(word);
            }

            sb.append("YES").append("\n");
        }

        System.out.println(sb);
    }

    static class Node {
        Map<Character, Node> child;
        boolean isEnd;

        public Node() {
            this.child = new HashMap<>();
            this.isEnd = false;
        }
    }

    static class TrieNode {
        Node root;

        public TrieNode() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node node = this.root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }

            node.isEnd = true;
        }

        public boolean search(String word) {
            Node node = this.root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (node.child.containsKey(c) && node.child.get(c).isEnd) {
                    return false;
                }

                if (!node.child.containsKey(c)) {
                    return true;
                } else {
                    node = node.child.get(c);
                }
            }

            return node.isEnd;
        }
    }
}
