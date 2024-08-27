import java.util.Scanner;

public class AVLTree {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            try {
                System.out.println("\nAVL Tree Menu:");
                System.out.println("1. Insert Node");
                System.out.println("2. Delete Node");
                System.out.println("3. Search Node");
                System.out.println("4. Display AVL Tree");
                System.out.println("5. Pre-order Traversal");
                System.out.println("6. In-order Traversal");
                System.out.println("7. Post-order Traversal");
                System.out.println("8. Exit");
                System.out.print("Enter your choice (1-8): ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter the value to insert: ");
                        int value = Integer.parseInt(scanner.nextLine());
                        tree.insert(value);
                        System.out.println("\n" + value + " has been successfully inserted");
                        break;
                    case 2:
                        System.out.print("Enter the value to delete: ");
                        value = Integer.parseInt(scanner.nextLine());
                        Node deleteResult = tree.search(value);
                        if (deleteResult != null) {
                            tree.delete(value);
                            System.out.println("\n" + value + " has been successfully deleted");
                        } else {
                            System.out.println("\nNode is non-existent!");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the value to search: ");
                        value = Integer.parseInt(scanner.nextLine());
                        Node searchResult = tree.search(value);
                        if (searchResult != null) {
                            System.out.println("\nSearch Results:");
                            System.out.println("Node found: " + searchResult.data);
                            System.out.println("Node Height: " + (searchResult.height - 1));
                            System.out.println("Node Depth: " + (searchResult.depth - 1));
                            System.out.println("Left Child: " + (searchResult.left != null ? searchResult.left.data : "None"));
                            System.out.println("Right Child: " + (searchResult.right != null ? searchResult.right.data : "None"));
                            } else {
                                System.out.println("\nNode not found!");
                            }
                        break;
                    case 4:
                        tree.displayTree();
                        break;
                    case 5:
                        tree.displayPreOrder();
                        break;
                    case 6:
                        tree.displayInOrder();
                        break;
                    case 7:
                        tree.displayPostOrder();
                        break;
                    case 8:
                        System.out.println("\nExiting...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nInvalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input! Please enter a valid integer.");
            }
        }
    }
}
