CREATE OR REPLACE FUNCTION DeletePublisherFromRelationshipTable()
returns trigger as 
$BODY$
BEGIN 
	DELETE FROM books WHERE publisher_id = OLD.publisher_id;
	RETURN OLD;
END 
$BODY$
LANGUAGE plpgsql;

