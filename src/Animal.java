/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;

/**
 *
 * @author Güray
 */
public class Animal 
{
    private int value;
    private Point location;
    private int size;
    //animalType type; or int animalType;
    
    public Animal(Point location,int size,int value)
    {
        this.location = location;
        this.size = size;
        this.value = value;
    }

    public Animal(){}

    public void drawAnimal( Graphics g)
    {
        g.setColor(Color.RED);
        g.fillOval(location.x * Stage.BOX_WIDTH, location.y * Stage.BOX_HEIGHT, Stage.BOX_WIDTH, Stage.BOX_HEIGHT);
        g.setColor(Color.BLACK);
    }

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

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
}
