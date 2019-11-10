CREATE OR REPLACE FUNCTION DeleteBooksFromRelationshipTableByAutorId()
returns trigger as 
$BODY$
BEGIN 
	DELETE FROM booksauthors WHERE author_id = OLD.author_id;
	RETURN OLD;
END 
$BODY$
LANGUAGE plpgsql;
