package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

import org.w3c.dom.events.MouseEvent;

import dao.livrariaDAO;
import model.Livro;
import view.ViewInitialPage;

public class LivrariaController {

	private ViewInitialPage _ViewInitialPage;
	private livrariaDAO _LivrariaDAO;

	public LivrariaController() {
		_ViewInitialPage = new ViewInitialPage();
		_LivrariaDAO = new livrariaDAO();
		_ViewInitialPage.SetChangeCardAction(new SetCardVisible());
		_ViewInitialPage.SetActionListenerSearchButton(new SearchBooks());

	}

	class SearchBooks implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Livro> lstLivros = _LivrariaDAO.GetLivrosByNome(_ViewInitialPage.GetBooksTextSearch());
			_ViewInitialPage.MontaTableLivros(lstLivros);
		}
	}

	class SetCardVisible implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object Source = e.getSource();
			JButton btnChecked = (JButton) Source;

			switch (btnChecked.getName()) {
			case "btnEditoras":
				_ViewInitialPage.SetCardVisible("pnlCardEditoras");
				break;
			case "btnAutores":
				_ViewInitialPage.SetCardVisible("pnlCardAutores");
				break;
			case "btnLivros":
				_ViewInitialPage.SetCardVisible("pnlCardLivros");
				break;
			default:
				break;
			}
		}
	}

}
