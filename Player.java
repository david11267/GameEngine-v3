import java.awt.*;
import java.util.*;

public class Player  {
    int score = 0;
    Bat bat = new Bat(250,600-50,100,50);
    ArrayList<Ball> balls = new ArrayList<Ball>();
    Dimension dimension = new Dimension();


    Player(Dimension dimension) {
    this.dimension = dimension;
    }
    public  void SpawnBalls(int amount){
        Random rnd = new Random();
        Timer timer = new Timer();
        for (int i = 0; i <amount ; i++) {

            timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    int xVel = rnd.nextInt(-3,3);
                    int yVel = rnd.nextInt(-3,3);
                    balls.add(new Ball(300,400,xVel,yVel));
                }
            },1000*i);
        }
    }
    void drawScore(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.drawString("SCORE:"+ this.score,100,50);
        graphics.drawString("Balls:"+ this.balls.size(),100,75);

    }

}
