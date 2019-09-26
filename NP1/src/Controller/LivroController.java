package Controller;

public class LivroController {

    //Favor realizar a conexão (não tenho os dados) - Gabriel
    public final static String DATABASE = "Teste";
	public final static String URL = "jdbc:postgresql:" + DATABASE;
	public final static String USER = "teste";
	public final static String PASS = "123456";
    public String filtro;

    //Favor validar - Gabriel
    //Testa a conexão com o banco antes de buscar os dados  
    static public void testaConexao() {
		try(Connection connection = DriverManager.getConnection(URL, USER, PASS)){
			System.out.println("Conexao feita");
		} catch(SQLException e) {
			System.out.println("ERROR: Erro ao tentar conexao");
			e.printStackTrace();
		}
	}

    //Favor validar - Gabriel
    //Busca Livro sem filtro 
    static public void listaLivros() {

		final String query = "SELECT * FROM livros";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASS))
        {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()) {
				System.out.println(
                "Nome: " + rs.getString(1) + 
                " Preço: " + rs.getDouble(2) +
                " Autor: " + rs.getString(3) + 
                " Editora: "  + rs.getString(4));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
    }

    //Favor validar - Gabriel
    //Busca Livro especificos
    static public void buscaLivro() {
        
        final String query = "select * from livros like '" + this.filtro + "'";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASS))
        {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) 
            {
                System.out.println(  
                "Nome: " + rs.getString(1) + 
                " Preço: " + rs.getDouble(2) +
                " Autor: " + rs.getString(3) + 
                " Editora: "  + rs.getString(4));    
            }
        } catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
