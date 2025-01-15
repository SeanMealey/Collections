public class MinHeap {
    private int capacity;
    private int size;
    private Record[] minHeap;

    public MinHeap() {
        this.capacity = 512 * 8;
        this.minHeap = new Record[capacity];
        this.size = 0;
    }
    public boolean isEmpty(){
        if(size<=0){
            return true;
        }else{
            return false;
        }
    }


    public int getLeft(int pos) {
        if (pos < 0) {
            return -2;
        }
        else if (pos >= size / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }


    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    public boolean isLeaf(int pos) {
        return (pos >= size / 2) && (pos < size);
    }



    public int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }

    public void insert(Record record) {
        if (size >= capacity) {
            return;
        }
        int cur = size++;
        minHeap[cur] = record;

        while ((cur != 0) && minHeap[cur].compareTo(minHeap[parent(cur)]) < 0) {
            swapNodes(cur, parent(cur));
            cur = parent(cur);
        }
    }

    public Record getRoot() {
        return minHeap[0];
    }

    private void update(int pos) {
        while ((pos > 0) && minHeap[pos].compareTo(minHeap[parent(pos)]) < 0) {
            swapNodes(pos, parent(pos));
            pos = parent(pos);
        }
        if (size != 0) {
            siftDown(pos);
        }
    }

    public void modify(int pos, Record newVale) {
        if ((pos < 0) || (pos >= size)) {
            return;
        }
        minHeap[pos] = newVale;
        update(pos);
    }

    public void rSortMod(int pos, Record newVale) {
        minHeap[pos] = newVale;
    }

    public Record removeMin() {
        if (size == 0) {
            return null;
        }

        swapNodes(0, --size);

        if (size != 0) {
            siftDown(0);
        }

        return minHeap[size];
    }

    private void siftDown(int pos) {
        if ((pos < 0) || (pos >= size)) {
            return;
        }
        while (!isLeaf(pos)) {
            int j = getLeft(pos);

            if ((j < (size - 1))
                    && (minHeap[j].compareTo(minHeap[j + 1]) > 0)) {
                j++;
            }
            if (minHeap[j].compareTo(minHeap[pos]) >= 0) {
                return;
            }
            swapNodes(pos, j);
            pos = j;
        }
    }

    private void swapNodes(int first, int second) {
        Record temp = minHeap[first];
        minHeap[first] = minHeap[second];
        minHeap[second] = temp;
    }
}