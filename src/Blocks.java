/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Güray
 */
public class Blocks 
{
    ArrayList<Block> blocks;

    public Blocks()
    {
        blocks = new ArrayList<Block>();
    }

    public void createBlock(Point p){
        blocks.add( new Block( p));
    }

    public void drawBlocks(Graphics g)
    {

        for( Block block:blocks)
        {
            g.setColor(Color.pink);
            g.fillRoundRect(block.getLocation().x * dragonCanvas.BOX_WIDTH, block.getLocation().y * dragonCanvas.BOX_HEIGHT,
                    dragonCanvas.BOX_WIDTH, dragonCanvas.BOX_HEIGHT, 5, 5);
            g.setColor(Color.BLACK);
        }
    }

    public boolean contains( Point p)
    {
        for( Block block:blocks)
            if( block.getLocation().equals( p))
                return true;

        return false;
    }
}
