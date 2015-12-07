import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.sound.midi.MidiDevice.Info;
/**
 * 
 * 
 * @author Orxan
 *
 */
public class InfoPanel extends Canvas {
	
	LeaderBoard lb = new LeaderBoard();
	ArrayList<Score> score = lb.getArray();
	BufferedImage bufferedimage;
	
	public InfoPanel()
	{
		
	}
	
    public void drawScore (Graphics g)
    {
        g.setColor(Color.BLUE);
        g.setFont(new Font("default", Font.BOLD, 16));
        
        for (int i = 0; i < 3; i++) {
        	 g.drawString(score.toString() + "\n" , 5, i + 30);
        	 System.out.println(score.toString());
		}

        g.setColor(Color.BLACK);

    }

}
