/*********************************************************************************************
 * @file : ArcadPrinter.java
 * @author : Sean Mealey
 * @date :08/12/21
 **********************************************************************************************/

import java.awt.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArcadePrinter {
    public static void main(String args[]) throws InterruptedException{
        //Scanner and opening messages
        Scanner scanny = new Scanner(System.in);
        System.out.println("Welcome to the Virtual Arcade!");
        System.out.println("");
        System.out.println("How many tokens would you like to purchase?");
        int tokens = scanny.nextInt();
        //initializes token value
        scanny.nextLine();

        //Was going to do a file input thing here but decided it was dumb and inorganic
//        System.out.println("Do you have any tokens from a previous session? (Y/N)");
//        if(scanny.nextLine().equalsIgnoreCase("Y")){
//            System.out.println("Great! Let's upload that receipt for you then.");
//            System.out.println("What is the name of the file");
//            String fileName = scanny.nextLine();
//        }
        int choice = 0;
        //While function that repeatedly brings up menu until they decide to leave
        while(choice!=4){
            System.out.println("Tokens: " + tokens);
            System.out.println("");
            System.out.println("Please select one of our amazing games/quizzes/puzzles. ");
            System.out.println("");
            System.out.println("1. Nations of the World Capitals Guessing Game (1 token)");
            System.out.println("2. 21 (1 token per game)");
            System.out.println("3. Word Find (2 tokens)");
            System.out.println("4. Exit the Arcade");

            int userSelection = scanny.nextInt();
            //Takes player to capital guessing
            if(userSelection==1){
                int score = 0;
                if(tokens>0){
                    score = capitalGuesser();
                }else{
                    System.out.println("Insufficient Tokens");
                }
                tokens--;
                tokens = tokens + score;
            }
            //Takes player to 21
            if(userSelection==2){
                int score = 0;
                if(tokens>0){
                    score = cardGame21(score,tokens);
                }else{
                    System.out.println("Insufficient Tokens");
                }
                tokens = tokens + score;
            }
            //Takes player to word find
            if(userSelection==3){
                if(tokens>2){
                    tokens -= 2;
                    tokens = wordFind(tokens);
                }else{
                    System.out.println("Insufficient Tokens");
                }
            }
            //Sets exit value to exit value
            if(userSelection==4){
                choice = 4;
            }

        }
        System.out.println("");
        System.out.println("");
        //Takes player to prize area before they leave
        System.out.println("You currently have " + tokens + " tokens.");
        scanny.nextLine();
        System.out.println("Would you like to exchange your tokens for a prize (Y/N)");
        if(scanny.nextLine().equalsIgnoreCase("Y")){
            prizeArea(tokens);
        }else{
            System.out.println("Come back soon!");
        }
    }
    //Capital guesser works by large arrays of data
    //Plays game to guess capitals
    public static int capitalGuesser() throws InterruptedException{
        Scanner scanny = new Scanner(System.in);
        String[] capitals = {"Kabul","Tirana","Algiers","Pago Pago","Andorra la Vella","Luanda","The Valley","St. John's","Buenos Aires","Yerevan","Oranjestad","Canberra","Vienna","Baku","Nassau","Manama","Dhaka","Bridgetown","Minsk","Brussels","Belmopan","Porto-Novo","Hamilton","Thimphu","La Paz","Sarajevo","Gaborone","Brasilia","Bandar Seri Begawan","Sofia","Ouagadougou","Bujumbura","Praia","Phnom Penh","Yaoundé","Ottawa-Gatineau","Bangui","N'Djamena","Santiago","Beijing","Hong Kong","Macao","Taibei","Bogota","Moroni","Brazzaville","Rarotonga","San Jose","Yamoussoukro","Zagreb","Havana","Willemstad","Nicosia","Prague","Pyongyang","Kinshasa","Copenhagen","Djibouti","Roseau","Santo Domingo","Quito","Cairo","San Salvador","Malabo","Asmara","Tallinn","Addis Ababa","Suva","Helsinki","Paris","Cayenne","Libreville","Banjul","Tbilisi","Berlin","Accra","Athens","Nuuk ","St.George's","Guatemala City","Conakry","Bissau","Georgetown","Port-au-Prince","Vatican City","Tegucigalpa","Budapest","Reykjavik","Delhi","Jakarta","Tehran","Baghdad","Dublin","Jerusalem","Rome","Kingston","Tokyo","Amman","Astana","Nairobi","Tarawa","Kuwait City","Bishkek","Vientiane","Riga","Beirut","Maseru","Monrovia","Tripoli","Vaduz","Vilnius","Luxembourg","Antananarivo","Lilongwe","Kuala Lumpur","Male","Bamako","Valletta","Majuro","Nouakchott","Port Louis","Mexico City","Palikir","Monaco","Ulaanbaatar","Podgorica","Rabat","Maputo","Nay Pyi Taw","Windhoek","Nauru","Kathmandu","Amsterdam","Noumea","Wellington","Managua","Niamey","Abuja","Alofi","Saipan","Oslo","Muscat","Islamabad","Koror","Panama City","Port Moresby","Asuncion","Lima","Manila","Warsaw","Lisbon","San Juan","Doha","Seoul","Chisinau","Saint-Denis","Bucharest","Moscow","Kigali","Jamestown","Basseterre","Castries","Saint-Pierre","Kingstown","Apia","San Marino","Sao Tome","Riyadh","Dakar","Belgrade","Victoria","Freetown","Singapore","Philipsburg","Bratislava","Ljubljana","Honiara","Mogadishu","Cape Town","Juba","Madrid","Colombo","Jerusalem","Khartoum","Paramaribo","Mbabane","Stockholm","Bern","Damascus","Dushanbe","Skopje","Bangkok","Dili","Lome","Nuku'alofa","Port of Spain","Tunis","Ankara","Ashgabat","Cockburn Town","Funafuti","Kampala","Kiev","Abu Dhabi","London","Dodoma","Washington DC","Charlotte Amalie","Montevideo","Tashkent","Port Vila","Caracas","Ha Noi","El Aaiun","Sana'a","Lusaka","Harare"};
        String[] countries = {"Afghanistan","Albania","Algeria","American Samoa","Andorra","Angola","Anguilla","Antigua and Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia ","Bosnia and Herzegovina","Botswana","Brazil","Brunei","Bulgaria","Burkina Faso","Burundi","Cabo Verde","Cambodia","Cameroon","Canada","Central African Republic","Chad","Chile","China","Hong Kong","Macao","Taiwan","Colombia","Comoros","Congo","Cook Islands","Costa Rica","Côte d'Ivoire","Croatia","Cuba","Curaçao","Cyprus","Czechia","North Korea","Democratic Republic of the Congo","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Fiji","Finland","France","French Guiana","Gabon","Gambia","Georgia","Germany","Ghana","Greece","Greenland","Grenada","Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti","Vatican","Honduras","Hungary","Iceland","India","Indonesia","Iran ","Iraq","Ireland","Israel","Italy","Jamaica","Japan","Jordan","Kazakhstan","Kenya","Kiribati","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Fed. States of Micronesia ","Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar","Namibia","Nauru","Nepal","Netherlands","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Niue","Northern Mariana Islands","Norway","Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","South Korea","Republic of Moldova","Réunion","Romania","Russian Federation","Rwanda","Saint Helena","Saint Kitts and Nevis","Saint Lucia","Saint Pierre and Miquelon","Saint Vincent and the Grenadines","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Sint Maarten (Dutch part)","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Sudan","Spain","Sri Lanka","State of Palestine","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syrian Arab Republic","Tajikistan","TFYR Macedonia","Thailand","Timor-Leste","Togo","Tonga","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Turks and Caicos Islands","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United Republic of Tanzania","United States of America","United States Virgin Islands","Uruguay","Uzbekistan","Vanuatu","Venezuela (Bolivarian Republic of)","Vietnam","Western Sahara","Yemen","Zambia","Zimbabwe"};
        String[] countryCap = new String[countries.length];
        for(int i=0;i<countries.length;i++){
            String newString = capitals[i] + " is the capital of " + countries[i];
            countryCap[i] = newString;
        }
        //Initializes the three essential arrays

        //Prints instructions and a little cheat sheet but its so many options no ones ever gonna search plus that ruins the integrity of the game
        System.out.println("Welcome to the Guessing Capitals Game");
        System.out.println("Please enter the capital of the inputted country");
        System.out.println("10 Questions.");
        System.out.println("");
        Thread.sleep(1000);
        System.out.println("Might be a little hard... so heres some help");
        System.out.println("");
        Thread.sleep(2000);
        for(int i=0;i<countryCap.length;i++){
            Thread.sleep(10);
            System.out.println(countryCap[i]);
        }
        System.out.println("");
        System.out.println("Hope you got all that. Let's begin!");
        System.out.println("");
        System.out.println("");


        //variables to keep track of score and interations
        int count = 0;
        int wrong = 0;
        int correct = 0;
        while(count <=10){
            //picks random capital
            System.out.println("");
            Random rand = new Random();
            int randIndex = rand.nextInt(countries.length);
            System.out.print(countries[randIndex] + ": ");
            String capitalGuess = scanny.nextLine();
            boolean isCapital = false;
            //checks to see if it is any capital
            for(int i=0;i<countries.length;i++){
                if(capitals[i].equalsIgnoreCase(capitalGuess)){
                    isCapital = true;
                }
            }
            String correctCapital = capitals[randIndex];
            String correctCountry = countries[randIndex];
            //Calls the checking function which uses binary searches to confirm it is the right country or not
            if(isCapital){
                boolean isCorrect = isCorrectCapital(countryCap,countries, capitals, capitalGuess, correctCountry);
                if(isCorrect == true){
                    System.out.println("Correct!");
                    correct++;
                }else{
                    System.out.print("Wrong!" );
                    System.out.println("The correct answer is: " + correctCapital);
                    wrong++;
                }
            }else {
                    System.out.print("Sorry, that is not a capital city.");
                    System.out.println("The correct answer is: " + correctCapital);
                    wrong++;
            }
            count++;
        }
        //score is one token for each correct
        System.out.println("");
        System.out.println("You got " + correct + " out of 10 correct!");
        int tokens = correct;
        System.out.println("You recieved " + tokens + " tokens!");
        return tokens;
    }
    //Specific binary search called by isCorrectCapital()
    public static int binaryCapitalSearch(String[] capitals, String capitalGuess, int low, int high){
        if(high >= low){
            int middle = (low + high) / 2;
            if(capitalGuess.equalsIgnoreCase(capitals[middle])){
                return middle;
            }else if(capitalGuess.compareTo(capitals[middle]) < 0){
                return binaryCapitalSearch(capitals, capitalGuess, low, middle-1);
            }else  {
                return binaryCapitalSearch(capitals, capitalGuess, middle+1, high);
            }
        }else{
            return -1;
        }
    }
    //Checks if correct capital through bubble sorts and binary searches
    public static boolean isCorrectCapital(String[] countryCap, String[] countries, String[] capitals, String capitalGuess, String correctCountry){
        String[] capitalsAlph = new String[countries.length];
        for(int i=0;i<capitals.length;i++){
            capitalsAlph[i] = capitals[i];
        }
        capitalsAlph = bubbleSort(capitalsAlph);
        int capitalIndexAlph = binaryCapitalSearch(capitalsAlph, capitalGuess, 0, capitals.length-1);
        int capitalIndex = 0;
        if(capitalIndexAlph ==  -1){
            return false;
        }
        int countryIndex = capitalIndex;
        for(int i=0;i<countries.length;i++){
            if(countries[i].equals(correctCountry)){
                countryIndex = i;
            }
        }

        //Makes sure the capital is related to that country
        if(countryCap[countryIndex].contains(capitalGuess) && countryCap[countryIndex].contains(correctCountry)){
            return true;
        }else{
            return false;
        }
    }
    //Basic bubble sort to aplhabetize the array
    public static String[] bubbleSort(String[] capitals){
        for(int i=0;i<capitals.length-1;i++){
            for(int j=0;j<capitals.length-i-1;j++){
                if(capitals[j].compareTo(capitals[j+1]) > 0){
                    String temp = capitals[j];
                    capitals[j] = capitals[j+1];
                    capitals[j+1] = temp;
                }
            }
        }
        return capitals;
    }
    //calls createPrizes and manages the interface of the binary search tree of prizes
    public static void prizeArea (int tokens) throws InterruptedException{
        BinarySearchTree prizeBST = createPrizes();
        System.out.println("Tokens: " + tokens);
        System.out.println("");
        System.out.println("Welcome to the Prize Area! Please select anything from our catalogue");
        System.out.println("As long as you have enough tokens that is");
        System.out.println("");
        Thread.sleep(500);
        prizeBST.printCatalogue();
        //prints catalohue

        System.out.print("Enter the name of your desired prize or none if you wish to leave: ");
        Scanner scanny = new Scanner(System.in);
        String userPrize = scanny.nextLine();
        while( !(userPrize.equalsIgnoreCase("None")) ){
            System.out.println("Purchased!");
            Thread.sleep(500);
            if(userPrize.equalsIgnoreCase("Buddy")){
                prizeBST.buyPrize("Buddy");
                prizeBST.remove("Buddy");
                tokens = tokens -2;
            }
            if(userPrize.equalsIgnoreCase("Friends")){
                prizeBST.buyPrize("Friends");
                prizeBST.remove("Friends");
                tokens = tokens -5;
            }

            System.out.println("Tokens: " + tokens);
            System.out.println("");
            System.out.println("Would you like anything else?");
            System.out.println("");
            Thread.sleep(500);
            prizeBST.printCatalogue();
            System.out.println("");
            System.out.println("");
            System.out.print("Enter the name of your desired prize or none if you wish to leave: ");
            userPrize = scanny.nextLine();
        }

        System.out.println("Have a nice day!");

    }
    //Creates 3 prizes through FileIO stuff
    //Adds them to binary tree
    public static BinarySearchTree createPrizes(){
        BinarySearchTree prizeBST = new BinarySearchTree();

        FileOutputStream fileStream = null;
        PrintWriter printer = null;

        try {
            File buddy = new File("Buddy.txt");
            fileStream = new FileOutputStream(buddy);
            printer = new PrintWriter(fileStream);

            printer.println("");
            printer.println("");

            printer.println("                        ________");
            printer.println("                       |  *  *  |");
            printer.println("                       |        |");
            printer.println("                       |  ____  |");
            printer.println("                        ________ ");
            printer.println("                            |");
            printer.println("                            |");
            printer.println("                     -------|-------");
            printer.println("                            |");
            printer.println("                            |");
            printer.println("                            |");
            printer.println("                           / \\");
            printer.println("                          /   \\");
            printer.println("                         /     \\");
            printer.println("                        /       \\");


            Prize prize1 = new Prize(2,"Buddy",buddy);
            Node prizeNode = new Node(prize1);
            prizeBST.insert(prizeNode);
        }catch(Exception e){
            System.out.println("ERROR: Prize1 could not be created");
        }

        try {
            File Friends = new File("Friends.txt");
            fileStream = new FileOutputStream(Friends);
            printer = new PrintWriter(fileStream);

            printer.println("");
            printer.println("");

            printer.println("                        ________           ________");
            printer.println("                       |  *  *  |         |  *  *  | ");
            printer.println("                       |        |         |        |  ");
            printer.println("                       | |____| |         | |____| |");
            printer.println("                       |________|         |________|");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                     -------|------------------|-------");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                           / \\                |");
            printer.println("                          /   \\              /   \\");
            printer.println("                         /     \\            /     \\");
            printer.println("                        /       \\          /       \\");


            Prize prize2 = new Prize(5,"Friends",Friends);
            Node prizeNode = new Node(prize2);
            prizeBST.insert(prizeNode);
        }catch(Exception e){
            System.out.println("ERROR: Prize2 could not be created");
        }

        try {
            File Party = new File("Party.txt");
            fileStream = new FileOutputStream(Party);
            printer = new PrintWriter(fileStream);

            printer.println("");
            printer.println("");

            printer.println("                        ________           ________");
            printer.println("                       |  *  *  |         |  *  *  | ");
            printer.println("                       |        |         |        |  ");
            printer.println("                       | |____| |         | |____| |");
            printer.println("                       |________|         |________|");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                     -------|------------------|-------");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                           / \\                |");
            printer.println("                          /   \\              /   \\");
            printer.println("                         /     \\            /     \\");
            printer.println("                        /       \\          /       \\");
            printer.println("                        ________           ________");
            printer.println("                       |  *  *  |         |  *  *  | ");
            printer.println("                       |        |         |        |  ");
            printer.println("                       | |____| |         | |____| |");
            printer.println("                       |________|         |________|");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                     -------|------------------|-------");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                           / \\                |");
            printer.println("                          /   \\              /   \\");
            printer.println("                         /     \\            /     \\");
            printer.println("                        /       \\          /       \\");
            printer.println("                        ________           ________");
            printer.println("                       |  *  *  |         |  *  *  | ");
            printer.println("                       |        |         |        |  ");
            printer.println("                       | |____| |         | |____| |");
            printer.println("                       |________|         |________|");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                     -------|------------------|-------");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                            |                  |");
            printer.println("                           / \\                |");
            printer.println("                          /   \\              /   \\");
            printer.println("                         /     \\            /     \\");
            printer.println("                        /       \\          /       \\");


            Prize prize3 = new Prize(10,"Party",Party);
            Node prizeNode = new Node(prize3);
            prizeBST.insert(prizeNode);
        }catch(Exception e){
            System.out.println("ERROR: Prize3 could not be created");
        }

        printer.close();


        return prizeBST;
    }
    //Uses deck object and then simple while loops and sums to track game rules
    public static int cardGame21(int score, int tokens) throws InterruptedException{
        Deck deck = new Deck();
        deck.shuffle();
        Scanner scanny = new Scanner(System.in);
        int playerSum = 0;
        int dealerSum = 0;
        boolean playerHasAce = false;
        boolean dealerHasAce = false;

        System.out.println("Welcome to 21! The objective of the game is to have the sum of your cards be as close to 21 as possible without being greater. ");
        System.out.println("If you get closer without going over than me, you win! But if you tie me, have less than me, or go over you lose");
        System.out.println("1 token per play");
        System.out.println("Press Y to begin: ");
        while( !(scanny.nextLine().equalsIgnoreCase("Y")) ){
            System.out.println("Press Y to begin: ");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("PLAYER                                                                          COMPUTER");
        System.out.println("");
        Thread.sleep(1000);
        Card firstCard = deck.topCard();
        firstCard.printCard();
        playerSum += firstCard.get21Value();
        if(firstCard.get21Value()==11){
            playerHasAce = true;
        }

        Thread.sleep(1500);
        System.out.print("                                                                    ");
        Card secondCard = deck.topCard();
        secondCard.printCard();
        dealerSum += secondCard.get21Value();
        if(secondCard.get21Value()==11){
            playerHasAce = true;
        }
        System.out.println("");

        Thread.sleep(1500);
        Card thirdCard = deck.topCard();
        thirdCard.printCard();
        playerSum += thirdCard.get21Value();
        if(thirdCard.get21Value()==11){
            playerHasAce = true;
        }
        System.out.println("");

        Thread.sleep(1000);

        System.out.println("Would you like another card? (Y/N)");
        String playerWant = scanny.nextLine();

        System.out.println("");
        System.out.println("PLAYER                                                                          COMPUTER");
        while(playerSum <= 21 && playerWant.equalsIgnoreCase("Y") ){
            System.out.println("");
            Card newCard = deck.topCard();
            newCard.printCard();
            playerSum += newCard.get21Value();
            if(playerSum > 21){
                System.out.println("");
                System.out.println("Busted, you lose!");
                System.out.println("");
                score--;
            }else {
                Thread.sleep(1500);
                System.out.println("");
                System.out.println("Would you like another card? (Y/N)");
                playerWant = scanny.nextLine();
            }
        }
        System.out.println("");
        while(dealerSum <= 16 && playerSum < 22){
            System.out.print("                                                                                ");
            Card newCard = deck.topCard();
            newCard.printCard();
            dealerSum += newCard.get21Value();
            System.out.println("");
            Thread.sleep(1000);
        }
        if(dealerSum > 21 && playerSum < 22) {
            System.out.println("Computer busted, you win!");
            System.out.println("");
            score++;
        }else if(dealerSum >= playerSum && dealerSum < 22){
            System.out.println("Computer wins!");
            System.out.println("");
            score--;
        }else if(playerSum>dealerSum && playerSum<22){
            System.out.println("Player wins!");
            System.out.println("");
            score++;
        }
        Thread.sleep(1500);
        System.out.println("");

        System.out.println("Would you like to play again? (Y/N)");
        if(scanny.nextLine().equalsIgnoreCase("Y") && (tokens - score) > 0 ){
            cardGame21(score,tokens);
        }
    return score;
    }
    //3 questions 10 expected answers each
    public static int wordFind(int tokens){
        Scanner scanny = new Scanner(System.in);
        System.out.println("Welcome to WordFind!");
        System.out.println("Find 10 anagrams of 4 letters of more (Enter quit to give up)");
        System.out.println("Question 1: ");
        String[] question1 = {"ARROWED","DRAWLER","WARLORD","WORLDER","ADORER","DRAWER","LARDER","LOADER","ORDEAL","REDOWA","REDRAW","RELOAD","REWARD","REWORD","ROARED","WARDER","WARRED","WELDOR","ADORE","ALDER","ARDOR","ARROW","DARER","DEWAR","DOWEL","DOWER","DRAWL","DREAR","DWALE","LADER","LAWED","LOWED","LOWER","OARED","OLDER","ORDER","OREAD","RARED","RAWER","ROWED","ROWEL","ROWER","WADER","WALED","WALER","WARED","WEALD","WOALD","WORLD","AERO","ALOE","ALOW","AWED","AWOL","DALE","DARE","DEAL","DEAR","DOER","DOLE","DORE","DORR","DRAW","DREW","EARL","LADE","LARD","LEAD","LEAR","LEWD","LOAD","LODE","LORD","LORE","LOWE","ODEA","OLDE","OLEA","ORAD","ORAL","ORLE","ORRA","OWED","RALE","RARE","READ","REAL","REAR","REDO","ROAD","ROAR","RODE","ROLE","WADE","WALE","WARD","WARE","WEAL","WEAR","WELD","WOAD","WOLD","WORD","WORE"};

        String[] answers = insertationSort(question1);
        System.out.println("REALWORD");
        System.out.println("");
        int count = 0;
        String[] prevAns1 = new String[answers.length];
        int wrongs = 0;
        String userResponse = scanny.nextLine();
        while( !(userResponse.equalsIgnoreCase("quit")) && count < 10 ){
            String userResponseCap = userResponse.toUpperCase();
            if( (binarySearch(answers, userResponseCap, 0, answers.length-1)) != -1){
                if(prevAns1[0]!=null) {
                    prevAns1 = insertationSort(prevAns1);
                    if ((binarySearch(prevAns1, userResponseCap, 0, prevAns1.length - 1)) == -1) {
                        System.out.println("Correct!");
                        prevAns1[wrongs] = userResponseCap;
                        wrongs++;
                        count++;
                    } else {
                        System.out.println("Already answered");
                    }
                }else{
                    System.out.println("Correct!");
                    prevAns1[wrongs] = userResponseCap;
                    wrongs++;
                    count++;
                }
            }else{
                System.out.println("Wrong!");
            }
            userResponse = scanny.nextLine();
        }
        String[] question2 = {"ABET","ABLE","ABRI","AHIS","AILS","AIRS","AIRT","AITS","ALBS","ALES","ALIT","ALTS","ARBS","ARES","ARIL","ARSE","ARTS","ATES","BAHT","BAIL","BAIT","BALE","BALS","BARE","BARS","BASE","BASH","BASS","BAST","BATE","BATH","BATS","BEAL","BEAR","BEAT","BELS","BELT","BEST","BETA","BETH","BETS","BIAS","BIER","BILE","BIRL","BISE","BISH","BITE","BITS","BLAE","BLAH","BLAT","BLET","BRAE","BRAS","BRAT","BRIE","BRIS","BRIT","EARL","EARS","EAST","EATH","EATS","ELHI","ERAS","ERST","ESTS","ETAS","ETHS","HAES","HAET","HAIL","HAIR","HALE","HALT","HARE","HARL","HART","HAST","HATE","HATS","HEAL","HEAR","HEAT","HEIL","HEIR","HERB","HERL","HERS","HEST","HETS","HIES","HILA","HILT","HIRE","HISS","HIST","HITS","ILEA","IRES","ISBA","ISLE","LABS","LAHS","LAIR","LARI","LARS","LASE","LASH","LASS","LAST","LATE","LATH","LATI","LATS","LEAR","LEAS","LEHR","LEIS","LESS","LEST","LETS","LIAR","LIAS","LIBS","LIER","LIES","LIRA","LIRE","LIST","LITE","LITS","RAIL","RAIS","RALE","RASE","RASH","RATE","RATH","RATS","REAL","REBS","REIS","RESH","REST","RETS","RHEA","RIAL","RIAS","RIBS","RIEL","RILE","RISE","RITE","SABE","SABS","SAIL","SALE","SALS","SALT","SARI","SASH","SATE","SATI","SEAL","SEAR","SEAS","SEAT","SEIS","SELS","SERA","SERS","SESH","SETA","SETS","SHAT","SHEA","SHES","SHIT","SHRI","SIAL","SIBS","SILT","SIRE","SIRS","SITE","SITH","SITS","SLAB","SLAT","SLIT","SRIS","STAB","STAR","STIR","TABS","TAEL","TAHR","TAIL","TALE","TALI","TARE","TARS","TASE","TASS","TEAL","TEAR","TEAS","TELA","TELS","THAE","THIR","THIS","TIER","TIES","TILE","TILS","TIRE","TIRL","ABETS","ABLER","ABLES","ABRIS","AIRTH","AIRTS","AISLE","ALERT","ALIST","ALTER","ARIEL","ARILS","ARISE","ARLES","ARSES","ARSIS","ARTEL","ASHES","ASSET","ASTER","ASTIR","BAHTS","BAILS","BAITH","BAITS","BALER","BALES","BALTI","BARES","BASER","BASES","BASIL","BASIS","BASSI","BASTE","BASTS","BATES","BATHE","BATHS","BEALS","BEARS","BEAST","BEATS","BELTS","BERTH","BESTS","BETAS","BETHS","BIERS","BILES","BIRLE","BIRLS","BIRSE","BIRTH","BISES","BITER","BITES","BLAHS","BLARE","BLASE","BLAST","BLATE","BLATS","BLEAR","BLEAT","BLESS","BLEST","BLETS","BLISS","BLITE","BRAES","BRAIL","BRASH","BRASS","BRATS","BRIES","BRISS","BRITH","BRITS","EARLS","EARTH","EASTS","HABIT","HAETS","HAILS","HAIRS","HALER","HALES","HALTS","HARES","HARLS","HARTS","HASTE","HATER","HATES","HEALS","HEARS","HEART","HEATS","HEILS","HEIRS","HEIST","HERBS","HERLS","HESTS","HILAR","HILTS","HIRES","HISTS","IRATE","ISBAS","ISLES","ISLET","ISTLE","ITHER","LAIRS","LAITH","LARES","LARIS","LASER","LASES","LASSI","LASTS","LATER","LATHE","LATHI","LATHS","LEARS","LEASH","LEAST","LEHRS","LIARS","LIBER","LIBRA","LIERS","LIRAS","LISTS","LITAS","LITER","LITES","LITHE","LITRE","RAILS","RAISE","RALES","RASES","RATEL","RATES","RATHE","REALS","REHAB","RELIT","RESAT","RESIT","RESTS","RETIA","RHEAS","RIALS","RIBES","RIELS","RILES","RISES","RITES","SABER","SABES","SABIR","SABLE","SABRE","SAHIB","SAILS","SAITH","SALES","SALTS","SARIS","SATES","SATIS","SEALS","SEARS","SEATS","SELAH","SERAI","SERAL","SETAL","SHALE","SHALT","SHARE","SHEAL","SHEAR","SHEAS","SHIEL","SHIER","SHIES","SHIRE","SHIRT","SHIST","SHITE","SHITS","SHRIS","SIALS","SILTS","SIRES","SISAL","SITAR","SITES","SLABS","SLASH","SLATE","SLATS","SLIER","SLITS","STABS","STAIR","STALE","STARE","STARS","STASH","STEAL","STELA","STIES","STILE","STIRS","STRIA","TABER","TABES","TABLE","TAELS","TAHRS","TAILS","TALER","TALES","TARES","TARSI","TASER","TASES","TASSE","TEALS","TEARS","TELIA","TERAI","TESLA","THALI","THEIR","THIRL","TIERS","TILER","TILES","TIRES","TIRLS","TRAIL","TRASH","TRASS","TRESS","TRIAL","TRIBE","TRIES","TSARS","ABLEST","ABSEIL","AIREST","AIRTHS","AISLES","ALBEIT","ALBITE","ALERTS","ALTERS","ARIELS","ARISES","ARTELS","ARTSIE","ASHIER","ASHLER","ASSERT","ASTERS","BAILER","BAITER","BALERS","BALTIS","BAREST","BARITE","BASEST","BASHER","BASHES","BASILS","BASSER","BASSET","BASTER","BASTES","BATHER","BATHES","BEASTS","BERTHA","BERTHS","BESTIR","BIASES","BIRLES","BIRSES","BIRTHS","BISHES","BISTER","BISTRE","BITERS","BLAHER","BLARES","BLASTS","BLEARS","BLEATS","BLITES","BLITHE","BRAILS","BRAISE","BRASIL","BREAST","BREATH","BRISES","BRITHS","EARTHS","ESTRAL","HABILE","HABITS","HAILER","HALERS","HALEST","HALIER","HALITE","HALTER","HASLET","HASSEL","HASSLE","HASTES","HATERS","HEARTS","HEISTS","HERBAL","HIRSEL","HIRSLE","HISSER","ISLETS","ISTLES","LABRET","LASERS","LASHER","LASHES","LASSIE","LASTER","LATHER","LATHES","LATHIS","LATISH","LEASTS","LIASES","LIBERS","LIBRAE","LIBRAS","LISTER","LITERS","LITHER","LITRES","RABIES","RAISES","RASHES","RASSLE","RATELS","REBAIT","REHABS","RELISH","RELIST","RESAIL","RESIST","RESITS","RETAIL","RETIAL","RIBLET","SABERS","SABIRS","SABLER","SABLES","SABRES","SAHIBS","SAILER","SAITHE","SALTER","SALTIE","SATIRE","SELAHS","SERAIL","SERAIS","SERIAL","SHALES","SHARES","SHEALS","SHEARS","SHEILA","SHELTA","SHIELS","SHIERS","SHIEST","SHIRES","SHIRTS","SHITES","SIESTA","SISTER","SISTRA","SITARS","SLATER","SLATES","SLIEST","STABLE","STAIRS","STALER","STALES","STARES","STEALS","STELAI","STELAR","STILES","STRIAE","TABERS","TABLES","TAHSIL","TAILER","TALERS","TASERS","TASSEL","TASSIE","TERAIS","TERBIA","TESLAS","THALER","THALIS","THEIRS","THESIS","THIRLS","TILERS","TRAILS","TRIALS","TRIBAL","TRIBES","ABLEIST","ABSEILS","AIRLESS","ALBITES","ARTLESS","ARTSIES","ASHIEST","ASHLERS","ASTILBE","BAILERS","BAITERS","BARITES","BARLESS","BASHERS","BASTERS","BASTILE","BATHERS","BEARISH","BERTHAS","BESTIAL","BESTIRS","BISTERS","BISTRES","BLAHEST","BLASTER","BLASTIE","BLATHER","BLISTER","BLITHER","BRAISES","BRALESS","BRASHES","BRASILS","BRASSIE","BREASTS","BREATHS","BRISTLE","HAILERS","HALBERT","HALIERS","HALITES","HALTERS","HARSLET","HASLETS","HASTIER","HATLESS","HELIAST","HERBALS","HIRABLE","HIRSELS","HIRSLES","HITLESS","LABRETS","LASHERS","LASTERS","LATHERS","LATHIER","LIBRATE","LISTERS","RASHEST","REALIST","REBAITS","RELISTS","RESAILS","RETAILS","RIBLESS","RIBLETS","SABLEST","SAILERS","SALTERS","SALTIER","SALTIES","SALTIRE","SALTISH","SATIRES","SERAILS","SERIALS","SHALIER","SHEILAS","SHELTAS","SLASHER","SLATERS","SLATHER","SLATIER","SLITHER","STABILE","STABLER","STABLES","TAHSILS","TAILERS","TERBIAS","THALERS","TRASHES","TRIABLE","TRIBALS","ABLEISTS","ASTILBES","BASTILES","BATHLESS","BLASTERS","BLASTIER","BLASTIES","BLATHERS","BLISTERS","BLITHERS","BRASHEST","BRISTLES","HAIRLESS","HALBERTS","HARSLETS","HELIASTS","LIBRATES","REALISTS","SALTIERS","SALTIRES","SHALIEST","SLATHERS","SLITHERS","STABILES","STABLERS","STABLISH","BRASHIEST","ESTABLISH","HERBALIST"};
        String[] answers2 = insertationSort(question2);
        String[] prevAns = new String[answers2.length];
        System.out.println("Question 2: ");
        System.out.println("ARCADE");
        System.out.println("");
        userResponse = scanny.nextLine();
        wrongs = 0;
        while( !(userResponse.equalsIgnoreCase("quit")) && count < 20){
            String userResponseCap = userResponse.toUpperCase();
            if( (binarySearch(answers2, userResponseCap, 0, answers2.length-1)) != -1){
                if(prevAns[0]!=null) {
                    prevAns = insertationSort(prevAns);

                    if ((binarySearch(prevAns, userResponseCap, 0, prevAns.length - 1)) == -1) {
                        System.out.println("Correct!");
                        prevAns[wrongs] = userResponseCap;
                        wrongs++;
                        count++;
                    } else {
                        System.out.println("Already answered");
                    }
                }else{
                    System.out.println("Correct!");
                    prevAns[wrongs] = userResponseCap;
                    wrongs++;
                    count++;
                }
            }else{
                System.out.println("Wrong!");
            }
            userResponse = scanny.nextLine();
        }

        String[] question3 = {"acred","arced","cadre","cared","cedar","raced","caaed","areca","cad re","rec ad","car ed","arc ed","arc ad","car ad","ace ad","cred","card","drac","aced","cade","dace","ecad","acer","acre","care","race","raca"};
        String[] answers3 = insertationSort(question3);
        String[] prevAns3 = new String[answers3.length];
        wrongs = 0;
        System.out.println("Question 3: ");
        System.out.println("ARCADE");
        System.out.println("");
        userResponse = scanny.nextLine();
        while( !(userResponse.equalsIgnoreCase("quit")) && count < 30){
            String userResponseCap = userResponse.toUpperCase();
            if( (binarySearch(answers3, userResponseCap, 0, answers3.length-1)) != -1 ){
                if(prevAns[0]!=null) {
                    prevAns3 = insertationSort(prevAns3);
                    if ((binarySearch(prevAns3, userResponseCap, 0, prevAns3.length - 1)) == -1) {
                        System.out.println("Correct!");
                        prevAns3[wrongs] = userResponseCap;
                        wrongs++;
                        count++;
                    } else {
                        System.out.println("Already answered");
                    }
                }else{
                    System.out.println("Correct!");
                    prevAns3[wrongs] = userResponseCap;
                    wrongs++;
                    count++;
                }
            }else{
                System.out.println("Wrong!");
            }
            userResponse = scanny.nextLine();
        }
        if(count == 30){
            System.out.println("Perfect game! Have 10 tokens");
            tokens += 10;
        }else if(count >= 25){
            System.out.println("An excellent perfomance. 5 tokens");
            tokens += 5;
        }else if(count >= 20){
            System.out.println("Not bad at all. 3 tokens won.");
            tokens += 3;
        }else if(count > 15){
            System.out.println("Not very impressive performance. No tokens won");
        }else if(count < 10){
            System.out.println("An utterly horrible showing. Loss of an addition 3 tokens");
            tokens -= 3;
        }
    return tokens;

    }
    //Binary search almost idential to the other one for the capitals
    public static int binarySearch(String[] letters, String capitalGuess, int low, int high){
        if(high >= low){
            int middle = (low + high) / 2;
            if(capitalGuess.equalsIgnoreCase(letters[middle])){
                return middle;
            }else if(capitalGuess.compareTo(letters[middle]) < 0){
                return binarySearch(letters, capitalGuess, low, middle-1);
            }else  {
                return binarySearch(letters, capitalGuess, middle+1, high);
            }
        }else{
            return -1;
        }
    }
    //Sort method that works by moving each element as far left as possible to its correct position one at a time
    public static String[] insertationSort(String[] answers){
        for(int i=1;i< answers.length-1;i++){
            while(i> 0 && answers[i].compareTo(answers[i-1]) < 0){
                String temp = answers[i];
                answers[i] = answers[i-1];
                answers[i-1] = temp;
                i--;
            }
        }
        return answers;
    }
}

