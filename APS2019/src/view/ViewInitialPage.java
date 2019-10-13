package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.Livro;
import model.PersonalizadeJbutton;
import model.RenderTable;
import java.awt.Color;
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

public class ViewInitialPage extends JFrame {

	private JPanel contentPane;
	CardLayout cardLayout;
	JPanel pnlCards;
	JButton btnEditoras;
	JButton btnAutores;
	JButton btnLivros;
	JTextField txtBuscaLivros;
	DefaultTableModel dtm = new DefaultTableModel();
	JButton btnPesquisar;
	JTable tblLivros;
	JScrollPane BookscrollPane;

	public ViewInitialPage() {
		setForeground(new Color(0, 128, 128));
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\NP12019\\APS2019\\imgs\\Book-Blank-Book-icon.png"));
		setTitle("Livraria");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1122, 683);
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

		Object[] colNames = { "Nome", "Autores", "Editora", "Preço", "", "","" };
		Object[][] data = new Object[0][7];
		dtm = new DefaultTableModel(data, colNames);

		splitPanel.setResizeWeight(0.4);
		splitPanel.setBounds(0, 0, 1106, 644);
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

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, SystemColor.textHighlight,
				SystemColor.textHighlight, SystemColor.textHighlight));
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 764, 104);
		pnlCardLivros.add(panel);
		panel.setLayout(null);

		txtBuscaLivros = new JTextField();
		txtBuscaLivros.setBounds(10, 62, 387, 20);
		panel.add(txtBuscaLivros);
		txtBuscaLivros.setColumns(10);

		JLabel lblTeszte = new JLabel("LIVROS");
		lblTeszte.setBounds(262, 8, 62, 20);
		panel.add(lblTeszte);
		lblTeszte.setFont(new Font("Tahoma", Font.BOLD, 16));

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Livro");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton.setBackground(SystemColor.controlHighlight);
		rdbtnNewRadioButton.setBounds(10, 35, 70, 23);
		panel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.setBackground(SystemColor.controlHighlight);
		rdbtnAutor.setBounds(82, 36, 62, 23);
		panel.add(rdbtnAutor);

		JRadioButton rdbtnEditora = new JRadioButton("Editora");
		rdbtnEditora.setBackground(SystemColor.controlHighlight);
		rdbtnEditora.setBounds(146, 36, 109, 23);
		panel.add(rdbtnEditora);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setForeground(Color.BLACK);
		btnPesquisar.setIcon(new ImageIcon("D:\\NP12019\\APS2019\\imgs\\Search.png"));
		btnPesquisar.setBounds(407, 61, 132, 23);

		tblLivros = new JTable(dtm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblLivros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = tblLivros.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY()/ tblLivros.getRowHeight();
				
				if(row < tblLivros.getRowCount() && row >= 0 && column < tblLivros.getColumnCount() && column >= 0) {
					Object value = tblLivros.getValueAt(row, column);
					if(value instanceof JButton) {
					((JButton)value).doClick();
					JButton button = (JButton)value; 
					}
					
				}
			}
		});
		tblLivros.setBounds(22, 484, 567, -354);
		pnlCardLivros.add(tblLivros);
		tblLivros.setRowHeight(40);

		panel.add(btnPesquisar);

		BookscrollPane = new JScrollPane(tblLivros);
		BookscrollPane.setBounds(0, 106, 764, 536);
		pnlCardLivros.add(BookscrollPane);

		JPanel pnlCardAutores = new JPanel();
		pnlCards.add(pnlCardAutores, "pnlCardAutores");

		JLabel lblTeste = new JLabel("AUTORES");
		lblTeste.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlCardAutores.add(lblTeste);

		JPanel pnlCardEditoras = new JPanel();
		pnlCards.add(pnlCardEditoras, "pnlCardEditoras");

		JLabel lblTeste_1 = new JLabel("EDITORAS");
		lblTeste_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlCardEditoras.add(lblTeste_1);

		cardLayout = (CardLayout) (pnlCards.getLayout());

		JPanel InitialPanel = new JPanel();
		pnlCards.add(InitialPanel, "InitialPanel");

		btnEditoras.setName("btnEditoras");
		btnAutores.setName("btnAutores");
		btnLivros.setName("btnLivros");

		BookscrollPane.setVisible(false);

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

	public void SetActionListenerSearchButton(ActionListener act) {
		btnPesquisar.addActionListener(act);
	}

	public void MontaTableLivros(ArrayList<Livro> lstLivros) {

		tblLivros.setDefaultRenderer(Object.class, new RenderTable());

		
		//CRIAR UMA CLASSE DE BOTAO QUE HERDA DO JBUTTON POREM SETA TODOS ESSES VALORES
		PersonalizadeJbutton btn1 = new PersonalizadeJbutton("D:\\NP12019\\APS2019\\imgs\\edit.png");
		PersonalizadeJbutton btn2 = new PersonalizadeJbutton("D:\\\\NP12019\\\\APS2019\\\\imgs\\\\ExcludeIcon.png");
		PersonalizadeJbutton btn3 = new PersonalizadeJbutton("D:\\\\NP12019\\\\APS2019\\\\imgs\\\\Details.png");
				
		tblLivros.getColumnModel().getColumn(3).setPreferredWidth(10);
		tblLivros.getColumnModel().getColumn(4).setPreferredWidth(10);
		tblLivros.getColumnModel().getColumn(5).setPreferredWidth(10);
		tblLivros.getColumnModel().getColumn(6).setPreferredWidth(10);
		
		dtm.setNumRows(0);

		for (Livro lLivro : lstLivros) {
			Object[] dados = new Object[7];
			dados[0] = lLivro.getNome();
			dados[1] = lLivro.getAutor();
			dados[2] = lLivro.getEditora();
			dados[3] = lLivro.getPreco();
			dados[4] = btn1;
			dados[5] = btn2;
			dados[6] = btn3;

			dtm.addRow(dados);
		}
		BookscrollPane.setVisible(true);

	}
}
