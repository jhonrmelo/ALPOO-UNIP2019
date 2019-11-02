package model;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class FiltroBuscaLivro {

	private String NameLivro;
	private int publisherID;
	private int AuthorID;

	public FiltroBuscaLivro(String pNameLivro, int pPublisherID, int pAuthorId) {

		NameLivro = pNameLivro;
		publisherID = pPublisherID;
		AuthorID = pAuthorId;
	}

	public String getNameLivro() {
		return NameLivro;
	}

	public void setNameLivro(String nameLivro) {
		NameLivro = nameLivro;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	public int getAuthorID() {
		return AuthorID;
	}

	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}

	public String GetWhere() {
		ArrayList<String> whereList = new ArrayList<String>();

		if (!StringUtils.isEmpty(NameLivro)) {
			whereList.add("TITLE ILIKE (?)");
		}
		if (publisherID != 0) {
			whereList.add("PS.PUBLISHER_ID = (?)");
		}

		if (AuthorID != 0) {
			whereList.add("BA.AUTHOR_ID = (?)");
		}
		if(whereList.size() > 0) {
			return "WHERE " + String.join("AND ", whereList);
		}
		return "";
	}
	
	public PreparedStatement getPrepareStatement (PreparedStatement pstm) {
		return null;
	}

}
