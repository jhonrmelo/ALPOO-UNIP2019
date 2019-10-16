package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class viewPnlCardAutores  extends JPanel {
	
	public  viewPnlCardAutores() {
		
		JLabel lblTitulo = new JLabel("AUTORES");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblTitulo);

	}

}
