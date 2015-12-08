import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by omer on 08.12.2015.
 */
public class Stage
{

    //VARIABLES
    public static final int BOX_HEIGHT	 = 30;
    public static final int BOX_WIDTH	 = 30;
    public static final int GRID_WIDTH 	 = 20;
    public static final int GRID_HEIGHT	 = 20;


    private Door door;

    private Blocks blocks;

    private Animal animal;
    //private int direction = Directions.NO_DIRECTION;

    private int winScore;

    public int getWinScore()
    {
        return winScore;
    }

    public Stage( int stageNum)
    {
        blocks = new Blocks();

        animal = new Animal( new Point(-1,-1), 10, 10);

        switch (stageNum)
        {
            case 1:
                door = new Door( new Point((GRID_WIDTH - 1) / 2, (GRID_HEIGHT - 1) / 2));

                winScore = 20;
                break;
            case 2:
                blocks.createBlock(new Point(5,5));
                blocks.createBlock(new Point(6,6));
                blocks.createBlock(new Point(7,7));

                door = new Door( new Point((GRID_WIDTH - 1), (GRID_HEIGHT - 3)));
                winScore = 100;
                break;
            case 3:
                blocks.createBlock(new Point(5,5));
                blocks.createBlock(new Point(6,6));
                blocks.createBlock(new Point(7,7));
                blocks.createBlock(new Point(8,8));
                blocks.createBlock(new Point(9,9));


                door = new Door( new Point((GRID_WIDTH - 1), (GRID_HEIGHT - 1)));

                winScore = 150;
                break;
        }


    }


    public void drawAllComponents( Graphics bufferGraphics)
    {
        DrawGrid(bufferGraphics);
        blocks.drawBlocks(bufferGraphics);
        door.drawDoor(bufferGraphics);
        animal.drawAnimal( bufferGraphics);

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

    public boolean contains( Point p)
    {
        if( blocks.contains(p) || door.getLocation().equals( p) || animal.getLocation().equals( p))
            return true;
        return false;
    }

    public boolean isCollidingWithDoor( Point p)
    {
        return door.getLocation().equals( p);
    }

    public boolean isCollidingWithBlock( Point p)
    {
        return blocks.contains(p);
    }

    public boolean isCoolidingWithAnimal( Point p)
    {
        return animal.getLocation().equals(p);
    }

    public boolean isDoorOpen()
    {
        return door.getState();
    }

    public void replaceAnimal( Dragon dragon)
    {
        Random random =  new Random ();
        int randomX = random.nextInt(GRID_WIDTH);
        int randomY = random.nextInt(GRID_HEIGHT);
        Point randomPoint =  new Point(randomX, randomY);

        while (dragon.contains(randomPoint) || this.contains( randomPoint) ) {
            randomX = random.nextInt(GRID_WIDTH);
            randomY = random.nextInt(GRID_HEIGHT);
            randomPoint = new Point(randomX, randomY);
        }
        animal.setLocation(randomPoint);

    }

    public void setDoorState( boolean state)
    {
        door.setState( state);
    }

}