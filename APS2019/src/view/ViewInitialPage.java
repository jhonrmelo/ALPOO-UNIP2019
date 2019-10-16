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
	ViewSplitPanel splitPanel;

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

		splitPanel = new ViewSplitPanel();
		contentPane.add(splitPanel);
		setVisible(true);
	}

	public void SetChangeCardAction(ActionListener act) {
		splitPanel.SetChangeCardAction(act);
	}

	public void SetActionListenerSearchButton(ActionListener act) {
		splitPanel.SetActionListenerSearchButton(act);
	}

	public String GetBooksTextSearch() {
		return splitPanel.GetBooksTextSearch();
	}

	public void MontaTableLivros(ArrayList<Livro> lstLivros) {
		splitPanel.MontaTableLivros(lstLivros);
	}

	public void SetCardVisible(String CardName) {
		splitPanel.SetCardVisible(CardName);
	}
}
