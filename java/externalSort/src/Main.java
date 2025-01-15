import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main(args[0]);
    }

    private Block input;
    private Block output;
    private MinHeap minHeap;
    private Parser parser;
    private Record removed;
    private String fileName;
    private int sout = 0;
    private RandomAccessFile file;

    public Main(String fileName) throws IOException {
        this.fileName = fileName;
        this.sout = 0;
        this.removed = null;
        parser = new Parser(fileName);
        minHeap = new MinHeap();
        int iterations = 0;

        while ((minHeap.getSize() < 512 * 8) && !parser.getEOF()) {
            byte[] byteArray = parser.getBlock();
            if (byteArray != null) {
                byte[] id;
                byte[] value;
                int byteCount = 0;

                while (byteCount < parser.getCurrBytes()) {
                    id = new byte[8];
                    value = new byte[8];

                    for (int i = 0; i < 8; i++) {
                        id[i] = byteArray[byteCount];
                        byteCount++;
                    }
                    for (int i = 0; i < 8; i++) {
                        value[i] = byteArray[byteCount];
                        byteCount++;
                    }
                    minHeap.insert(new Record(id, value));
                }
            }
        }

        if (!parser.getEOF()) {
            byte[] temp = parser.getBlock();
            input = new Block(temp, parser.getCurrBytes());
        }
        else {
            input = new Block();
        }
        output = new Block();


        file = new RandomAccessFile(fileName, "rw");
        int nDead = 0;
        boolean rFlag = false;

        while (minHeap.getSize() > 0) {
            if (input.getSize() == 0 && parser.getEOF()) {
                if (!rFlag) {
                    minHeap.setSize(nDead + minHeap.getSize());
                    nDead = 0;
                }
                while (minHeap.getSize() > 0) {
                    if (output.getSize() < 8192 ) {
                        Record removedRec = minHeap.getRoot();
                        this.removed = removedRec;
//                        printRecord(output.getSize(), removedRec);
                        output.insert(minHeap.removeMin().getRecord());
                    }
                    else {
                        file.write(output.clear());
                        Record removedRec = minHeap.getRoot();
                        this.removed = removedRec;
//                        printRecord(output.getSize(), removedRec);
                        output.insert(minHeap.removeMin().getRecord());
                    }
                    iterations++;
                }

                file.write(output.clear());
            }
            else {

                Record removedRec = minHeap.getRoot();
                this.removed = removedRec;

                if (output.getSize() < 8192 ) {
//                    printRecord(output.getSize(), removedRec);
                    output.insert(removedRec.getRecord());
                }
                else {
                    file.write(output.clear());
//                    printRecord(output.getSize(), removedRec);
                    output.insert(removedRec.getRecord());
                }

                if (input.getSize() > 0) {
                    byte[] rec = input.remove();
                    byte[] id = new byte[8];
                    byte[] value = new byte[8];

                    for (int i = 0; i < 8; i++) {
                        id[i] = rec[i];
                        value[i] = rec[i + 8];
                    }

                    Record newRec = new Record(id, value);

                    if (newRec.compareTo(removed) < 0) {
                        minHeap.removeMin();
                        minHeap.rSortMod(minHeap.getSize(), newRec);
                        nDead++;
                    }
                    else {
                        minHeap.modify(0, newRec);
                    }

                    if (input.getSize() == 0) {
                        minHeap.setSize(nDead + minHeap.getSize());
                        nDead = 0;

                        byte[] temp = parser.getBlock();
                        if (temp != null) {
                            input.setArray(temp, parser.getCurrBytes());
                        }
                        else {
                            rFlag = true;
                        }
                    }
                }
            }
        }
        //file.close();

        file.seek(0);
        for (int i = 0; i < iterations ; i++) {
            //System.out.print(fos.readLong());
            file.readLong();
            System.out.print(" " + file.readDouble() + " ");
            if (i % 5 == 0) {
                System.out.println("");
            }
        }
    }



    private void printRecord(int i, Record r) {
        ByteBuffer idTemp = ByteBuffer.wrap(r.getID());
        ByteBuffer valueTemp = ByteBuffer.wrap(r.getValue());
        long id = idTemp.getLong();
        double value = valueTemp.getDouble();

        if (i % 8192 == 0) {
            if (++sout % 5 == 0) {
                System.out.println(id + " " + value);
            }
            else {
                System.out.print(id + " " + value + " ");
            }
        }
    }
    
}