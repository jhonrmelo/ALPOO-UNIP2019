package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import DAO.LivroDAO;
import Model.Livro;
import View.DetalhesAutores;
import View.LivrariaView;

public class LivrariaController {

	LivroDAO DAO;
	LivrariaView lView; 
	DetalhesAutores lDetalhes;

	public LivrariaController() {

		DAO = new LivroDAO();
		lView = new LivrariaView();

		lView.BuscaComporamento(new BuscaLivros());
		lView.ShowDetalhes(new MostraDetalhes());
		lView.DetalhesAutores(new DetalhesAutor());

	}

	public void ExecutaConexao() {
		DAO.fazConexao();
	}

	public ArrayList<Livro> GetLivrosByNome(String Nome) {

		return DAO.GetLivrosByNome(Nome);
	}
	
	class DetalhesAutor implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			lDetalhes = new DetalhesAutores();
			Livro livro = lView.GetLinha();
			String[] Autores = livro.getAutor().split(";");
			lDetalhes.SetTblAutores(Autores);	
		}
	}
	class BuscaLivros implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Livro> lstLivros = GetLivrosByNome(lView.GetTextSearch());
			lView.MontaTabelaLivros(lstLivros);
		}
	}

	class MostraDetalhes implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(e.getClickCount() == 1) {
				Livro livro = lView.GetLinha();
				lView.SetDetalhes(livro);
				lView.ChangeVisibilityPanel();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
