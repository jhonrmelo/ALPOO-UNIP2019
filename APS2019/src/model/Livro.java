package model;

public class Livro {
	private String isbn;
	private String Nome;
	private double Preco;
	private String Autor;
	private String Editora;
	private int EditoraID;
	
	
	public Livro(String pIsbn,String pNome, double pPreco, String pAutor, String pEditora) {
		
		isbn = pIsbn;
		Nome  = pNome;
		Preco = pPreco;
		Autor = pAutor;
		Editora = pEditora;
	}
	
	public Livro(String pIsbn,String pNome, double pPreco, int pEditoraID) {
		isbn = pIsbn;
		Nome  = pNome;
		Preco = pPreco;
		EditoraID = pEditoraID;
	}


	public String getIsbn() {
		return isbn;
	}


	public int getEditoraID() {
		return EditoraID;
	}

	public void setEditoraID(int editoraID) {
		EditoraID = editoraID;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
