package controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.w3c.dom.events.MouseEvent;

import com.sun.net.httpserver.Authenticator.Success;

import controller.LivrariaController.SetActionTblPublisher;
import dao.livrariaDAO;
import model.Autor;
import model.Editora;
import model.FiltroBuscaLivro;
import model.Item;
import model.Livro;
import view.ViewInitialPage;
import view.viewBookDetail;
import view.viewInsertPublisher;
import view.viewUpdatePublisher;


public class LivrariaController {

	private ViewInitialPage _ViewInitialPage;
	private livrariaDAO _LivrariaDAO;
	private viewBookDetail _viewBookDetail;
	private viewInsertPublisher _viewInsertPublisher;
	private viewUpdatePublisher _viewUpdatePublisher;
	String[] options;

	public LivrariaController() {
		options = new String[2];
		options[0] = new String("Sim");
		options[1] = new String("Não");
		
		_ViewInitialPage = new ViewInitialPage();
		_LivrariaDAO = new livrariaDAO();
		_ViewInitialPage.SetChangeCardAction(new SetCardVisible());
		_ViewInitialPage.SetActionListenerSearchButton(new SearchBooks());
		_ViewInitialPage.SetActionTable(new SetActionTblLivros());
		_ViewInitialPage.SetActionlistenerSearchButtonAuthors(new SearchAutors());
		_ViewInitialPage.SetActionlistenerSearchButtonPublisher(new SearchPublishers());
		_ViewInitialPage.AddActionListenerBtnInsertEditora(new OpenViewInsertPublisher());
		ArrayList<Editora> Editoras = _LivrariaDAO.GetEditoraToCombobox();
		ArrayList<Autor> Autores = _LivrariaDAO.GetAutoresToCombobox();		
		_ViewInitialPage.LoadComboboxSearch(Editoras, Autores);
		_ViewInitialPage.SetActionTableButtonPublisher(new SetActionTblPublisher());

	}

	class SearchBooks implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String NameBook = _ViewInitialPage.GetBooksTextSearch();
			Item AuthorItem = _ViewInitialPage.GetsearchCbbAuthor();
			Item PublisherItem = _ViewInitialPage.GetSearchCbbEditora();
			FiltroBuscaLivro filtro = new FiltroBuscaLivro(NameBook, PublisherItem.getId(), AuthorItem.getId());
			ArrayList<Livro> lstLivros = _LivrariaDAO.GetLivrosByFiltro(filtro);
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
					if (button.getName() == "btnDetalhes" && e.getClickCount() == 1) {
						Livro llivro = _ViewInitialPage.getLivroBySelectedRow();
						_viewBookDetail = new viewBookDetail();
						_viewBookDetail.SetDetails(llivro);
						_viewBookDetail.SetTableAuthor(llivro.getAutor().split(";"));

					} else if (button.getName() == "btnExcluir") {

						int resposta = JOptionPane.showOptionDialog(_ViewInitialPage, "Deseja excluir este livro?",
								"Exclusão", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (resposta == JOptionPane.YES_OPTION) {
							Livro llivro = _ViewInitialPage.getLivroBySelectedRow();
							_LivrariaDAO.DeleteBookByISBN(llivro.getIsbn());
							_ViewInitialPage.SearchAfterActionBooks();
						}
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

	class OpenViewInsertPublisher implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			_viewInsertPublisher = new viewInsertPublisher();
			_viewInsertPublisher.AddActionListenerBtnCadastrar(new InsertPublisher());

		}
	}

	class InsertPublisher implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Editora publisher = _viewInsertPublisher.GetPublisherToInsert();

			if (StringUtils.isEmpty(publisher.getName()) || StringUtils.isEmpty(publisher.getUrl())) {
				JOptionPane.showMessageDialog(_viewInsertPublisher,
						"Preencha todos os campos para seguir com o Cadastro", "Cadastro de Editora",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (_LivrariaDAO.InsertPublisher(publisher)) {
					JOptionPane.showMessageDialog(_viewInsertPublisher, "Editora Cadastrada com sucesso",
							"Cadastro de Editora", JOptionPane.INFORMATION_MESSAGE);
					_viewInsertPublisher.dispose();
					_ViewInitialPage.SearchAfterActionPublisher();
				} else {
					JOptionPane.showMessageDialog(_viewInsertPublisher, "Ocorreu um erro ao Cadastrar, tente novamente",
							"Cadastro de Editora", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	//ALTERACAO DO GABRIEL 
	// - Buscando os dados da editora
	class SetActionTblPublisher implements MouseListener { 
		
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			JTable tblAux = _ViewInitialPage.getTblPublisher();
			int column = tblAux.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY() / tblAux.getRowHeight();

			if (row < tblAux.getRowCount() && row >= 0 && column < tblAux.getColumnCount() && column >= 0) {
				Object value = tblAux.getValueAt(row, column);
				if (value instanceof JButton) {
					((JButton) value).doClick();
					JButton button = (JButton) value;
					if(button.getName() == "btnEditar" && e.getClickCount() == 1) {
						Editora editora =  _ViewInitialPage.getEditoraBySelectedRow();
						_viewUpdatePublisher = new viewUpdatePublisher();
						_viewUpdatePublisher.SetDetails(editora);
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

	// - Busca os dados da view e envia para o DAO
	class EditPublishers implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Editora editora = _viewUpdatePublisher.getDetails();
			_LivrariaDAO.EditPublisher(editora);
		}
	}

}
