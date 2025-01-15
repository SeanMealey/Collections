
public class HashObject {

    //Possibly needs work

    //Essentially the Record Class
    //Passes ID and Sequence to DNAdbase
    //Complete all of these constructors

    MemoryHandleHolder memID;
    MemoryHandleHolder memSeq;
    int skip;
    Boolean tombstone;


    //Constructor
    public HashObject(MemoryHandleHolder memID, MemoryHandleHolder memSeq, int counter) {
        this.memID = memID;
        this.memSeq = memSeq;
        skip = counter;
        tombstone = false;
    }

    //Constructor
    public HashObject(MemoryHandleHolder memID, MemoryHandleHolder memSeq) {
        this.memID = memID;
        this.memSeq = memSeq;
        skip = 0;
        tombstone = false;
    }

    //Getter and setter methods

    public MemoryHandleHolder getFull() {
        return memSeq;
    }

    public int getSkip() {
        return skip;
    }

    public boolean getTombstone() {
        return tombstone;
    }

    public MemoryHandleHolder getId() {
        return memID;
    }

    public void setTombstone(Boolean bool){
        tombstone = bool;
    }
}
