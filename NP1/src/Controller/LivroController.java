package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import DAO.LivroDAO;
import Model.Livro;

public class LivroController {
	
	private LivroDAO DAO;
	
	public LivroController() {
	DAO = new LivroDAO();
	}
	
	public void ExecutaConexao() {
		DAO.fazConexao();
	}
    
	public ArrayList<Livro> GetLivrosByNome (String Nome){
		
		return DAO.GetLivrosByNome(Nome);
	}
	
	
	public void MontaTabelaLivros(DefaultTableModel dtm, String Busca) {
		
		ArrayList<Livro> lstLivro = GetLivrosByNome(Busca);
		dtm.setNumRows(0);
		
		for(Livro llivro : lstLivro) {
			dtm.addRow(new Object[] {llivro.getNome(), llivro.getPreco(), llivro.getAutor(),llivro.getEditora()});		
		}
		
	}
}

