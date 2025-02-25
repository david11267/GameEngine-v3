import java.awt.*;
import java.util.Random;

public class Brick extends Sprite {
    int state;
    Color color;
    int HP;
    int ScoreWorth;

    public Color getColor() {
        return color;
    }

    public Brick(int x, int y, int width, int height) {
        super(x, y, width, height);
        Random rand = new Random();
        state = rand.nextInt(3);
        boundingBox.setBounds(getX(),getY(),getWidth(),getHeight());

        if (state ==0){
            color = Color.BLUE;
            HP =1;
            ScoreWorth=1;
        }
        if (state ==1){
            color = Color.GREEN;
            HP =2;
            ScoreWorth=2;
        }
        if (state ==2){
            color = Color.RED;
            HP =3;
            ScoreWorth=3;
        }

    }

    boolean damageBrick(boolean hit){
        if (hit){
            HP--;
            color = new Color(color.getRed(),color.getGreen(),color.getBlue(),(color.getAlpha()/2));
            if (HP==0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(Keyboard keyboard) {

    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(getColor());
        graphics.fillRect(getX(),getY(),getWidth(),getHeight());
    }
}
