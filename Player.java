import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Player  {
    int score = 0;
    Bat bat = new Bat(250,600-50,100,50);
    ArrayList<Ball> balls = new ArrayList<Ball>();
    Dimension dimension = new Dimension();

    Player(Dimension dimension) {
    this.dimension = dimension;
    }


}
