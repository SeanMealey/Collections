public class Pair <TheType extends Comparable<TheType>> {
    private TheType firstVal;
    private TheType secondVal;

    /* Type the code for The constructor here. */
    public Pair(TheType aVal, TheType bVa){
        firstVal = aVal;
        secondVal = bVa;
    }

    public String toString() {
        /* Type your code here. */
        String newString = "[";
        newString = newString + firstVal + ", " + secondVal + "]";
        return newString;
    }

    // Implement Comparable's expected compareTo()
    public int compareTo(Pair<TheType> otherPair) {
        /* Type your code here. */
        int compInt = (otherPair.firstVal + otherPair.secondVal).compareTo(firstVal+secondVal);
        if(compInt = 0){
            return 0;
        }else if(compInt>0){
            return 1;
        }else{
            return -1;
        }
    }

    // Return '<', '=', or '>' according to the ordering of this pair and otherPair
    public char comparisonSymbol(Pair<TheType> otherPair) {
        /* Type your code here. */
        int compInt = compareTo(otherPair);
        if(compInt==-1){
            return '<';
        }else if(compInt==0){
            return '=';
        }else{
            return '>';
        }
    }

}
