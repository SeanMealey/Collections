public class Block {
    public Record[] records;

    public Block(Record[] records){
        this.records = records;
    }
    public void sortBlock(){
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
    public Record[] getArray(){
        return records;
    }
    public Record getRecord(int position){
        return records[position];
    }
}
