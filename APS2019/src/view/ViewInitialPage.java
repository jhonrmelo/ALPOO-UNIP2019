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
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

	public ViewInitialPage() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - getSize().width, dim.height / 2 - getSize().height / 2);

		setForeground(new Color(0, 128, 128));
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\NP12019\\APS2019\\imgs\\Book-Blank-Book-icon.png"));
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
		lblimgLivros.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\Book-Blank-Book-icon.png"));

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
		lblimgAutores.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\authors.png"));

		JLabel lblimgEditoras = new JLabel("");
		lblimgEditoras.setBounds(26, 293, 48, 45);
		pnlMenu.add(lblimgEditoras);
		lblimgEditoras.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\Editora.png"));

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel
				.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\76b8379d-9bb8-4b95-a7b0-649773a2d888_200x200.png"));
		lblNewLabel.setBounds(40, 11, 205, 174);
		pnlMenu.add(lblNewLabel);

		pnlCards = new JPanel();
		splitPanel.setRightComponent(pnlCards);
		pnlCards.setLayout(new CardLayout(0, 0));

		JPanel pnlCardLivros = new JPanel();
		pnlCards.add(pnlCardLivros, "pnlCardLivros");
		pnlCardLivros.setLayout(null);

		JPanel pnlBuscaLivros = new JPanel();
		pnlBuscaLivros.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight,
				SystemColor.textHighlight, SystemColor.textHighlight, SystemColor.textHighlight));
		pnlBuscaLivros.setBackground(SystemColor.controlHighlight);
		pnlBuscaLivros.setBounds(0, 0, 752, 104);
		pnlCardLivros.add(pnlBuscaLivros);
		pnlBuscaLivros.setLayout(null);

		txtBuscaLivros = new JTextField();
		txtBuscaLivros.setBounds(10, 62, 387, 20);
		pnlBuscaLivros.add(txtBuscaLivros);
		txtBuscaLivros.setColumns(10);

		JLabel lblTituloLivro = new JLabel("LIVROS");
		lblTituloLivro.setBounds(335, 11, 62, 20);
		pnlBuscaLivros.add(lblTituloLivro);
		lblTituloLivro.setFont(new Font("Tahoma", Font.BOLD, 16));

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Livro");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton.setBackground(SystemColor.controlHighlight);
		rdbtnNewRadioButton.setBounds(101, 38, 70, 23);
		pnlBuscaLivros.add(rdbtnNewRadioButton);

		JRadioButton rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.setBackground(SystemColor.controlHighlight);
		rdbtnAutor.setBounds(173, 39, 62, 23);
		pnlBuscaLivros.add(rdbtnAutor);

		JRadioButton rdbtnEditora = new JRadioButton("Editora");
		rdbtnEditora.setBackground(SystemColor.controlHighlight);
		rdbtnEditora.setBounds(237, 39, 109, 23);
		pnlBuscaLivros.add(rdbtnEditora);

		btnPesquisarLivro = new JButton("Pesquisar");
		btnPesquisarLivro.setForeground(Color.BLACK);
		btnPesquisarLivro.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\Search.png"));
		btnPesquisarLivro.setBounds(407, 61, 132, 23);

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
		
		JLabel lblBuscarPor = new JLabel("Buscar Por:");
		lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBuscarPor.setBounds(10, 41, 85, 16);
		pnlBuscaLivros.add(lblBuscarPor);

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
		btnPesquisarAutor.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\Search.png"));

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
		btnPesquisaEditora.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\Search.png"));
		
		JButton btnCadastrarEditora = new JButton("Cadastrar");
		btnCadastrarEditora.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\add.png"));
		btnCadastrarEditora.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrarEditora.setBounds(549, 61, 132, 23);
		pnlBuscaEditora.add(btnCadastrarEditora);

		cardLayout = (CardLayout) (pnlCards.getLayout());

		JPanel InitialPanel = new JPanel();
		pnlCards.add(InitialPanel, "InitialPanel");

		btnEditoras.setName("btnEditoras");
		btnAutores.setName("btnAutores");
		btnLivros.setName("btnLivros");

		BookscrollPane.setVisible(false);
		autoresScrollPane.setVisible(false);

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

	public String GetAuthorsTextSearch() {
		return txtBuscaAutores.getText();
	}

	public String GetPublishersTextSearch() {
		return txtBuscaEditora.getText();
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

	public void MontaTableLivros(ArrayList<Livro> lstLivros) {

		Object[] colNames = { "ISBN", "Nome", "Autores", "Editora", "Preço", "", "", "" };
		Object[][] data = new Object[0][8];
		dtmBooks = new DefaultTableModel(data, colNames);

		tblLivros.setModel(dtmBooks);

		tblLivros.setDefaultRenderer(Object.class, new RenderTable());

		JtableButton btnEdit = new JtableButton("D:\\NP12019\\APS2019\\imgs\\edit.png", "btnEditar");
		JtableButton btnExclude = new JtableButton("D:\\\\NP12019\\\\APS2019\\\\imgs\\\\ExcludeIcon.png", "btnExcluir");
		JtableButton btnDetails = new JtableButton("D:\\\\NP12019\\\\APS2019\\\\imgs\\\\Details.png", "btnDetalhes");

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

		JtableButton btnEdit = new JtableButton("D:\\NP12019\\APS2019\\imgs\\edit.png", "btnEditar");
		JtableButton btnExclude = new JtableButton("D:\\\\NP12019\\\\APS2019\\\\imgs\\\\ExcludeIcon.png", "btnExcluir");

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

		JtableButton btnEdit = new JtableButton("D:\\NP12019\\APS2019\\imgs\\edit.png", "btnEditar");
		JtableButton btnExclude = new JtableButton("D:\\\\NP12019\\\\APS2019\\\\imgs\\\\ExcludeIcon.png", "btnExcluir");

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

	public Livro getLivroBySelectedRow() {
		int linhaSelectionada = tblLivros.getSelectedRow();

		String ISBN = dtmBooks.getValueAt(linhaSelectionada, 0).toString();
		String Nome = dtmBooks.getValueAt(linhaSelectionada, 1).toString();
		String Autores = dtmBooks.getValueAt(linhaSelectionada, 2).toString();
		String Editora = dtmBooks.getValueAt(linhaSelectionada, 3).toString();
		double Preco = (double) dtmBooks.getValueAt(linhaSelectionada, 4);

		return new Livro(ISBN, Nome, Preco, Autores, Editora);
	}
}
