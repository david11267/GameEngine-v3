import java.awt.*;
import java.util.*;


public class Game {
	ArrayList<Wall> walls= new ArrayList<Wall>(Arrays.asList(
			new Wall(0,0,25,600),
			new Wall(800-25,0,25,600),
			new Wall(25,0,750,25)
	));

	Player player = new Player();
	ArrayList<Ball> balls = new ArrayList<Ball>();



	public Game(GameBoard board, Dimension dimension) {
		balls.add(new Ball(dimension.width/2, dimension.height/2, 10, 10));
	}

	public void update(Keyboard keyboard) {
		player.bat.update(keyboard);

		for (Ball ball: balls){
			ball.update(keyboard);
		}
	}

	public void draw(Graphics2D graphics) {
		for (Wall wall : walls) {
			wall.draw(graphics);
		}

		player.bat.draw(graphics);
		balls.forEach(ball -> ball.draw(graphics));
	}
}
