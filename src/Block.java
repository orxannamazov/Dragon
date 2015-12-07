
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
    
    public Block(Point location)
    {
        this.location = location;
    }


    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public BufferedImage getBlockImage() {
        return blockImage;
    }

    public void setBlockImage(BufferedImage blockImage) {
        this.blockImage = blockImage;
    }
}
