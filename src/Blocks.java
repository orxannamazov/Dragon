/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Güray
 */
public class Blocks 
{
    ArrayList<Block> blocks;
    
    public void createBlocks(Block newBlock){
        blocks.add(newBlock);
    }
    
    public void drawBlocks(BufferedImage image)
    {
        
    }
}
