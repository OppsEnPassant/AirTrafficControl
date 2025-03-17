class PriorityQueue {
    
    int currentSize;
    Node root;
    Node[] array = new Node[25];

    
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

    //This method sets the currentSize to 0, sets root to null, and resets all elements of the array to null, effectively emptying the heap.
    public void clear() {
        currentSize = 0;
        root = null;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }


    public boolean isEmpty() {
        return currentSize == 0;
    }

    /* While this method adds a new node x to the heap, if the heap is full (i.e., currentSize is equal to the length of the array), it returns false. 
    Otherwise, it adds the new node at the next available position in the array, increments currentSize, and performs heapify up operation to maintain the heap property.
    It then returns true to indicate that the node was successfully added to the heap. Code snippet from an online source. */
    
    public boolean add(Node x) {
        if (currentSize == array.length) {
            return false; 
        }

        array[currentSize] = x;
        currentSize++;

    
        heapifyUp(currentSize - 1);

        return true;
    }

    private void heapifyUp(int index) {
        while (index > 0 && array[index].arrival_time.before(array[parent(index)].arrival_time)) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    
    
    /* This method removes the root node from the heap. It returns null if the heap is empty. 
    Otherwise, it removes the root node by swapping it with the last node, decrements currentSize, and then performs a percolateDown() operation starting from the root to maintain the heap property.
    Then, it returns the removed node. Code snippet from an online source. */
    
    public Node remove() {
        if (isEmpty()) {
            return null;
        }

        Node removedNode = array[0];
        array[0] = array[currentSize - 1];
        array[currentSize - 1] = null;
        currentSize--;

        
        percolateDown(0);

        return removedNode;
    }

    private void percolateDown(int hole) {
        int child;
        Node tmp = array[hole];
        while (hole * 2 + 1 < currentSize) {
            child = hole * 2 + 1;
            if (child != currentSize - 1 && array[child + 1].arrival_time.before(array[child].arrival_time))
                child++;
            if (array[child].arrival_time.before(tmp.arrival_time))
                array[hole] = array[child];
            else
                break;
            hole = child;
        }
        array[hole] = tmp;
    }

    
    private int parent(int i) {
        return (i - 1) / 2;
    }

    
    private void swap(int i, int j) {
        Node temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
