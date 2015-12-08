import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Help extends JFrame {

	private JPanel contentPane;
	private String text; 


	/**
	 * Create the frame.
	 */
	public Help() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 480, 600);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JLabel lbltxt = new JLabel("");
		lbltxt.setIcon(new ImageIcon(Help.class.getResource("/images/help.png")));
		lbltxt.setBounds(6, 6, 435, 180);
		JTextArea txtArea = new JTextArea();
		txtArea.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtArea.setForeground(Color.WHITE);
		txtArea.setBounds(23, 220, 413, 265);
		txtArea.setBackground(new Color(1,1,1, (float) 0.01));
		txtArea.setEditable(false);
		txtArea.setSelectedTextColor(Color.green);
		setText();
	
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		txtArea.setColumns(1);
		txtArea.setText(text);
		contentPane.setLayout(null);
		contentPane.add(lbltxt);
		contentPane.add(txtArea);
		setBackgroundPicture("/images/BlackBack.jpg");
		
	}

	public void setBackgroundPicture(String name)
	{
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Main.class.getResource(name)));
		background.setBounds(0, 0, 480, 600);
		contentPane.add(background);
	}
	
	public void setText()
	{
		text = "           Welcome to our game!! \n "
				+ "In order to play this game you need to know some instructions: \n"
				+ "1. arrow keys:  they will let you move to right, left, up, down sides, \ndepends on which arrow will you choose.\n"
				+ "2. P button let you play or pause this game. \n"
				+ "3. At the begining of game you will be asked to write your name.\n";
		
	}
}
