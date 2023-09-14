class AVLNode {
	int element;
    AVLNode leftNode;
    AVLNode rightNode;
    int height;

    //constructor
    public AVLNode() {
        this.element = 0;
        this.leftNode = null;
        this.rightNode = null;
        this.height = 0;
    }
    
    //constructor
    public AVLNode(int element) {
        this.element = element;
        this.leftNode = null;
        this.rightNode = null;
        this.height = 1;
    }

    //constructor
    public AVLNode(int element, AVLNode leftNode, AVLNode rightNode) {
        this.element = element;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.height = 1 + Math.max(getHeight(leftNode), getHeight(rightNode));
    }

    // Getters and setters
    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public AVLNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(AVLNode leftNode) {
        this.leftNode = leftNode;
    }

    public AVLNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(AVLNode rightNode) {
        this.rightNode = rightNode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Insert a node in AVL tree
    public AVLNode insertNode(int element) {
        if (element < this.element) {
            if (leftNode == null) {
                leftNode = new AVLNode(element);
            } else {
                leftNode = leftNode.insertNode(element);
            }
        } else if (element > this.element) {
            if (rightNode == null) {
                rightNode = new AVLNode(element);
            } else {
                rightNode = rightNode.insertNode(element);
            }
        }
        return balance();
    }

    // Get height of a node
    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.getHeight();
    }

    // Get balance factor of a node
    private int getBalance() {
        return getHeight(rightNode) - getHeight(leftNode);
    }

    // Balance a node
    private AVLNode balance() {
        int balanceFactor = getBalance();
        if (balanceFactor > 1) {
            if (getHeight(rightNode.getRightNode()) > getHeight(rightNode.getLeftNode())) {
                return leftRotate();
            } else {
                rightNode = rightNode.rightRotate();
                return leftRotate();
            }
        } else if (balanceFactor < -1) {
            if (getHeight(leftNode.getLeftNode()) > getHeight(leftNode.getRightNode())) {
                return rightRotate();
            } else {
                leftNode = leftNode.leftRotate();
                return rightRotate();
            }
        }
        return this;
    }

    //rotates the tree to the right
    private AVLNode rightRotate() {
        AVLNode newRoot = leftNode;
        leftNode = newRoot.rightNode;
        newRoot.rightNode = this;
        updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    //rotates the tree to the left
    private AVLNode leftRotate() {
        AVLNode newRoot = rightNode;
        rightNode = newRoot.leftNode;
        newRoot.leftNode = this;
        updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    //double rotation
    private AVLNode rightLeftRotate() {
        rightNode = rightNode.rightRotate();
        return leftRotate();
    }

    //double rotation
    private AVLNode leftRightRotate() {
        leftNode = leftNode.leftRotate();
        return rightRotate();
    }

    //balances the tree
    public int balance1() {
        int rightHeight = (rightNode == null) ? 0 : rightNode.height;
        int leftHeight = (leftNode == null) ? 0 : leftNode.height;
        return rightHeight - leftHeight;
    }

    //checks if the tree is balanced
    public boolean isBalanced() {
        int balance = balance1();
        return balance == 0 || balance == 1 || balance == -1;
    }

    //rebalances if it is not balanced
    private void rebalance() {
        updateHeight();
        int balance = balance1();

        if (balance == -2) {
            if (leftNode.balance1() <= 0) {
                rightRotate();
            } else {
                leftRightRotate();
            }
        } else if (balance == 2) {
            if (rightNode.balance1() >= 0) {
                leftRotate();
            } else {
                rightLeftRotate();
            }
        }
    }

    //updates the height of the tree
    private void updateHeight() {
        int leftHeight = (leftNode == null) ? 0 : leftNode.height;
        int rightHeight = (rightNode == null) ? 0 : rightNode.height;
        height = Math.max(leftHeight, rightHeight) + 1;
    }

    //main method
    public static void main(String[] args) {
        AVLNode firstTree = new AVLNode();
        AVLNode secondTree = new AVLNode();
        firstTree.insertNode(27);
        firstTree.insertNode(20);
        firstTree.insertNode(25);
        firstTree.insertNode(15);
        firstTree.insertNode(8);
        firstTree.insertNode(7);
        firstTree.insertNode(4);
        firstTree.insertNode(5);
        firstTree.insertNode(2);
        secondTree.insertNode(20);
        secondTree.insertNode(19);
        secondTree.insertNode(15);
        secondTree.insertNode(16);
        secondTree.insertNode(27);
        secondTree.insertNode(29);
        secondTree.insertNode(28);
        secondTree.insertNode(4);
        secondTree.insertNode(5);
        secondTree.insertNode(2);
        secondTree.insertNode(1);

        System.out.println("First tree is balanced? " + firstTree.isBalanced());
        System.out.println("Second tree is balanced? " + secondTree.isBalanced());
    }
}
