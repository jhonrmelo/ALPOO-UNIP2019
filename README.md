<h1> APS UNIP 2019 2ºSEMESTRE 2º ANO  </h1>
<HR>

- Trabalho executado na linguagem de programação JAVA com o uso do banco de dados PostgreSQL.
- A principal função do sistema é um CRUD onde é possivel administrar os registros relacionados a uma livaria Ficticia. 
- Projeto desenvolvido apenas com fins acadêmicos.

 <h3> COLABORADORES <H3> 
 <span>  Gabriel Souza, Github:<a href= https://github.com/gabrielpsouza> @gabrielpsouza. </a> </span>  <br/>
 <span> Victor Rodriguez, Github: <a href = https://github.com/VictorRodrigues145> @VictorRodrigues145. </a> </span> <br/>
 <span> Thiago Melo, Github:  <a href = https://github.com/ThiaguinhoMeloo>  @ThiaguinhoMeloo. </a> </span> <br/>
 
Para criação das tabelas segue scripts: 
Authors:
 CREATE TABLE Authors (
  author_id SERIAL PRIMARY KEY, 
  name CHAR(25), 
  fname CHAR(25)
);

Publishers:
CREATE TABLE Publishers (
  publisher_id SERIAL PRIMARY KEY, 
  name CHAR(30), 
  url CHAR(80)
);

Books:
CREATE TABLE Books (
  title CHAR(60), 
  isbn CHAR(13) PRIMARY KEY, 
  publisher_id INT, 
  price DECIMAL(10,2),
  FOREIGN KEY (publisher_id) REFERENCES Publishers (publisher_id)
);

BooksAuthors:
CREATE TABLE BooksAuthors (
  isbn CHAR(13), 
  author_id INT, 
  seq_no INT,
  FOREIGN KEY (isbn) REFERENCES Books (isbn),
  FOREIGN KEY (author_id) REFERENCES Authors (author_id),
  PRIMARY KEY (isbn, author_id)
);

