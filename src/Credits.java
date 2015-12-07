
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Güray
 */
public class Credits extends Canvas{
    final private String CREATOR1 = "GÜRAY BAYDUR";
    final private String CREATOR2 = "ÖMER AKGÜL";
    final private String CREATOR3 = "BURAK BÖR";
    final private String CREATOR4 = "ORKHAN NAMAZOV";
    
    final private String[] CREATORS = {CREATOR1,CREATOR2,CREATOR3,CREATOR4};
    
     public void drawCredits (Graphics g)
    {
        g.setColor(Color.BLUE);
      //  g.setFont(new Font("default", Font.BOLD, 16));
        
        for (int i=0 ,j = 0; i < CREATORS.length; i++) {
        	 g.drawString(CREATORS[i].toString() + "\n" , 30, j);
                 j+=30;
		}

        g.setColor(Color.BLACK);

    }
}
