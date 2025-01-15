public class Block {
    private byte[] blockArray;
    private static final int BLOCK_LENGTH = 8192;
    private int size;
    private int index;

    public Block() {
        this.size = 0;
        blockArray = new byte[BLOCK_LENGTH];
    }

    public Block(byte[] buffer, int length) {
        this.size = 0;

        if (buffer != null && length <= BLOCK_LENGTH) {
            blockArray = new byte[BLOCK_LENGTH];
            setArray(buffer, length);
            this.index = 0;
        }
        else {
            this.blockArray = null;
        }
    }

    public void setArray(byte[] buff, int length) {
        if (length <= BLOCK_LENGTH) {
            for (int i = 0; i < length; i++) {
                blockArray[i] = buff[i];
            }
            size = length;
            index = 0;
        }
    }

    public int getSize() {
        return this.size;
    }

    public byte[] remove() {
        if (size == 0) {
            return null;
        }

        byte[] temp = new byte[16];
        int j = 0;

        for (int i = index; i < index + 16; i++) {
            temp[j] = blockArray[i];
            blockArray[i] = 0;
            j++;

        }

        index = index + 16;
        size = size - 16;
        return temp;
    }

    public int insert(byte[] bytes) {
        int x = 0;
        if (bytes.length + size <= BLOCK_LENGTH) {
            for (int i = size; i < size + 16; i++) {
                blockArray[i] = bytes[x];
                x++;
            }
            size = size + bytes.length;
            return size;
        }

        return -1;

    }

    public byte[] clear() {

        byte[] temp = new byte[this.size];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.blockArray[i];
        }

        this.blockArray = new byte[BLOCK_LENGTH];
        this.size = 0;
        return temp;
    }

}