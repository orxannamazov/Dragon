import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class DragonGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1201181141355006261L;

	private JPanel contentPane;

	private dragonCanvas dragonCanvas;
	private JPanel panel;
	InfoPanel ip = new InfoPanel();
	private JLabel lblScore;
	private static JLabel lblSc;
	private JLabel lblName;
	private static JLabel lblN;
	private JPanel panel_1;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DragonGui frame = new DragonGui();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DragonGui() {
		setTitle("DRAGON VALLEY" );
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 100, 900, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		dragonCanvas =  new dragonCanvas();
		dragonCanvas.setBounds(55, 21, 599, 598);

		dragonCanvas.setVisible(true);
		contentPane.setLayout(null);
		dragonCanvas.setFocusable(true);

		// Start the game and let the controller know its canvas.
		GameController controller = new GameController( dragonCanvas);
		dragonCanvas.setGameController( controller);
		
		contentPane.add(dragonCanvas);
		
		String text = "GROUP 11!";
		JLabel lblbottom = new JLabel(text, SwingConstants.CENTER);
		lblbottom.setBounds(5, 633, 790, 20);
		lblbottom.setFont(new Font("default", Font.BOLD, 16));
		lblbottom.setForeground(Color.BLUE);
		
		contentPane.add(lblbottom);
		
		
		panel = new JPanel();
		panel.setBounds(678, 21, 216, 247);
		
		//panel.add(ip.getComp());
	
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblScore = new JLabel("Score");
		lblScore.setBounds(17, 64, 61, 16);
		panel.add(lblScore);
		
		lblSc = new JLabel("");
		lblSc.setBounds(111, 64, 61, 16);
		panel.add(lblSc);
		
		lblName = new JLabel("Name");
		lblName.setBounds(17, 36, 61, 16);
		panel.add(lblName);
		
		lblN = new JLabel("Name");
		lblN.setBounds(100, 36, 61, 16);
		panel.add(lblN);
		
		panel_1 = new JPanel();
		panel_1.setBounds(678, 315, 216, 304);
		panel_1.add(ip.topThree());
		contentPane.add(panel_1);
	
	}
	
	public static void printName(String name)
	{
		lblN.setText(name);
	}
	public static void printScore (int point)
	{
		lblSc.setText(new Integer(point) + "");
	}

}
