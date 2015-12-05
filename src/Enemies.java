/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Güray
 */
public class Enemies
{
    private ArrayList<Enemy> enemies; 
    public void Enemies()
    {
        enemies = new ArrayList<Enemy>();
    }
    
    public void drawEnemy(BufferedImage image){
        for(Enemy e: enemies)
        {
             
        }
    }
    
    public void createEnemy(Enemy newEnemy)
    {
        enemies.add(newEnemy);
    }
    
    public boolean deleteEnemy(Point location){
        for(int i = 0; i < enemies.size();i++)
        {
            if( enemies.get(i).getLocation().getX() == location.getX() && enemies.get(i).getLocation().getY() == location.getY() ){
                enemies.remove(i);
                return true;
            }
            /*
            if(enemies.get(i).contains(location))
            {
                enemies.remove(i);
                return true;
            }
                
            
            */
        }
        return false;
    }
}
