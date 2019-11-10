package model;

public class BooksAuthors {
	private String isbn;
	private int AuthorID;
	private int seq_no;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BooksAuthors(String isbn, int authorID, int seq_no) {
		this.isbn = isbn;
		AuthorID = authorID;
		this.seq_no = seq_no;
	}

	public int getAuthorID() {
		return AuthorID;
	}

	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}

	public int getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}
}
