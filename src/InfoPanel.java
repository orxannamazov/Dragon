import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class InfoPanel extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTable table;
	private DefaultTableModel tableModel;
	LeaderBoard lB = new LeaderBoard();
	ArrayList<Score> score = lB.getArray();
	DragonGui dg ; 


//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InfoPanel frame = new InfoPanel();
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
	public InfoPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 100, 100, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel = new JPanel();

		//dg.setPanel(lblSalam);
		//topThree();
		
	}

	
	public Component topThree ()
	{

		String[] columnNames = {" ","Rank", "Name","Point"};
		tableModel = new DefaultTableModel(columnNames, 1);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
		
		
		for (int i = 0; i < 3; i++) {
			String name = lB.getArray().get(i).getName();
			int point = lB.getArray().get(i).getPoint();
			
			Object [] obj = {" ",  new Integer(i+1), name, new Integer(point)};
			tableModel.addRow(obj);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 50, 425);
		//scrollPane.getViewport().setBackground(Color.blue);
		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		table.setRowSelectionAllowed(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(1).setMinWidth(90);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.setFont(new Font("Let's go Digital", Font.PLAIN, 14));
		table.setOpaque(false);
		table.setFillsViewportHeight(true);
		return scrollPane;
		
	}
}


//
//LeaderBoard lb = new LeaderBoard();
//ArrayList<Score> score = lb.getArray();
//BufferedImage bufferedimage;
//
//public InfoPanel()
//{
//	
//}
//
//public void drawScore (Graphics g)
//{
//    g.setColor(Color.BLUE);
//    g.setFont(new Font("default", Font.BOLD, 16));
//    
//    for (int i = 0; i < 3; i++) {
//    	 g.drawString(score.toString() + "\n" , 5, i + 30);
//    	 System.out.println(score.toString());
//	}
//
//    g.setColor(Color.BLACK);
//
//}

