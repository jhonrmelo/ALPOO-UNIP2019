package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class DetalhesAutores extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel dtm;


	public DetalhesAutores() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 205, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-getSize().width, dim.height/2-getSize().height/2);
		
		Object[] colNames = {"Autores" };
		Object[][] data = new Object[0][1];
		dtm = new DefaultTableModel(data, colNames);
		
		JLabel lblAutores = new JLabel("Autores");
		lblAutores.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAutores.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAutores, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		setVisible(true);
	}
	
	public void SetTblAutores(String[] Autores ) {
		
		dtm.setNumRows(0);
		
		for(String Autor : Autores) {
			Object[] data = new Object[1];
			data[0] = Autor;
			dtm.addRow(data);
		}
		
	}
}
