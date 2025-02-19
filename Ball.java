import javax.naming.BinaryRefAddr;
import java.awt.*;
import java.util.Random;


public class Ball extends Sprite {
    public enum Directions {
        downRight, upRight, downLeft, upLeft;
    }
    Directions direction;
    int speed = 5;
    int size =10;

    void move(int speed, Directions direction) {
        switch (direction){
            case downRight:
                this.setX(getX()+speed);
                this.setY(getY()+speed);
                break;
            case upRight:
                this.setX(getX()+speed);
                this.setY(getY()-speed);
                break;
            case downLeft:
                this.setX(getX()-speed);
                this.setY(getY()+speed);
                break;
            case upLeft:
                this.setX(getX()-speed);
                this.setY(getY()-speed);
                break;
        }
    }

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        Random rand = new Random();
        this.direction = Directions.values()[rand.nextInt(4)];
    }

    @Override
    public void update(Keyboard keyboard) {
        move(this.speed, this.direction);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
