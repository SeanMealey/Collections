/*********************************************************************************************
* @file : AuthoringAssistant.java
* @description : A program that takes a string as input and provides a menu detailing the different actions
* that can be perfomed on it and subsequently does so.
* @author : Sean Mealey
* @date : 4/5/21
* @acknowledgement : none
**********************************************************************************************/

import java.util.Scanner;

public class AuthoringAssistant {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String inputString;

        //User display text
        System.out.println("Enter a sample text:");
        inputString = scnr.nextLine();
        System.out.println("\nYou entered: " + inputString);

        //Calls menu function to display to user
        printMenu();

        //Takes in user choice and then uses while loop to keep repeating if they dont quit
        char menuChoice = scnr.next().charAt(0);

        while (menuChoice != 'q') {

            //If statements used for each option
            //All just basic method output depending on their method type

            if (menuChoice == 'c') {
                int charNum = getNumOfNonWSCharacters(inputString);
                System.out.println("Number of non-whitespace characters: " + charNum);
            }
            else if (menuChoice == 'w') {
                int wordCount = getNumOfWords(inputString);
                System.out.println("Number of words: " + wordCount);
            }
            else if (menuChoice == 'f') {
                System.out.println("Enter a word or phrase to be found: ");
                String findWord = scnr.next();
                System.out.println(findWord);
                int instances = findText(findWord, inputString);
                System.out.println(findWord + " instances " + instances);
            }
            else if (menuChoice == 'r') {
                String noExclam = replaceExclamation(inputString);
                System.out.println("Edited text: " + noExclam);
            }
            else if (menuChoice == 's') {
                String userShorten = shortenSpace(inputString);
                System.out.println("Edited text: " + userShorten);
            }

            printMenu();
            menuChoice = scnr.next().charAt(0);

        }


    }

//Just counts all the non space characters using for loop and if statement
    public static int getNumOfNonWSCharacters(String usrStr) {
        int count = 0;
        for(int i=0; i<usrStr.length(); i++) {
            if (usrStr.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }


//Counts number of words by splitting them into an array by space
    public static int getNumOfWords(String usrStr) {
    usrStr = shortenSpace(usrStr);
    String[] wordList = usrStr.split(" ");
    int count = 0;
    for(int i=0;i<wordList.length;i++) {
        if (wordList[i] != " ") {
            count++;
        }
    }
    return count;


//        int count = 1;
//        for(int i=0; i<usrStr.length(); i++) {
//            if (usrStr.charAt(i) == ' ') {
//                count++;
//            }
//        }
//        return count;
    }

//Splits words into a list and search for ones equal to the input word and increases instances accordingly
    public static int findText( String toFind, String usrStr) {
        String[] wordList = usrStr.split(" ");
        int count = 0;
        for(int i=0; i<wordList.length; i++) {
            if(toFind.equals(wordList[i])){
                count++;
            }
        }
        return count;
    }


//Searches for ! and then uses sub string and concationation to create new strings without them and with . instead
    public static String replaceExclamation(String usrStr) {
        for(int i=0; i<usrStr.length(); i++) {
            if (usrStr.charAt(i) == '!') {
                if (usrStr.charAt(usrStr.length()-1) == '!') {
                    usrStr = usrStr + ".";
                }
                usrStr = usrStr.substring(0,i) + "." + usrStr.substring(i+1,usrStr.length()-1);
            }
        }
        return usrStr;
    }

//Very similar to replace exclamation a tad bit more complicated but just for loops and if statements
    public static String shortenSpace(String usrStr) {
        for(int i=0; i<usrStr.length()-1; i++) {
            if (usrStr.charAt(i) == ' ' && usrStr.charAt(i+1) == ' ') {
                usrStr = usrStr.substring(0,i) + usrStr.substring(i+1,usrStr.length()-1);
            }
        }
        if (usrStr.charAt(usrStr.length()-2) == ' ' && usrStr.charAt(usrStr.length()-1) == ' ') {
                usrStr = usrStr.substring(0,usrStr.length()-1);
        }
        //Coveing end base case
        if (usrStr.charAt(0) == ' ' && usrStr.charAt(1) == ' ') {
                usrStr = usrStr.substring(2,usrStr.length()-1);
        }
        //Covering index(0) base case
        return usrStr;
    }

//Void method used to print menu
    public static void printMenu() {

        System.out.println("MENU");
        System.out.println("c - Number of non-whitespace characters");
        System.out.println("w - Number of words");
        System.out.println("f - Find text");
        System.out.println("r - Replace all !'s");
        System.out.println("s - Shorten spaces");
        System.out.println("q - Quit");
        System.out.println("");
        System.out.println("Choose an option:");

    }
}