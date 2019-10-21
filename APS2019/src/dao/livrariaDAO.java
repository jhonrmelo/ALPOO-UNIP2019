package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Autor;
import model.Editora;
import model.Livro;
import util.SqlConnection;

public class livrariaDAO {
	public ArrayList<Livro> GetLivrosByNome(String nome) {

		ArrayList<Livro> lstLivro = new ArrayList<Livro>();

		try (Connection connection = SqlConnection.GetConnection()) {

			final String SqlQuery = "SELECT BKS.ISBN ,BKS.TITLE,BKS.PRICE, STRING_AGG(AUTH.FNAME || ' ' || AUTH.NAME,'; ') AS AUTHORNAME, PS.NAME AS PUBLISHERNAME "
					+ "FROM BOOKS BKS " + "JOIN BOOKSAUTHORS BA ON BA.ISBN =  BKS.ISBN "
					+ "JOIN AUTHORS AUTH ON AUTH.AUTHOR_ID = BA.AUTHOR_ID "
					+ "JOIN PUBLISHERS PS ON PS.PUBLISHER_ID = BKS.PUBLISHER_ID " + "WHERE TITLE ILIKE LOWER(?)"
					+ "GROUP BY  BKS.ISBN ,BKS.TITLE, BKS.PRICE, PS.NAME ";

			PreparedStatement pstm = connection.prepareStatement(SqlQuery);
			pstm.setString(1, "%" + nome + "%");
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

			final String sqlQuery = "SELECT AUTHOR_ID AS  ID, FNAME AS  NOME, NAME AS SOBRENOME " + 
									"FROM AUTHORS AUTH "
								  + "WHERE NAME LIKE (?) OR FNAME LIKE (?) " 
								  + "ORDER BY AUTHOR_ID";

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

			final String sqlQuery = " SELECT PUBLISHER_ID AS ID,  NAME AS NOME, URL " 
							       + "FROM PUBLISHERS "
								   + "WHERE NAME ILIKE (?)"
								   + "ORDER BY PUBLISHER_ID";

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
}
