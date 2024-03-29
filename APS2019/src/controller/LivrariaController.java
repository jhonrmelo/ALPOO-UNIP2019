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
import model.BooksAuthors;
import model.Editora;
import model.FiltroBuscaLivro;
import model.Item;
import model.Livro;
import view.ViewInitialPage;
import view.viewBookDetail;
import view.viewInsertAuthor;
import view.viewInsertBooks;
import view.viewInsertPublisher;
import view.viewUpdateAuthor;
import view.viewUpdateBooks;
import view.viewUpdatePublisher;

public class LivrariaController {

	private ViewInitialPage _ViewInitialPage;
	private livrariaDAO _LivrariaDAO;
	private viewBookDetail _viewBookDetail;
	private viewInsertPublisher _viewInsertPublisher;
	private viewUpdatePublisher _viewUpdatePublisher;
	private viewInsertAuthor _viewInsertAuthor;
	private viewUpdateAuthor _viewUpdateAuthor;
	private viewInsertBooks _viewInsertBooks;
	private viewUpdateBooks _viewUpdateBooks;
	String[] options;

	public LivrariaController() {
		options = new String[2];
		options[0] = new String("Sim");
		options[1] = new String("N�o");

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
		_ViewInitialPage.SetActionTableButtonAuthor(new SetActionTableAuthor());
		_ViewInitialPage.AddActionListenerBtnInsertAuthor(new OpenViewInsertAuthor());
		_ViewInitialPage.AddActionListenerBtnInsertBook(new OpenViewInsertBook());

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
								"Exclus�o", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (resposta == JOptionPane.YES_OPTION) {
							Livro llivro = _ViewInitialPage.getLivroBySelectedRow();
							_LivrariaDAO.DeleteBookByISBN(llivro.getIsbn());
							JOptionPane.showMessageDialog(_viewInsertPublisher, "Livro Exclu�do com sucesso",
									"Exclus�o de livros", JOptionPane.INFORMATION_MESSAGE);
							_ViewInitialPage.SearchAfterActionBooks();
						}
					}
					else if (button.getName() == "btnEditar") {
						String isbn = _ViewInitialPage.GetIsbnSelectedBook();
						_viewUpdateBooks = new viewUpdateBooks();
						ArrayList<Editora> Editoras = _LivrariaDAO.GetEditoraToCombobox();
						ArrayList<Autor> Autores = _LivrariaDAO.GetAutoresToCombobox();
						 _viewUpdateBooks.LoadComboboxSearch(Editoras, Autores);
						
						Livro livro = _LivrariaDAO.GetLivroByIsbn(isbn);
						ArrayList<Autor> lstAuthor = _LivrariaDAO.GetAuthorsByisbn(isbn);
						Editora editora = _LivrariaDAO.GetEditoraByID(livro.getEditoraID());
						_viewUpdateBooks.SetActionTblAutor(new RemoveAuthorsTblAuthorsUpdate());
						_viewUpdateBooks.AddActionBtnSelecionar(new AddAuthorInTableUpdate());
						_viewUpdateBooks.AddActionBtnEditar(new UpdateBooks());
						_viewUpdateBooks.SetDetails(livro, new Item(editora.getPublisher_id(),editora.getName()));
						_viewUpdateBooks.SetAuthorsTable(lstAuthor);
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

	class OpenViewInsertAuthor implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			_viewInsertAuthor = new viewInsertAuthor();
			_viewInsertAuthor.SetActionListenerBtnCadastrar(new InsertAuthor());

		}
	}

	class OpenViewInsertBook implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			_viewInsertBooks = new viewInsertBooks();
			ArrayList<Editora> Editoras = _LivrariaDAO.GetEditoraToCombobox();
			ArrayList<Autor> Autores = _LivrariaDAO.GetAutoresToCombobox();
			_viewInsertBooks.LoadComboboxSearch(Editoras, Autores);
			_viewInsertBooks.AddActionBtnSelecionar(new AddAuthorInTable());
			_viewInsertBooks.AddActionBtnCadastrar(new InsertBooks());
			_viewInsertBooks.SetActionTblAutor(new RemoveAuthorsTblAuthors());
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

	class InsertAuthor implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Autor author = _viewInsertAuthor.GetAuthor();

			if (StringUtils.isEmpty(author.getName()) || StringUtils.isEmpty(author.getFname())) {
				JOptionPane.showMessageDialog(_viewInsertAuthor, "Preencha todos os campos para seguir com o Cadastro",
						"Cadastro de Autor", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (_LivrariaDAO.InsertAuthor(author)) {
					JOptionPane.showMessageDialog(_viewInsertAuthor, "Autor Cadastrado com sucesso",
							"Cadastro de Autor", JOptionPane.INFORMATION_MESSAGE);
					_viewInsertAuthor.dispose();
					_ViewInitialPage.SearchAfterActionAuthor();
				} else {
					JOptionPane.showMessageDialog(_viewInsertAuthor, "Ocorreu um erro ao Cadastrar, tente novamente",
							"Cadastro de Autor", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	class EditPublishers implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Editora publisher = _viewUpdatePublisher.getDetails();

			if (StringUtils.isEmpty(publisher.getName()) || StringUtils.isEmpty(publisher.getUrl())) {
				JOptionPane.showMessageDialog(_viewUpdatePublisher, "Preencha todos os campos para seguir com a edi��o",
						"Edi��o de Editora", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(_viewUpdatePublisher, "Editora editada com sucesso", "Edi��o de Editora",
						JOptionPane.INFORMATION_MESSAGE);
				_viewUpdatePublisher.dispose();
				_LivrariaDAO.EditPublisher(publisher);
				_ViewInitialPage.SearchAfterActionPublisher();
			}

		}
	}

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
					if (button.getName() == "btnEditar" && e.getClickCount() == 1) {
						Editora editora = _ViewInitialPage.getEditoraBySelectedRow();
						_viewUpdatePublisher = new viewUpdatePublisher();
						_viewUpdatePublisher.SetDetails(editora);
						_viewUpdatePublisher.SetActionBtnEdit(new EditPublishers());
					} else if (button.getName() == "btnExcluir" && e.getClickCount() == 1) {

						int resposta = JOptionPane.showOptionDialog(_ViewInitialPage, "Deseja excluir esta Editora?",
								"Exclus�o", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (resposta == JOptionPane.YES_OPTION) {
							Editora editora = _ViewInitialPage.getEditoraBySelectedRow();
							_LivrariaDAO.DeletePublisherByID(editora.getPublisher_id());
							JOptionPane.showMessageDialog(_viewInsertPublisher, "Editora Exclu�da com sucesso",
									"Exclus�o de editora", JOptionPane.INFORMATION_MESSAGE);
							_ViewInitialPage.SearchAfterActionPublisher();
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

	class EditAuthors implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Autor author = _viewUpdateAuthor.getDetails();

			if (StringUtils.isEmpty(author.getName()) || StringUtils.isEmpty(author.getFname())) {
				JOptionPane.showMessageDialog(_viewUpdateAuthor, "Preencha todos os campos para seguir com a edi��o",
						"Edi��o de Autor", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(_viewUpdateAuthor, "Autor editado com sucesso", "Edi��o de Autor",
						JOptionPane.INFORMATION_MESSAGE);
				_viewUpdateAuthor.dispose();
				_LivrariaDAO.EditAuthors(author);
				_ViewInitialPage.SearchAfterActionAuthor();
			}

		}
	}

	class SetActionTableAuthor implements MouseListener {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			JTable tblAux = _ViewInitialPage.getTblAuthor();
			int column = tblAux.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY() / tblAux.getRowHeight();

			if (row < tblAux.getRowCount() && row >= 0 && column < tblAux.getColumnCount() && column >= 0) {
				Object value = tblAux.getValueAt(row, column);
				if (value instanceof JButton) {
					((JButton) value).doClick();
					JButton button = (JButton) value;
					if (button.getName() == "btnEditar" && e.getClickCount() == 1) {
						Autor Autor = _ViewInitialPage.getAuthorBySelectedRow();
						_viewUpdateAuthor = new viewUpdateAuthor();
						_viewUpdateAuthor.SetDetails(Autor);
						_viewUpdateAuthor.SetActionBtnEdit(new EditAuthors());
					} else if (button.getName() == "btnExcluir" && e.getClickCount() == 1) {
						int resposta = JOptionPane.showOptionDialog(_ViewInitialPage, "Deseja excluir este autor?",
								"Exclus�o", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (resposta == JOptionPane.YES_OPTION) {
							Autor autor = _ViewInitialPage.getAuthorBySelectedRow();
							int respostabook = JOptionPane.showOptionDialog(_ViewInitialPage,
									"Deseja excluir os livros relacionados a esse autor?(Se ele for um autor unico ser� exclu�do automaticamente)",
									"Exclus�o", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									options, null);
							if (respostabook == JOptionPane.YES_OPTION) {

								_LivrariaDAO.DeleteBooksByAuthorID(autor.getAuthorID());
								_LivrariaDAO.DeleteAuthorByAuthorID(autor.getAuthorID());
							} else {
								_LivrariaDAO.DeleteAuthorByAuthorID(autor.getAuthorID());
								;
							}
							JOptionPane.showMessageDialog(_viewInsertPublisher, "Autor Exclu�do com sucesso",
									"Exclus�o de editora", JOptionPane.INFORMATION_MESSAGE);
							_ViewInitialPage.SearchAfterActionAuthor();
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

	class AddAuthorInTable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Item item = _viewInsertBooks.GetSelectedAuthor();
			if (item.getId() != 0) {
				boolean inserted = false;
				ArrayList<Integer> idsInseridos = _viewInsertBooks.GetIDsAuthors();
				for (Integer i : idsInseridos) {
					if (i == item.getId()) {
						inserted = true;
					}
				}
				if (inserted) {
					JOptionPane.showMessageDialog(_viewInsertBooks,
							"Este autor j� foi selecionado, escolha outras op��es!", "Cadastro de Livro",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					_viewInsertBooks.InsertAuthorInTabel(item.getId(), item.getDescription());
				}
			} else {
				JOptionPane.showMessageDialog(_viewInsertBooks, "� necess�rio selecionar um autor v�lido!",
						"Cadastro de Livro", JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}
	class AddAuthorInTableUpdate implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Item item = _viewUpdateBooks.GetSelectedAuthor();
			if (item.getId() != 0) {
				boolean inserted = false;
				ArrayList<Integer> idsInseridos = _viewUpdateBooks.GetIDsAuthors();
				for (Integer i : idsInseridos) {
					if (i == item.getId()) {
						inserted = true;
					}
				}
				if (inserted) {
					JOptionPane.showMessageDialog(_viewUpdateBooks,
							"Este autor j� foi selecionado, escolha outras op��es!", "Edi��o de Livro",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					_viewUpdateBooks.InsertAuthorInTabel(item.getId(), item.getDescription());
				}
			} else {
				JOptionPane.showMessageDialog(_viewUpdateBooks, "� necess�rio selecionar um autor v�lido!",
						"Edi��o de Livro", JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}

	class InsertBooks implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String nome = _viewInsertBooks.getNomeToInsert();
			String isbn = _viewInsertBooks.getIsbnToInsert();
			String Preco = _viewInsertBooks.getPrecoToInsert();
			int EditoraID = _viewInsertBooks.getEditoraIDToInsert();
			ArrayList<BooksAuthors> lstBooksAuthors = new ArrayList<BooksAuthors>();
			ArrayList<Integer> AuthorsIDs = _viewInsertBooks.GetIDsAuthors();
			int auxiliar = 1;

			if (!StringUtils.isEmpty(nome) && !StringUtils.isEmpty(isbn) && !StringUtils.isEmpty(Preco)) {
				if (EditoraID != 0) {
					if (AuthorsIDs.size() > 0) {
						//Caso todas as valida��es passem
						Livro livro = new Livro(isbn, nome, Double.parseDouble(Preco.replace(",", ".")), EditoraID);
						_LivrariaDAO.InsertBook(livro);

						for (Integer i : AuthorsIDs) {
							lstBooksAuthors.add(new BooksAuthors(isbn, i, auxiliar));
							auxiliar++;
						}
						_LivrariaDAO.InsertBookRelationTable(lstBooksAuthors);
						
						JOptionPane.showMessageDialog(_viewInsertBooks, "Livro cadastrado com sucesso!",
								"Cadastro de Livro", JOptionPane.INFORMATION_MESSAGE);
						_viewInsertBooks.dispose();
						_ViewInitialPage.SearchAfterActionBooks();
					} else {
						JOptionPane.showMessageDialog(_viewInsertBooks, "� necess�rio selecionar ao menos um autor!",
								"Cadastro de Livro", JOptionPane.INFORMATION_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(_viewInsertBooks, "� preciso selecionar ao menos uma editora",
							"Cadastro de Livro", JOptionPane.INFORMATION_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(_viewInsertBooks, "Preencha todos os campos para seguir com a inser��o",
						"Cadastro de Livro", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	class UpdateBooks implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String nome = _viewUpdateBooks.getNomeToInsert();
			String isbn = _viewUpdateBooks.getIsbnToInsert();
			String Preco = _viewUpdateBooks.getPrecoToInsert();
			int EditoraID = _viewUpdateBooks.getEditoraIDToInsert();
			ArrayList<BooksAuthors> lstBooksAuthors = new ArrayList<BooksAuthors>();
			ArrayList<Integer> AuthorsIDs = _viewUpdateBooks.GetIDsAuthors();
			int auxiliar = 1;

			if (!StringUtils.isEmpty(nome) && !StringUtils.isEmpty(isbn) && !StringUtils.isEmpty(Preco)) {
				if (EditoraID != 0) {
					if (AuthorsIDs.size() > 0) {
						//Caso todas as valida��es passem
						Livro livro = new Livro(isbn, nome, Double.parseDouble(Preco.replace(",", ".")), EditoraID);
						_LivrariaDAO.UpdateBook(livro);

						for (Integer i : AuthorsIDs) {
							lstBooksAuthors.add(new BooksAuthors(isbn, i, auxiliar));
							auxiliar++;
						}
						_LivrariaDAO.DeleteRelationTableBooksByISBN(isbn);
						_LivrariaDAO.InsertBookRelationTable(lstBooksAuthors);
						
						JOptionPane.showMessageDialog(_viewUpdateBooks, "Livro Alterado com sucesso!",
								"Edi��o de Livro", JOptionPane.INFORMATION_MESSAGE);
						_viewUpdateBooks.dispose();
						_ViewInitialPage.SearchAfterActionBooks();
					} else {
						JOptionPane.showMessageDialog(_viewUpdateBooks, "� necess�rio selecionar ao menos um autor!",
								"Edi��o de Livro", JOptionPane.INFORMATION_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(_viewUpdateBooks, "� preciso selecionar ao menos uma editora",
							"Edi��o de Livro", JOptionPane.INFORMATION_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(_viewUpdateBooks, "Preencha todos os campos para seguir com a edi��o",
						"Edi��o de Livro", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}

	class RemoveAuthorsTblAuthors implements MouseListener {
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if (e.getClickCount() == 2) {
				int rowToremove = _viewInsertBooks.GetSelectedRow();
				_viewInsertBooks.RemoveRow(rowToremove);
			}

		}	
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {

		}
	}
	class RemoveAuthorsTblAuthorsUpdate implements MouseListener {
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if (e.getClickCount() == 2) {
				int rowToremove = _viewUpdateBooks.GetSelectedRow();
				_viewUpdateBooks.RemoveRow(rowToremove);
			}

		}	
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {

		}
	}


}
