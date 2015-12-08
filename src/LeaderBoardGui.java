import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

public class LeaderBoardGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	LeaderBoard lB = new LeaderBoard();

	
	/**
	 * Create the frame.
	 */
	public LeaderBoardGui() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(400, 200, 450, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("DesktopIcon.border"));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		
		panel.setBounds( 69, 31, 300, 69);
		panel.setBackground(Color.MAGENTA);
		panel2.setBounds(69, 112, 300, 404);
		
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        contentPane.add(panel);
        contentPane.add(panel2);
        
		JLabel lblNewLabel = new JLabel("LEADERBOARD\n");
		lblNewLabel.setAlignmentX(0.9f);
		lblNewLabel.setFont(new Font("Let's go Digital", Font.BOLD, 52));
		panel.add(lblNewLabel);
		
		String[] columnNames = {"Rank", "Name","Point"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 1);
		table = new JTable(tableModel);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		table.setEnabled(false);
		table.setOpaque(false);
		table.setRowSelectionAllowed(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getTableHeader().setReorderingAllowed(false);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		
		for (int i = 0; i < lB.getArray().size(); i++) {
			int id = lB.getArray().get(i).getId();
			String name = lB.getArray().get(i).getName();
			int point = lB.getArray().get(i).getPoint();
			
			Object [] obj = {new Integer(i+1), name, new Integer(point)};
			tableModel.addRow(obj);
		}
			

		table.setFont(new Font("Let's go Digital", Font.PLAIN, 21));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.blue);
		panel2.add(scrollPane);
	}

}
