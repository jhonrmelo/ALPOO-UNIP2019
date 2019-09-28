package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.LivroController;
import Model.Livro;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class View {

	private JFrame frame;
	private JTextField txtSearch;
	DefaultTableModel dtm = new DefaultTableModel();
	private JTable tblLivros;
	private JScrollPane scrollPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 575, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel PanelPesquisa = new JPanel();
		PanelPesquisa.setBackground(SystemColor.controlHighlight);
		PanelPesquisa.setForeground(SystemColor.controlHighlight);
		PanelPesquisa.setBounds(10, 11, 539, 73);
		frame.getContentPane().add(PanelPesquisa);
		PanelPesquisa.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(37, 30, 335, 20);
		PanelPesquisa.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new BuscaLivros());

		btnPesquisar.setForeground(Color.BLACK);
		btnPesquisar.setBounds(382, 29, 109, 23);
		PanelPesquisa.add(btnPesquisar);
		
		JLabel lblFiltroDePesquisa = new JLabel("Filtro de Pesquisa:");
		lblFiltroDePesquisa.setForeground(SystemColor.activeCaptionText);
		lblFiltroDePesquisa.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblFiltroDePesquisa.setBounds(37, 11, 153, 14);
		PanelPesquisa.add(lblFiltroDePesquisa);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 91, 539, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		tblLivros = new JTable(dtm);
		tblLivros.setBounds(10, 229, 519, -217);
		panel.add(tblLivros);
		
		scrollPane = new JScrollPane(tblLivros);
		scrollPane.setBounds(10, 0, 519, 239);
		panel.add(scrollPane);

			
		
			
	}
	
	//Metodos de eventos
	class BuscaLivros implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			tblLivros.setModel(dtm = new DefaultTableModel());
			
			dtm.addColumn("Titulo");
			dtm.addColumn("Preço");	
			dtm.addColumn("Autore(s)");		
			dtm.addColumn("Editora");				
			
			tblLivros.getColumnModel().getColumn(0).setPreferredWidth(100);
			tblLivros.getColumnModel().getColumn(1).setPreferredWidth(100);
			tblLivros.getColumnModel().getColumn(2).setPreferredWidth(100);
			tblLivros.getColumnModel().getColumn(3).setPreferredWidth(100);
			
			LivroController livro = new LivroController();
			
			livro.MontaTabelaLivros(dtm,txtSearch.getText());
						
		}
		
	}
}
