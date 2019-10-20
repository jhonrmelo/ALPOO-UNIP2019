package model;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class JtableButton extends JButton {

	public JtableButton(String IconRoad, String nome) {
		this.setIcon(new ImageIcon(IconRoad));
		this.setBackground(Color.white);
		setBorder(new LineBorder(Color.white));
		setName(nome);

		switch (getName()) {
		case "btnDetalhes":
			setToolTipText("Detalhes");
			break;
		case "btnExcluir":
			setToolTipText("Excluir");
			break;
		case "btnEditar":
			setToolTipText("Editar");
			break;
		default:
			break;
		}
	}
}
