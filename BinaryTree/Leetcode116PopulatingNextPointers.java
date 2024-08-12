package BinaryTree;

public class Leetcode116PopulatingNextPointers {

    /**
     * Perform BFS and at every iteration in BFS, Point previous node's next to current node. Initially the previous node is null.
     *
     * */


    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public Node connect(Node root) {
        if(root == null) return root;
        // BFS

        Queue<Node> que = new LinkedList<>();

        que.offer(root);

        while(!que.isEmpty()){
            int n = que.size();
            Node prev = null;

            for(int i=0;i<n;i++){
                Node node = que.poll();
                if(node.left!=null){
                    que.offer(node.left);
                }
                if(node.right!=null){
                    que.offer(node.right);
                }
                if(prev==null){
                    prev = node;
                } else {
                    prev.next = node;
                    prev = node;
                }
            }
        }

        return root;
    }
}
