
import java.awt.Point;
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
    
    public Door(Point location, BufferedImage doorImage)
    {
        this.doorImage = doorImage;
        this.location = location;
        
    }
    
    public boolean isOpened(){
        //if()
        return state;
    }
    
    public void setState(boolean isOpened){
        state = isOpened;
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

    /**
     * @param doorImage the doorImage to set
     */
    public void setDoorImage(BufferedImage doorImage) {
        this.doorImage = doorImage;
    }
}
