import java.util.Scanner;
public class Battleship
{
    public static void main(String[] args) throws InterruptedException {
        Scanner scanny = new Scanner(System.in);

        char[][] hitBoard = new char[10][10];
        char[][] shipBoard = new char[10][10];

        System.out.println("WELCOME TO BATTLESHIP");
        System.out.println("");

        System.out.println("PLAYER 1: ");
        String name1 = scanny.nextLine();
        Player player1 = new Player(name1);

        System.out.println("PLAYER 2: ");
        String name2 = scanny.nextLine();
        Player player2 = new Player(name2);

        System.out.println(name1+"'s TURN TO PLACE SHIPS");
        player1.printClassroom(player1.getShipBoard());
        player1.setUpShips(player1.getShipBoard());
        Thread.sleep(2000);
        clearScreen();

        System.out.println("PRESS ENTER TO CONTINUE");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
        clearScreen();
        System.out.println(name2+"'s TURN TO PLACE SHIPS");
        player2.printClassroom(player2.getShipBoard());
        player2.setUpShips(player2.getShipBoard());
        Thread.sleep(2000);
        clearScreen();

        boolean win = false;
        while(win!=true){
            System.out.println(name1+"'s TURN TO ATTACK");
            System.out.println("WHERE WOULD YOU LIKE TO ATTACK?");
            System.out.print("ENTER ROW: ");
            int attackRow = scanny.nextInt();
            System.out.print("ENTER COLUMN: ");
            int attackColumn = scanny.nextInt();
           
            System.out.println("HITBOARD");
            player1.printClassroom(player1.attack(attackRow,attackColumn, player2.getShipBoard()));
            System.out.println("");
            System.out.println("SHIPBOARD");
            player1.printClassroom(player1.getShipBoard());
            
            scanny.nextLine();
            System.out.println("PRESS ENTER TO CONTINUE");
            if(win==true){
                break;
            }
           
            scanny.nextLine();
            System.out.println("PRESS ENTER TO CONTINUE");
           
            clearScreen();
            try
            {
                System.in.read();
            }  
            catch(Exception e)
            {}  

            System.out.println(name2+"'s TURN TO ATTACK");
            System.out.println("WHERE WOULD YOU LIKE TO ATTACK?");
            System.out.print("ENTER ROW: ");
            int attackRow2 = scanny.nextInt();
            System.out.print("ENTER COLUMN: ");
            int attackColumn2 = scanny.nextInt();

            System.out.println("HITBOARD");
            player2.printClassroom(player2.attack(attackRow2,attackColumn2,player1.getShipBoard()));
            System.out.println("");
            System.out.println("SHIPBOARD");
            player1.printClassroom(player2.getShipBoard());

            int count1 = 0;
            int count2 = 0;

            for(int r=0;r<10;r++){
                for(int c=0;c<10;c++){
                    if(player1.getHitBoard()[r][c] == 'x'){
                        count1++;
                    }
                    if(player2.getHitBoard()[r][c] == 'x' ){
                        count2++;
                    }
                }
            }  

            if(count1 == 13 || count2 == 13){
                win = true;
            }

            System.out.println("PRESS ENTER TO CONTINUE");
            try
            {
                System.in.read();
            }  
            catch(Exception e)
            {}  
           
            clearScreen();
        }
    }

    public static void clearScreen(){
        System.out.println('\u000c');
    }

}
