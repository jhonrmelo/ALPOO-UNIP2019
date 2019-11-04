package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Livro;
import model.Autor;
import model.Editora;
import model.Item;
import model.JtableButton;
import model.RenderTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Button;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;

public class ViewInitialPage extends JFrame {

	private JPanel contentPane;
	CardLayout cardLayout;
	JPanel pnlCards;
	JButton btnEditoras;
	JButton btnAutores;
	JButton btnLivros;
	JButton btnPesquisarAutor;
	JButton btnPesquisaEditora;
	JTextField txtBuscaLivros;
	DefaultTableModel dtmBooks = new DefaultTableModel();
	DefaultTableModel dtmAuthors = new DefaultTableModel();
	DefaultTableModel dtmPublisher = new DefaultTableModel();
	JButton btnPesquisarLivro;
	JTable tblLivros;
	JScrollPane BookscrollPane;
	private JTextField txtBuscaAutores;
	private JTextField txtBuscaEditora;
	JScrollPane autoresScrollPane;
	JTable tblAutores;
	JTable tblEditora;
	JScrollPane EditoraScrollPane;
	JButton btnCadastrarEditora;
	JButton btnCadastrarAutor;
	JComboBox cbbAutor;
	JComboBox cbbEditora;
	JPanel pnlBuscaLivros;
	private final JButton btnCadastrar = new JButton("Cadastrar");

	public ViewInitialPage() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - getSize().width, dim.height / 2 - getSize().height / 2);

		setForeground(new Color(0, 128, 128));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ViewInitialPage.class.getResource("/imgs/Book-Blank-Book-icon.png")));
		setTitle("Livraria");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSplitPane splitPanel = new JSplitPane() {

			// Define o tamanho exato do menu
			private final int location = 300;
			{
				setDividerLocation(location);
			}

			@Override
			public int getDividerLocation() {
				return location;
			}

			@Override
			public int getLastDividerLocation() {
				return location;
			}
		};

		splitPanel.setResizeWeight(0.4);
		splitPanel.setBounds(0, 0, 1071, 644);
		contentPane.add(splitPanel);

		JPanel pnlMenu = new JPanel();
		splitPanel.setLeftComponent(pnlMenu);
		pnlMenu.setBackground(new Color(30, 144, 255));
		pnlMenu.setLayout(null);

		btnLivros = new JButton("Livros");
		btnLivros.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLivros.setForeground(new Color(0, 0, 0));
		btnLivros.setBackground(SystemColor.activeCaptionBorder);
		btnLivros.setBounds(62, 191, 169, 40);
		pnlMenu.add(btnLivros);

		JLabel lblimgLivros = new JLabel("");
		lblimgLivros.setBounds(26, 191, 48, 42);
		pnlMenu.add(lblimgLivros);
		lblimgLivros.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/Book-Blank-Book-icon.png")));

		btnAutores = new JButton("Autores");
		btnAutores.setForeground(new Color(0, 0, 0));
		btnAutores.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAutores.setBackground(SystemColor.activeCaptionBorder);
		btnAutores.setBounds(62, 242, 169, 40);
		pnlMenu.add(btnAutores);

		btnEditoras = new JButton("Editoras");
		btnEditoras.setForeground(new Color(0, 0, 0));
		btnEditoras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditoras.setBackground(SystemColor.activeCaptionBorder);
		btnEditoras.setBounds(62, 293, 169, 40);
		pnlMenu.add(btnEditoras);

		JLabel lblimgAutores = new JLabel("");
		lblimgAutores.setBounds(26, 244, 48, 32);
		pnlMenu.add(lblimgAutores);
		lblimgAutores.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/authors.png")));

		JLabel lblimgEditoras = new JLabel("");
		lblimgEditoras.setBounds(26, 293, 48, 45);
		pnlMenu.add(lblimgEditoras);
		lblimgEditoras.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/Editora.png")));

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(
				ViewInitialPage.class.getResource("/imgs/76b8379d-9bb8-4b95-a7b0-649773a2d888_200x200.png")));
		lblNewLabel.setBounds(40, 11, 205, 174);
		pnlMenu.add(lblNewLabel);

		pnlCards = new JPanel();
		splitPanel.setRightComponent(pnlCards);
		pnlCards.setLayout(new CardLayout(0, 0));

		JPanel pnlCardLivros = new JPanel();
		pnlCards.add(pnlCardLivros, "pnlCardLivros");
		pnlCardLivros.setLayout(null);

		pnlBuscaLivros = new JPanel();
		pnlBuscaLivros.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight,
				SystemColor.textHighlight, SystemColor.textHighlight, SystemColor.textHighlight));
		pnlBuscaLivros.setBackground(SystemColor.controlHighlight);
		pnlBuscaLivros.setBounds(0, 0, 752, 104);
		pnlCardLivros.add(pnlBuscaLivros);
		pnlBuscaLivros.setLayout(null);

		txtBuscaLivros = new JTextField();
		txtBuscaLivros.setBounds(10, 62, 146, 20);
		pnlBuscaLivros.add(txtBuscaLivros);
		txtBuscaLivros.setColumns(10);

		JLabel lblTituloLivro = new JLabel("LIVROS");
		lblTituloLivro.setBounds(335, 11, 62, 20);
		pnlBuscaLivros.add(lblTituloLivro);
		lblTituloLivro.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnPesquisarLivro = new JButton("Pesquisar");
		btnPesquisarLivro.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisarLivro.setForeground(Color.BLACK);
		btnPesquisarLivro.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/Search.png")));
		btnPesquisarLivro.setBounds(460, 61, 132, 23);

		tblLivros = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblLivros.setBounds(22, 484, 567, -354);
		pnlCardLivros.add(tblLivros);
		tblLivros.setRowHeight(40);

		pnlBuscaLivros.add(btnPesquisarLivro);


		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(10, 45, 48, 14);
		pnlBuscaLivros.add(lblNome);

		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEditora.setBounds(166, 45, 52, 14);
		pnlBuscaLivros.add(lblEditora);




		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAutor.setBounds(313, 45, 48, 14);
		pnlBuscaLivros.add(lblAutor);
		btnCadastrar.setForeground(Color.BLACK);
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCadastrar.setBounds(603, 62, 132, 23);
		pnlBuscaLivros.add(btnCadastrar);
		btnCadastrar.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/add.png")));

		BookscrollPane = new JScrollPane(tblLivros);
		BookscrollPane.setBounds(0, 106, 752, 536);
		pnlCardLivros.add(BookscrollPane);

		JPanel pnlCardAutores = new JPanel();
		pnlCards.add(pnlCardAutores, "pnlCardAutores");
		pnlCardAutores.setLayout(null);

		JPanel pnlBuscaAutores = new JPanel();
		pnlBuscaAutores.setBounds(0, 0, 752, 104);
		pnlBuscaAutores.setBackground(SystemColor.controlHighlight);
		pnlCardAutores.add(pnlBuscaAutores);
		pnlBuscaAutores.setLayout(null);

		pnlBuscaAutores.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight,
				SystemColor.textHighlight, SystemColor.textHighlight, SystemColor.textHighlight));

		JLabel lblTituloAutor = new JLabel("AUTORES");
		lblTituloAutor.setBounds(335, 11, 86, 22);
		pnlBuscaAutores.add(lblTituloAutor);
		lblTituloAutor.setFont(new Font("Tahoma", Font.BOLD, 17));

		txtBuscaAutores = new JTextField();
		txtBuscaAutores.setBounds(10, 62, 387, 20);
		pnlBuscaAutores.add(txtBuscaAutores);
		txtBuscaAutores.setColumns(10);

		btnPesquisarAutor = new JButton("Pesquisar");
		btnPesquisarAutor.setBounds(407, 61, 132, 23);
		pnlBuscaAutores.add(btnPesquisarAutor);
		btnPesquisarAutor.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/Search.png")));

		tblAutores = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		;
		tblAutores.setBounds(10, 634, 622, -533);
		tblAutores.setRowHeight(40);
		pnlCardAutores.add(tblAutores);

		autoresScrollPane = new JScrollPane(tblAutores);
		autoresScrollPane.setBounds(0, 106, 752, 536);
		pnlCardAutores.add(autoresScrollPane);

		JPanel pnlCardEditoras = new JPanel();
		pnlCards.add(pnlCardEditoras, "pnlCardEditoras");
		pnlCardEditoras.setLayout(null);

		tblEditora = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		;
		tblEditora.setBounds(10, 634, 622, -533);
		tblEditora.setRowHeight(40);
		pnlCardEditoras.add(tblEditora);

		EditoraScrollPane = new JScrollPane(tblEditora);
		EditoraScrollPane.setBounds(0, 106, 752, 536);
		pnlCardEditoras.add(EditoraScrollPane);

		JLabel lblTituloEditora = new JLabel("EDITORAS");
		lblTituloEditora.setBounds(335, 11, 100, 22);
		lblTituloEditora.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlCardEditoras.add(lblTituloEditora);

		
		
		JPanel pnlBuscaEditora = new JPanel();
		pnlCardEditoras.add(pnlBuscaEditora);

		pnlBuscaEditora.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight,
				SystemColor.textHighlight, SystemColor.textHighlight, SystemColor.textHighlight));

		pnlBuscaEditora.setBackground(SystemColor.controlHighlight);
		pnlBuscaEditora.setBounds(0, 0, 752, 104);
		pnlBuscaEditora.setLayout(null);

		txtBuscaEditora = new JTextField();
		txtBuscaEditora.setBounds(10, 62, 387, 20);
		pnlBuscaEditora.add(txtBuscaEditora);
		txtBuscaEditora.setColumns(10);

		btnPesquisaEditora = new JButton("Pesquisar");
		btnPesquisaEditora.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPesquisaEditora.setBounds(407, 61, 132, 23);
		pnlBuscaEditora.add(btnPesquisaEditora);
		btnPesquisaEditora.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/Search.png")));

		btnCadastrarEditora = new JButton("Cadastrar");
		btnCadastrarEditora.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/add.png")));
		btnCadastrarEditora.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrarEditora.setBounds(549, 61, 132, 23);
		pnlBuscaEditora.add(btnCadastrarEditora);
		
		btnCadastrarAutor = new JButton("Cadastrar");
		btnCadastrarAutor.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/add.png")));
		btnCadastrarAutor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrarAutor.setBounds(549, 61, 132, 23);
		pnlBuscaAutores.add(btnCadastrarAutor);

		cardLayout = (CardLayout) (pnlCards.getLayout());

		JPanel InitialPanel = new JPanel();
		pnlCards.add(InitialPanel, "InitialPanel");

		btnEditoras.setName("btnEditoras");
		btnAutores.setName("btnAutores");
		btnLivros.setName("btnLivros");

		BookscrollPane.setVisible(false);
		autoresScrollPane.setVisible(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		setLocationRelativeTo(null);

		cardLayout.show(pnlCards, "InitialPanel");

		setVisible(true);
	}

	public void SetCardVisible(String CardName) {
		cardLayout.show(pnlCards, CardName);
	}

	public void SetChangeCardAction(ActionListener act) {
		btnAutores.addActionListener(act);
		btnLivros.addActionListener(act);
		btnEditoras.addActionListener(act);
	}

	public String GetBooksTextSearch() {
		return txtBuscaLivros.getText();
	}
	public Item GetsearchCbbAuthor() {
		return  (Item) cbbAutor.getSelectedItem();
	}
	public Item GetSearchCbbEditora() {
		return  (Item) cbbEditora.getSelectedItem();
	}


	public String GetAuthorsTextSearch() {
		return txtBuscaAutores.getText();
	}

	public String GetPublishersTextSearch() {
		return txtBuscaEditora.getText();
	}
	public Item GetItemPublisher () {
		return  (Item) cbbAutor.getSelectedItem();
	}

	public void SetActionListenerSearchButton(ActionListener act) {
		btnPesquisarLivro.addActionListener(act);
	}

	public void SetActionlistenerSearchButtonAuthors(ActionListener act) {
		btnPesquisarAutor.addActionListener(act);
	}

	public void SetActionlistenerSearchButtonPublisher(ActionListener act) {
		btnPesquisaEditora.addActionListener(act);
	}

	public void SetActionTable(MouseListener msa) {
		tblLivros.addMouseListener(msa);
	}
	
	public void SetActionTableButtonPublisher(MouseListener msa) {
		tblEditora.addMouseListener(msa);
	}
	public void SetActionTableButtonAuthor(MouseListener msa) {
		tblAutores.addMouseListener(msa);
	}
	
	

	public void AddActionListenerBtnInsertEditora(ActionListener act) {
		btnCadastrarEditora.addActionListener(act);
	}
	public void AddActionListenerBtnInsertAuthor (ActionListener act) {
		btnCadastrarAutor.addActionListener(act);
	}

	public void MontaTableLivros(ArrayList<Livro> lstLivros) {

		Object[] colNames = { "ISBN", "Nome", "Autores", "Editora", "Preço", "", "", "" };
    
		Object[][] data = new Object[0][8];
		dtmBooks = new DefaultTableModel(data, colNames);

		tblLivros.setModel(dtmBooks);

		tblLivros.setDefaultRenderer(Object.class, new RenderTable());

		JtableButton btnEdit = new JtableButton("btnEditar");
		btnEdit.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/edit.png")));
		JtableButton btnExclude = new JtableButton("btnExcluir");
		btnExclude.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/ExcludeIcon.png")));
		JtableButton btnDetails = new JtableButton("btnDetalhes");
		btnDetails.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/Details.png")));

		tblLivros.getColumnModel().getColumn(4).setPreferredWidth(10);
		tblLivros.getColumnModel().getColumn(5).setPreferredWidth(10);
		tblLivros.getColumnModel().getColumn(6).setPreferredWidth(10);
		tblLivros.getColumnModel().getColumn(7).setPreferredWidth(10);

		dtmBooks.setNumRows(0);

		for (Livro lLivro : lstLivros) {
			Object[] dados = new Object[8];
			dados[0] = lLivro.getIsbn();
			dados[1] = lLivro.getNome();
			dados[2] = lLivro.getAutor();
			dados[3] = lLivro.getEditora();
			dados[4] = lLivro.getPreco();
			dados[5] = btnEdit;
			dados[6] = btnExclude;
			dados[7] = btnDetails;

			dtmBooks.addRow(dados);
		}
		BookscrollPane.setVisible(true);
	}

	public void MontaTableAutores(ArrayList<Autor> lstAutor) {

		Object[] colNames = { "ID", "Nome", "Sobrenome", "", "" };
		Object[][] data = new Object[0][5];
		dtmAuthors = new DefaultTableModel(data, colNames);

		tblAutores.setModel(dtmAuthors);
		tblAutores.setDefaultRenderer(Object.class, new RenderTable());

		JtableButton btnEdit = new JtableButton("btnEditar");
		btnEdit.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/edit.png")));
		JtableButton btnExclude = new JtableButton("btnExcluir");
		btnExclude.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/ExcludeIcon.png")));

		tblAutores.getColumnModel().getColumn(3).setPreferredWidth(10);
		tblAutores.getColumnModel().getColumn(4).setPreferredWidth(10);

		dtmAuthors.setNumRows(0);

		for (Autor lAutor : lstAutor) {
			Object[] dados = new Object[5];
			dados[0] = lAutor.getAuthorID();
			dados[1] = lAutor.getFname();
			dados[2] = lAutor.getName();
			dados[3] = btnEdit;
			dados[4] = btnExclude;
			dtmAuthors.addRow(dados);
		}
		autoresScrollPane.setVisible(true);

	}

	public void MontaTableEditora(ArrayList<Editora> lstEditoras) {

		Object[] colNames = { "ID", "Nome", "Url", "", "" };
		Object[][] data = new Object[0][5];
		dtmPublisher = new DefaultTableModel(data, colNames);

		tblEditora.setModel(dtmPublisher);
		tblEditora.setDefaultRenderer(Object.class, new RenderTable());

		JtableButton btnEdit = new JtableButton("btnEditar");
		btnEdit.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/edit.png")));
		JtableButton btnExclude = new JtableButton("btnExcluir");
		btnExclude.setIcon(new ImageIcon(ViewInitialPage.class.getResource("/imgs/ExcludeIcon.png")));

		tblEditora.getColumnModel().getColumn(3).setPreferredWidth(10);
		tblEditora.getColumnModel().getColumn(4).setPreferredWidth(10);

		dtmPublisher.setNumRows(0);

		for (Editora lEditora : lstEditoras) {
			Object[] dados = new Object[5];
			dados[0] = lEditora.getPublisher_id();
			dados[1] = lEditora.getName();
			dados[2] = lEditora.getUrl();
			dados[3] = btnEdit;
			dados[4] = btnExclude;
			dtmPublisher.addRow(dados);
		}
		EditoraScrollPane.setVisible(true);
	}

	public JTable getTblLivro() {
		return tblLivros;
	}
	public JTable getTblPublisher() {
		return tblEditora;
	}
	public JTable getTblAuthor() {
		return tblAutores;
	}



	public Editora getEditoraBySelectedRow() {
	int linhaSelectionada = tblEditora.getSelectedRow();

	int ID = (int) dtmPublisher.getValueAt(linhaSelectionada, 0);
	String Nome = dtmPublisher.getValueAt(linhaSelectionada, 1).toString();
	String URL = dtmPublisher.getValueAt(linhaSelectionada, 2).toString();

	return new Editora(ID, Nome, URL);
	}

	public Autor getAuthorBySelectedRow() {
	int linhaSelectionada = tblAutores.getSelectedRow();

	int ID = (int) dtmAuthors.getValueAt(linhaSelectionada, 0);
	String Nome = dtmAuthors.getValueAt(linhaSelectionada, 1).toString();
	String Sobrenome = dtmAuthors.getValueAt(linhaSelectionada, 2).toString();

	return new Autor(ID, Sobrenome, Nome);
	}
	
	
	public Livro getLivroBySelectedRow() {
		int linhaSelectionada = tblLivros.getSelectedRow();

		String ISBN = dtmBooks.getValueAt(linhaSelectionada, 0).toString();
		String Nome = dtmBooks.getValueAt(linhaSelectionada, 1).toString();
		String Autores = dtmBooks.getValueAt(linhaSelectionada, 2).toString();
		String Editora = dtmBooks.getValueAt(linhaSelectionada, 3).toString();
		double Preco = (double) dtmBooks.getValueAt(linhaSelectionada, 4);

		return new Livro(ISBN, Nome, Preco, Autores, Editora);
	}

	public void SearchAfterActionPublisher() {
		txtBuscaEditora.setText("");
		btnPesquisaEditora.doClick();
	}
	public void SearchAfterActionAuthor() {
		txtBuscaAutores.setText("");
		btnPesquisarAutor.doClick();
	}

	public void SearchAfterActionBooks() {
		txtBuscaLivros.setText("");
		cbbAutor.setSelectedIndex(0);
		cbbEditora.setSelectedIndex(0);
		btnPesquisarLivro.doClick();
	}

	public void LoadComboboxSearch(ArrayList<Editora> Editoras, ArrayList<Autor> Autores) {

		Vector<Item> modelCbbAutor =  new Vector<Item>();
		Vector<Item> modelcbbEditora = new Vector<Item>();
		
		Item customItem = new Item(0, "Todos");
		
		modelCbbAutor.add(customItem);
		modelcbbEditora.add(customItem);
		

		
		for(Autor _autor : Autores) {
			modelCbbAutor.add(new Item(_autor.getAuthorID(), _autor.getFname()));
		}
		
		for (Editora _editora : Editoras) {
			modelcbbEditora.add(new Item(_editora.getPublisher_id(), _editora.getName()));
		}
		cbbAutor = new JComboBox<Item>(modelCbbAutor);
		cbbAutor.setBounds(313, 61, 137, 22);
		pnlBuscaLivros.add(cbbAutor);	
		cbbAutor.setSelectedIndex(0);
			
		cbbEditora = new JComboBox<Item>(modelcbbEditora);
		cbbEditora.setBounds(166, 61, 137, 22);
		pnlBuscaLivros.add(cbbEditora);
		cbbEditora.setSelectedIndex(0);

	}
	
}
