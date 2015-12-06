
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
public class Block {
    
    private Point location;
    private BufferedImage blockImage;
    
    public Block(Point location, BufferedImage blockImage){
        this.location = location;
        this.blockImage = blockImage;
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
     * @return the blockImage
     */
    public BufferedImage getBlockImage() {
        return blockImage;
    }

    /**
     * @param blockImage the blockImage to set
     */
    public void setBlockImage(BufferedImage blockImage) {
        this.blockImage = blockImage;
    }
}
