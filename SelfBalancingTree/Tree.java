class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public void displayTree() {
        if (root == null) {
            System.out.println("\nTree is empty!");
        } else {
            System.out.println("\nAVL Tree:");
            displayTree(root, 0);
            System.out.println();
           }
    }

    private void displayTree(Node node, int level) {
        if (node == null) {
            return;
        }

        displayTree(node.right, level + 1);
        System.out.println();

        if (node == root) {
            System.out.print("Root -> ");
        } else {
            for (int i = 0; i < level; i++) {
                System.out.print("        ");
            }
            System.out.print(" -> ");
        }

        System.out.print(node.data);

        displayTree(node.left, level + 1);
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1) {
            if (data < node.left.data) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (data > node.right.data) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node node, int data) {
        if (node == null) {
            return null;
        }

        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = getMinValueNode(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }

        if (node == null) {
            return null;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    public Node search(int data) {
        return searchNode(root, data, 1);
    }

    private Node searchNode(Node node, int data, int depth) {
        if (node == null) {
            return node;
        }

        if (node.data == data) {
            node.depth = depth;
            return node;
        }

        if (data < node.data) {
            return searchNode(node.left, data, depth + 1);
        } else {
            return searchNode(node.right, data, depth + 1);
        }
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        Node temp = newRoot.right;

        newRoot.right = node;
        node.left = temp;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        Node temp = newRoot.left;

        newRoot.left = node;
        node.right = temp;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node getMinValueNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void displayPostOrder() {
        if (root == null) {
            System.out.println("\nTree is empty!");
        } else {
            System.out.println("\nTree in post-order:");
            displayPostOrder(root);
            System.out.println();
           }
    }

    public void displayPreOrder() {
        if (root == null) {
            System.out.println("\nTree is empty!");
        } else {
            System.out.println("\nTree in pre-order:");
            displayPreOrder(root);
            System.out.println();
           }
    }

    private void displayPreOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");
        displayPreOrder(node.left);
        displayPreOrder(node.right);
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("\nTree is empty!");
        } else {
            System.out.println("\nTree in in-order:");
            displayInOrder(root);
            System.out.println();
           }
    }

    private void displayInOrder(Node node) {
        if (node == null) {
            return;
        }

        displayInOrder(node.left);
        System.out.print(node.data + " ");
        displayInOrder(node.right);
    }

    private void displayPostOrder(Node node) {
        if (node == null) {
            return;
        }

        displayPostOrder(node.left);
        displayPostOrder(node.right);
        System.out.print(node.data + " ");
    }
}