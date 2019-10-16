package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class viewPnlBuscaLivros extends JPanel {

	JTextField txtBuscaLivros;
	JButton btnPesquisar;
	public viewPnlBuscaLivros() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, SystemColor.textHighlight,
				SystemColor.textHighlight, SystemColor.textHighlight));
		setBackground(SystemColor.controlHighlight);
		setBounds(0, 0, 764, 104);
		setLayout(null);
		
		
		txtBuscaLivros = new JTextField();
		txtBuscaLivros.setBounds(10, 62, 387, 20);
		add(txtBuscaLivros);
		txtBuscaLivros.setColumns(10);
		
		JLabel lblTitulo = new JLabel("LIVROS");
		lblTitulo.setBounds(262, 8, 62, 20);
		add(lblTitulo);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Livro");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton.setBackground(SystemColor.controlHighlight);
		rdbtnNewRadioButton.setBounds(10, 35, 70, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.setBackground(SystemColor.controlHighlight);
		rdbtnAutor.setBounds(82, 36, 62, 23);
		add(rdbtnAutor);

		JRadioButton rdbtnEditora = new JRadioButton("Editora");
		rdbtnEditora.setBackground(SystemColor.controlHighlight);
		rdbtnEditora.setBounds(146, 36, 109, 23);
		add(rdbtnEditora);
		

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setForeground(Color.BLACK);
		btnPesquisar.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\Search.png"));
		btnPesquisar.setBounds(407, 61, 132, 23);
		add(btnPesquisar);

	}
	
	public String GetBooksTextSearch() {
		return txtBuscaLivros.getText();
	}
	
	public void SetActionListenerSearchButton(ActionListener act) {
		btnPesquisar.addActionListener(act);
	}

}
