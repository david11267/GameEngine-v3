import javax.naming.BinaryRefAddr;
import java.awt.*;
import java.awt.geom.Rectangle2D;
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

            boolean fromLeft = collbox.width < collbox.height && this.boundingBox.getX() < otherBoundingBox.getX();
            boolean fromRight = collbox.width < collbox.height && this.boundingBox.getX() > otherBoundingBox.getX();
            boolean fromTop = collbox.width > collbox.height && this.boundingBox.getY() < otherBoundingBox.getY();
            boolean fromBottom = collbox.width > collbox.height && this.boundingBox.getY() > otherBoundingBox.getY();

            if (fromLeft || fromRight) this.xVelocity = xVelocity*-1;
            if (fromTop || fromBottom) this.yVelocity = yVelocity*-1;
            return true;
        }
        return false;
    }

    public boolean collisionCheck(Bat batBoundingBox, Keyboard keyboard) {

        if (batBoundingBox.boundingBox.intersects(this.boundingBox)) {
            Rectangle collbox = batBoundingBox.boundingBox.intersection(this.boundingBox);

            boolean ballRightIntersection = collbox.width < collbox.height && this.boundingBox.getX() < batBoundingBox.boundingBox.getX();
            boolean ballLeftIntersection = collbox.width < collbox.height && this.boundingBox.getX() > batBoundingBox.boundingBox.getX();
            boolean ballBottomIntersection = collbox.width > collbox.height && this.boundingBox.getY() < batBoundingBox.boundingBox.getY();
            boolean ballTopIntersection = collbox.width > collbox.height && this.boundingBox.getY() > batBoundingBox.boundingBox.getY();

            if (ballRightIntersection || ballLeftIntersection) {
                if (xVelocity > 0) {
                    xVelocity = (xVelocity + batBoundingBox.speed) * -1;
                } else if (xVelocity < 0) {
                    xVelocity = (xVelocity - batBoundingBox.speed) * -1;
                }


                if (ballRightIntersection && keyboard.isKeyDown(Key.Left)) {
                    System.out.println("ball right coll ");
                    this.setX(batBoundingBox.getX() - this.getWidth() - xVelocity);
                }
            }

            if (ballLeftIntersection && keyboard.isKeyDown(Key.Right)) {
                System.out.println("ball left coll ");
                this.setX(batBoundingBox.getX() + getWidth() + xVelocity);
            }



            if (ballTopIntersection || ballBottomIntersection){
                yVelocity = yVelocity*-1;

                if (keyboard.isKeyDown(Key.Right)){
                    xVelocity++;
                }

                if (keyboard.isKeyDown(Key.Left)){
                    xVelocity--;
                }
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
