

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 * Created by omer on 05.12.2015.
 */
public class GameController implements Runnable
{

    //VARIABLES
    public static final int BOX_HEIGHT	 = 30;
    public static final int BOX_WIDTH	 = 30;
    public static final int GRID_WIDTH 	 = 20;
    public static final int GRID_HEIGHT	 = 20;
    
    //hold a reference to the dragon canvas so that it can paint
    dragonCanvas canvas;
    private boolean enterName = true;
    private String name;
    private int oldscore;

	private Timer gameTimer;

    //private LinkedList<Point> dragon;// dragon stuff
    private Dragon dragon;

    //private Door door;

    //private Blocks blocks;

    //private Point fruit;

    private Stage stage;
    //private int direction = Directions.NO_DIRECTION;

    private int score = 0, curScore;

    private Thread runThread;
    //private Graphics globalGraphics;
    private boolean gameisOver =  false;
    private boolean levelPassed = false;

    // Create database
    Database test =  Main.db;

    boolean writen = false;

    Color backgroundColor;
    private boolean gamePaused;
    private int stageNum = 1;

    public GameController( dragonCanvas c)
    {
        canvas = c;

        dragon = new Dragon();
        //blocks = new Blocks();
        //door = new Door( new Point(GRID_WIDTH - 1, GRID_HEIGHT - 1));

        stage = new Stage(1);
        
        backgroundColor = canvas.getBackground1();
        dragon.defaultDragon();
        defaultGame();

        //replaceFruit(); // should be replaced by the following
        stage.replaceAnimal( dragon);

        //blocks are already in the stage.
        //blocks.createBlock(getRandomPoint());
        //blocks.createBlock(getRandomPoint());
       // blocks.createBlock( getRandomPoint());

        if (runThread == null)
        {
            runThread = new Thread(this);
            runThread.start();
        }

        gamePaused = false;
    }



    public void defaultGame()
    {
        score = 0;
        writen = false;
        stage.setDoorState( false);
        
    }

    public void notify( Graphics g, String type)
    {
        backgroundColor = Color.blue;

        if( type.equals("game_over"))
        {
            g.setColor(Color.red);
            g.setFont(new Font("default", Font.BOLD, 45));
            g.drawString("GAME IS OVER ", (150), (BOX_HEIGHT * GRID_HEIGHT) / 2);
            g.setColor(Color.BLACK);
        }
        if( type.equals("level_passed"))
        {

            g.setColor(Color.green);
            g.setFont(new Font("default", Font.BOLD, 30));
            g.drawString("STAGE " + (stageNum - 1) + " PASSED", (150), (BOX_HEIGHT * GRID_HEIGHT) / 2);
            g.setColor(Color.BLACK);
        }
        if(type.equals("pause"))
        {
            g.setColor(Color.yellow);
            g.setFont(new Font("default", Font.BOLD, 45));
            g.drawString("PRESS P TO CONT.", (150), (BOX_HEIGHT * GRID_HEIGHT) / 2);
            g.setColor(Color.BLACK);
        }
        
    }

    public BufferedImage draw()
    {

        BufferedImage bufferedimage = new BufferedImage(BOX_WIDTH * GRID_WIDTH + 10 , BOX_HEIGHT * GRID_HEIGHT + 10, BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics bufferGraphics = bufferedimage.getGraphics();
        
        Color defaultColor = bufferGraphics.getColor();

        bufferGraphics.clearRect(0, 0, BOX_WIDTH * GRID_WIDTH + 10, BOX_HEIGHT * GRID_HEIGHT + 10);

        bufferGraphics.setColor(backgroundColor);
        bufferGraphics.fillRect(0, 0, BOX_WIDTH * GRID_WIDTH + 10, BOX_HEIGHT * GRID_HEIGHT + 10);
        bufferGraphics.setColor(defaultColor);




        dragon.drawDragon(bufferGraphics);
        stage.drawAllComponents( bufferGraphics);
     //   drawScore(bufferGraphics);

        printScore();
  //      printName();

        if (gameisOver) {

            if( levelPassed)
            {
                notify( bufferGraphics, "level_passed");
            }
            else
            {
                if (!writen) {
                    LeaderBoard lb = new LeaderBoard();

                    test.writeToDb(name, curScore);               /** MUST BE CHANGED **/
                    lb.printScore();
                    curScore = 0;
                    writen = true;
                }
                notify( bufferGraphics, "game_over");
            }
        }
        else if( gamePaused)
        {
            notify( bufferGraphics, "paused");
        }
        else
           backgroundColor = canvas.getBackground1();  // Put first background color again.

        bufferGraphics.drawImage(bufferedimage, 0, 0, BOX_WIDTH * GRID_WIDTH, BOX_HEIGHT * GRID_HEIGHT, canvas);

        if(enterName){
        	name = enterName();
        	enterName = false;
        }
        return bufferedimage;
    }
    
    public void printName()
    {
    	DragonGui.printName(name);
    }
    

    public void printScore()
    {
    	DragonGui.printScore(score);
    }
	
    public static String enterName()
	{
    	Main.dragon.setVisible(false);
    	String name  = JOptionPane.showInputDialog("Please enter your name ");
    	
    	if (name == null){

	        Main.frame.setVisible(true);	        
	        }
    	else
    		Main.dragon.setVisible(true);
		return  name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
   
	public void drawScore (Graphics g)
    {

        g.setColor(Color.BLUE);
        g.setFont(new Font("default", Font.BOLD, 16));
        g.drawString("Score: " + score, 0, BOX_HEIGHT * GRID_HEIGHT + 20);

        g.setColor(Color.BLACK);

    }
    
    public void Move()
    {
        /** for start point gameover is always true. inorder to fix this bug I wrote this statement **/
        if(dragon.getDirection() ==  Directions.NO_DIRECTION){ // in order not to run this func
            printName();
        	return;
        }

        gameisOver =  false;
        levelPassed = false;


        Point head = dragon.getHead();
        Point newPoint = head;

        switch (dragon.getDirection()) {
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

        dragon.remove(dragon.getTail());

        if( curScore >= stage.getWinScore())
            stage.setDoorState(true);
        else
            stage.setDoorState(false);

        if (stage.isCoolidingWithAnimal(newPoint))
        {
            //the dragon has hit fruit
            Point addPoint =  (Point) newPoint.clone();
            score += 10;
            curScore = score;

            switch (dragon.getDirection()) {
                case Directions.NORTH:
                    newPoint = new Point(head.x, head.y - 1);
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
            dragon.push(addPoint); // when it hits to fruit, dragon will grow up
            stage.replaceAnimal(dragon);
            printName();

        }
        else if( stage.isCollidingWithDoor(newPoint))
        {
            if( stage.isDoorOpen())
            {
                dragon.defaultDragon();
                oldscore = score;
                defaultGame();
                score = oldscore;
                oldscore = 0;
                gameisOver =  true;
                levelPassed = true;

                if( stageNum == 3)
                    stageNum = 1;

                stage = new Stage( ++stageNum);
                stage.replaceAnimal( dragon);
            }
            else
            {
                dragon.defaultDragon();
                defaultGame();
                gameisOver =  true;
            }

            return;

        }
        else if( stage.isCollidingWithBlock( newPoint))
        {
            dragon.defaultDragon();
            defaultGame();
            gameisOver =  true;
            return;
        }
        else if (newPoint.x < 0 || newPoint.x > GRID_WIDTH-1)
        {
            //we went oob, reset game
            dragon.defaultDragon();
            defaultGame();
            gameisOver =  true;
            return;
        }
        else if (newPoint.y < 0 || newPoint.y > GRID_HEIGHT-1)
        {
            //we went oob, reset game
            dragon.defaultDragon();
            defaultGame();
            gameisOver =  true;
            return;

        }
        else if (dragon.contains(newPoint))
        {
            //we ran into ourselves, reset game
            dragon.defaultDragon();
            defaultGame();
            gameisOver =  true;
            
            return;
        }

        //if we reach this point in code, we're still good
        dragon.push(newPoint);  // if we're good, so dragon should move
    }

    public void keyPressed(KeyEvent e) {



        int direction = dragon.getDirection();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (direction !=  Directions.SOUTH)
                    dragon.setDirection(Directions.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                if (direction !=  Directions.NORTH)
                    dragon.setDirection(Directions.SOUTH);
                break;
            case KeyEvent.VK_RIGHT:
                if (direction !=  Directions.WEST)
                    dragon.setDirection(Directions.EAST);
                break;
            case KeyEvent.VK_LEFT:
                if (direction !=  Directions.EAST)
                    dragon.setDirection(Directions.WEST);
                break;
            case KeyEvent.VK_W:
                if (direction !=  Directions.SOUTH)
                    dragon.setDirection(Directions.NORTH);
                break;
            case KeyEvent.VK_S:
                if (direction !=  Directions.NORTH)
                    dragon.setDirection(Directions.SOUTH);
                break;
            case KeyEvent.VK_D:
                if (direction !=  Directions.WEST)
                    dragon.setDirection(Directions.EAST);
                break;
            case KeyEvent.VK_A:
                if (direction !=  Directions.EAST)
                    dragon.setDirection(Directions.WEST);
                break;
            case KeyEvent.VK_P:
                if( gameTimer.isRunning())
                {
                    if( !gameisOver)
                    {
                        gameTimer.stop();
                        gamePaused = true;
                    }

                }
                else
                {
                    gameTimer.start();
                    gamePaused = false;
                }

                break;


            default:
                break;
        }

    }

    @Override
    public void run() {
        //c = getBackground(); // in order to save background color
        /*while (true)
        {
            //runs indefinitely
            Move();
            canvas.draw(draw());

            try
            {
                Thread.currentThread();
                Thread.sleep(200);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }*/

        gameTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Move();
                canvas.draw(draw());
            }
        });
        gameTimer.start();
    }
}
