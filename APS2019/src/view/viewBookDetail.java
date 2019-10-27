package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Livro;

public class viewBookDetail extends JFrame {

	private JPanel contentPane;
	private JTextField txtnome;
	private JTextField txtEditora;
	private JTextField txtPreco;
	DefaultTableModel dtm = new DefaultTableModel();
	private JTextField txtISBN;
	private JTable tblAutores;
	JScrollPane scrollPane;

	public viewBookDetail() {
		setTitle("Detalhe Livro");

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - getSize().width, dim.height / 2 - getSize().height / 2);

		setIconImage(Toolkit.getDefaultToolkit().getImage(viewBookDetail.class.getResource("/imgs/Book-Blank-Book-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 436, 284);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Object[] colNames = { "Autores" };
		Object[][] data = new Object[0][1];
		dtm = new DefaultTableModel(data, colNames);

		JLabel lblDetalhes = new JLabel("Detalhes");
		lblDetalhes.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDetalhes.setBounds(166, 11, 82, 14);
		contentPane.add(lblDetalhes);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(10, 41, 46, 14);
		contentPane.add(lblNome);

		txtnome = new JTextField();
		txtnome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtnome.setEditable(false);
		txtnome.setBounds(76, 40, 304, 20);
		contentPane.add(txtnome);
		txtnome.setColumns(10);

		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEditora.setBounds(10, 104, 60, 14);
		contentPane.add(lblEditora);

		txtEditora = new JTextField();
		txtEditora.setEditable(false);
		txtEditora.setBounds(76, 102, 158, 20);
		contentPane.add(txtEditora);
		txtEditora.setColumns(10);

		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreco.setBounds(257, 73, 48, 14);
		contentPane.add(lblPreco);

		txtPreco = new JTextField();
		txtPreco.setEditable(false);
		txtPreco.setBounds(315, 71, 65, 20);
		contentPane.add(txtPreco);
		txtPreco.setColumns(10);

		JLabel lblISBN = new JLabel("ISBN:");
		lblISBN.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblISBN.setBounds(10, 74, 48, 14);
		contentPane.add(lblISBN);

		txtISBN = new JTextField();
		txtISBN.setEditable(false);
		txtISBN.setBounds(76, 71, 158, 20);
		contentPane.add(txtISBN);
		txtISBN.setColumns(10);

		tblAutores = new JTable(dtm);
		tblAutores.setBounds(315, 163, 245, 38);
		contentPane.add(tblAutores);

		scrollPane = new JScrollPane(tblAutores);
		scrollPane.setBounds(59, 148, 297, 72);
		contentPane.add(scrollPane);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		setLocationRelativeTo(null);

		setVisible(true);

	}

	public void SetDetails(Livro pLivro) {
		txtnome.setText(pLivro.getNome());
		txtPreco.setText("R$ " + String.format("%.02f", pLivro.getPreco()));
		txtEditora.setText(pLivro.getEditora());
		txtISBN.setText(pLivro.getIsbn());
	}

	public void SetTableAuthor(String[] Autores) {
		dtm.setNumRows(0);

		for (String Autor : Autores) {
			Object[] dados = new Object[1];
			dados[0] = Autor;
			dtm.addRow(dados);
		}
		scrollPane.setVisible(true);
	}
	

}
