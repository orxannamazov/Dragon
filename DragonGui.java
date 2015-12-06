import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class DragonGui extends JFrame {

	private JPanel contentPane;
	//private JTextField txtHellop;
	private dragonCanvas dragonCanvas;
	//private JTextField textField;
	static JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DragonGui frame = new DragonGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DragonGui() {
		setTitle("DRAGON VALLEY" );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 820, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);



		
		dragonCanvas =  new dragonCanvas();
		dragonCanvas.setPreferredSize(new Dimension(820, 680));

		dragonCanvas.setVisible(true);
		contentPane.setLayout(new BorderLayout(0, 0));
		dragonCanvas.setFocusable(true);

		// Start the game and let the controller know its canvas.
		GameController controller = new GameController( dragonCanvas);
		dragonCanvas.setGameController( controller);
		
		contentPane.add(dragonCanvas,BorderLayout.CENTER);
		
		JLabel lblDragon = new JLabel("DRAGON");
		lblDragon.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblDragon, BorderLayout.NORTH);
		
		String text = "GROUP 11!";
		JLabel lblbottom = new JLabel(text, SwingConstants.CENTER);
		lblbottom.setFont(new Font("default", Font.BOLD, 16));
		lblbottom.setForeground(Color.BLUE);
		
		contentPane.add(lblbottom, BorderLayout.SOUTH);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("NAME");
		contentPane.add(textField_1, BorderLayout.WEST);
		textField_1.setColumns(10);
		
		InfoPanel infoPanel = new InfoPanel();
		infoPanel.setPreferredSize(new Dimension(220, 680));
		infoPanel.setVisible(true);
		infoPanel.setFocusable(true);
		contentPane.add(infoPanel,BorderLayout.EAST);
	}
}
