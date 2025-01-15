
public class HashFunction {
    HashObject[] table;

    public HashFunction (int size){
        table = new HashObject[size];
    }

    //SFOLD method
    //Provided for us
    //Gonna need this for the other methods

    public long sfold(String s, int M) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        char c[] = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }
        sum = (sum * sum) >> 8;
        return(Math.abs(sum) % M);
    }

    //Remove object from hash table
    public HashObject remove(String id, int skip){


//        while(table[index]!=null){

        if (skip > 31) {
            return null;
        }

        int index = (int) sfold(id, table.length);
//        long hashKey = sfold(id, table.length);
//        int index = (int)hashKey;

        int empty = index;
        int bucket = index / 32;

        if (bucket == 0) {
            empty = bucket;
        }

        for (int i = 0; i < 32; i++) {
            HashObject hasher = table[index];
            if ((index + 1) % 32 ==0) {
                index =empty;
            } else {
                index++;
            } if (skip == 0){
                if (hasher == null) {
                    return null;
                }
                hasher.setTombstone(true);
                return hasher;
            } if (hasher.getTombstone()){
                continue;
            } if (hasher == null){
                return null;
            }
            skip--;
        }
        return null;
    }

    //Find object in hash table
    public HashObject search (String str, int i) {
        if (i > 31) {
            return null;
        }
        int index = (int)sfold(str, table.length);
        int empty = index;
        int bucket = index / 32;

        if (bucket == 0) {
            empty = bucket;
        }

//        long hashKey = sfold(str, table.length);
//        int index = (int)hashKey;

        for (int j=0; j <32; j++) {
            HashObject hasher = table[index];
            if ((index + 1) % 32 ==0) {
                index = empty;
            } else {
                index++;
            } if (i == 0) {
                return hasher;
            } if (hasher.getTombstone()) {
                continue;
            } if (hasher == null){
                return null;
            }
            i--;
        }
        return null;
    }

//    public long probe(String s, int i) {
//        return (sfold(s,i) + i) % table.length;
//    }

    //Insert dna sequence and id into hash table
    public int insert(String id, HashObject hashObjectNew){
        int index = (int)sfold(id, table.length);

//        long hashKey = sfold(id, table.length);
//        int index = (int)hashKey;

        int empty = index;
        int bucket = index / 32;

        if (bucket == 0){
            empty = bucket;
        }

//        if(table[index]==null){
//            table[index] = hashObjectNew;
//        }else{
//            while(table[index]!=null){
//                index = (index + 1) % table.length;
//            }
//            table[index] = hashObjectNew;
//        }

        for (int i =0; i < 32; i++) {
            if (table[index] == null || table[index].getTombstone()) {
                table[index] = hashObjectNew;
                return index;
            }
            if ((index +1) % 32 == 0) {
                index = empty;
            }
            else {
                index++;
            }
        }
        return -1;
    }

    public HashObject[] print() {
        return table;
    }
}