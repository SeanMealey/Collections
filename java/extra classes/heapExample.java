public class heapExample {
    // Member variables of this class
    private Record[] Heap;
    private int size;
    private int maxsize;

    // Initializaing front as static with unity
    private static final int FRONT = 1;

    // Constructor of this class
    public heapExample(int maxsize)
    {

        // This keyword refers to current object itself
        this.maxsize = maxsize;
        this.size = 0;

        Heap = new Record[this.maxsize + 1];
        Heap[0] = new Record();
    }

    // Method 1
    // Returning the position of
    // the parent for the node currently
    // at pos
    private int parent(int pos) { return pos / 2; }

    // Method 2
    // Returning the position of the
    // left child for the node currently at pos
    private int leftChild(int pos) { return (2 * pos); }

    // Method 3
    // Returning the position of
    // the right child for the node currently
    // at pos
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    // Method 4
    // Returning true if the passed
    // node is a leaf node
    private boolean isLeaf(int pos)
    {

        if (pos > (size / 2) && pos <= size) {
            return true;
        }

        return false;
    }

    // Method 5
    // To swap two nodes of the heap
    private void swap(int fpos, int spos)
    {

        Record tmp = new Record();
        tmp = Heap[fpos];

        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    // Method 6
    // To heapify the node at pos
    private void minHeapify(int pos)
    {

        // If the node is a non-leaf node and greater
        // than any of its child
        if (!isLeaf(pos)) {
            if (Heap[pos].getKeyValue() > Heap[leftChild(pos)].getKeyValue()
                    || Heap[pos].getKeyValue() > Heap[rightChild(pos)].getKeyValue()) {

                // Swap with the left child and heapify
                // the left child
                if (Heap[leftChild(pos)].getKeyValue()
                        < Heap[rightChild(pos)].getKeyValue()) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }

                // Swap with the right child and heapify
                // the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    // Method 7
    // To insert a node into the heap
    public void insert(Record element)
    {

        if (size >= maxsize) {
            return;
        }

        Heap[++size] = element;
        int current = size;

        while (Heap[current].getKeyValue() < Heap[parent(current)].getKeyValue()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Method 8
    // To print the contents of the heap
    public void print()
    {
        for (int i = 0; i < size; i++) {

            // Printing the parent and both childrens
            System.out.println("("+Heap[i].getKeyValue()+":["+i+"]"+"), ");
        }
    }

    // Method 9
    // To remove and return the minimum
    // element from the heap
    public Record remove()
    {

        Record popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);

        return popped;
    }

}
