import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Wall extends Sprite {
    Color color;
    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.ORANGE;
    }

    @Override
    public void update(Keyboard keyboard) {
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
