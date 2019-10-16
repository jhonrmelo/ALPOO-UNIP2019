package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class viewPnlCardEditoras extends JPanel {
	
	public viewPnlCardEditoras() {
		
		JLabel lblTitulo = new JLabel("EDITORAS");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblTitulo);
		
	}

}
