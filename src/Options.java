
import static com.oracle.jrockit.jfr.DataType.INTEGER;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.lang.Integer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Güray
 */
public class Options extends JPanel implements MouseListener,KeyListener
{
    //final private int SOUND_OPTION_SELECTED = 1;
    //final private int DIFFICULTY_OPTION_SELECTED = 2;
    
    private JButton soundButton;
    private JTextField difficulty;
    private boolean isSoundOpen;

   public Options()
   {
       soundButton  = new JButton("ENABLE/DISABLE BUTTON");
       isSoundOpen = true;
       difficulty = new JTextField("PLEASE ENTER THE DIFFICULTY LEVEL", 100);
       
       add(soundButton);
       add(difficulty);
       addMouseListener(this);
       addKeyListener( this);
       setVisible(true);
       setPreferredSize(new Dimension(500,500));
   }
    
     public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.BLUE);
        g.drawString("------OPTIONS MENU-------", 45,45 );
        
    }
    
   

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getComponent() == soundButton)
        {
           if(isSoundOpen())
               setSoundOpen(false);
           else
               setSoundOpen(true);
           
           //updateSound();
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER && difficulty.getText() != "") {
                int difficultyLevel = Integer.parseInt(difficulty.getText());
                if( difficultyLevel > 0){}
                    //updateDifficulty(difficultyLevel); according to Number
                    
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the soundOpen
     */
    public boolean isSoundOpen() {
        return isSoundOpen;
    }

    /**
     * @param soundOpen the soundOpen to set
     */
    public void setSoundOpen(boolean soundOpen) {
        this.isSoundOpen = soundOpen;
    }
}
