CREATE OR REPLACE FUNCTION DeleteBooksFromRelationshipTable()
returns trigger as 
$BODY$
BEGIN 
	DELETE FROM booksauthors WHERE isbn = OLD.ISBN;
	RETURN OLD;
END 
$BODY$
LANGUAGE plpgsql;
