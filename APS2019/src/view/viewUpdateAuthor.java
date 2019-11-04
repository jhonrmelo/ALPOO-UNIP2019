package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Autor;

public class viewUpdateAuthor extends JFrame {

	private JPanel contentPane;
	private JTextField txtnome;
	private JButton btnEdit;
	DefaultTableModel dtm = new DefaultTableModel();
	private JTextField txtSobrenom;
	private JTextField txtId;

	public viewUpdateAuthor() {
		setTitle("Editar Autor");
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

	    btnEdit = new JButton("Editar");
		btnEdit.setIcon(new ImageIcon(viewUpdatePublisher.class.getResource("/imgs/edit.png")));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEdit.setBounds(263, 113, 123, 33);
		contentPane.add(btnEdit);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSobrenome.setBounds(224, 62, 87, 14);
		contentPane.add(lblSobrenome);

		txtSobrenom = new JTextField();
		txtSobrenom.setBounds(224, 78, 162, 20);
		contentPane.add(txtSobrenom);
		txtSobrenom.setColumns(10);

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
	
	public void SetDetails(Autor author) {
		txtnome.setText(author.getFname().trim());
		txtId.setText( Integer.toString(author.getAuthorID()));
		txtSobrenom.setText(author.getName().trim());
	}
	
	public Autor getDetails() {
		return new Autor(Integer.parseInt(txtId.getText()),txtSobrenom.getText(), txtnome.getText());
	}
	
	public void SetActionBtnEdit(ActionListener act) {
		btnEdit.addActionListener(act);
	}

}
