import java.util.Scanner;

public class LabProgram {
    public static Pair<Integer> readIntegerPair(Scanner scnr) {
        /* Type your code here. */
        int num1 = scnr.nextInt();
        int num2 = scnr.nextInt();
        Pair<Integer> intPair = new Pair<>(num1,num2);
        return intPair;
    }

    public static Pair<Double> readDoublePair(Scanner scnr) {
        double num1 = scnr.nextDouble();
        double num2 = scnr.nextDouble();
        Pair<Double> intPair = new Pair<>(num1,num2);
        return intPair;
    }

    public static Pair<String> readWordPair(Scanner scnr) {
        /* Type your code here. */
        String num1 = scnr.next();
        String num2 = scnr.next();
        Pair<String> intPair = new Pair<>(num1,num2);
        return intPair;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        Pair<Integer> integerPair1 = readIntegerPair(scnr);
        Pair<Integer> integerPair2 = readIntegerPair(scnr);

        Pair<Double> doublePair1 = readDoublePair(scnr);
        Pair<Double> doublePair2 = readDoublePair(scnr);

        Pair<String> wordPair1 = readWordPair(scnr);
        Pair<String> wordPair2 = readWordPair(scnr);

        System.out.println(integerPair1.toString() + " " + integerPair1.comparisonSymbol(integerPair2) + " " + integerPair2.toString() );
    }
