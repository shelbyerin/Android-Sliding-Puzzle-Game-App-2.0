package cosc426.assign4part1;
import java.util.Random;

public class Game {
    private final char BLANK = '0';
    public char[][] board = {};
    public char[][] goal = {};
    public int x,y;
    public int startx,starty;
    public int endx, endy;

    public Game(){
        board = createBoard();
        //sets the x and y values for the blank square
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3; j++){
                if(board[i][j] == BLANK){
                    this.x = i;
                    this.y = j;
                }
            }
        }
        goal = generateGoal();
    }
    //this generates the goal (ascending order)
    private char[][] generateGoal(){
        char[][] array = {{'1','2','3'},{'4','5','6'},{'7','8','0'}};
        return array;
    }
    //creates the board randomizes the values
    private char[][] createBoard(){
        char[] array = {'0','1','2','3','4','5','6','7','8'};
        char[] shuffledArray = shuffleArray(array);
        char[][]returnArray = {{shuffledArray[0],shuffledArray[1], shuffledArray[2]},{shuffledArray[3],shuffledArray[4], shuffledArray[5]},{shuffledArray[6],shuffledArray[7], shuffledArray[8]}};
        return returnArray;
    }
    //function to shuffle the array (randomize)
    private static char[] shuffleArray(char[] array)
    {
        int index;
        char temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--){
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }
    //sets the start values of the index within the board
    public void setStart(int startX, int startY) {
        this.starty = (int) Math.ceil((startX - 100) / 300);
        this.startx = (int) Math.ceil((startY - 240) / 300);
    }
    //sets the end values of the index within the board
    public void setEnd(int endX, int endY){
        this.endy = (int)Math.ceil((endX - 100) / 300);
        this.endx = (int)Math.ceil((endY - 240) / 300);
    }
    //checks to see if the start or end index was the blank
    public boolean checkForBlank(){
        if((this.startx == this.x && this.starty == this.y) || (this.endx == this.x && this.endy == this.y)){
            return true;
        }else{
            return false;
        }
    }
    public void moveBlank(){
        //this checks for all the conditions needed in order to move the blank
        if((this.startx == this.x && this.starty == this.y) && //start at the blank
                ((this.endx == this.x+1 && this.endy == this.y) ||
                        (this.endx == this.x && this.endy == this.y+1) ||
                        (this.endx == this.x-1 && this.endy == this.y) ||
                        (this.endx == this.x && this.endy == this.y-1))){
            this.board[startx][starty] = this.board[endx][endy];
            this.board[endx][endy] = BLANK;
            x = endx;
            y = endy;
        }else if((this.endx == this.x && this.endy == this.y) && //ends with the blank
            ((this.startx == this.x+1 && this.starty == this.y) ||
                    (this.startx == this.x && this.starty == this.y+1) ||
                    (this.startx == this.x-1 && this.starty == this.y) ||
                    (this.startx == this.x && this.starty == this.y-1))){
            this.board[endx][endy] = this.board[startx][starty];
            this.board[startx][starty] = BLANK;
            x = startx;
            y = starty;
        }else{
            return; //no movement here since the start or end wasn't the blank
        }
    }
    public boolean checkWin(){
        int numCorrect = 0;
        //checks for the number of squares that match with the board and goal
        for(int i = 0 ; i< 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(board[i][j] == goal[i][j]){
                    numCorrect = numCorrect+1;
                }else{
                    numCorrect = numCorrect;
                }
            }
        }//if all 9 are in correct spots - display that they won
        if(numCorrect == 9){
            return true;
        }else{
            return false;
        }
    }
    //all the get functions
    public char[][] getBoard(){
        return  board;
    }
    public char[][] getGoal(){
        return  goal;
    }
    public int getX(){ return this.x; }
    public int getY(){ return this.y; }
    public int getStartx(){return this.startx;}
    public int getStarty(){return this.starty;}
    public int getEndx(){return this.endx;}
    public int getEndy(){return this.endy;}
}