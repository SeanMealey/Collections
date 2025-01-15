import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class Parser {
    private RandomAccessFile raf;
    private Boolean endFile;
    private int totalBytes;
    private int currBytes;

    public Parser(String fileName) throws IOException {
        try {
            this.raf = new RandomAccessFile(fileName, "r");
            this.endFile = false;
            this.totalBytes = 0;
        }
        catch (FileNotFoundException e) {
            return;
        }
    }


    public byte[] getBlock() throws IOException {

        ByteBuffer block = ByteBuffer.allocate(8192);
        this.currBytes = 0;

        try {
            for (int i = 0; i < 8192; i++) {
                byte b = raf.readByte();
                this.totalBytes++;
                currBytes++;
                block.put(b);
            }
            byte[] barr = block.array();
            return barr;
        }
        catch (EOFException e) {

            this.endFile = true;
            this.raf.close();
            byte[] barr = block.array();

            if (this.currBytes == 0) {
                return null;
            }
            else {
                return barr;
            }
        }
    }
    public boolean getEOF() {
        return this.endFile;
    }
    public int getCurrBytes() {
        return this.currBytes;
    }
}

