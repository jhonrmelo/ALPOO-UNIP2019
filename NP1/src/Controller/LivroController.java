package Controller;

import java.util.ArrayList;

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
}

