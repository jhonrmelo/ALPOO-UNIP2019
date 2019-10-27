CREATE TRIGGER BooksRelationShipDelete
  BEFORE DELETE
  ON  BOOKS
  FOR EACH ROW
  EXECUTE PROCEDURE DeleteBooksFromRelationshipTable();
  
  
  