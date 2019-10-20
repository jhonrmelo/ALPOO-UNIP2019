package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.Console;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;

import org.w3c.dom.events.MouseEvent;

import dao.livrariaDAO;
import model.Autor;
import model.Editora;
import model.Livro;
import view.ViewInitialPage;
import view.viewBookDetail;

public class LivrariaController {

	private ViewInitialPage _ViewInitialPage;
	private livrariaDAO _LivrariaDAO;
	private viewBookDetail _viewBookDetail;

	public LivrariaController() {
		_ViewInitialPage = new ViewInitialPage();
		_LivrariaDAO = new livrariaDAO();
		_ViewInitialPage.SetChangeCardAction(new SetCardVisible());
		_ViewInitialPage.SetActionListenerSearchButton(new SearchBooks());
		_ViewInitialPage.SetActionTable(new SetActionTblLivros());
		_ViewInitialPage.SetActionlistenerSearchButtonAuthors(new SearchAutors());
		_ViewInitialPage.SetActionlistenerSearchButtonPublisher(new SearchPublishers());

	}

	class SearchBooks implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Livro> lstLivros = _LivrariaDAO.GetLivrosByNome(_ViewInitialPage.GetBooksTextSearch());
			_ViewInitialPage.MontaTableLivros(lstLivros);
		}
	}

	class SearchAutors implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Autor> lstAutor = _LivrariaDAO.GetAuthorsByNome(_ViewInitialPage.GetAuthorsTextSearch());
			_ViewInitialPage.MontaTableAutores(lstAutor);
		}
	}

	class SearchPublishers implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Editora> lstEditora = _LivrariaDAO
					.GetPublishersByName(_ViewInitialPage.GetPublishersTextSearch());
			_ViewInitialPage.MontaTableEditora(lstEditora);
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

	class SetActionTblLivros implements MouseListener {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			JTable tblAux = _ViewInitialPage.getTblLivro();
			int column = tblAux.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY() / tblAux.getRowHeight();

			if (row < tblAux.getRowCount() && row >= 0 && column < tblAux.getColumnCount() && column >= 0) {
				Object value = tblAux.getValueAt(row, column);
				if (value instanceof JButton) {
					((JButton) value).doClick();
					JButton button = (JButton) value;
					if(button.getName() == "btnDetalhes" && e.getClickCount() == 1) {
						Livro llivro =  _ViewInitialPage.getLivroBySelectedRow();
						_viewBookDetail = new viewBookDetail();
						_viewBookDetail.SetDetails(llivro);
						_viewBookDetail.SetTableAuthor(llivro.getAutor().split(";"));
										
					}
				}
			}
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {

		}

	}
}
