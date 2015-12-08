

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;


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
    


    //private LinkedList<Point> dragon;// dragon stuff
    private Dragon dragon;
    private DragonGui dg = new DragonGui();

    private Door door;

    private Blocks blocks;

    private Point fruit;
    //private int direction = Directions.NO_DIRECTION;

    private int score = 0, curScore;

    private Thread runThread;
    //private Graphics globalGraphics;
    private boolean gameisOver =  false;
    private boolean levelPassed = false;

    // Create database
    Database test =  new Database();

    boolean writen = false;

    Color backgroundColor;

    public GameController( dragonCanvas c)
    {
        canvas = c;

        dragon = new Dragon();
        blocks = new Blocks();
        door = new Door( new Point(GRID_WIDTH - 1, GRID_HEIGHT - 1));
        
        backgroundColor = canvas.getBackground1();
        dragon.defaultDragon();
        defaultGame();
        replaceFruit();

        blocks.createBlock(getRandomPoint());
        blocks.createBlock(getRandomPoint());
        blocks.createBlock( getRandomPoint());

        if (runThread == null)
        {
            runThread = new Thread(this);
            runThread.start();
        }
    }

    public Point getRandomPoint()
    {
        Random random =  new Random ();
        int randomX = random.nextInt(GRID_WIDTH);
        int randomY = random.nextInt(GRID_HEIGHT);
        Point randomPoint =  new Point(randomX, randomY);

        while (dragon.contains(randomPoint) && !randomPoint.equals( fruit) && !blocks.contains( randomPoint)) {
            randomX = random.nextInt(GRID_WIDTH);
            randomY = random.nextInt(GRID_HEIGHT);
            randomPoint = new Point(randomX, randomY);
        }
        return randomPoint;

    }

    /*public void paint(Graphics g)
    {
        this.setPreferredSize(new Dimension(720, 680));

        //dragon = new LinkedList<Point>(); // dragon stuff


        globalGraphics = g.create();
        this.addKeyListener(this); // add its own keyListener

        
    }*/

    public void replaceFruit()
    {
        Random random =  new Random ();
        int randomX = random.nextInt(GRID_WIDTH);
        int randomY = random.nextInt(GRID_HEIGHT);
        Point randomPoint =  new Point(randomX, randomY);

        while (dragon.contains(randomPoint) ) {
            randomX = random.nextInt(GRID_WIDTH);
            randomY = random.nextInt(GRID_HEIGHT);
            randomPoint = new Point(randomX, randomY);
        }
        fruit = randomPoint;

    }


    public void defaultGame()
    {
        score = 0;
        writen = false;
        door.setState( false);
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
            g.setFont(new Font("default", Font.BOLD, 45));
            g.drawString("LEVEL PASSED", (150), (BOX_HEIGHT * GRID_HEIGHT) / 2);
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



        DrawFruit(bufferGraphics);
        DrawGrid(bufferGraphics);
        dragon.drawDragon(bufferGraphics);
        blocks.drawBlocks(bufferGraphics);
        door.drawDoor( bufferGraphics);
        drawScore(bufferGraphics);
        printScore();

        if (gameisOver) {

            if( levelPassed)
            {
                notify( bufferGraphics, "level_passed");
            }
            else
            {

                if (!writen) {
                    LeaderBoard lb = new LeaderBoard();
                   // String name = DragonGui.textField_1.getText(); /** ****** ******* **/
                  //  test.writeToDb(name, curScore);               /** MUST BE CHANGED **/
                    lb.printScore();
                    curScore = 0;
                    writen = true;
                }
                notify( bufferGraphics, "game_over");
            }


        }
        else
           backgroundColor = canvas.getBackground1();  // Put first background color again.

        bufferGraphics.drawImage(bufferedimage, 0, 0, BOX_WIDTH * GRID_WIDTH, BOX_HEIGHT * GRID_HEIGHT, canvas);

        return bufferedimage;
    }



    public void printScore()
    {
    	dg.printScore(score);
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

        if( curScore == 100)
            door.setState( true);

        if (newPoint.equals(fruit))
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
            replaceFruit();

        }
        else if( door.getLocation().equals( head))
        {
            if( door.getState())
            {
                dragon.defaultDragon();
                defaultGame();
                gameisOver =  true;
                levelPassed = true;
            }
            else
            {
                dragon.defaultDragon();
                defaultGame();
                gameisOver =  true;
            }

            return;

        }
        else if( blocks.contains( head))
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






    public void DrawFruit(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillOval(fruit.x * BOX_WIDTH, fruit.y * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
        g.setColor(Color.BLACK);
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

        Timer t = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Move();
                canvas.draw(draw());
            }
        });

        t.start();



    }
}
