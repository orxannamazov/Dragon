import java.awt.*;
import java.util.LinkedList;

/**
 * Created by omer on 05.12.2015.
 */
public class Dragon
{

    private LinkedList<Point> dragon;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    private int direction;

    public Dragon()
    {
        dragon = new LinkedList<Point>();
    }


    public void defaultDragon()
    {
        dragon.clear();

        dragon.add(new Point(3,1));
        dragon.add(new Point(2,1));
        dragon.add(new Point(1,1));

        direction = Directions.NO_DIRECTION;
    }

    public void drawDragon(Graphics g)
    {
        g.setColor(Color.GREEN);
        for (Point p : dragon)
        {
            g.fillOval(p.x * dragonCanvas.BOX_WIDTH, p.y * dragonCanvas.BOX_HEIGHT,
                                    dragonCanvas.BOX_WIDTH, dragonCanvas.BOX_HEIGHT);
        }
        g.setColor(Color.BLACK);
    }

    public void push( Point newPoint)
    {

        dragon.push(newPoint);
    }

    public boolean contains( Point newPoint)
    {

        return dragon.contains( newPoint);
    }

    public Point getHead()
    {

        return dragon.peekFirst();
    }

    public boolean remove( Point p)
    {
        return dragon.remove( p);
    }

    public Point getTail()
    {
        return dragon.peekLast();
    }
}
