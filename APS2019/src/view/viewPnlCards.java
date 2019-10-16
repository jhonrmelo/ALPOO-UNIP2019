package view;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Livro;

public class viewPnlCards  extends JPanel{
	CardLayout cardLayout;
	viewPnlCardLivros pnlCardLivros;
	viewPnlCardAutores pnlCardAutores;
	viewPnlCardEditoras pnlCardEditoras;
	
	public viewPnlCards() {
 		setLayout(new CardLayout(0, 0));
		
		 pnlCardLivros = new viewPnlCardLivros();		
		 pnlCardAutores = new viewPnlCardAutores();
		 pnlCardEditoras = new viewPnlCardEditoras();		
		JPanel InitialPanel = new JPanel();

		
		add(pnlCardLivros,"pnlCardLivros");
		add(pnlCardAutores,"pnlCardAutores");
		add(pnlCardEditoras, "pnlCardEditoras");
		add(InitialPanel, "InitialPanel");
				
		cardLayout = (CardLayout) (getLayout());
		cardLayout.show(this, "InitialPanel"); 
		
	}
	
	public void SetCardVisible(String CardName) {
		cardLayout.show(this, CardName);
	}
	public void SetActionListenerSearchButton(ActionListener act) {
		pnlCardLivros.SetActionListenerSearchButton(act);
	}
	public String GetBooksTextSearch() {
		return pnlCardLivros.GetBooksTextSearch();
	}
	public void MontaTableLivros(ArrayList<Livro> lstLivros) {
		pnlCardLivros.MontaTableLivros(lstLivros);
	}

}
