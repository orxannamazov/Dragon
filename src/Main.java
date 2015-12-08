import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	static Main frame;
	static String name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Main();
					frame.setVisible(true);
					//frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 960, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		buttonInit();
		setBackgroundPicture("/images/dragon3.jpg");

		
	}


	public void buttonInit()
	{
		JLabel btnPlayNow = new JLabel("");
		btnPlayNow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPlayNow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Play_off.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPlayNow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Play_on.png")));
				DragonGui dragon = new DragonGui();
				 name = JOptionPane.showInputDialog("Please write your name : ");
				dragon.setVisible(true);
				frame.setVisible(false);
				dragon.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//				        if (JOptionPane.showConfirmDialog(frame, 
//				            "Are you sure to close this window?", "Really Closing?", 
//				            JOptionPane.YES_NO_OPTION,
//				            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
//				        	System.exit(0);
//				        }
				    	frame.setVisible(true);
				    }
				});

				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnPlayNow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Play_off.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPlayNow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Play_on.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnPlayNow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Play_on.png")));
			}
		
		});
		btnPlayNow.setIcon(new ImageIcon(Main.class.getResource("/images/Play_on.png")));
		btnPlayNow.setBounds(722, 60, 156, 56);
		contentPane.add(btnPlayNow);
		
		JLabel btnleaderBoard = new JLabel("button 2");
		btnleaderBoard.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				btnleaderBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LeaderBoard_on.png")));
				LeaderBoardGui frame = new LeaderBoardGui();
				frame.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnleaderBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LeaderBoard_off.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnleaderBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LeaderBoard_off.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnleaderBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LeaderBoard_on.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnleaderBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LeaderBoard_on.png")));
			}
		});
		btnleaderBoard.setIcon(new ImageIcon(Main.class.getResource("/images/Leaderboard_on.png")));
		btnleaderBoard.setBounds(704, 333, 169, 66);
		contentPane.add(btnleaderBoard);
		
		JLabel btnHelp = new JLabel("");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help_off.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help_on.png")));
				Help help = new Help ();
				help.setVisible(true);
				frame.setVisible(false);
				help.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				    	frame.setVisible(true);
				    }
				});

				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help_off.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help_on.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help_off.png")));
			}
		});
		btnHelp.setIcon(new ImageIcon(Main.class.getResource("/images/help_on.png")));
		btnHelp.setBounds(722, 260, 156, 48);
		contentPane.add(btnHelp);
		
		JLabel btnOptions = new JLabel("");
		btnOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/options_off.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				btnOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/options_on.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/options_off.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/options_on.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/options_off.png")));
			}
		});
		btnOptions.setIcon(new ImageIcon(Main.class.getResource("/images/options_on.png")));
		btnOptions.setBounds(722, 139, 156, 58);
		contentPane.add(btnOptions);
	}

	public void setBackgroundPicture(String name)
	{
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Main.class.getResource(name)));
		background.setBounds(0, 0, 960, 600);
		contentPane.add(background);
	}
}
