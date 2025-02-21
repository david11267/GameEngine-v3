import java.awt.*;
import java.util.*;


public class Game {
	ArrayList<Wall> walls= new ArrayList<Wall>(Arrays.asList(
			new Wall(0,0,25,600),
			new Wall(800-25,0,25,600),
			new Wall(25,0,750,25)
	));

	Player player;


	public Game(GameBoard board, Dimension dimension) {
		player = new Player(dimension);
		player.balls.add(new Ball(dimension.width/2, dimension.height/2, 10, 10));
		player.balls.add(new Ball(dimension.width/2, dimension.height/2, 10, 10));
		player.balls.add(new Ball(dimension.width/2, dimension.height/2, 10, 10));

	}

	public void update(Keyboard keyboard) {
		player.bat.update(keyboard);
		for (Ball ball: player.balls){
			ball.update(keyboard);
			for (Wall wall: walls){
				ball.collisionCheck(wall.boundingBox);
			}
			ball.collisionCheck(player.bat.boundingBox);

		}
	}

	public void draw(Graphics2D graphics) {
		for (Wall wall : walls) {
			wall.draw(graphics);
		}
		player.bat.draw(graphics);
		player.balls.forEach(ball -> ball.draw(graphics));
	}
}
