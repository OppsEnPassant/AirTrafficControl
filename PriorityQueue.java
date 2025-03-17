class PriorityQueue {
    
    int currentSize;
    Node root;
    Node[] array = new Node[25];

    // Initializes currentSize to 0, root to null, and initializes the array with 25 elements, all set to null.
    public PriorityQueue() {
        this.currentSize = 0;
        this.root = null;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    
    public PriorityQueue(int currentSize, Node root, Node[] array) {
        this.currentSize = currentSize;
        this.root = root;
        this.array = array;
    }

    
    public int getCurrentSize() {
        return currentSize;
    }

    public Node getRoot() {
        return root;
    }

    public Node[] getArray() {
        return array;
    }

    
    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setArray(Node[] array) {
        this.array = array;
    }
}
