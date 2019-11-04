package model;

import javax.print.DocFlavor.STRING;

public class Autor {

	private int AuthorID;
	private String name;
	private String fname;
	
	public Autor(int pAutorId, String pName, String pFName) {
		AuthorID = pAutorId;
		name = pName;
		fname =  pFName;
	}
	public Autor (String pName, String pFName) {
		name = pName;
		fname =  pFName;
	}
	
	public int getAuthorID() {
		return AuthorID;
	}
	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	
}
