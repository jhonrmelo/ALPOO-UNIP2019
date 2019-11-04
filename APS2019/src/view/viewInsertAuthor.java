package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Autor;

public class viewInsertAuthor extends JFrame {

	private JPanel contentPane;
	private JPanel pnlCadastro;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	JButton btnAdicionar;

	public viewInsertAuthor() {

		setTitle("Cadastro de Autor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewInsertAuthor.class.getResource("/imgs/authors.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 181);
		pnlCadastro = new JPanel();
		pnlCadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlCadastro);
		pnlCadastro.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(24, 30, 48, 14);
		pnlCadastro.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(24, 48, 162, 20);
		pnlCadastro.add(txtNome);
		txtNome.setColumns(10);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(224, 48, 162, 20);
		pnlCadastro.add(txtSobrenome);
		txtSobrenome.setColumns(10);

		JLabel lblUrl = new JLabel("Sobrenome:");
		lblUrl.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUrl.setBounds(224, 31, 82, 14);
		pnlCadastro.add(lblUrl);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(viewInsertPublisher.class.getResource("/imgs/add.png")));
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdicionar.setBounds(258, 108, 128, 23);
		pnlCadastro.add(btnAdicionar);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}

	public void SetActionListenerBtnCadastrar(ActionListener act) {
		btnAdicionar.addActionListener(act);
	}
	
	public Autor GetAuthor() {
		return new Autor(txtSobrenome.getText(),txtNome.getText());
	}
}
