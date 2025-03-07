import java.awt.*;

public class Bat extends Wall {
    int speed = 5;
    String name;
    Rectangle boundingBox;
    private String getName(){return this.name;}

    public Bat(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.GRAY;
        this.name = "D";
        this.boundingBox = new Rectangle(x, y, width, height);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        graphics.setColor(Color.white);
        graphics.drawString(getName(),getX(),(getY()+getHeight()/2));
    }

    @Override
    public void update(Keyboard keyboard) {
        if (getX()<=775-getWidth()  && keyboard.isKeyDown(Key.Right)) {
            this.setX(getX()+speed);
        }
        if (getX()>=25  && keyboard.isKeyDown(Key.Left)) {
            this.setX(getX()-speed);
        }
        boundingBox.setLocation(getX(), getY());
    }
}
