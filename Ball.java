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
        return getY() >= 600;
    }

    public boolean collisionCheck(Rectangle otherBoundingBox) {

        if (otherBoundingBox.intersects(this.boundingBox)) {
            System.out.println("Collision Detected");
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
