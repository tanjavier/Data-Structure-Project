
class Node {
    int data;
    int height;
    int depth;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.height = 1;
        this.depth = 1;
        this.left = null;
        this.right = null;
    }
}