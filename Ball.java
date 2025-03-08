import javax.naming.BinaryRefAddr;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;


public class Ball extends Sprite {
    int xVelocity;
    int yVelocity;
    void move(int xVelocity, int yVelocity) {
        setX(getX()+(xVelocity));
        setY(getY()+(yVelocity));
        updateBoundingBoxPosition();
    }


    public Ball(int x, int y, int xVelocity, int  yVelocity) {
        super(x, y, 10, 10);
        this.xVelocity =xVelocity;
        this.yVelocity =yVelocity;
    }


    boolean outOfBounds(){
        //change this to be adjusted by Dimension class
        return (getY() >= 600||getY() <= 0||getX() >= 800||getX() <= 0);
    }

    public boolean collisionCheck(Rectangle otherBoundingBox) {

        if (otherBoundingBox.intersects(this.boundingBox)) {
            Rectangle collbox = otherBoundingBox.intersection(this.boundingBox);

            boolean fromLeft = collbox.width < collbox.height && this.boundingBox.getX() < otherBoundingBox.getX() ;
            boolean fromRight = collbox.width < collbox.height && this.boundingBox.getX() > otherBoundingBox.getX();
            boolean fromTop = collbox.width > collbox.height && this.boundingBox.getY() < otherBoundingBox.getY();
            boolean fromBottom = collbox.width > collbox.height && this.boundingBox.getY() > otherBoundingBox.getY();

            if (fromLeft || fromRight) this.xVelocity = xVelocity*-1;
            if (fromTop || fromBottom) this.yVelocity = yVelocity*-1;
            return true;
        }
        return false;
    }

    public boolean collisionCheck(Bat bat, Keyboard keyboard) {

        if (bat.boundingBox.intersects(this.boundingBox)) {
            Rectangle collbox = bat.boundingBox.intersection(this.boundingBox);

            boolean ballRightIntersection = collbox.width < collbox.height && this.boundingBox.getX() < bat.boundingBox.getX() && bat.getX()+bat.getWidth()/2 > this.getX()+getWidth();
            boolean ballLeftIntersection = collbox.width < collbox.height && this.boundingBox.getX() > bat.boundingBox.getX() && bat.getX()+bat.getWidth()/2 < this.getX();
            boolean ballBottomIntersection = collbox.width > collbox.height && this.boundingBox.getY() < bat.boundingBox.getY();

            if (ballRightIntersection){
                System.out.println("ballRightIntersection");
                    xVelocity = xVelocity*-1;
                    this.setX(bat.getX()-this.getWidth()-bat.speed);
            }
            if (ballLeftIntersection){
                System.out.println("ballLeftIntersection");
                xVelocity = xVelocity*-1;
                this.setX(bat.getX()+bat.getWidth()+bat.speed);
            }
            if (ballBottomIntersection){
                System.out.println("ballBottomIntersection");
                yVelocity=yVelocity*-1;

                if (keyboard.isKeyDown(Key.Left)){xVelocity--;}
                if (keyboard.isKeyDown(Key.Right)){xVelocity++;}
            }
            return true;
        }
        return false;
    }


    @Override
    public void update(Keyboard keyboard) {
        move(xVelocity,yVelocity);

    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());

    }
}
