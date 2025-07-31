import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Set<String> nickName;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        Trie colorTrie = new Trie();
        for (int i = 0; i < c; i++) {
            colorTrie.insert(br.readLine());
        }

        nickName = new HashSet<>();
        for (int i = 0; i < n; i++) {
            nickName.add(br.readLine());
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            String teamName = br.readLine();
            if (colorTrie.search(teamName)) {
                sb.append("Yes");
            } else {
                sb.append("No");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static class Node {
        //        Map<Character, Node> child;
        Node[] child;
        boolean isEnd;

        public Node() {
//            this.child = new HashMap<>();
            this.child = new Node[26];
            this.isEnd = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node node = this.root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.child[c - 'a'] == null) {
                    node.child[c - 'a'] = new Node();
                }
                node = node.child[c - 'a'];
//                node.child.putIfAbsent(c, new Node());
//                node = node.child.get(c);
            }

            node.isEnd = true;
        }

        public boolean search(String word) {
            Node node = this.root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.child[c - 'a'] == null) {
                    return false;
                }
                node = node.child[c - 'a'];
//                if (!node.child.containsKey(c)) {
//                    return false;
//                }
//                node = node.child.get(c);

                if (node.isEnd && nickName.contains(word.substring(i + 1))) {
                    return true;
                }
            }

            return false;
        }
    }
}
