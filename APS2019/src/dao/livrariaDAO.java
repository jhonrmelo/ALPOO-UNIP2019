package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Livro;
import util.SqlConnection;


public class livrariaDAO {
	public ArrayList<Livro> GetLivrosByNome(String nome) {

		ArrayList<Livro> lstLivro = new ArrayList<Livro>();
		
		try (Connection connection =  SqlConnection.GetConnection()) {

			final String SqlQuery = "SELECT BKS.TITLE,BKS.PRICE, STRING_AGG(AUTH.FNAME || ' ' || AUTH.NAME,'; ') AS AUTHORNAME, PS.NAME AS PUBLISHERNAME "
									+ "FROM BOOKS BKS "
									+ "JOIN BOOKSAUTHORS BA ON BA.ISBN =  BKS.ISBN "
									+ "JOIN AUTHORS AUTH ON AUTH.AUTHOR_ID = BA.AUTHOR_ID "
									+ "JOIN PUBLISHERS PS ON PS.PUBLISHER_ID = BKS.PUBLISHER_ID "
									+ "WHERE TITLE ILIKE LOWER(?)"
									+ "GROUP BY BKS.TITLE, BKS.PRICE, PS.NAME ";


			PreparedStatement pstm = connection.prepareStatement(SqlQuery);
			pstm.setString(1, "%" + nome + "%");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Livro livro = new Livro(rs.getString("TITLE"), rs.getDouble("PRICE"), rs.getString("AUTHORNAME"),
						rs.getString("PUBLISHERNAME"));
				lstLivro.add(livro);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lstLivro;

	}
}
