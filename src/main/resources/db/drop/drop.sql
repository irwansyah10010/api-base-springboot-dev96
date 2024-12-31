-- Drop table
DROP TABLE tb_user;

-- Drop table
DROP TABLE tb_status_user;

-- Drop table
DROP TABLE tb_app_logging;

-- Drop table
DROP TABLE tb_file;

-- Drop table
DROP TABLE tb_role;

DELETE FROM databasechangelog WHERE id='create-schema';
DELETE FROM databasechangelog WHERE id='create-data';
DELETE FROM databasechangelog WHERE id='create-data-user';




