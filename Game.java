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
	GameBoard board;
	boolean gameOver=false;
	Dimension dimension;

	public Game(GameBoard board, Dimension dimension) {
		this.board = board;
		player = new Player(dimension,5);
		player.SpawnBalls(1,4);
		this.dimension = dimension;

		int brickBoundery = (int)(dimension.getWidth()/3);
		int brickAndSpaceWidth=brickBoundery/4;
		for (int i = 0; i <3 ; i++) {
			for (int j = 0; j <5 ; j++) {
				bricks.add(new Brick(brickBoundery+(j*brickAndSpaceWidth),100+(i*50),20,10));
			}

		}
	}

	void collisionChecks(Keyboard keyboard){
		for (int i = 0; i < player.balls.size() ; i++) {
			player.balls.get(i).update(keyboard);
			player.balls.get(i).collisionCheck(player.bat, keyboard);

			for (Wall wall: walls){
				player.balls.get(i).collisionCheck(wall.boundingBox);
			}

			for (int y = 0; y <bricks.size() ; y++) {
				boolean died = bricks.get(y).damageBrick(player.balls.get(i).collisionCheck(bricks.get(y).boundingBox));
				if (died){
					player.score += bricks.get(y).ScoreWorth;
					bricks.remove(bricks.get(y));
				}
			}
			if (player.balls.get(i).outOfBounds()){
				if(player.DestroyBallUntilGameOver(player.balls.get(i))){
					gameOver=true;
				};

			}
		}


	}

	public void update(Keyboard keyboard) {
		collisionChecks(keyboard);
		player.bat.update(keyboard);




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

		if (gameOver){
			graphics.setColor(Color.MAGENTA);
			graphics.drawString("Game over",dimension.width/2,dimension.height/2);
		}

		player.drawScore(graphics);
	}
}
