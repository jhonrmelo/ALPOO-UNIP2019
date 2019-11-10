package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

import model.Autor;
import model.ClasseMonetaria;
import model.Editora;
import model.Item;
import model.JtableButton;
import model.Livro;
import model.RenderTable;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class viewInsertBooks extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	JComboBox cbbEditora;
	JComboBox cbbAutor;
	JButton btnSelecionarAutor;
	private DefaultTableModel dtmAutor;
	private JTextField txtIsbn;
	JButton btnCadastrar;
	JTextField txtpreco;
	private JTable tblAutor;

	public viewInsertBooks() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(viewInsertBooks.class.getResource("/imgs/Book-Blank-Book-icon.png")));
		setTitle("Cadastro de Livros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(26, 11, 48, 14);
		contentPane.add(lblNome);

		Object[] colNames = { "ID", "Nome" };

		Object[][] data = new Object[0][2];
		dtmAutor = new DefaultTableModel(data, colNames);

		txtNome = new JTextField();
		txtNome.setBounds(26, 27, 389, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblPreo = new JLabel("Pre\u00E7o");
		lblPreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreo.setBounds(319, 68, 48, 14);
		contentPane.add(lblPreo);

		txtpreco = new JTextField("0");
		txtpreco.setDocument(new ClasseMonetaria());
		txtpreco.setBounds(319, 88, 96, 20);
		contentPane.add(txtpreco);
		txtpreco.setColumns(10);

		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEditora.setBounds(31, 119, 48, 14);
		contentPane.add(lblEditora);

		JLabel lblAutores = new JLabel("Autores");
		lblAutores.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAutores.setBounds(166, 119, 63, 14);
		contentPane.add(lblAutores);

		btnSelecionarAutor = new JButton("Selecionar");
		btnSelecionarAutor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelecionarAutor.setBounds(319, 134, 96, 23);
		contentPane.add(btnSelecionarAutor);

		JLabel lblAutoresSelecionados = new JLabel("Autores Selecionados");
		lblAutoresSelecionados.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAutoresSelecionados.setBounds(60, 168, 152, 14);
		contentPane.add(lblAutoresSelecionados);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 192, 389, 197);
		contentPane.add(scrollPane);

		tblAutor = new JTable(dtmAutor);
		scrollPane.setViewportView(tblAutor);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		setLocationRelativeTo(null);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(viewInsertBooks.class.getResource("/imgs/add.png")));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrar.setBounds(296, 412, 119, 23);
		contentPane.add(btnCadastrar);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIsbn.setBounds(27, 68, 48, 14);
		contentPane.add(lblIsbn);

		txtIsbn = new JTextField();
		txtIsbn.setBounds(27, 88, 282, 20);
		contentPane.add(txtIsbn);
		txtIsbn.setColumns(10);

		JLabel lbldoisCliquesPara = new JLabel("(Dois cliques para remover)");
		lbldoisCliquesPara.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldoisCliquesPara.setBounds(207, 168, 163, 14);
		contentPane.add(lbldoisCliquesPara);
		setVisible(true);
	}

	public void LoadComboboxSearch(ArrayList<Editora> Editoras, ArrayList<Autor> Autores) {

		Vector<Item> modelCbbAutor = new Vector<Item>();
		Vector<Item> modelcbbEditora = new Vector<Item>();

		Item customItem = new Item(0, "Todos");

		modelCbbAutor.add(customItem);
		modelcbbEditora.add(customItem);

		for (Autor _autor : Autores) {
			modelCbbAutor.add(new Item(_autor.getAuthorID(), _autor.GetFullName()));
		}

		for (Editora _editora : Editoras) {
			modelcbbEditora.add(new Item(_editora.getPublisher_id(), _editora.getName()));
		}
		cbbAutor = new JComboBox<Item>(modelCbbAutor);
		cbbAutor.setBounds(166, 134, 143, 22);
		contentPane.add(cbbAutor);
		cbbAutor.setSelectedIndex(0);

		cbbEditora = new JComboBox<Item>(modelcbbEditora);
		cbbEditora.setBounds(31, 134, 125, 22);
		contentPane.add(cbbEditora);
		cbbEditora.setSelectedIndex(0);

	}

	public Item GetSelectedAuthor() {
		return (Item) cbbAutor.getSelectedItem();
	}

	public void InsertAuthorInTabel(int id, String Nome) {

		JtableButton btnExclude = new JtableButton("btnExcluir");

		Object[] dados = new Object[2];
		dados[0] = id;
		dados[1] = Nome;
		dtmAutor.addRow(dados);

	}

	public void AddActionBtnSelecionar(ActionListener act) {
		btnSelecionarAutor.addActionListener(act);
	}

	public void AddActionBtnCadastrar(ActionListener act) {
		btnCadastrar.addActionListener(act);
	}
	public String getIsbnToInsert() {
		return txtIsbn.getText();
	}
	public String getNomeToInsert() {
		return txtNome.getText();
	}
	public String getPrecoToInsert() {
		return txtpreco.getText();
	}
	public int getEditoraIDToInsert() {
		Item Publisher  =  (Item)cbbEditora.getSelectedItem();
		return Publisher.getId();
	}

}
