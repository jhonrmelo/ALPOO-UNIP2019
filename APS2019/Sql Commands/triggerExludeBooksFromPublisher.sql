CREATE TRIGGER BooksRelationShipPublisherDelete
  BEFORE DELETE
  ON  PUBLISHERS
  FOR EACH ROW
  EXECUTE PROCEDURE DeletePublisherFromRelationshipTable();
  
 
  
  