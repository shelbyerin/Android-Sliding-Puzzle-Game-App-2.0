package cosc426.assign4part1;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InterfaceBuilder extends RelativeLayout{
    private TextView[][] board;
    private TextView instructions;
    public InterfaceBuilder(Context context) {
        super(context);
        final int DP = (int) (getResources().getDisplayMetrics().density);

        GridLayout gridOne = new GridLayout(context);
        gridOne.setId(GridLayout.generateViewId());
        gridOne.setColumnCount(3);
        gridOne.setRowCount(3);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(0, 0);
        params = new RelativeLayout.LayoutParams(0, 0);
        params.width = 900;
        params.height = 900;
        params.topMargin = 100;
        params.leftMargin = 100;
        params.rightMargin = 100;
        gridOne.setLayoutParams(params);
        //gets the board for within the grid
        board = new TextView[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new TextView(context);
                board[i][j].setBackgroundColor(Color.parseColor("#72c5d6"));
                board[i][j].setText("");
                board[i][j].setTextColor(Color.parseColor("#0b1315"));
                board[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                board[i][j].setGravity(Gravity.CENTER);
                GridLayout.LayoutParams params1 = new GridLayout.LayoutParams();
                params1.width = 300;
                params1.height = 300;
                params1.bottomMargin = 1;
                params1.rightMargin = 1;
                board[i][j].setLayoutParams(params1);
                gridOne.addView(board[i][j]);
            }
        }
        addView(gridOne);
        //displays the instructions (and the win)
        instructions = new TextView(context);
        instructions.setId(TextView.generateViewId());
        instructions.setText("Objective: Organize the numbers in ascending order (1-8  with the blank space at the end) from left to right in the grid by moving the blank space with one of its neighbor.");
        instructions.setTextColor(Color.parseColor("#0b1315"));
        instructions.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        instructions.setPadding(10 * DP,10 * DP, 10 * DP, 10 * DP);
        params = new RelativeLayout.LayoutParams(0, 0);
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.addRule(BELOW, gridOne.getId());
        params.topMargin = 40 * DP;
        instructions.setLayoutParams(params);
        addView(instructions);
    }
    //displays a win message and makes the background of the board red
    public void displayWin(){
        instructions.setText("Congratulations You Won!");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setBackgroundColor(Color.parseColor("#c80e05"));
            }
        }
    }
    //draws the board on the interface
    public void drawBoard(char[][] board){
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(board[i][j] == '0'){
                    this.board[i][j].setText(' '+ "");
                }else {
                    this.board[i][j].setText(board[i][j] + "");
                }
            }
        }
    }
}