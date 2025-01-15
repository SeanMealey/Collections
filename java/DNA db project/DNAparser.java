
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DNAparser {

    //COMPLETE

    private MemoryManager mem;
    private File input;

    public DNAparser (File myFile, int size, String output) throws IOException{
        input = myFile;
        mem = new MemoryManager(output, size);
    }

//      This does not work at all
//    nodes = new Node[hashSize];
//
//    //Hash map for storing nodes
//    hash = new HashFunction<String, Node> (hash);
//
//    //Memory manager
//    manager = new MemoryManager(output, hashSize);
//
//    //Cook up the scanner
//
//    File parseFile = new File(input);
//
//    Scanner scanny = new Scanner(parseFile);

    public MemoryManager getMemory(){return mem;}

    public boolean parse() throws IOException {
        Scanner scanny = new Scanner(input);
        while (scanny.hasNextLine()) {
            String line = scanny.nextLine().trim();
            String[] inputInfo = line.split("\\s+");
            if (line.equals("")) {
                continue;
            }

            //            if (inputInfo[0].trim().equals("insert")) {
//                String sequence = scnr.nextLine();
//                HashObject input = memory.insert(inputInfo[1].trim(), sequence, Integer.valueOf(inputInfo[2]));
//
//                if (input == null) {
//                    HashObject searched = memory.searchHash(inputInfo[1].trim());
//
//                    if (searched.getSkip() == 32) {
//                        System.out.println("The Sequence " + inputInfo[1].trim() + " cannot be added");
//                    } else {
//                        System.out.println("The Sequence " + inputInfo[1].trim() + " already exists");
//                    }
//                }
//
//                else if (inputInfo[0].trim().equals("remove")) {
//                    String removed = memory.remove(inputInfo[1].trim());
//                    if (removed == null) {
//                        System.out.println("The Sequence ID " + inputInfo[1].trim() + " is not in  memory");
//                    } else {
//                        System.out.println("Sequence " + inputInfo[1].trim() + " removed");
//                        System.out.println(removed);
//                    }
//
//                }
//                else if (inputInfo[0].trim().equals("print")) {
//                    memory.print();
//                }
//                else if (inputInfo[0].trim().equals("search")) {
//                    String searched = memory.search(inputInfo[1]);
//                    if (searched == null) {
//                        System.out.println("The Sequence ID " + inputInfo[1].trim() + " is not in memory.");
//                    }
//                }
//            }


            // Using switch statement helped fix issues from if-else above

            //Make the switch
            switch (inputInfo[0]) {
                //Remove Case
                case "remove" :
                    String remove = mem.remove(inputInfo[1]);
                    if (remove == null) {
                        System.out.println("The Sequence ID " + inputInfo[1] + " is not in  memory");
                    } else {
                        System.out.println("Sequence Removed " + inputInfo[1] + ":");
                        System.out.println(remove);
                    }
                    break;
                    //insert case
                case "insert":
                    String seq = scanny.nextLine();
                    HashObject input = mem.insert(inputInfo[1], seq, Integer.valueOf(inputInfo[2]));

                    if (input == null) {
                        HashObject searchHash = mem.searchHash(inputInfo[1]);

                        if (searchHash.getSkip() == 32) {
                            System.out.println("The Sequence " + inputInfo[1] + " cannot be added");
                        } else {
                            System.out.println("SequenceID " + inputInfo[1] + " exists");
                        }
                    }
                    break;
                    //Print case easy
                case "print" :
                    mem.print();
                    break;
                    //search case
                case "search" :
                    String searched = mem.search(inputInfo[1]);
                    if (searched == null) {
                        System.out.println("The Sequence ID " + inputInfo[1] + " is not in memory.");
                    }
                    break;
            }
        }
        scanny.close();
        return true;
    }
}
