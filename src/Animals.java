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
public class Animals 
{
    ArrayList<Animal> animals; 
    private int animalCount;
    
    private static final int SHARK = 10;
    private static final int WOLF = 20;
    private static final int BEAR = 30;
    private static final int KINGKONG = 40;
    
    
    final static int[] ANIMAL_TABLE = {SHARK,WOLF,BEAR,KINGKONG}; 
    
    public Animals()
    {
        animals = new ArrayList<Animal>();
    }
    
    public void createAnimal(Animal animal)
    {
        animals.add(animal);
    }
    
    public void drawAnimal(BufferedImage g)
    {
        
    }
    
    public boolean deleteAnimal(Point location){
        for(int i = 0; i < animals.size();i++)
        {
            if( animals.get(i).getLocation().getX() == location.getX() && animals.get(i).getLocation().getY() == location.getY() ){
                animals.remove(i);
                return true;
            }
            /*
            if(animals.get(i).contains(location))
            {
                animals.remove(i);
                return true;
            }
                
            
            */
        }
        return false;
    }

    /**
     * @return the animalCount
     */
    public int getAnimalCount() {
        return animalCount;
    }

    /**
     * @param animalCount the animalCount to set
     */
    public void setAnimalCount(int animalCount) {
        this.animalCount = animalCount;
    }
}
