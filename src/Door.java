
import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Güray
 */
public class Door 
{
    private Point location;
    private BufferedImage doorImage;
    private boolean state;
    
    public Door(Point location)
    {
        this.location = location;
        state = false;
    }
    
    public boolean isOpened(){
        return state;
    }
    
    public void setState(boolean isOpened){
        state = isOpened;
    }

    public boolean getState(){
        return state;
    }

    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * @return the doorImage
     */
    public BufferedImage getDoorImage() {
        return doorImage;
    }

    public void drawDoor( Graphics g)
    {
        if( state)
            g.setColor(Color.blue);
        else
            g.setColor(Color.black);
        g.fillRoundRect(location.x * dragonCanvas.BOX_WIDTH, location.y * dragonCanvas.BOX_HEIGHT,
                dragonCanvas.BOX_WIDTH, dragonCanvas.BOX_HEIGHT, 5, 5);
        g.setColor(Color.BLACK);
    }

    /**
     * @param doorImage the doorImage to set
     */
    public void setDoorImage(BufferedImage doorImage) {
        this.doorImage = doorImage;
    }
}
