import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

import javafx.scene.layout.Background;

public class dragonCanvas extends Canvas implements Runnable, KeyListener
{
	//VARIABLES
	private final int BOX_HEIGHT	 = 30;
	private final int BOX_WIDTH		 = 30;
	private final int GRID_WIDTH 	 = 20;
	private final int GRID_HEIGHT	 = 20;

	Color c; 

	private LinkedList<Point> dragon;
	private Point fruit;
	private int direction = Directions.NO_DIRECTION;
	
	private int score = 0;

	private Thread runThread;
	private Graphics globalGraphics;
	private boolean gameisOver =  false;

	public void paint(Graphics g) 
	{
		this.setPreferredSize(new Dimension(720, 680));
		
		dragon = new LinkedList<Point>();
		
		DefaultDragon();
		replaceFruit();
		
		globalGraphics = g.create();
		this.addKeyListener(this); // add its own keyListener
		
		if (runThread == null) 
		{
			runThread = new Thread(this);
			runThread.start();
		}
	}

	public void replaceFruit()
	{
		Random random =  new Random ();
		int randomX = random.nextInt(GRID_WIDTH);
		int randomY = random.nextInt(GRID_HEIGHT); 
		Point randomPoint =  new Point(randomX, randomY);
		
		while (dragon.contains(randomPoint)) {
			randomX = random.nextInt(GRID_WIDTH);
			randomY = random.nextInt(GRID_HEIGHT); 
			randomPoint = new Point(randomX, randomY); 
		}
		fruit = randomPoint;
		
	}
	
	public void DefaultDragon()
	{
		dragon.clear();
		
		dragon.add(new Point(3,1));
		dragon.add(new Point(2,1));
		dragon.add(new Point(1,1));
		
		direction = Directions.NO_DIRECTION;
		
		score = 0;
	}
	
	public void Draw(Graphics g) 
	{
		
		g.clearRect(0, 0, BOX_WIDTH * GRID_WIDTH + 10 , BOX_HEIGHT * GRID_HEIGHT + 30);
		
		BufferedImage bufferedimage = new BufferedImage(BOX_WIDTH * GRID_WIDTH + 10 , BOX_HEIGHT * GRID_HEIGHT + 30, BufferedImage.TYPE_INT_ARGB);
		Graphics bufferGraphics = bufferedimage.getGraphics();
		
		DrawFruit(bufferGraphics);
		DrawGrid(bufferGraphics);
		DrawDragon(bufferGraphics);
		drawScore(bufferGraphics);
		
	if (gameisOver) {
			this.setBackground(Color.BLUE);
			gameOverDisplay(bufferGraphics);
	}
	else	
		this.setBackground(c);

	
		g.drawImage(bufferedimage, 0, 0, BOX_WIDTH * GRID_WIDTH, BOX_HEIGHT * GRID_HEIGHT, this);
	}
	
	
	
	public void drawScore (Graphics g)
	{
		g.setColor(Color.BLUE);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString("Score: " + score , 0 , BOX_HEIGHT * GRID_HEIGHT + 20);
		
		g.setColor(Color.BLACK);
		
	}
	public void Move() 
	{
		if(direction ==  Directions.NO_DIRECTION){
			return;
		}

		gameisOver =  false;
	
		Point head = dragon.peekFirst();
		Point newPoint = head;
		
		switch (direction) {
		case Directions.NORTH:
			newPoint = new Point(head.x, head.y- 1);
			break;
		case Directions.SOUTH:
			newPoint = new Point(head.x, head.y + 1);
			break;
		case Directions.WEST:
			newPoint = new Point(head.x - 1, head.y);
			break;
		case Directions.EAST:
			newPoint = new Point(head.x + 1, head.y);
			break;
		}

		dragon.remove(dragon.peekLast());

		if (newPoint.equals(fruit)) 
		{
			//the dragon has hit fruit
			Point addPoint =  (Point) newPoint.clone();
			score += 10;
			
			switch (direction) {
			case Directions.NORTH:
				newPoint = new Point(head.x, head.y- 1);
				break;
			case Directions.SOUTH:
				newPoint = new Point(head.x, head.y + 1);
				break;
			case Directions.WEST:
				newPoint = new Point(head.x - 1, head.y);
				break;
			case Directions.EAST:
				newPoint = new Point(head.x + 1, head.y);
				break;
			}
			dragon.push(addPoint);
			replaceFruit();
			
		}
		else if (newPoint.x < 0 || newPoint.x > GRID_WIDTH-1)
		{
			//we went oob, reset game
			DefaultDragon();
			gameisOver =  true;
			return;
		}
		else if (newPoint.y < 0 || newPoint.y > GRID_HEIGHT-1) 
		{
			//we went oob, reset game
			DefaultDragon();
			gameisOver =  true;
			return;
			
		}
		else if (dragon.contains(newPoint)) 
		{
			//we ran into ourselves, reset game
			DefaultDragon();
			gameisOver =  true;
			return;
		}

		//if we reach this point in code, we're still good
		dragon.push(newPoint);
	}

	public void DrawGrid(Graphics g) 
	{
		//drawing an outside rect
		g.drawRect(0, 0, GRID_WIDTH * BOX_WIDTH, GRID_HEIGHT * BOX_HEIGHT);
		//drawing the vertical lines
		for (int x = BOX_WIDTH; x <= GRID_WIDTH * BOX_WIDTH; x+=BOX_WIDTH)
		{
			g.drawLine(x, 0, x, BOX_HEIGHT * GRID_HEIGHT);
		}
		//drawing the horizontal lines
		for (int y = BOX_HEIGHT; y < GRID_HEIGHT * BOX_HEIGHT; y+=BOX_HEIGHT) 
		{
			g.drawLine(0, y, GRID_WIDTH * BOX_WIDTH, y);
		}
	}

	public void DrawDragon(Graphics g)
	{
		g.setColor(Color.GREEN);
		for (Point p : dragon)
		{
			g.fillOval(p.x * BOX_WIDTH, p.y * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
		}
		g.setColor(Color.BLACK);
	}
	
	
	public void gameOverDisplay (Graphics g)
	{
		g.setColor(Color.red);
		g.setFont(new Font("default", Font.BOLD, 45));
		g.drawString("GAME IS OVER ", (150) , (BOX_HEIGHT * GRID_HEIGHT)/2);
		g.setColor(Color.BLACK);
	}

	public void DrawFruit(Graphics g) 
	{
		g.setColor(Color.RED);
		g.fillOval(fruit.x * BOX_WIDTH, fruit.y * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
		g.setColor(Color.BLACK);
	}

	@Override
	public void run() {
		c = getBackground();
		while (true) 
		{
			//runs indefinitely
			Move();
			Draw(globalGraphics);

			try 
			{
				Thread.currentThread();
				Thread.sleep(120);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (direction !=  Directions.SOUTH)
				direction = Directions.NORTH;
			break;
		case KeyEvent.VK_DOWN:
			if (direction !=  Directions.NORTH)
				direction = Directions.SOUTH;
			break;
		case KeyEvent.VK_RIGHT:
			if (direction !=  Directions.WEST)
				direction = Directions.EAST;
			break;
		case KeyEvent.VK_LEFT:
			if (direction !=  Directions.EAST)
				direction = Directions.WEST;
			break;

		default:
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}