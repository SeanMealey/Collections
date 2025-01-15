import java.nio.ByteBuffer;
import java.util.Arrays;


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

        }
        if(keyValue > otherRec.getKeyValue()){
            return 1;
        }else{
            return -1;
        }
    }
    public byte[] getIDBytes(){
        return idArray;
    }
    public byte[] getKeyBytes(){
        return keyArray;
    }
}
