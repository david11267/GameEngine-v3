import java.awt.*;
import java.util.*;

public class Player  {
    int score = 0;
    Bat bat;
    ArrayList<Ball> balls = new ArrayList<Ball>();
    int ballsLeft;
    Dimension dimension = new Dimension();


    Player(Dimension dimension, int totalBalls) {
        bat = new Bat(dimension.width/2-50,dimension.height-50,100,50);
         this.dimension = dimension; this.ballsLeft = totalBalls;
    }

    public  void SpawnBalls(int amount, int speed){
        Random rnd = new Random();
        Timer timer = new Timer();
        for (int i = 0; i <amount ; i++) {

            timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    int xVel = rnd.nextInt(-1*speed,1*speed);
                    int yVel =0;
                    do {
                        yVel=rnd.nextInt(-1*speed,1*speed);
                    } while(yVel==0);

                    balls.add(new Ball(dimension.width/2,dimension.height/2,xVel,yVel));
                }
            },1000*i);
        }
    }
    public  boolean DestroyBallUntilGameOver(Ball ball){
        balls.remove(ball);
        ballsLeft--;
        if (ballsLeft > 0){
            this.SpawnBalls(1,5);
            return false;
        }
        return true;
    }


    void drawScore(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.drawString("SCORE:"+ this.score,100,50);
        graphics.drawString("Balls:"+ this.ballsLeft,100,75);

    }

}
