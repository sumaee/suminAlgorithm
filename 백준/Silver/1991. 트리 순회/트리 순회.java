import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Tree tree = new Tree();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String root = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            tree.makeTree(root, left, right);
        }

        //전위 순회
        tree.pre(tree.node);
        sb.append("\n");
        //중위 순회
        tree.in(tree.node);
        sb.append("\n");
        //후위 순회
        tree.post(tree.node);

        System.out.println(sb);
    }

    static class Tree {
        Node node;

        //root가 없으면 입력받은 것을 root로 지정
        public void makeTree(String data, String left, String right) {
            if (node == null) {
                if (!data.equals(".")) {
                    node = new Node(data);
                }

                if (!left.equals(".")) {
                    node.left = new Node(left);
                }

                if (!right.equals(".")) {
                    node.right = new Node(right);
                }
            } else {
                findRoot(node, data, left, right);
            }
        }

        public void findRoot(Node node, String data, String left, String right) {
            if (node == null) {
                return;
            }

            if (node.data.equals(data)) {

                if (!left.equals(".")) {
                    node.left = new Node(left);
                }
                if (!right.equals(".")) {
                    node.right = new Node(right);
                }
            } else {
                findRoot(node.left, data, left, right);
                findRoot(node.right, data, left, right);
            }
        }

        public void pre(Node root) {
            sb.append(root.data);
            if (root.left != null) {
                pre(root.left);
            }
            if (root.right != null) {
                pre(root.right);
            }
        }

        public void in(Node root) {
            if (root.left != null) {
                in(root.left);
            }
            sb.append(root.data);
            if (root.right != null) {
                in(root.right);
            }
        }

        public void post(Node root) {
            if (root.left != null) {
                post(root.left);
            }
            if (root.right != null) {
                post(root.right);
            }
            sb.append(root.data);
        }
    }


    static class Node {
        String data;
        Node left, right;

        public Node(String data) {
            this.data = data;
        }
    }
}
