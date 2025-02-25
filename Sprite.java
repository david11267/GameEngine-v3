import java.awt.*;

public abstract class Sprite {
	private int x, y, width, height;
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public Rectangle boundingBox;  //custom
	public void setX(int x) { this.x = x; };
	public void setY(int y) { this.y = y; };
	public void setWidth(int width) { this.width = width; };
	public void setHeight(int height) { this.height = height; };
	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		boundingBox = new Rectangle(getX(),getY(),getWidth(),getHeight());
	}
	public void updateBoundingBoxPosition(){
		//custom
		this.boundingBox.setLocation(getX(),getY());
	}
	public abstract void update(Keyboard keyboard);
	public abstract void draw(Graphics2D graphics);
}
