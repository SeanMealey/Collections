
public class TicTacToe{

//Mainly all same code as alternating circle
    public static void drawGrid(){
        int x = 0;
        int y = 0;

        for(int i=0;i<30;i++){
            StdDraw.line(x,y,x,y+30);
            x += 10;
         }

         //resets x cordinate
         x = 0;

         //Draws all the vertical lines
         for(int i=0;i<=30;i++){
            StdDraw.line(x,y,x+30,y);
            y+=10;
         }




    }

//Main function
    public static void main(String[] args) {
        final int NUM_CLICKS = 9;
        final int CELL_SIZE = 10;
        final int GRID_SIZE = 30;
        boolean[][] board = new boolean[GRID_SIZE/CELL_SIZE][GRID_SIZE/CELL_SIZE];
        boolean[][] oboard = new boolean[3][3];
        boolean[][] xboard = new boolean[3][3];


        //[3][3]
        StdDraw.setScale(-1, GRID_SIZE+1);
        StdDraw.setPenRadius(0.005);

        drawGrid();
        int xposition = 1;
        int yposition = 1;

        int clicks = 0;
        int count = 0;

        //Created two individual win condition functions so i did not worry about having to figure out who won
        while(xwinCondition(xboard) == false && owinCondition(oboard) == false) {
            if (StdDraw.isMousePressed()) {
                clicks++;
                // get coordinates of mouse click
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                // pause drawing to regain control of the mouse
                StdDraw.pause(300);
                // adjust coordinates based on the cell size
                // draw circle


                //All same as previous programs
                if(count % 2 == 0){
                    StdDraw.setPenColor(StdDraw.RED);
                }else{
                    StdDraw.setPenColor(StdDraw.BLUE);
                }
                if(count % 2 == 0){

                        board = drawCircle(x,y, xposition, yposition,board, oboard);

                }else{
                        board = drawX(x,y, xposition, yposition,board, xboard);

                }

                //Same fancy count
                count = 0;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        if(board[i][j]==true){
                            count++;
                        }
                    }
                }


            }
        }

        //Individualized messages for the skilled players
        System.out.println("Done!");
        if(owinCondition(oboard)==true){
            System.out.println("O has won!!!");
        }
        if(xwinCondition(xboard)==true){
            System.out.println("X has won!!!");
        }

    }

    //Both of these functions have two new parameters for the win board for x and win board for o
    //Completely same otherwise

    public static boolean[][] drawCircle(double x, double y, int xposition, int yposition, boolean[][] board, boolean[][] oboard  ){
        if(x < 10 && y < 10){

            xposition = 3;
            yposition = 1;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.circle(5,5,5);
            }
        }
        if(x < 20 && x >= 10 && y < 10){

            xposition = 3;
            yposition = 2;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.circle(15,5,5);
            }
        }
        if(x > 20 && y < 10){

            xposition = 3;
            yposition = 3;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.circle(25,5,5);
            }
        }
        if((x < 10 && y < 20) && y >= 10 ){

            xposition = 2;
            yposition = 1;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.circle(5,15,5);
            }
        }
        if(x < 10 && y > 20){

            xposition = 1;
            yposition = 1;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.circle(5,25,5);
            }
        }
        if( (x < 20 && y < 20) && y >= 10 && x >= 10){

            xposition = 2;
            yposition = 2;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.circle(15,15,5);
            }
        }
        if( (x < 20 && y > 20) && x >= 10){

            xposition = 1;
            yposition = 2;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.circle(15,25,5);
            }
        }
        if(x > 20 && y > 10 && y <= 20){

            xposition = 2;
            yposition = 3;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.circle(25,15,5);
            }
        }
        if(x > 20 && y > 20){

            xposition = 1;
            yposition = 3;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.circle(25,25,5);
            }
        }
        board[xposition-1][yposition-1] = true;
        oboard[xposition-1][yposition-1] = true;
        //Just turn the spot on Oboard true as well for win condition
        return board;
    }


//All same
    public static boolean[][] drawX(double x, double y, int xposition, int yposition, boolean[][] board, boolean[][] xboard ){
        if(x < 10 && y < 10){

            xposition = 3;
            yposition = 1;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.line(0,0,10,10);
                StdDraw.line(0,10,10,0);

            }
        }
        if(x < 20 && x >= 10 && y < 10){

            xposition = 3;
            yposition = 2;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.line(10,0,20,10);
                StdDraw.line(10,10,20,0);
            }
        }
        if(x > 20 && y < 10){


            xposition = 3;
            yposition = 3;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.line(20,0,30,10);
                StdDraw.line(20,10,30,0);
            }
        }
        if((x < 10 && y < 20) && y >= 10 ){


            xposition = 2;
            yposition = 1;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.line(0,10,10,20);
                StdDraw.line(0,20,10,10);
            }
        }
        if(x < 10 && y > 20){


            xposition = 1;
            yposition = 1;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.line(0,20,10,30);
                StdDraw.line(0,30,10,20);
            }
        }
        if( (x < 20 && y < 20) && y >= 10 && x >= 10){


            xposition = 2;
            yposition = 2;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.line(10,10,20,20);
                StdDraw.line(10,20,20,10);
            }
        }
        if( (x < 20 && y > 20) && x >= 10){


            xposition = 1;
            yposition = 2;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.line(10,20,20,30);
                StdDraw.line(10,30,20,20);
            }
        }
        if(x > 20 && y > 10 && y <= 20){


            xposition = 2;
            yposition = 3;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.line(20,10,30,20);
                StdDraw.line(20,20,30,10);
            }
        }
        if(x > 20 && y > 20){


            xposition = 1;
            yposition = 3;
            if(board[xposition-1][yposition-1] == false){
                StdDraw.line(20,20,30,30);
                StdDraw.line(20,30,30,20);
            }
        }
        board[xposition-1][yposition-1] = true;
        xboard[xposition-1][yposition-1] = true;
        //Just turn the spot on xboard true as well for win condition
        return board;
    }

    //Win condition just evaluates the 9 different win states
    //Easy to do this way in such a simplistic game
    public static boolean owinCondition( boolean[][] oboard){
        boolean win = false;
        if(oboard[0][0] && oboard[0][1] && oboard[0][2]){
            win = true;
        }
        if(oboard[1][0] && oboard[1][1] && oboard[1][2]){
            win = true;
        }
        if(oboard[2][0] && oboard[2][1] && oboard[2][2]){
            win = true;
        }
        if(oboard[0][0] && oboard[1][0] && oboard[2][0]){
            win = true;
        }
        if(oboard[0][1] && oboard[1][1] && oboard[2][1]){
            win = true;
        }
        if(oboard[0][2] && oboard[1][2] && oboard[2][2]){
            win = true;
        }
        if(oboard[0][0] && oboard[1][1] && oboard[2][2]){
            win = true;
        }
        if(oboard[2][0] && oboard[1][1] && oboard[0][2]){
            win = true;
        }
        return win;
    }

    //Same process occurring here for the other board.
    public static boolean xwinCondition( boolean[][] xboard){
        boolean win = false;
        if(xboard[0][0] && xboard[0][1] && xboard[0][2]){
            win = true;
        }
        if(xboard[1][0] && xboard[1][1] && xboard[1][2]){
            win = true;
        }
        if(xboard[2][0] && xboard[2][1] && xboard[2][2]){
            win = true;
        }
        if(xboard[0][0] && xboard[1][0] && xboard[2][0]){
            win = true;
        }
        if(xboard[0][1] && xboard[1][1] && xboard[2][1]){
            win = true;
        }
        if(xboard[0][2] && xboard[1][2] && xboard[2][2]){
            win = true;
        }
        if(xboard[0][0] && xboard[1][1] && xboard[2][2]){
            win = true;
        }
        if(xboard[2][0] && xboard[1][1] && xboard[0][2]){
            win = true;
        }
        return win;
    }
}

