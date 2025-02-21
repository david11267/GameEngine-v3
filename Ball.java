import javax.naming.BinaryRefAddr;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;


public class Ball extends Sprite {

    int xDir, yDir;
    int speed = 1;
    int size =10;
    Rectangle boundingBox;

    void move(int speed, int xDir, int yDir) {
        setX(getX()+(xDir*speed));
        setY(getY()+(yDir*speed));
        this.boundingBox.setLocation(getX(), getY());
    }

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        Random rand = new Random();
        xDir = rand.nextBoolean() ? -1 : 1;
        yDir = rand.nextBoolean() ? -1 : 1;
        this.boundingBox = new Rectangle(x,y,width,height);
    }


    public void collisionCheck(Rectangle otherBoundingBox) {

        if (otherBoundingBox.intersects(this.boundingBox)) {
            System.out.println("Collision Detected");
            Rectangle collbox = otherBoundingBox.intersection(this.boundingBox);

            boolean fromLeft = collbox.width < collbox.height && this.boundingBox.x < otherBoundingBox.x;
            boolean fromRight = collbox.width < collbox.height && this.boundingBox.x > otherBoundingBox.x;
            boolean fromTop = collbox.width > collbox.height && this.boundingBox.y < otherBoundingBox.y;
            boolean fromBottom = collbox.width > collbox.height && this.boundingBox.y > otherBoundingBox.y;

            if (fromLeft) this.xDir = -xDir;
            if (fromRight) this.xDir = -xDir;
            if (fromTop) this.yDir = -yDir;
            if (fromBottom) this.yDir = -yDir;

        }



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
