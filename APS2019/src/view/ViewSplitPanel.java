package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.Livro;

public class ViewSplitPanel extends JSplitPane {
	private int Location = 300;
	viewPnlMenu pnlMenu;
	viewPnlCards pnlCards;

	public ViewSplitPanel() {
		setDividerLocation(Location);
		getDividerLocation();
		getLastDividerLocation();
		setResizeWeight(0.4);
		setBounds(0, 0, 1106, 644);

		pnlMenu = new viewPnlMenu();
		setLeftComponent(pnlMenu);

		pnlCards = new viewPnlCards();
		setRightComponent(pnlCards);

	}

	@Override
	public int getDividerLocation() {
		return Location;
	}

	@Override
	public int getLastDividerLocation() {
		return Location;
	}

	public void SetChangeCardAction(ActionListener act) {
		pnlMenu.SetChangeCardAction(act);
	}

	public void SetActionListenerSearchButton(ActionListener act) {
		pnlCards.SetActionListenerSearchButton(act);
	}

	public String GetBooksTextSearch() {
		return pnlCards.GetBooksTextSearch();
	}

	public void MontaTableLivros(ArrayList<Livro> lstLivros) {
		pnlCards.MontaTableLivros(lstLivros);
	}
	
	public void SetCardVisible(String CardName) {
		pnlCards.SetCardVisible(CardName);
	}
}
