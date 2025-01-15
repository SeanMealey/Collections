import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.io.*;
import java.io.InputStream;
import java.io.FileInputStream;
import java.awt.Desktop;

//String line = reader.readLine();
//        while(line != null){
//        System.out.println(line);
//        line = reader.readLine();
//        }
public class main2 {
    static String fileName = "src/run.bin";
    public static void main(String[] args) {
        //Opens file and saves data to byte[]
        String filename = "src/run.bin";
        try {
            FileInputStream fis = new FileInputStream(filename);
            byte[] bytes = fis.readAllBytes();
            Record[] records = new Record[8192];

            //turns 131072 into 8172 Records in a record[]
            int recordCount = 0;
            int bytePosition = 0;
            for(int i=0;i<8192;i++){
                byte[] first8 = new byte[8];
                byte[] second8 = new byte[8];
                for (int j = 0; j < 8; j++) {
                    first8[j] = bytes[bytePosition];
                    bytePosition++;
                }
                for (int j = 0; j < 8; j++) {
                    second8[j] = bytes[bytePosition];
                    bytePosition++;
                }
                records[recordCount] = new Record(first8, second8);
                recordCount++;
            }

//            Test println to make sure all records are there
//            for(int i=0;i< records.length;i++){
//                if(records[i]!=null){
//                    System.out.println(records[i].getKeyValue());
//                    System.out.println(i);
//                }
//            }



            //Seperate records into blocks of 512 then sort them
            //Returns block[] of sorted blocks
            Block[] blocks = new Block[16];
            int recordPosition = 0;
            for(int i=0;i< 16;i++){
                Record[] tempRecords = new Record[512];
                for (int j = 0; j < 512; j++) {
                    tempRecords[j] = records[recordPosition];
                    recordPosition++;
                }
                Block newBlock = new Block(tempRecords);
                //newBlock.sortBlock();
                blocks[i] = newBlock;
            }
            //All works but for some reason the blocks are not sorted

//            for(int i=0;i<8;i++){
//                for(int k=0;k<512;k++){
//                    System.out.println(blocks[i].getRecord(k).getKeyValue());
//                }
//            }
            //System.out.println(blocks[7].getRecord(511).getKeyValue());
            MinHeap heap = new MinHeap(4096);
            for(int i=0;i<8;i++){
                for(int k=0;k<512;k++){
                    heap.insert(blocks[i].getRecord(k));
                }
            }
//            heap.heapSort();
//            heap.print();
            ArrayList<File> files = new ArrayList<File>();
            int fileCount = 0;
//            byte[] inputBuffer = new byte[8192];
//            byte[] outputBuffer = new byte[8192];
//            int inputSize = 8192;
            Record[] inputBuffer = blocks[8].getArray();
            int blockCount = 9;
            Record[] outputBuffer = new Record[512];
            int inputSize = 511;
            int inputCount = 0;
            int outputCount = 0;
            int byteCount = 65536; //half of all bytes are left
            outputBuffer[outputCount] = heap.remove();
            int runLength = 0;
            while(blockCount<15) {
                while (inputSize > 0) {
                    //Replacement selection
                    //returns 4-8 merge files


                    //If the next record in the input buffer is smaller than the last one in output buffer
                    //The record cannot be inserted in the heap so it is moved to the end
                    if(outputCount > 0 && inputBuffer[inputCount].getKeyValue()<outputBuffer[outputCount-1].getKeyValue()){
                        //To small for the run move to end of heap array
                        heap.moveToEnd(inputBuffer[inputCount]);
                        inputBuffer[inputCount] = new Record();
                        inputSize--;
                        byteCount -= 16;
                    }else{
                        heap.insert(inputBuffer[inputCount]);
                        inputBuffer[inputCount] = new Record();
                        inputCount++;
                        inputSize--;
                        byteCount -= 16;

                        outputBuffer[outputCount] = heap.remove();
                        outputCount++;
                        //This bubble sorts output buffer every time which is unnecessary since we insert 1 element at a time
                        sortInBuffer(outputBuffer);
                        runLength++;
                    }
                    //for one block
//                    if(heap.isEmpty()){
//                        break;//idk dont ask
//                    }
                }

                //Writes output buffer to run file
                //Saves all the files in an arrayList of files
                    File run = new File("run.txt");
                    FileWriter runStream = new FileWriter(run);
                    for(int i=0;i<runLength;i++){
                        if(outputBuffer[i]!=null){
                            runStream.write(outputBuffer[i].getId() + " " + outputBuffer[i].getKeyValue());
                        }
                    }
                    files.add(run);
                    fileCount++;



                    System.out.println(outputBuffer.length);

                    outputBuffer = new Record[512];

                //Resets input for input buffer for next iteration
                blockCount++;
                inputBuffer = blocks[blockCount].getArray();

            }
            int totalCount = 0;
            for(int i=0;i<files.size();i++){
                for(int k=0;k<512;k++){
                    System.out.println(blocks[i].getRecord(k).getKeyValue());
                    totalCount++;
                }
            }
            System.out.println(totalCount);

            //MERGE ALL FILES
            //MERGE ALL FILES
            //MERGE ALL FILES
            //MERGE ALL FILES

            //TEST
            Desktop desktop = Desktop.getDesktop();
            if(files.size() > 0 && files.get(0).exists()){
                desktop.open(files.get(0));
            }

            //TEST

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //OPTIMIZE THIS
    public static void sortInBuffer(Record[] records){
        for(int i=0;i<records.length;i++){
            for(int j=1;j<records.length - i;j++){
                if(records[j]!=null) {
                    if (records[j].getKeyValue() < records[j - 1].getKeyValue()) {
                        Record temp = records[j];
                        records[j] = records[j - 1];
                        records[j - 1] = temp;
                    }
                }
            }
        }
    }
    private static byte[] read(String file, int position, int size) throws IOException{
        RandomAccessFile file1 = new RandomAccessFile(file,"r");

        file1.seek(position);
        byte[] readBytes = new byte[size];
        file1.read(readBytes);
        file1.close();
        return readBytes;
    }
}

/*
Class Record/byteBuffer
Need java.io.randomaccess to work on bin file
Store data as a byte[]
Can use byte buffer
or use a record class with self defined methods

Class heap
Need a generic min heap

Class manager
A. Replacement selection
B. Multi-merg
    input is run file
    output is mergeOutputFile

Class externalSort
Main class for everything to be read from

Files: input
        Run
        MergeOutputFile

512 records in a block
8 blocks in the heap ? ish


take in binary bytes store them in byte[]
from byte

//Bin file input
//byte[]
//byte[]/8 = way more byte arrays
//For all byte[]/8: new Record(byte[] first8, byte[] second8)
//sort block


 */
//        try {
//            System.out.println(new String(read(fileName,0,10)));
//            byte[] bytes = read(fileName,0,10);
//            Record[] records = new Record[512];
//            for(int i=0;i< bytes.length;i++){
//                byte[] first8 = new byte[8];
//                byte[] second8 = new byte[8];
//                records[i] = new Record(first8, second8);
//            }
//            for(int i=0;i< records.length;i++){
//                System.out.println(records[i].getId());
//            }
//        }
//        catch(IOException e){
//            System.out.println("Didn't work");
//            e.printStackTrace();
//        }

//Stores all of the byte[] into a Record[]
//            int recordCount = 0;
//            for(int i =0;i < bytes.length;i++){
//                byte[] first8 = new byte[8];
//                byte[] second8 = new byte[8];
//                if(i+16<bytes.length) {
//                    for (int j = 0; j < 8; j++) {
//                        first8[j] = bytes[i];
//                        i++;
//                    }
//                    for (int j = 0; j < 8; j++) {
//                        second8[j] = bytes[i];
//                        i++;
//                    }
//                }
//                records[recordCount] = new Record(first8, second8);
//                recordCount++;
//            }