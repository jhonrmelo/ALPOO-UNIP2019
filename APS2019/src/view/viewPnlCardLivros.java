package view;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import model.Livro;
import model.PersonalizadeJbutton;
import model.RenderTable;

public class viewPnlCardLivros extends JPanel {
	JTable  tblLivros;
	DefaultTableModel dtm = new DefaultTableModel();
	viewPnlBuscaLivros pnlBuscaLivros ;
	JScrollPane BookscrollPane;
	
	public viewPnlCardLivros() {
		setLayout(null);
		
		 pnlBuscaLivros = new viewPnlBuscaLivros();	
		add(pnlBuscaLivros);

		Object[] colNames = { "Nome", "Autores", "Editora", "Preço", "", "","" };
		Object[][] data = new Object[0][7];
		dtm = new DefaultTableModel(data, colNames);
		
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
		setBounds(22, 484, 567, -354);
		tblLivros.setRowHeight(40);
		add(tblLivros);
		

		BookscrollPane = new JScrollPane(tblLivros);
		BookscrollPane.setBounds(0, 106, 764, 536);	
		BookscrollPane.setVisible(false);	
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
	public void SetActionListenerSearchButton(ActionListener act) {
		pnlBuscaLivros.SetActionListenerSearchButton(act);
	}
	public String GetBooksTextSearch() {
		return pnlBuscaLivros.GetBooksTextSearch();
	}

}
