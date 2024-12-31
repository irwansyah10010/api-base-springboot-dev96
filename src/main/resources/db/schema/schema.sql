-- public.tb_app_logging definition

-- Drop table

-- DROP TABLE public.tb_app_logging;

CREATE TABLE public.tb_app_logging (
	id varchar(255) NOT NULL,
	"data" varchar(255) NULL,
	description varchar(255) NULL,
	"source" varchar(255) NULL,
	created_at bigint NULL,
	created_by varchar(255) NULL,
	updated_at bigint NULL,
	updated_by varchar(255) NULL,
	versions int4 NULL,
	PRIMARY KEY(id)
);


-- public.tb_file definition

-- Drop table

-- DROP TABLE public.tb_file;

CREATE TABLE public.tb_file (
	id varchar(255) NOT NULL,
	ext varchar(255) NULL,
	file varchar(255) NULL,
	created_at bigint NULL,
	created_by varchar(255) NULL,
	updated_at bigint NULL,
	updated_by varchar(255) NULL,
	versions int4 NULL,
	PRIMARY KEY(id)
);


-- public.tb_role definition

-- Drop table

-- DROP TABLE public.tb_role;

CREATE TABLE public.tb_role (
	role_code varchar(255) NOT NULL,
	role_name varchar(255) NULL,
	description varchar(255) NULL,
	created_at bigint NULL,
	created_by varchar(255) NULL,
	updated_at bigint NULL,
	updated_by varchar(255) NULL,
	versions int4 NULL,
	is_active bool NULL,
	PRIMARY KEY(role_code)
);

-- public.tb_status_user definition

-- Drop table

-- DROP TABLE public.tb_status_user;

CREATE TABLE public.tb_status_user (
	id varchar(255) NOT NULL,
	enabled bool NULL,
	invalid_login_counter int4 NULL,
	is_login_web bool NULL,
	last_ip_web varchar(255) NULL,
	last_login_web int8 NULL,
	last_logout_web int8 NULL,
	session_id varchar(255) NULL,
	PRIMARY KEY(id)
);

-- public.tb_user definition

-- Drop table

-- DROP TABLE public.tb_user;

CREATE TABLE public.tb_user (
	username varchar(255) NOT NULL,
	full_name varchar(255) NULL,
	date_of_birth date NULL,
	place_of_birth varchar(255) NULL,
	email varchar(255) NULL,
	pass varchar(255) NULL,
	phone_number varchar(255) NULL,
	address varchar(255) NULL,
	profile_picture_id varchar(255) NULL,
	role_id varchar(255) NULL,
	status_id varchar(255) NULL,
	created_at bigint NULL,
	created_by varchar(255) NULL,
	updated_at bigint NULL,
	updated_by varchar(255) NULL,
	is_active bool NULL,
	versions int4 NULL,
	PRIMARY KEY(username),
	CONSTRAINT fk_file FOREIGN KEY (profile_picture_id) REFERENCES public.tb_file(id),
	CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES public.tb_role(role_code),
	CONSTRAINT fk_status_user FOREIGN KEY (status_id) REFERENCES public.tb_status_user(id)
);

DELETE FROM databasechangelog WHERE id='rollback-schema';