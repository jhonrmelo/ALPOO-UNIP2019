package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class viewPnlMenu extends JPanel {
	JButton btnLivros;
	JButton btnAutores;
	JButton btnEditoras;
	
	public viewPnlMenu() {
		setBackground(new Color(30, 144, 255));
		setLayout(null);
		
		btnLivros = new JButton("Livros");
		btnLivros.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLivros.setForeground(new Color(0, 0, 0));
		btnLivros.setBackground(SystemColor.activeCaptionBorder);
		btnLivros.setBounds(62, 191, 169, 40);		
		add(btnLivros);
		
		JLabel lblimgLivros = new JLabel("");
		lblimgLivros.setBounds(26, 191, 48, 42);
		add(lblimgLivros);
		lblimgLivros.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\Book-Blank-Book-icon.png"));
		
		
		btnAutores = new JButton("Autores");
		btnAutores.setForeground(new Color(0, 0, 0));
		btnAutores.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAutores.setBackground(SystemColor.activeCaptionBorder);
		btnAutores.setBounds(62, 242, 169, 40);
		add(btnAutores);
		
		
		btnEditoras = new JButton("Editoras");
		btnEditoras.setForeground(new Color(0, 0, 0));
		btnEditoras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditoras.setBackground(SystemColor.activeCaptionBorder);
		btnEditoras.setBounds(62, 293, 169, 40);
		add(btnEditoras);
		
		JLabel lblimgAutores = new JLabel("");
		lblimgAutores.setBounds(26, 244, 48, 32);
		add(lblimgAutores);
		lblimgAutores.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\authors.png"));
		
		JLabel lblimgEditoras = new JLabel("");
		lblimgEditoras.setBounds(26, 293, 48, 45);
		add(lblimgEditoras);
		lblimgEditoras.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\Editora.png"));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel
				.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\76b8379d-9bb8-4b95-a7b0-649773a2d888_200x200.png"));
		lblNewLabel.setBounds(40, 11, 205, 174);
		add(lblNewLabel);
		
		btnEditoras.setName("btnEditoras");
		btnAutores.setName("btnAutores");
		btnLivros.setName("btnLivros");
	}
	
	public  void SetChangeCardAction(ActionListener act) {
		btnAutores.addActionListener(act);
		btnLivros.addActionListener(act);
		btnEditoras.addActionListener(act);
	}

}
