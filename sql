ALTER TABLE functions
ADD CONSTRAINT fk_function_resource
FOREIGN KEY (resource_id)
REFERENCES resources (resource_id)
ON DELETE CASCADE;