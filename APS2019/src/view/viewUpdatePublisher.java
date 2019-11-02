package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Editora;
import model.Livro;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class viewUpdatePublisher extends JFrame {

	private JPanel contentPane;
	private JTextField txtnome;
	private JButton btnEdit;
	DefaultTableModel dtm = new DefaultTableModel();
	private JTextField txtURL;
	private JTextField txtId;

	public viewUpdatePublisher() {
		setTitle("Editar Editora");
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewUpdatePublisher.class.getResource("/imgs/Editora.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 196);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(24, 62, 48, 14);
		contentPane.add(lblNome);

		txtnome = new JTextField();
		txtnome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtnome.setBounds(24, 78, 162, 20);
		contentPane.add(txtnome);
		txtnome.setColumns(10);

		JButton btnEdit = new JButton("Editar");
		btnEdit.setIcon(new ImageIcon(viewUpdatePublisher.class.getResource("/imgs/edit.png")));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEdit.setBounds(263, 113, 123, 33);
		contentPane.add(btnEdit);

		JLabel lbURL = new JLabel("URL:");
		lbURL.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbURL.setBounds(224, 62, 48, 14);
		contentPane.add(lbURL);

		txtURL = new JTextField();
		txtURL.setBounds(224, 78, 162, 20);
		contentPane.add(txtURL);
		txtURL.setColumns(10);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("ID");
		txtId.setColumns(10);
		txtId.setBounds(24, 31, 48, 20);
		contentPane.add(txtId);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		setLocationRelativeTo(null);
		
		setVisible(true);

	}

	public void SetDetails(Editora pEditora) {
		txtId.setText(Integer.toString(pEditora.getPublisher_id()));
		txtnome.setText(pEditora.getName());
		txtURL.setText(pEditora.getUrl());
	}

	public Editora getDetails() {
	 txtId.getText();
	 txtnome.getText();
	 txtURL.getText();
	 
	 return new Editora(Integer.parseInt(txtId.getText()), txtnome.getText(), txtURL.getText());
	}

	public void SetActionBtnEdit(ActionListener act) {
		// botao recebe o action listener
		btnEdit.addActionListener(act);
	}
}
