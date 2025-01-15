
import java.nio.ByteBuffer;


public class Record implements Comparable<Record>{
    private long id;
    private double keyValue;
    private byte[] idArray;
    private byte[] keyArray;

    public Record(){
        id = -1;
        keyValue = -1;
        idArray = new byte[8];
        keyArray = new byte[8];
    }
    public Record(byte[] idArray, byte[] keyArray){
        ByteBuffer idBuffer = ByteBuffer.wrap(idArray);
        id = idBuffer.getLong();
        ByteBuffer keyBuffer = ByteBuffer.wrap(keyArray);
        keyValue = keyBuffer.getDouble();
        this.keyArray = keyArray;
        this.idArray = idArray;
    }
    public long getId(){
        return id;
    }
    public double getKeyValue(){
        return keyValue;
    }

    @Override
    public int compareTo(Record otherRec) {
        if(keyValue == otherRec.getKeyValue()){
            return 0;
        }else if(keyValue > otherRec.getKeyValue()){
            return 1;
        }else if(keyValue < otherRec.getKeyValue()){
            return -1;
        }else{
            return 0;
        }
    }
    public byte[] getID(){
        return idArray;
    }
    public byte[] getValue(){
        return keyArray;
    }
    public byte[] getRecord() {
        byte[] record = new byte[16];

        for (int i = 0; i < 8; i++) {
            record[i] = idArray[i];
            record[i + 8] = keyArray[i];
        }

        return record;
    }
}
