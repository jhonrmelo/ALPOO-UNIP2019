package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import model.Autor;
import model.Editora;
import model.FiltroBuscaLivro;
import model.Livro;
import util.SqlConnection;

public class livrariaDAO {
	public ArrayList<Livro> GetLivrosByFiltro(FiltroBuscaLivro filtro) {

		ArrayList<Livro> lstLivro = new ArrayList<Livro>();

		try (Connection connection = SqlConnection.GetConnection()) {

			final String SqlQuery = "SELECT BKS.ISBN ,BKS.TITLE,BKS.PRICE, STRING_AGG(AUTH.FNAME || ' ' || AUTH.NAME,'; ') AS AUTHORNAME, PS.NAME AS PUBLISHERNAME "
					+ "FROM BOOKS BKS " + "JOIN BOOKSAUTHORS BA ON BA.ISBN =  BKS.ISBN "
					+ "JOIN AUTHORS AUTH ON AUTH.AUTHOR_ID = BA.AUTHOR_ID "
					+ "JOIN PUBLISHERS PS ON PS.PUBLISHER_ID = BKS.PUBLISHER_ID " + filtro.GetWhere()
					+ "GROUP BY  BKS.ISBN ,BKS.TITLE, BKS.PRICE, PS.NAME ";

			PreparedStatement pstm = connection.prepareStatement(SqlQuery);
			int parameterIndex = 1;
			if (!StringUtils.isEmpty(filtro.getNameLivro())) {
				pstm.setString(parameterIndex, "%" + filtro.getNameLivro() + "%");
				parameterIndex++;
			}
			if (filtro.getPublisherID() != 0) {
				pstm.setInt(parameterIndex, filtro.getPublisherID());
				parameterIndex++;
			}

			if (filtro.getAuthorID() != 0) {
				pstm.setInt(parameterIndex, filtro.getAuthorID());
			}

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Livro livro = new Livro(rs.getString("isbn"), rs.getString("TITLE"), rs.getDouble("PRICE"),
						rs.getString("AUTHORNAME"), rs.getString("PUBLISHERNAME"));
				lstLivro.add(livro);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lstLivro;

	}

	public ArrayList<Autor> GetAuthorsByNome(String nome) {

		ArrayList<Autor> lstAuthrors = new ArrayList<Autor>();

		try (Connection connection = SqlConnection.GetConnection()) {

			final String sqlQuery = "SELECT AUTHOR_ID AS  ID, FNAME AS  NOME, NAME AS SOBRENOME " + "FROM AUTHORS AUTH "
					+ "WHERE NAME ILIKE (?) OR FNAME ILIKE (?) " + "ORDER BY AUTHOR_ID";

			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setString(1, "%" + nome + "%");
			pstm.setString(2, "%" + nome + "%");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Autor autor = new Autor(rs.getInt("Id"), rs.getString("sobrenome"), rs.getString("nome"));
				lstAuthrors.add(autor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lstAuthrors;

	}

	public ArrayList<Editora> GetPublishersByName(String nome) {

		ArrayList<Editora> lstPublisher = new ArrayList<Editora>();

		try (Connection connection = SqlConnection.GetConnection()) {

			final String sqlQuery = " SELECT PUBLISHER_ID AS ID,  NAME AS NOME, URL " + "FROM PUBLISHERS "
					+ "WHERE NAME ILIKE (?)" + "ORDER BY PUBLISHER_ID";

			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setString(1, "%" + nome + "%");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Editora editora = new Editora(rs.getInt("ID"), rs.getString("NOME"), rs.getString("URL"));
				lstPublisher.add(editora);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lstPublisher;

	}

	public boolean InsertPublisher(Editora publisher) {
		try (Connection connection = SqlConnection.GetConnection()) {
			final String sqlQuery = "INSERT INTO PUBLISHERS (NAME, URL) VALUES((?),(?))";
			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setString(1, publisher.getName());
			pstm.setString(2, publisher.getUrl());

			pstm.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean InsertAuthor(Autor author) {

		try (Connection connection = SqlConnection.GetConnection()) {
			final String sqlQuery = "INSERT INTO Authors (NAME, FNAME) VALUES((?),(?))";
			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setString(1, author.getName());
			pstm.setString(2, author.getFname());
			pstm.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void InsertBook(Livro livro) {
		try (Connection connection = SqlConnection.GetConnection()) {
			final String sqlQuery = "INSERT INTO BOOKS (ISBN, TITLE,PUBLISHER_ID,PRICE) VALUES((?),(?),(?),(?))";
			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setString(1, livro.getIsbn());
			pstm.setString(2, livro.getNome());
			pstm.setInt(3, livro.getEditoraID());
			pstm.setDouble(4, livro.getPreco());
			pstm.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean EditPublisher(Editora pEditora) {

		try (Connection connection = SqlConnection.GetConnection()) {

			final String sqlQuery = "UPDATE PUBLISHERS SET NAME = (?), URL = (?) WHERE PUBLISHER_ID = (?)";

			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setString(1, pEditora.getName());
			pstm.setString(2, pEditora.getUrl());
			pstm.setInt(3, pEditora.getPublisher_id());
			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean EditAuthors(Autor author) {

		try (Connection connection = SqlConnection.GetConnection()) {

			final String sqlQuery = "UPDATE AUTHORS SET NAME = (?), FNAME = (?) WHERE AUTHOR_ID = (?)";

			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setString(1, author.getName());
			pstm.setString(2, author.getFname());
			pstm.setInt(3, author.getAuthorID());
			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean DeleteBookByISBN(String isbn) {
		try (Connection connection = SqlConnection.GetConnection()) {

			final String sqlQuery = "DELETE FROM BOOKS WHERE ISBN = (?)";
			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setString(1, isbn);
			pstm.execute();

			return true;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void DeleteBooksByAuthorID(int id) {
		try (Connection connection = SqlConnection.GetConnection()) {
			
			final String sqlQuery = "DELETE FROM BOOKS " + 
									"WHERE BOOKS.ISBN IN (SELECT ISBN FROM BOOKSAUTHORS WHERE AUTHOR_ID = (?))";
			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setInt(1, id);
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteAuthorByAuthorID(int id) {
		try (Connection connection = SqlConnection.GetConnection()) {
			final String sqlQuery = "DELETE FROM AUTHORS WHERE AUTHOR_ID = (?)";
			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setInt(1, id);
			pstm.execute();	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

	public boolean DeletePublisherByID(int id) {
		try (Connection connection = SqlConnection.GetConnection()) {

			final String sqlQuery = "DELETE FROM PUBLISHERS WHERE PUBLISHER_ID = (?)";
			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			pstm.setInt(1, id);
			pstm.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Editora> GetEditoraToCombobox() {
		ArrayList<Editora> Editoras = new ArrayList<Editora>();
		try (Connection connection = SqlConnection.GetConnection()) {
			final String sqlQuery = "SELECT * FROM PUBLISHERS";
			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Editoras.add(new Editora(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Editoras;
	}

	public ArrayList<Autor> GetAutoresToCombobox() {
		ArrayList<Autor> Autores = new ArrayList<Autor>();
		try (Connection connection = SqlConnection.GetConnection()) {
			final String sqlQuery = "SELECT * FROM AUTHORS";
			PreparedStatement pstm = connection.prepareStatement(sqlQuery);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Autores.add(new Autor(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Autores;
	}
}
