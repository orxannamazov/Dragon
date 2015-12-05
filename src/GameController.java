import java.awt.*;
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

    Color c;

    //private LinkedList<Point> dragon;// dragon stuff
    private Dragon dragon;


    private Point fruit;
    //private int direction = Directions.NO_DIRECTION;

    private int score = 0, curScore;

    private Thread runThread;
    //private Graphics globalGraphics;
    private boolean gameisOver =  false;

    // Create database
    Database test =  new Database("localhost", 27017,  "DragonValley", "Users");

    boolean writen = false;

    //hold a reference to the dragon canvas so that it can paint
    dragonCanvas canvas;



    public GameController( dragonCanvas c)
    {
        canvas = c;

        dragon = new Dragon();

        dragon.defaultDragon();
        defaultGame();
        replaceFruit();

        if (runThread == null)
        {
            runThread = new Thread(this);
            runThread.start();
        }
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

        while (dragon.contains(randomPoint)) {
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
    }

    public BufferedImage draw()
    {

        BufferedImage bufferedimage = new BufferedImage(BOX_WIDTH * GRID_WIDTH + 10 , BOX_HEIGHT * GRID_HEIGHT + 30, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics bufferGraphics = bufferedimage.getGraphics();
        bufferGraphics.clearRect(0, 0, BOX_WIDTH * GRID_WIDTH + 10 , BOX_HEIGHT * GRID_HEIGHT + 30);

        DrawFruit(bufferGraphics);
        DrawGrid(bufferGraphics);
        dragon.drawDragon(bufferGraphics);
        drawScore(bufferGraphics);

        if (gameisOver) {
            canvas.setBackground(Color.BLUE);
            if (!writen) {

                String name = DragonGui.textField_1.getText(); /** ****** ******* **/
                test.writeToDb(name, curScore);               /** MUST BE CHANGED **/

                curScore = 0;
                writen = true;
            }
            gameOverDisplay(bufferGraphics);
        }
        else
            canvas.setBackground(c);  // Put first background color again.

        bufferGraphics.drawImage(bufferedimage, 0, 0, BOX_WIDTH * GRID_WIDTH, BOX_HEIGHT * GRID_HEIGHT, canvas);

        return bufferedimage;
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
            System.out.println("not moving");
            return;
        }

        gameisOver =  false;

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
        while (true)
        {
            //runs indefinitely
            Move();
            canvas.draw(draw());

            try
            {
                Thread.currentThread();
                Thread.sleep(110);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
