import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class InfoPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5672566820247332014L;
	private JPanel contentPane;
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
		new JPanel();

		//topThree();
		
	}

	
	public Component topThree ()
	{

		String[] columnNames = {" ", "Rank", "Name","Point"};
		tableModel = new DefaultTableModel(columnNames, 1);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
		int size  = 0;
		if (lB.getCollectionLength() > 3) {
			size = 3; 
		}
		else
			size = lB.getCollectionLength();
		
		for (int i = 0; i < size; i++) {
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



