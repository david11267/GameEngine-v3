import javax.naming.BinaryRefAddr;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;


public class Ball extends Sprite {

    int xDir, yDir;
    int speed = 4;
    int size =10;
    void move(int speed, int xDir, int yDir) {
        setX(getX()+(xDir*speed));
        setY(getY()+(yDir*speed));
        updateBoundingBoxPosition();
    }


    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        Random rand = new Random();
        xDir = rand.nextBoolean() ? -1 : 1;
        yDir = rand.nextBoolean() ? -1 : 1;
    }


    boolean outOfBounds(){
        if (getY() >= 600){
            return true;}
        return false;
    }

    public boolean collisionCheck(Rectangle otherBoundingBox) {

        if (otherBoundingBox.intersects(this.boundingBox)) {
            System.out.println("Collision Detected");
            Rectangle collbox = otherBoundingBox.intersection(this.boundingBox);

            boolean fromLeft = collbox.width < collbox.height && this.boundingBox.getX() < otherBoundingBox.getX();
            boolean fromRight = collbox.width < collbox.height && this.boundingBox.getX() > otherBoundingBox.getX();
            boolean fromTop = collbox.width > collbox.height && this.boundingBox.getY() < otherBoundingBox.getY();
            boolean fromBottom = collbox.width > collbox.height && this.boundingBox.getY() > otherBoundingBox.getY();

            if (fromLeft) this.xDir = -xDir;
            if (fromRight) this.xDir = -xDir;
            if (fromTop) this.yDir = -yDir;
            if (fromBottom) this.yDir = -yDir;

            return true;
        }
        return false;
    }


    @Override
    public void update(Keyboard keyboard) {
        move(speed, xDir, yDir);

    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());

    }
}
