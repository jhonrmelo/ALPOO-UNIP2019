package Model;

public class Livro {
	private String Nome;
	private double Preco;
	private String Autor;
	private String Editora;
	
	
	public Livro(String pNome, double pPreco, String pAutor, String pEditora) {
		
		Nome  = pNome;
		Preco = pPreco;
		Autor = pAutor;
		Editora = pEditora;
	}


	public String getNome() {
		return Nome;
	}


	public void setNome(String nome) {
		Nome = nome;
	}


	public double getPreco() {
		return Preco;
	}


	public void setPreco(double preco) {
		Preco = preco;
	}


	public String getAutor() {
		return Autor;
	}


	public void setAutor(String autor) {
		Autor = autor;
	}


	public String getEditora() {
		return Editora;
	}


	public void setEditora(String editora) {
		Editora = editora;
	}
}
