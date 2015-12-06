/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Güray
 */
public class Enemy 
{
    private int value;
    private Point location;
    //private ArrayList<Point> loc;
    //EnemyType type; 
    private boolean isEatable;
    
    /**
     *
     * @param location
     * @param value
     */
    public Enemy(Point location, int value) //EnemyType ??????
    {
        this.value = value;
        this.location = location;
    }
    
   /* public Enemy(ArrayList<Point> loc, int value) //EnemyType ??????
    {
        this.value = value;
        this.loc = loc;
    }
    */
    
    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }
    
    /*public ArrayList<Point> getLocation() {
        return loc;
    }*/

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }
     /*public void setLocation(ArrayList<Point> loc) {
        this.loc = loc;
    }
    */
    
    /**
     * @return the isEatable
     */
    public boolean getIsEatable() {
        return isEatable;
    }

    /**
     * @param isEatable the isEatable to set
     */
    public void setIsEatable(boolean isEatable) {
        this.isEatable = isEatable;
    }
}
