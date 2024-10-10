import java.util.Scanner;

class Node {
    int value;
    Node left, right;

    public Node(int item) {
        value = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    // Constructor to initialize the BST
    public BinarySearchTree() {
        root = null;
    }

    // Method to insert a new node into the BST
    void insert(int value) {
        root = insertRec(root, value);
    }

    // Recursive method to insert a new value
    Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    // Method to delete a node
    void delete(int value) {
        root = deleteRec(root, value);
    }

    // Recursive method to delete a node
    Node deleteRec(Node root, int value) {
        // Base case: if the tree is empty
        if (root == null) {
            return root;
        }

        // Traverse the tree to find the node to delete
        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            // Case 1: Node has no children (leaf node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node has only one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has two children, find the in-order successor (smallest in the right subtree)
            root.value = minValue(root.right);

            // Delete the in-order successor
            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    // Helper method to find the minimum value node in the right subtree
    int minValue(Node root) {
        int minVal = root.value;
        while (root.left != null) {
            minVal = root.left.value;
            root = root.left;
        }
        return minVal;
    }
    

    
    // InOrder traversal of the BST
    void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }

    // PreOrder traversal of the BST
    void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // PostOrder traversal of the BST
    void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.value + " ");
        }
    }
}

public class BSTApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        int option;
        do {
            System.out.println("Menu:");
            System.out.println("1. Create a binary search tree");
            System.out.println("2. Add a node");
            System.out.println("3. Delete a node");
            System.out.println("4. Print nodes by InOrder");
            System.out.println("5. Print nodes by PreOrder");
            System.out.println("6. Print nodes by PostOrder");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Creating BST with nodes: 1, 2, 3, 4, 5, 6, 7");
                    int[] initialData = {1, 2, 3, 4, 5, 6, 7};
                    for (int value : initialData) {
                        bst.insert(value);
                    }
                    break;
                case 2:
                    System.out.print("Enter value to add: ");
                    int value = scanner.nextInt();
                    bst.insert(value);
                    break;
                case 3:
                    System.out.print("Enter value to delete: ");
                    int valueToDelete = scanner.nextInt();
                    bst.delete(valueToDelete);
                    break;
                case 4:
                    System.out.println("InOrder Traversal:");
                    bst.inOrder();
                    break;
                case 5:
                    System.out.println("PreOrder Traversal:");
                    bst.preOrder();
                    break;
                case 6:
                    System.out.println("PostOrder Traversal:");
                    bst.postOrder();
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 7);

        scanner.close();
    }
}
