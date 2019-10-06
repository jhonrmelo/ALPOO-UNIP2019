package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.LivrariaController;
import Model.Livro;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.Console;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.CardLayout;
import javax.swing.JToggleButton;

public class LivrariaView extends JFrame {

	private JTextField txtSearch;
	DefaultTableModel dtm = new DefaultTableModel();
	private JTable tblLivros;
	private JScrollPane scrollPane;
	private JButton btnPesquisar;
	JLabel lblDetalhesEditora;
	JLabel lblDetalhesPreco;
	JPanel pnlDetalhes;
	JLabel lblDetalheLivro;
	JButton btnDetalhes;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public LivrariaView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setBounds(100, 100, 575, 542);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);

		JPanel PanelPesquisa = new JPanel();
		PanelPesquisa.setLayout(null);
		PanelPesquisa.setBackground(SystemColor.controlHighlight);
		PanelPesquisa.setForeground(SystemColor.controlHighlight);
		PanelPesquisa.setBounds(10, 11, 539, 73);
		getContentPane().add(PanelPesquisa);

		txtSearch = new JTextField();
		txtSearch.setBounds(37, 30, 335, 20);
		PanelPesquisa.add(txtSearch);
		txtSearch.setColumns(10);

		btnPesquisar = new JButton("Pesquisar");

		btnPesquisar.setForeground(Color.BLACK);
		btnPesquisar.setBounds(382, 29, 109, 23);
		PanelPesquisa.add(btnPesquisar);

		JLabel lblFiltroDePesquisa = new JLabel("Filtro de Pesquisa:");
		lblFiltroDePesquisa.setForeground(SystemColor.activeCaptionText);
		lblFiltroDePesquisa.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblFiltroDePesquisa.setBounds(37, 11, 153, 14);
		PanelPesquisa.add(lblFiltroDePesquisa);

		Object[] colNames = { "Nome", "Autores", "Editora", "Preço" };
		Object[][] data = new Object[0][4];
		dtm = new DefaultTableModel(data, colNames);

		JPanel panel = new JPanel();
		panel.setBounds(10, 91, 539, 250);
		getContentPane().add(panel);
		panel.setLayout(null);

		tblLivros = new JTable(dtm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblLivros.setBounds(10, 229, 519, -217);
		panel.add(tblLivros);

		scrollPane = new JScrollPane(tblLivros);
		scrollPane.setBounds(0, 0, 539, 239);
		panel.add(scrollPane);

		pnlDetalhes = new JPanel();
		pnlDetalhes.setBounds(10, 355, 539, 112);
		getContentPane().add(pnlDetalhes);
		pnlDetalhes.setLayout(null);

		JLabel lblLivro = new JLabel("LIVRO:");
		lblLivro.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblLivro.setBounds(10, 11, 77, 14);
		pnlDetalhes.add(lblLivro);

		JLabel lblAutores = new JLabel("AUTORES:");
		lblAutores.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblAutores.setBounds(10, 36, 113, 14);
		pnlDetalhes.add(lblAutores);

		JLabel lblEditora = new JLabel("EDITORA:");
		lblEditora.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblEditora.setBounds(10, 61, 113, 14);
		pnlDetalhes.add(lblEditora);

		JLabel lblPreco = new JLabel("PRE\u00C7O:");
		lblPreco.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblPreco.setBounds(10, 86, 113, 14);
		pnlDetalhes.add(lblPreco);

		lblDetalhesEditora = new JLabel("");
		lblDetalhesEditora.setBounds(116, 61, 333, 14);
		pnlDetalhes.add(lblDetalhesEditora);

		btnDetalhes = new JButton("Detalhes");
		btnDetalhes.setBounds(116, 36, 89, 16);
		pnlDetalhes.add(btnDetalhes);

		lblDetalhesPreco = new JLabel("");
		lblDetalhesPreco.setBounds(89, 86, 134, 14);
		pnlDetalhes.add(lblDetalhesPreco);

		lblDetalheLivro = new JLabel("");
		lblDetalheLivro.setBounds(89, 13, 394, 14);
		pnlDetalhes.add(lblDetalheLivro);

		pnlDetalhes.setVisible(false);

		setVisible(true);

	}

	public void BuscaComporamento(ActionListener Act) {
		btnPesquisar.addActionListener(Act);
	}

	public String GetTextSearch() {
		return txtSearch.getText();
	}

	public void MontaTabelaLivros(ArrayList<Livro> lstLivros) {
		dtm.setNumRows(0);

		for (Livro lLivro : lstLivros) {
			Object[] data = new Object[4];
			data[0] = lLivro.getNome();
			data[1] = lLivro.getAutor();
			data[2] = lLivro.getEditora();
			data[3] = lLivro.getPreco();
			dtm.addRow(data);
		}
	}

	public void ShowDetalhes(MouseListener ml) {
		tblLivros.addMouseListener(ml);
	}

	public void DetalhesAutores(ActionListener act) {
		btnDetalhes.addActionListener(act);

	}

	public Livro GetLinha() {
		int LinhaSelecionda = tblLivros.getSelectedRow();
		String Nome = dtm.getValueAt(LinhaSelecionda, 0).toString();
		String Autores = dtm.getValueAt(LinhaSelecionda, 1).toString();
		String Editora = dtm.getValueAt(LinhaSelecionda, 2).toString();
		double Preco = (double) dtm.getValueAt(LinhaSelecionda, 3);

		return new Livro(Nome, Preco, Autores, Editora);
	}

	public void SetDetalhes(Livro livro) {
		lblDetalheLivro.setText(livro.getNome());
		lblDetalhesEditora.setText(livro.getEditora());
		lblDetalhesPreco.setText("R$" + String.valueOf(livro.getPreco()));
	}

	public void ChangeVisibilityPanel() {
		pnlDetalhes.setVisible(true);
	}

}
