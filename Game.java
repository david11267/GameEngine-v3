import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Game {
	ArrayList<Wall> walls= new ArrayList<Wall>(Arrays.asList(
			new Wall(0,0,25,600),
			new Wall(800-25,0,25,600),
			new Wall(25,0,750,25)
	));
	Player player;
	ArrayList<Brick>bricks= new ArrayList<Brick>();


	public Game(GameBoard board, Dimension dimension) {
		player = new Player(dimension,5);
		//player.SpawnBalls(1,5);

		//testballs
		player.balls.add(new Ball(dimension.width/2,dimension.height-25,3,0));
		player.balls.add(new Ball(dimension.width/2,dimension.height/2,0,3));

		int brickBoundery = (int)(dimension.getWidth()/3);
		int brickAndSpaceWidth=brickBoundery/4;
		for (int i = 0; i <3 ; i++) {
			for (int j = 0; j <5 ; j++) {
				bricks.add(new Brick(brickBoundery+(j*brickAndSpaceWidth),100+(i*50),20,10));
			}

		}
	}

	void collisionChecks(Keyboard keyboard){
		for (Ball ball: player.balls){
			ball.update(keyboard);

			for (Wall wall: walls){
				ball.collisionCheck(wall.boundingBox);
			}

			ball.collisionCheck(player.bat, keyboard);
				for (int i = 0; i <bricks.size() ; i++) {
					boolean died = bricks.get(i).damageBrick(ball.collisionCheck(bricks.get(i).boundingBox));
					if (died){
						player.score += bricks.get(i).ScoreWorth;
						 bricks.remove(bricks.get(i));
					}
				}
		}
	}

	public void update(Keyboard keyboard) {
		collisionChecks(keyboard);
		player.bat.update(keyboard);
		collisionChecks(keyboard);


	}

	public void draw(Graphics2D graphics) {
		for (Wall wall : walls) {
			wall.draw(graphics);
		}
		player.bat.draw(graphics);

		for (int i = 0; i <player.balls.size() ; i++) {
			Ball currBall = player.balls.get(i);
			currBall.draw(graphics);
		}



		for (Brick brick:bricks){
			brick.draw(graphics);
		}

		player.drawScore(graphics);
	}
}
