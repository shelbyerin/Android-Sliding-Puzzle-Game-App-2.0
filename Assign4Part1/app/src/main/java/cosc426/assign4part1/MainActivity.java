package cosc426.assign4part1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    InterfaceBuilder appInterface;
    Game game;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //gets the interface from the class
        appInterface = new InterfaceBuilder(this);
        setContentView(appInterface);
        //creates boards in the constructor and draws by calling to appInterface
        game = new Game();
        appInterface.drawBoard(game.board);
        TouchHander temp = new TouchHander();
        appInterface.setOnTouchListener(temp);
    }
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private class TouchHander implements View.OnTouchListener{
        private int startX;
        private int startY;
        private int endY;
        private int endX;
        public  boolean onTouch(View v, MotionEvent event){
            if(!game.checkWin()) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    startX = x;
                    startY = y;
                    game.setStart(startX, startY);
                }
                if (action == MotionEvent.ACTION_UP) {
                    endX = x;
                    endY = y;
                    game.setEnd(endX, endY);
                }
                if (game.checkForBlank()) {
                    game.moveBlank();
                }
                appInterface.drawBoard(game.board);
                return true;
            }else{
                appInterface.displayWin();
                return false;
            }
        }
    }
}
